package pl.kowalczyk.day3bledna;

import java.util.*;

public class Wire {
    String name;
    private List<String> wire;

    public Wire(String name, List<String> wire) {
        this.name = name;
        this.wire = wire;
    }

    public List<String> getWire() {
        return wire;
    }

    @Override
    public String toString() {
        return "Wire{" +
                "name='" + name + '\'' +
                ", wire=" + wire +
                '}';
    }
}
