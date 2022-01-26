package com.example.apidemoapp.data;

public class State {
    String name;
    int cases;

    public State(String name, int cases) {
        this.name = name;
        this.cases = cases;
    }

    public String getName() {
        return name;
    }

    public int getCases() {
        return cases;
    }
}
