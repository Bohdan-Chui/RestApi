package bohdan.restApiTask.service;

import bohdan.restApiTask.model.Crypto;
import bohdan.restApiTask.model.Fiat;
import bohdan.restApiTask.model.Pair;
import bohdan.restApiTask.repository.PairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.swing.event.ListDataListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PairServiceTest {
    @Mock
    PairRepository repository;

    @Autowired
    RestTemplate template;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getPostsAsObject() {
    }

    @Test
    void getMinPrice() {
//        Pair pair1 = new Pair(Crypto.BTC, Fiat.USD, 10.);
//        Pair pair2 = new Pair(Crypto.BTC, Fiat.USD, 100.);
//        when(repository.getPairsByCrypto(Crypto.BTC)).thenReturn(Arrays.asList(pair1, pair2));
//        PairService service = new PairService(repository, template);
//        assertEquals(service.getMinPrice(Crypto.BTC),pair1);
    }

    @Test
    void getMaxPrice() {
    }

    @Test
    void getPairsPageable() {
    }

    @Test
    void scvCreator() {
    }

    @Test
    void getDTO() {
    }
}