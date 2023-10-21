package io.github.johannesschaefer.webnettools.payload.ping;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name = "ping", displayName = "Ping", cmd = "ping", description = "Standard Linux Ping tooling.")
@Group(name = PingPayload.GROUP_GENERAL)
@Group(name = PingPayload.GROUP_ADDITIONAL)
@Group(name = PingPayload.GROUP_ADVANCED)
@Group(name = PingPayload.GROUP_FORMAT)
public class PingPayload implements Payload {

    public static final String GROUP_GENERAL = "General options";
    public static final String GROUP_ADDITIONAL = "Additional options";
    public static final String GROUP_ADVANCED = "Advanced options";
    public static final String GROUP_FORMAT = "Output Format";

    @MainParameter(displayName = "IP / Hostname", description = "ping ip")
    private String host;

    // General options
    @NumberParam(displayName = "Count", param = "-c", min = 1., max = 100., step = 1., description = "Stop after sending (and receiving) count ECHO_RESPONSE packets. If this option is not specified, ping will operate until interrupted. If this option is specified in conjunction with ping sweeps, each sweep will consist of count packets.", group = PingPayload.GROUP_GENERAL)
    private Integer count = 3;
    @NumberParam(displayName = "Wait", param = "-i", min = 1., max = 100., step = 1., description = "Time in milliseconds to wait for a reply for each packet sent. If a reply arrives later, the packet is not printed as replied but considered as replied when calculating statistics.", group = PingPayload.GROUP_GENERAL)
    private Double wait = 1.0;
    @NumberParam(displayName = "Set the interval for sending each packet", param = "-i", min = 0.1, max = 600., step = 0.1, description = "Set the interval for sending each packet. Real numbers are allowed with a dot as a decimal separator (regardless of locale setup). The default is to wait for one second between each packet, normally, or not to wait in flood mode. Only the super-user may set the interval to values less than 0.2 seconds.", group = PingPayload.GROUP_GENERAL)
    private Float packetInterval;
    @BooleanParam(displayName = "DNS", param = "--no-dns", paramType = ParameterType.ONLY_PARAM, description = "Do not resolve host names", labelFalse = "Resolve", labelTrue = "Do not resolve", group = PingPayload.GROUP_GENERAL)
    private Boolean noDns = false;
    @NumberParam(displayName = "Deadline", param = "-w", min = 1., max = 100., step = 1., description = "Specify a timeout, in seconds, before ping exits regardless of how many packets have been sent or received. In this case, ping does not stop after count packets are sent; it waits either for the deadline to expire or until count probes are answered or for some error notification from the network.", group = PingPayload.GROUP_GENERAL)
    private Integer deadline;
    @NumberParam(displayName = "Timeout", param = "-W", min = 1., max = 100., step = 1., description = "Time to wait for a response, in seconds. The option affects only timeout in the absence of any responses; otherwise, ping waits for two round-trip times (RTTs).", group = PingPayload.GROUP_GENERAL)
    private Integer timeout;
    @BooleanParam(displayName = "Use IPv4 only", param = "-4", description = "Use IPv4 only.", group = PingPayload.GROUP_GENERAL)
    private Boolean useIPv4Only;
    @BooleanParam(displayName = "Use IPv6 only", param = "-6", description = "Use IPv6 only.", group = PingPayload.GROUP_GENERAL)
    private Boolean useIPv6Only;
    @BooleanParam(displayName = "Adaptive ping", param = "-A", description = "Adaptive ping. Interpacket interval adapts to round-trip time, so that effectively not more than one (or more, if preload is set) unanswered probe is present in the network. Minimal interval is 200msec unless super-user. On networks with low RTT, this mode is essentially equivalent to flood mode.", group = PingPayload.GROUP_GENERAL)
    private Boolean adaptivePing;
    @BooleanParam(displayName = "Allow pinging a broadcast address", param = "-b", description = "Allow pinging a broadcast address.", group = PingPayload.GROUP_GENERAL)
    private Boolean allowBroadcast;

    // Additional options
    @BooleanParam(displayName = "Do not allow ping to change source address of probes", param = "-B", description = "Do not allow ping to change the source address of probes. The address is bound to one selected when ping starts.", group = PingPayload.GROUP_ADDITIONAL)
    private Boolean doNotChangeSource;
    @BooleanParam(displayName = "Flood ping", param = "-f", description = "Flood ping. For every ECHO_REQUEST sent, a period '.' is printed, while for every ECHO_REPLY received, a backspace is printed. This provides a rapid display of how many packets are being dropped. If the interval is not given, it sets the interval to zero and outputs packets as fast as they come back or one hundred times per second, whichever is more. Only the super-user may use this option with zero intervals.", group = PingPayload.GROUP_ADDITIONAL)
    private Boolean floodPing;
    @NumberParam(displayName = "Select the number of packets to send without waiting for a reply", param = "-l", min = 1, max = 6., step = 1, description = "Select the number of packets to send without waiting for a reply. Only the super-user may select a preload of more than 3.", group = PingPayload.GROUP_ADDITIONAL)
    private Integer packetWitoutWait;
    @NumberParam(displayName = "Set the IP Time to Live (TTL)", param = "-t", min = 1., max = 100., step = 1., description = "Set the IP Time to Live (TTL).", group = PingPayload.GROUP_ADDITIONAL)
    private Integer timeToLive;
    @BooleanParam(displayName = "Print full user-to-user latency", param = "-U", description = "Print full user-to-user latency (the old behavior). Normally, ping prints network round-trip time, which can be different, e.g., due to DNS failures.", group = PingPayload.GROUP_ADDITIONAL)
    private Boolean fullUserLatency;
    @StringParam(displayName = "Set Quality of Service-related bits in ICMP datagrams", param = "-Q", description = "Set Quality of Service-related bits in ICMP datagrams. tos can be a decimal (ping only) or a hex number.", group = PingPayload.GROUP_ADDITIONAL)
    private String qosBits;
    @BooleanParam(displayName = "Report outstanding ICMP ECHO reply", param = "-O", description = "Report outstanding ICMP ECHO reply before sending the next packet. This is useful together with the timestamp -D to log output to a diagnostic file and search for missing answers.", group = PingPayload.GROUP_ADDITIONAL)
    private Boolean reportOutstandingReply;

    // Advanced options
    @BooleanParam(displayName = "Suppress loopback of multicast packets", param = "-L", description = "Suppress loopback of multicast packets. This flag only applies if the ping destination is a multicast address.", group = PingPayload.GROUP_ADVANCED)
    private Boolean suppressLoopback;
    @EnumParam(displayName = "Select Path MTU Discovery strategy", param = "-M", description = "Select Path MTU Discovery strategy. pmtudisc_option may be either do (prohibit fragmentation, even local one), want (do PMTU discovery, fragment locally when packet size is large), or dont (do not set DF flag).", group = PingPayload.GROUP_ADVANCED)
    private PingMtuDiscovery pmtuDiscovery;
    @BooleanParam(displayName = "Set the SO_DEBUG option on the socket being used", param = "-d", description = "Set the SO_DEBUG option on the socket being used. Essentially, this socket option is not used by the Linux kernel.", group = PingPayload.GROUP_ADVANCED)
    private Boolean debugSocket;
    @StringParam(displayName = "IPv6 flow label", param = "-F", description = "IPv6 only. Allocate and set a 20-bit flow label (in hex) on echo request packets. If the value is zero, the kernel allocates a random flow label.", group = PingPayload.GROUP_ADVANCED)
    private String ipv6FlowLabel;
    @StringParam(displayName = "Use mark to tag the packets going out", param = "-m", description = "Use mark to tag the packets going out. This is useful for a variety of reasons within the kernel, such as using policy routing to select specific outbound processing.", group = PingPayload.GROUP_ADVANCED)
    private String mark;
    @EnumParam(displayName = "Send ICMPv6 Node Information Queries", param = "-N", description = "IPv6 only. Send ICMPv6 Node Information Queries (RFC4620), instead of Echo Request. CAP_NET_RAW capability is required.", group = PingPayload.GROUP_ADVANCED)
    private PingIcmp6Information icmpv6NodeInfoOption;
    @NumberParam(displayName = "Specify the number of data bytes to be sent", param = "-s", min = 8., max = 1024., step = 8., description = "Specifies the number of data bytes to be sent. The default is 56, which translates into 64 ICMP data bytes when combined with the 8 bytes of ICMP header data.", group = PingPayload.GROUP_ADVANCED)
    private Integer dataBytes;
    @NumberParam(displayName = "Set socket sndbuf", param = "-S", min = 8., max = 512., step = 8., description = "Set socket sndbuf. If not specified, it is selected to buffer not more than one packet.", group = PingPayload.GROUP_ADVANCED)
    private Integer socketSendBuffer;
    @BooleanParam(displayName = "Bypass the normal routing tables and send directly to a host on an attached interface", param = "-r", description = "Bypass the normal routing tables and send directly to a host on an attached interface. If the host is not on a directly attached network, an error is returned. This option can be used to ping a local host through an interface that has no route through it, provided the option -I is also used.", group = PingPayload.GROUP_ADVANCED)
    private Boolean bypassRouting;
    @BooleanParam(displayName = "ping only. Record route", param = "-R", description = "Ping only. Record route. Includes the RECORD_ROUTE option in the ECHO_REQUEST packet and displays the route buffer on returned packets. Note that the IP header is only large enough for nine such routes. Many hosts ignore or discard this option.", group = PingPayload.GROUP_ADVANCED)
    private Boolean recordRoute;

    // Output Format
    @BooleanParam(displayName = "Verbose output", param = "-v", description = "Verbose output. Do not suppress DUP replies when pinging a multicast address.", group = PingPayload.GROUP_FORMAT)
    private Boolean verboseOutput;
    @BooleanParam(displayName = "Print timestamp before each line", param = "-D", description = "Print timestamp (unix time + microseconds as in gettimeofday) before each line.", group = PingPayload.GROUP_FORMAT)
    private Boolean printTimestamp;
    @EnumParam(displayName = "Set special IP timestamp options", param = "-T", description = "Set special IP timestamp options. timestamp option may be either tsonly (only timestamps), tsandaddr (timestamps and addresses), or tsprespec host1 [host2 [host3 [host4]]] (timestamp prespecified hops).", group = PingPayload.GROUP_FORMAT)
    private PingIpTimestamp timestampOption;

    @Override
    public String getCacheString() {
        return host;
    }
}
