package io.github.johannesschaefer.webnettools.payload.dig;

public enum DigQueryClass {
    IN("IN"),
    HS("HS"),
    CH("CH");

    private final String mode;

    DigQueryClass(String s) {
        mode = s;
    }

    public String toString() {
        return this.mode;
    }
}
