package com.example.dat;

public class load {
    String Origin,Destination,Description,Contact;

    @Override
    public String toString() {
        return "load{" +
                "Origin='" + Origin + '\'' +
                ", Destination='" + Destination + '\'' +
                ", Description='" + Description + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public load() {
    }

    public load(String origin, String destination, String description, String contact) {
        Origin = origin;
        Destination = destination;
        Description = description;
        Contact = contact;
    }
}
