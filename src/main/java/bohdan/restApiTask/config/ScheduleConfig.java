package bohdan.restApiTask.config;

import bohdan.restApiTask.util.Mapper;
import bohdan.restApiTask.model.Crypto;
import bohdan.restApiTask.model.Fiat;
import bohdan.restApiTask.service.PairService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * class help to get and save data
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {
    final PairService service;
    public static final int DELAY = 40000;

    public ScheduleConfig(PairService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = DELAY)
    public void scheduleFixedDelayTask() {
        service.save(Mapper.dtoPair(service.getPostsAsObject(Crypto.BTC, Fiat.USD)));
        service.save(Mapper.dtoPair(service.getPostsAsObject(Crypto.ETH, Fiat.USD)));
        service.save(Mapper.dtoPair(service.getPostsAsObject(Crypto.XRP, Fiat.USD)));
    }
}
