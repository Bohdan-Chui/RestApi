package bohdan.restApiTask.repository;

import bohdan.restApiTask.model.Crypto;
import bohdan.restApiTask.model.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairRepository extends MongoRepository<Pair,String> {
    List<Pair> getPairsByCrypto(Crypto crypto);
    Page<Pair> findByCrypto(Crypto crypto, Pageable pageable);

}
