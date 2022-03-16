package bohdan.restApiTask.service;

import bohdan.restApiTask.dto.DTOforCSV;
import bohdan.restApiTask.dto.RequestDTO;
import bohdan.restApiTask.exception.CustomException;
import bohdan.restApiTask.model.Crypto;
import bohdan.restApiTask.model.Fiat;
import bohdan.restApiTask.model.Pair;
import bohdan.restApiTask.repository.PairRepository;
import bohdan.restApiTask.util.CSVCreator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PairService {
    private final PairRepository repository;
    private final RestTemplate restTemplate;

    public PairService(PairRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public RequestDTO getPostsAsObject(Crypto crypto, Fiat fiat) {
        String url = "https://cex.io/api/last_price/{symbol1}/{symbol2}";
        ResponseEntity<RequestDTO> response = restTemplate.getForEntity(url, RequestDTO.class, crypto.name(), fiat.name());
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new CustomException("HttpStatus != ok, some problems");
        }
    }

    public void save(Pair pair){
        repository.save(pair);
    }

    public Pair getMinPrice(Crypto crypto){
        List<Pair> list =  repository.getPairsByCrypto(crypto);
        return list.stream().min(Comparator.comparing(Pair::getPrice)).orElseThrow(NoSuchElementException::new);
    }

    public Pair getMaxPrice(Crypto crypto){
        List<Pair> list =  repository.getPairsByCrypto(crypto);
        return list.stream().max(Comparator.comparing(Pair::getPrice)).orElseThrow(NoSuchElementException::new);
    }
    public List<Pair> getPairsPageable(Crypto crypto, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByCrypto(crypto, pageable).getContent().stream().
                sorted(Comparator.comparing(Pair::getPrice)).collect(Collectors.toList());
    }

    public CSVCreator scvCreator(){
        DTOforCSV btc  = getDTO(Crypto.BTC);
        DTOforCSV eth  = getDTO(Crypto.ETH);
        DTOforCSV xrp  = getDTO(Crypto.XRP);

        List<List<String>> csvBody = new ArrayList<>();
        csvBody.add(Arrays.asList(btc.getName(), btc.getMinPrice().toString(), btc.getMaxPrice().toString()));
        csvBody.add(Arrays.asList(eth.getName(), eth.getMinPrice().toString(), eth.getMaxPrice().toString()));
        csvBody.add(Arrays.asList(xrp.getName(), xrp.getMinPrice().toString(), xrp.getMaxPrice().toString()));

        return CSVCreator.builder().
                csvFileName("cryptoccurency").
                csvHeader(new String[]{"Name", "MinPrice","MaxPrice"}).
                csvBody(csvBody)
                .build();
    }
    public DTOforCSV getDTO(Crypto crypto){
        Optional<Pair> max = repository.getPairsByCrypto(crypto).stream().max(Comparator.comparing(Pair::getPrice));
        Optional<Pair> min = repository.getPairsByCrypto(crypto).stream().min(Comparator.comparing(Pair::getPrice));

        if(min.isEmpty() && max.isEmpty())
            throw new NoSuchElementException("Empty field");

        return DTOforCSV.builder().name(crypto.name()).
                maxPrice(max.get().getPrice()).
                minPrice(min.get().getPrice()).
                build();
    }
}
