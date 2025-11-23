package com.temporal.email.model;

public class EmailDetails {
    private String email;
    private String message;
    private int count;
    private boolean subscribed;

    public EmailDetails() {
    }

    public EmailDetails(String email, String message, int count, boolean subscribed) {
        this.email = email;
        this.message = message;
        this.count = count;
        this.subscribed = subscribed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}
