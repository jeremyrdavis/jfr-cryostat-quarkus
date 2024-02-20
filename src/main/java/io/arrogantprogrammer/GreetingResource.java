package io.arrogantprogrammer;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {

    private static final Logger LOGGER = getLogger(GreetingResource.class);

    @Inject
    GreetingService greetingService;

    @GET
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/name/{name}")
    public String helloName(@PathParam("name") String name) {
        var event = new GreetingEvent();
        event.begin();
        event.counter = event.counter++;
        event.message = "Hello, " + name + "1";
        event.commit();
        return "Hello, " + name + "!";
    }

    @GET
    @Path("/delay/{ms}")
    public String helloWithDelay(@PathParam("ms") long delay) {
        LOGGER.debug("Delaying for {} ms", delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello from RESTEasy Reactive.  Delayed for " + delay + " ms.";
    }

    @GET
    @Path("/memory/{mb}")
    public String helloWithMemory(@PathParam("mb") long mb) {
        LOGGER.debug("Allocating {} MB of memory", mb);
        byte[] bytes = new byte[(int) (mb * 1024 * 1024)];
        return "Hello from RESTEasy Reactive.  Allocated " + mb + " MB of memory.";
    }

    @GET
    @Path("/blocking/{ms}")
    public String helloBlocking(@PathParam("ms") long delay) {
        LOGGER.debug("Blocking for {} ms", delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello from RESTEasy Reactive.  Blocked for " + delay + " ms.";
    }

    @GET
    @Path("/filelogging/{num}")
    public String helloFileLogging(@PathParam("num") long num) {
        LOGGER.debug("Logging to file");
        Set<Greeting> greetingSet = new HashSet<>();
        for (int i = 0; i < num; i++) {
            if(i % 2 == 0) {
                greetingSet.add(greetingService.evenGreeting(i));
            }else{
                greetingSet.add(greetingService.oddGreeting(i));
            }
        }
        greetingSet.forEach(greeting -> {
            LOGGER.debug(greeting.toString());
        });
        greetingService.verifyGreetings();
        return "There are " + greetingService.getAllGreetings().size() + " greetings in the log file.";
    }
}
