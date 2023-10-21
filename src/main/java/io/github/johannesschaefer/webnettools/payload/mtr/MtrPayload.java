package io.github.johannesschaefer.webnettools.payload.mtr;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name = "mtr", displayName = "MTR", cmd = "mtr", description = "Network diagnostic tool combining traceroute and ping")
@Group(name = MtrPayload.GROUP_GENERAL)
@Group(name = MtrPayload.GROUP_ADDITIONAL)
@Group(name = MtrPayload.GROUP_ADVANCED)
@Group(name = MtrPayload.GROUP_FORMAT)
public class MtrPayload implements Payload {

    public static final String GROUP_GENERAL = "General options";
    public static final String GROUP_ADDITIONAL = "Additional options";
    public static final String GROUP_ADVANCED = "Advanced options";
    public static final String GROUP_FORMAT = "Output format";

    @MainParameter(displayName = "Hostname or IP address", description = "Specify a hostname or an IP address.")
    private String hostname;
    @FixedParam(param = "--report-wide", paramType = ParameterType.ONLY_PARAM)
    private Boolean report = true;

    // General Options
    @NumberParam(displayName = "Pings", param = "--report-cycles", min = 1., max = 1000., step = 1., description = "Set the number of pings sent.", group = MtrPayload.GROUP_GENERAL)
    private Integer pings = 10;
    @BooleanParam(displayName = "Use TCP", param = "--tcp", paramType = ParameterType.ONLY_PARAM, description = "Ping over TCP instead of ICMP.", group = MtrPayload.GROUP_GENERAL)
    private Boolean useTcp;
    @BooleanParam(displayName = "Use UDP", param = "--udp", paramType = ParameterType.ONLY_PARAM, description = "Ping over UDP instead of ICMP.", group = MtrPayload.GROUP_GENERAL)
    private Boolean useUdp;
    @NumberParam(displayName = "Target port", param = "--port", min = 1., max = 65536., step = 1., description = "Target port number for TCP, SCTP, or UDP.", group = MtrPayload.GROUP_GENERAL)
    private Integer port;
    @BooleanParam(displayName = "No DNS", param = "--no-dns", paramType = ParameterType.ONLY_PARAM, description = "Do not resolve host names.", group = MtrPayload.GROUP_GENERAL)
    private Boolean noDns;
    @BooleanParam(displayName = "Show IPs", param = "--show-ips", paramType = ParameterType.ONLY_PARAM, description = "Show IP addresses in addition to host names.", group = MtrPayload.GROUP_GENERAL)
    private Boolean showIps;

    // Additional Options
    @FileParam(displayName = "Upload File", param = "--filename", description = "Reads the list of hostnames or IPs from the specified file.", group = MtrPayload.GROUP_ADDITIONAL)
    private String filename;
    @BooleanParam(displayName = "Use MPLS", param = "--mpls", paramType = ParameterType.ONLY_PARAM, description = "Display information from ICMP extensions for MPLS (RFC 4950) that are encoded in the response packets.", group = MtrPayload.GROUP_ADDITIONAL)
    private Boolean useMpls;
    @BooleanParam(displayName = "Use SCTP", param = "--sctp", paramType = ParameterType.ONLY_PARAM, description = "Use Stream Control Transmission Protocol packets instead of ICMP ECHO.", group = MtrPayload.GROUP_ADDITIONAL)
    private Boolean useSctp;
    @BooleanParam(displayName = "IPv4 only", param = "-4", description = "Use IPv4 only", group = MtrPayload.GROUP_ADDITIONAL)
    private Boolean v4;
    @BooleanParam(displayName = "IPv6 only", param = "-6", description = "Use IPv6 only. (IPv4 may be used for DNS lookups.)", group = MtrPayload.GROUP_ADDITIONAL)
    private Boolean v6;
    @NumberParam(displayName = "Timeout", param = "--timeout", min = 1., max = 600., step = 1., description = "The number of seconds to keep probe sockets open before giving up on the connection. Using large values for this, especially combined with a short interval, will use up a lot of file descriptors.", group = MtrPayload.GROUP_ADDITIONAL)
    private Integer timeout;
    @NumberParam(displayName = "Interval", param = "--interval", min = 1., max = 600., step = 1., description = "The positive no. of seconds between ICMP ECHO requests.", group = MtrPayload.GROUP_ADDITIONAL)
    private Integer interval;
    @NumberParam(displayName = "Max Unknown Hosts", param = "--max-unknown", min = 1., max = 100., step = 1., description = "Specifies the maximum unknown host.", group = MtrPayload.GROUP_ADDITIONAL)
    private Integer maxUnknown;
    @NumberParam(displayName = "Maximum Hops", param = "--max-ttl", min = 1., max = 600., step = 1., description = "Specifies the maximum number of hops (max time-to-live value) traceroute will probe. Default is 30.", group = MtrPayload.GROUP_ADDITIONAL)
    private Integer maxHops;

    // Advanced Options
    @NumberParam(displayName = "Start TTL", param = "--first-ttl", min = 1., max = 600., step = 1., description = "Specifies with what TTL to start. Defaults to 1.", group = MtrPayload.GROUP_ADVANCED)
    private Integer startTtl;
    @NumberParam(displayName = "Max TTL", param = "--max-ttl", min = 1., max = 600., step = 1., description = "Specifies the maximum number of hops (max time-to-live value) traceroute will probe.", group = MtrPayload.GROUP_ADVANCED)
    private Integer maxTtl;
    @NumberParam(displayName = "Packet size", param = "--psize", min = 1., max = 600., step = 1., description = "This option sets the packet size used for probing. It is in bytes, inclusive IP and ICMP headers.", group = MtrPayload.GROUP_ADVANCED)
    private Integer psize;
    @NumberParam(displayName = "Gracetime", param = "--gracetime", min = 1., max = 600., step = 1., description = "The positive number of seconds to wait for responses after the final request.", group = MtrPayload.GROUP_ADVANCED)
    private Integer gracetime;
    @NumberParam(displayName = "TOS", param = "--tos", min = 0., max = 255., step = 1., description = "Specifies value for type of service field in IP header.", group = MtrPayload.GROUP_ADVANCED)
    private Integer tos;
    @NumberParam(displayName = "Bit Pattern", param = "--bitpattern", min = 0., max = 255., step = 1., description = "Specifies bit pattern to use in payload.", group = MtrPayload.GROUP_ADVANCED)
    private Integer bitpattern;

    // Output Format
    @EnumParam(displayName = "Display AS Info", param = "--ipinfo", paramType = ParameterType.ONLY_PARAM, description = "Displays information about each IP hop. 0: AS Number,  1: IP prefix,  2: Country code of the origin AS, 3: RIR (ripencc, arin, ...), 4: Allocation date of the IP prefix.", group = MtrPayload.GROUP_FORMAT)
    private MtrIpinfo displayAsInfo;
    @BooleanParam(displayName = "Raw Output", param = "--raw", paramType = ParameterType.ONLY_PARAM, description = "Use the raw output format. This format is better suited for archival of the measurement results.", group = MtrPayload.GROUP_FORMAT)
    private Boolean rawOutput;
    @BooleanParam(displayName = "CSV Output", param = "--csv", paramType = ParameterType.ONLY_PARAM, description = "Use the Comma-Separated-Value (CSV) output format.", group = MtrPayload.GROUP_FORMAT)
    private Boolean csvOutput;
    @BooleanParam(displayName = "JSON Output", param = "--json", paramType = ParameterType.ONLY_PARAM, description = "Use the JSON output format. This format is better suited for automated processing of the measurement results.", group = MtrPayload.GROUP_FORMAT)
    private Boolean jsonOutput;
    @BooleanParam(displayName = "XML Output", param = "--xml", paramType = ParameterType.ONLY_PARAM, description = "Use this option to tell mtr to use the xml output format. This format is better suited for automated processing of the measurement results.", group = MtrPayload.GROUP_FORMAT)
    private Boolean xmlOutput;
    @BooleanParam(displayName = "Split Output", param = "--split", paramType = ParameterType.ONLY_PARAM, description = "Set mtr to spit out a format that is suitable for a split-user interface.", group = MtrPayload.GROUP_FORMAT)
    private Boolean splitOutput;

    @Override
    public String getCacheString() {
        return hostname;
    }
}
