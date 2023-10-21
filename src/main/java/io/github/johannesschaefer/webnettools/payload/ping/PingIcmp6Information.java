package io.github.johannesschaefer.webnettools.payload.ping;

public enum PingIcmp6Information {
    name("name"),
    ipv4_all("ipv4-all"),
    ipv6_global("ipv6-global"),
    ipv6_sitelocal("ipv6-sitelocal"),
    ipv6_linklocal("ipv6-linklocal"),
    ipv6_all("ipv6-all");

    private final String mode;

    PingIcmp6Information(String s) {
        mode = s;
    }

    public String toString() {
        return this.mode;
    }
}

