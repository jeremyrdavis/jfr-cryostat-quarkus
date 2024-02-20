package io.arrogantprogrammer;

import jdk.jfr.Event;
import jdk.jfr.Label;

@Label("AJUG Greeting Event") public class GreetingEvent extends Event {

    @Label("Greeting Message")
    String message;

    @Label("Greeting Counter")
    long counter;

}
