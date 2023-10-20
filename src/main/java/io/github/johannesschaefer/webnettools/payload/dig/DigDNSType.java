package io.github.johannesschaefer.webnettools.payload.dig;

public enum DigDNSType {
    ANY("ANY"),
    A("A"),
    AAAA("AAAA"),
    CNAME("CNAME"),
    MX("MX"),
    NS("NS"),
    PTR("PTR"),
    SOA("SOA"),
    SRV("SRV"),
    TXT("TXT");

    private final String mode;

    DigDNSType(String s) {
        mode = s;
    }

    public String toString() {
        return this.mode;
    }
}
