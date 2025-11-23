package com.workflow;

public class GreetImpl implements Greeting {
    @Override
    public String greet(String name) {
        return "Hello " + name;
    }
}
