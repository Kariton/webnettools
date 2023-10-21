package io.github.johannesschaefer.webnettools.payload.ping;

public enum PingIpTimestamp {
    TSONLY("tsonly"),
    tsandaddr("tsandaddr");

    private final String mode;

    PingIpTimestamp(String s) {
        mode = s;
    }

    public String toString() {
        return this.mode;
    }
}