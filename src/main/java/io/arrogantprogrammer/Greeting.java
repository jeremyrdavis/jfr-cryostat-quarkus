package io.arrogantprogrammer;

import java.util.UUID;

public class Greeting {

    UUID id = UUID.randomUUID();

    String addressee;

    static final String TEMPLATE = "Hello, %s";

    public Greeting(String addressee) {
        this.addressee = addressee;
    }

    public Greeting() {
    }

    @Override
    public String toString() {
        if (addressee == null) {
            return TEMPLATE.replace("%s", "noboby");
        }else{
            return TEMPLATE.replace("%s", addressee);
        }
    }
}
