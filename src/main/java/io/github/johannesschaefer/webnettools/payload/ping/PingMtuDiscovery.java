package io.github.johannesschaefer.webnettools.payload.ping;

public enum PingMtuDiscovery {
    DO("do"),
    WANT("want"),
    DONT("dont");

    private final String mode;

    PingMtuDiscovery(String s) {
        mode = s;
    }

    public String toString() {
        return this.mode;
    }
}