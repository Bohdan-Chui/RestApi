package bohdan.restApiTask.controller;

import bohdan.restApiTask.exception.CustomException;
import bohdan.restApiTask.model.Crypto;
import bohdan.restApiTask.model.Pair;
import bohdan.restApiTask.service.PairService;
import bohdan.restApiTask.util.CSVCreator;
import bohdan.restApiTask.util.CheckIfPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/cryptocurrencies")
public class ApiController {
    private final PairService service;

    public ApiController(PairService service) {
        this.service = service;
    }
    @GetMapping("minprice")
    ResponseEntity<Pair> minPrice(@RequestParam("name") String name){
        CheckIfPresent.checkIfCrypto(name);
        Pair pair = service.getMinPrice(Crypto.valueOf(name.toUpperCase(Locale.ROOT)));
        return new ResponseEntity<>(pair, HttpStatus.OK);
    }
    @GetMapping("maxprice")
    ResponseEntity<Pair> maxPrice(@RequestParam("name") String name){
        CheckIfPresent.checkIfCrypto(name);
        Pair pair = service.getMaxPrice(Crypto.valueOf(name.toUpperCase(Locale.ROOT)));
        return new ResponseEntity<>(pair, HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<Pair>> elements(@RequestParam("name") String name,
                                  @RequestParam(name = "page",defaultValue = "0") String page,
                                  @RequestParam(name = "size", defaultValue = "10") String size){
        CheckIfPresent.checkIfCrypto(name);
        List <Pair> list = service.getPairsPageable(Crypto.valueOf(name.toUpperCase(Locale.ROOT)),
                Integer.parseInt(page), Integer.parseInt(size));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<Resource> exportCSV() {
        CSVCreator creator = service.scvCreator();
        return new ResponseEntity(
                creator.createInputStreamResource(),
                creator.createHeaders(),
                HttpStatus.OK
        );

    }

}
