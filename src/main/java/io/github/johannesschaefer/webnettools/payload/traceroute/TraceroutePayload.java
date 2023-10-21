package io.github.johannesschaefer.webnettools.payload.traceroute;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name="traceroute", displayName="traceroute", cmd="traceroute", description="Network diagnostic command for displaying possible routes (paths) and measuring transit delays of packets across an Internet Protocol (IP) network.")
@Group(name = TraceroutePayload.GROUP_GENERAL)
@Group(name = TraceroutePayload.GROUP_ADDITIONAL)
@Group(name = TraceroutePayload.GROUP_ADVANCED)
public class TraceroutePayload implements Payload {

    public static final String GROUP_GENERAL = "General options";
    public static final String GROUP_ADDITIONAL = "Additional options";
    public static final String GROUP_ADVANCED = "Advanced options";

    @MainParameter(displayName ="IP / Hostname", description="Trace route hostname")
    private String host;

    // General Options
    @BooleanParam(displayName = "Use IPv4", param = "-4", description = "Use IPv4", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useIPv4;
    @BooleanParam(displayName = "Use IPv6", param = "-6", description = "Use IPv6", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useIPv6;
    @BooleanParam(displayName = "Do Not Resolve IP Addresses", param = "-n", description = "Do not resolve IP addresses to their domain names", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean doNotResolveIP;
    @BooleanParam(displayName = "Do Not Fragment Packets", param = "-F", description = "Do not fragment packets", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean doNotFragment;
    @BooleanParam(displayName = "Use ICMP ECHO", param = "-I", description = "Use ICMP ECHO for tracerouting", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useIcmpEcho;
    @BooleanParam(displayName = "Use TCP SYN", param = "-T", description = "Use TCP SYN for tracerouting (default port is 80)", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useTcpSyn;
    @BooleanParam(displayName = "Use UDP", param = "-U", description = "Use UDP to particular port for tracerouting (instead of increasing the port per each probe), default port is 53", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useUdp;
    @BooleanParam(displayName = "Use UDPLITE", param = "-UL", description = "Use UDPLITE for tracerouting (default dest port is 53)", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useUdpLite;
    @BooleanParam(displayName = "Use DCCP Request", param = "-D", description = "Use DCCP Request for tracerouting (default port is 33434)", group = TraceroutePayload.GROUP_GENERAL)
    private Boolean useDccp;

    // Additional Options
    @StringParam(displayName = "Protocol", param = "-P", description = "Use raw packet of protocol prot for tracerouting", group = TraceroutePayload.GROUP_ADDITIONAL)
    private String proto;
    @BooleanParam(displayName = "Discover MTU", param = "--mtu", description = "Discover MTU along the path being traced.", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Boolean discoverMTU;
    @NumberParam(displayName = "Start from TTL", param = "-f", min = 1., max = 65536., step = 1., description = "Start from the first_ttl hop (instead from 1)", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Integer startTTL;
    @NumberParam(displayName = "Max TTL", param = "-m", min = 1., max = 65536., step = 1., description = "Set the max number of hops (max TTL to be reached). Default is 30", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Integer maxTTL;
    @NumberParam(displayName = "Simultaneous Queries", param = "-N", min = 1., max = 65536., step = 1., description = "Set the number of probes to be tried simultaneously (default is 16)", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Integer simultaneousQueries;
    @NumberParam(displayName = "Source Port", param = "--sport", min = 1., max = 65536., step = 1., description = "Use source port num for outgoing packets.", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Integer sourcePort;
    @NumberParam(displayName = "Destination Port", param = "-p", min = 1., max = 65536., step = 1., description = "Set the destination port to use. It is either initial udp port value for \"default\" method (incremented by each probe, default is 33434), or initial seq for \"icmp\" (incremented as well, default from 1), or some constant destination port for other methods (with default of 80 for \"tcp\", 53 for \"udp\", etc.)", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Integer destinationPort;
    @NumberParam(displayName = "Minimal Send Wait Interval", param = "-z", min = 1., max = 10000., step = 0.1, description = "Minimal time interval between probes (default 0). If the value is more than 10, then it specifies a number in milliseconds, else it is a number of seconds (float point values allowed too)", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Float minimalSend;
    @NumberParam(displayName = "Wait Times", param = "-w", min = 1., max = 65536., step = 0.1, description = "Wait for a probe no more than HERE (default 3) times longer than a response from the same hop, or no more than NEAR (default 10) times than some next hop, or MAX (default 5.0) seconds (float point values allowed too)", group = TraceroutePayload.GROUP_ADDITIONAL)
    private Float waitTimes;

    // Advanced Options
    @StringParam(displayName = "TOS/Traffic Class", param = "-t", description = "Set the TOS (IPv4 type of service) or TC (IPv6 traffic class) value for outgoing packets", group = TraceroutePayload.GROUP_ADVANCED)
    private String tosClass;
    @StringParam(displayName = "Flow Label (IPv6)", param = "-l", description = "Use specified flow_label for IPv6 packets", group = TraceroutePayload.GROUP_ADVANCED)
    private String flowLabel;
    @NumberParam(displayName = "Number of Queries", param = "-q", min = 1., max = 65536., step = 1., description = "Set the number of probes per each hop. Default is 3", group = TraceroutePayload.GROUP_ADVANCED)
    private Integer numberOfQueries;
    @BooleanParam(displayName = "Bypass Normal Routing", param = "-r", description = "Bypass the normal routing and send directly to a host on an attached network", group = TraceroutePayload.GROUP_ADVANCED)
    private String bypassNormal;
    @BooleanParam(displayName = "Show ICMP Extensions", param = "-e", description = "Show ICMP extensions (if present), including MPLS", group = TraceroutePayload.GROUP_ADVANCED)
    private Boolean showIcmp;
    @BooleanParam(displayName = "AS Path Lookups", param = "-A", description = "Perform AS path lookups in routing registries and print results directly after the corresponding addresses", group = TraceroutePayload.GROUP_ADVANCED)
    private Boolean asPath;
    @StringParam(displayName = "Traceroute Module", param = "-M", description = "Use specified module (either builtin or external) for traceroute operations. Most methods have their shortcuts (`-I' means `-M icmp' etc.)", group = TraceroutePayload.GROUP_ADVANCED)
    private String traceModule;
    @StringParam(displayName = "Traceroute Module Options", param = "-O", description = "Use module-specific option OPTS for the traceroute module. Several OPTS allowed, separated by comma. If OPTS is \"help\", print info about available options", group = TraceroutePayload.GROUP_ADVANCED)
    private String traceModOpt;
    @StringParam(displayName = "Firewall Mark", param = "--fwmark", description = "Set firewall mark for outgoing packets", group = TraceroutePayload.GROUP_ADVANCED)
    private String firewallMark;
    @BooleanParam(displayName = "Guess Backward Hops", param = "--back", description = "Guess the number of hops in the backward path and print if it differs", group = TraceroutePayload.GROUP_ADVANCED)
    private Boolean guessHops;

    @Override
    public String getCacheString() {
        return host;
    }
}
