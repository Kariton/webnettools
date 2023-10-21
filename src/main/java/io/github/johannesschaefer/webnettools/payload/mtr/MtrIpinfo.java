package io.github.johannesschaefer.webnettools.payload.mtr;

public enum MtrIpinfo {
    _0("0"),
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4");

    private final String mode;

    MtrIpinfo(String s) {
        mode = s;
    }

    public String toString() {
        return this.mode;
    }
}