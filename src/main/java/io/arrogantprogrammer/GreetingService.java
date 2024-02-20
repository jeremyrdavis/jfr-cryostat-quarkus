package io.arrogantprogrammer;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class GreetingService {

    private static final Logger LOGGER = getLogger(GreetingService.class);
    private Map<UUID, Greeting> allGreetings = new HashMap<>();

    public void verifyGreetings() {
        while (true) {
            allGreetings.keySet().forEach(key -> {
                if(!allGreetings.containsKey(key)){
                    LOGGER.error("Greeting with id {} is missing", key);
                }
            });
        }
    }

    List<Greeting> getAllGreetings() {
        allGreetings.keySet().forEach(key -> {
            if (allGreetings.get(key).addressee == null) {
                allGreetings.get(key).addressee = "nobody";
            }
        });
        return new ArrayList<>(allGreetings.values());
    }

    Greeting evenGreeting(int num) {
        Greeting greeting = new Greeting("even number " + num);
        allGreetings.put(greeting.id, greeting);
        return greeting;
    }

    Greeting oddGreeting(int num) {
        Greeting greeting = new Greeting("odd number " + num);
        allGreetings.put(greeting.id, greeting);
        return new Greeting("odd number " + num);
    }
}
