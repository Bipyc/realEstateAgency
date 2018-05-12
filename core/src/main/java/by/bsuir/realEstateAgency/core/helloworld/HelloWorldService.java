package by.bsuir.realEstateAgency.core.helloworld;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    private static final Logger logger = Logger.getLogger(HelloWorldService.class);

    public String getData() {
        logger.info("Get hello world!");
        return "Hello World! (HelloWorldService)";
    }

}
