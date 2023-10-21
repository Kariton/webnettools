package io.github.johannesschaefer.webnettools.payload.dig;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name = "dig", displayName = "Dig", cmd = "dig", description = "DNS lookup utility")
@Group(name = DigPayload.GROUP_GENERAL)
@Group(name = DigPayload.GROUP_ADDITIONAL)
@Group(name = DigPayload.QUERY_OPTIONS)
public class DigPayload implements Payload {

    public static final String GROUP_GENERAL = "General options";
    public static final String GROUP_ADDITIONAL = "Additional options";
    public static final String QUERY_OPTIONS = "Query options";

    @MainParameter(displayName = "Server", description = "The name or IP address of the name server to query. This can be an IPv4 address in dotted-decimal notation or an IPv6 address in colon-delimited notation. When the supplied server argument is a hostname, dig resolves that name before querying that name server. If no server argument is provided, dig consults /etc/resolv.conf and queries the name servers listed there. The reply from the name server that responds is displayed.")
    private String server;

    // General Options
    @EnumParam(displayName = "DNS Type", param = "-t", description = "DNS Type", group = DigPayload.GROUP_GENERAL)
    private DigDNSType dnsType;
    @StringParam(displayName = "Simplified Reverse Lookup", param = "-x", description = "Set simplified reverse lookups (e.g., -x 192.168.1.1)", group = DigPayload.GROUP_GENERAL)
    private String simplifiedReverseLookup;
    @EnumParam(displayName = "Query Class", param = "-c", paramType = ParameterType.ONLY_PARAM, description = "Set the query class", group = DigPayload.GROUP_GENERAL)
    private DigQueryClass queryClass;
    @NumberParam(displayName = "Non-Standard Port", param = "-p", min = 1., max = 65535., step = 1., description = "Send the query to a non-standard port on the server (e.g.: 5353)", group = DigPayload.GROUP_GENERAL)
    private Integer nonStandardPort;
    @StringParam(displayName = "Domain Name", param = "-q", description = "Specify the domain name to query", group = DigPayload.GROUP_GENERAL)
    private String domainName;
    @BooleanParam(displayName = "Query Times in Microseconds", param = "-u", paramType = ParameterType.ONLY_PARAM, description = "Provide query times in microseconds", group = DigPayload.GROUP_GENERAL)
    private boolean queryTimesInMicroseconds;

    // Additional Options
    @StringParam(displayName = "TSIG Authentication Key", param = "-y", description = "Sign queries using TSIG with the given authentication key (e.g.: hmac:keyname:secret)", group = DigPayload.GROUP_ADDITIONAL)
    private String tsigAuthenticationKey;

    // Query Options
    @BooleanParam(displayName = "AA Flag", param = "+aaflag", paramType = ParameterType.ONLY_PARAM, description = "Set the aa flag in the query", group = DigPayload.QUERY_OPTIONS)
    private boolean aaFlag;
    @BooleanParam(displayName = "Additional Section", param = "+additional", paramType = ParameterType.ONLY_PARAM, description = "Display the additional section of a reply", group = DigPayload.QUERY_OPTIONS)
    private boolean additionalSection;
    @StringParam(displayName = "Subnet", param = "+subnet", description = "Send an EDNS CLIENT-SUBNET option with the specified IP address or network prefix (e.g.: 192.168.1.1/24)", group = DigPayload.GROUP_ADDITIONAL)
    private String subnet;
    @BooleanParam(displayName = "AD Flag", param = "+adflag", paramType = ParameterType.ONLY_PARAM, description = "Set the AD (authentic data) bit in the query", group = DigPayload.QUERY_OPTIONS)
    private boolean adFlag;
    @BooleanParam(displayName = "All Display Flags", param = "+all", paramType = ParameterType.ONLY_PARAM, description = "Set or clear all display flags", group = DigPayload.QUERY_OPTIONS)
    private boolean allDisplayFlags;
    @BooleanParam(displayName = "Answer", param = "+answer", paramType = ParameterType.ONLY_PARAM, description = "Display the answer section of a reply", group = DigPayload.QUERY_OPTIONS)
    private boolean answer;
    @BooleanParam(displayName = "Authority", param = "+authority", paramType = ParameterType.ONLY_PARAM, description = "Display the authority section of a reply", group = DigPayload.QUERY_OPTIONS)
    private boolean authority;
    @BooleanParam(displayName = "Bad Cookie Retry", param = "+badcookie", paramType = ParameterType.ONLY_PARAM, description = "Retry the lookup with a new server cookie if a BADCOOKIE response is received", group = DigPayload.QUERY_OPTIONS)
    private boolean badCookieRetry;
    @BooleanParam(displayName = "Best Effort", param = "+besteffort", paramType = ParameterType.ONLY_PARAM, description = "Attempt to display the contents of malformed messages", group = DigPayload.QUERY_OPTIONS)
    private boolean bestEffort;
    @StringParam(displayName = "EDNS Buffer Size", param = "+bufsize", paramType = ParameterType.ONLY_PARAM, description = "Set the UDP message buffer size advertised using EDNS0 to B bytes (e.g., +bufsize=128)", group = DigPayload.QUERY_OPTIONS)
    private String ednsBufferSize;
    @BooleanParam(displayName = "CD Flag", param = "+cdflag", paramType = ParameterType.ONLY_PARAM, description = "Set the CD (checking disabled) bit in the query", group = DigPayload.QUERY_OPTIONS)
    private boolean cdFlag;
    @BooleanParam(displayName = "Class Display", param = "+class", paramType = ParameterType.ONLY_PARAM, description = "Display the CLASS when printing the record", group = DigPayload.QUERY_OPTIONS)
    private boolean classDisplay;
    @BooleanParam(displayName = "Command Comment", param = "+cmd", paramType = ParameterType.ONLY_PARAM, description = "Toggle the printing of the initial comment in the output", group = DigPayload.QUERY_OPTIONS)
    private boolean commandComment;
    @BooleanParam(displayName = "Comments", param = "+comments", paramType = ParameterType.ONLY_PARAM, description = "Toggle the display of comment lines in the output", group = DigPayload.QUERY_OPTIONS)
    private boolean comments;
    @BooleanParam(displayName = "Cookie", param = "+cookie", paramType = ParameterType.ONLY_PARAM, description = "Send a COOKIE EDNS option", group = DigPayload.QUERY_OPTIONS)
    private boolean cookie;
    @BooleanParam(displayName = "Cryptographic Fields", param = "+crypto", paramType = ParameterType.ONLY_PARAM, description = "Toggle the display of cryptographic fields in DNSSEC records", group = DigPayload.QUERY_OPTIONS)
    private boolean cryptographicFields;
    @StringParam(displayName = "Domain", param = "+domain", paramType = ParameterType.ONLY_PARAM, description = "Set the search list to contain the single domain somename (e.g., +domain=mydomain.com)", group = DigPayload.QUERY_OPTIONS)
    private String searchDomain;
    @StringParam(displayName = "DSCP", param = "+dscp", description = "Set the DSCP code point to be used when sending the query (e.g., +dscp=32)", group = DigPayload.QUERY_OPTIONS)
    private String dscp;
    @BooleanParam(displayName = "EDNS Version", param = "+edns", paramType = ParameterType.ONLY_PARAM, description = "Specify the EDNS version to query with", group = DigPayload.QUERY_OPTIONS)
    private boolean edns;
    @StringParam(displayName = "EDNS Flags Bits", param = "+ednsflags", paramType = ParameterType.ONLY_PARAM, description = "Set the must-be-zero EDNS flags bits to the specified value", group = DigPayload.QUERY_OPTIONS)
    private String ednsFlags;
    @BooleanParam(displayName = "EDNS Version Negotiation", param = "+ednsnegotiation", paramType = ParameterType.ONLY_PARAM, description = "Enable/Disable EDNS version negotiation", group = DigPayload.QUERY_OPTIONS)
    private boolean ednsNegotiation;
    @StringParam(displayName = "EDNS Option", param = "+ednsopt", description = "Specify the EDNS option with code point code and an optional payload of value as a hexadecimal string", group = DigPayload.QUERY_OPTIONS)
    private String ednsOption;
    @BooleanParam(displayName = "EDNS Expire Option", param = "+expire", paramType = ParameterType.ONLY_PARAM, description = "Send an EDNS Expire option", group = DigPayload.QUERY_OPTIONS)
    private boolean ednsExpire;
    @BooleanParam(displayName = "Try Next Server on SERVFAIL", param = "+fail", paramType = ParameterType.ONLY_PARAM, description = "Try or do not try the next server if a SERVFAIL is received", group = DigPayload.QUERY_OPTIONS)
    private boolean tryNextServerOnServfail;
    @BooleanParam(displayName = "Header-Only Query", param = "+header-only", paramType = ParameterType.ONLY_PARAM, description = "Send a query with a DNS header without a question section", group = DigPayload.QUERY_OPTIONS)
    private boolean headerOnlyQuery;
    @BooleanParam(displayName = "Show Source IP and Port", param = "+identify", paramType = ParameterType.ONLY_PARAM, description = "Show the IP address and port number that supplied the answer", group = DigPayload.QUERY_OPTIONS)
    private boolean showSourceIpAndPort;
    @BooleanParam(displayName = "IDN Input Processing", param = "+idnin", paramType = ParameterType.ONLY_PARAM, description = "Process or do not process IDN domain names on input", group = DigPayload.QUERY_OPTIONS)
    private boolean idnInputProcessing;
    @BooleanParam(displayName = "IDN Output Conversion", param = "+idnout", paramType = ParameterType.ONLY_PARAM, description = "Convert or do not convert puny code on output", group = DigPayload.QUERY_OPTIONS)
    private boolean idnOutputConversion;
    @BooleanParam(displayName = "Ignore Truncation", param = "+ignore", paramType = ParameterType.ONLY_PARAM, description = "Ignore or do not ignore truncation in UDP responses", group = DigPayload.QUERY_OPTIONS)
    private boolean ignoreTruncation;
    @BooleanParam(displayName = "EDNS Keepalive", param = "+keepalive", paramType = ParameterType.ONLY_PARAM, description = "Send or do not send an EDNS Keepalive option", group = DigPayload.QUERY_OPTIONS)
    private boolean ednsKeepalive;
    @BooleanParam(displayName = "Keep TCP Socket Open", param = "+keepopen", paramType = ParameterType.ONLY_PARAM, description = "Keep or do not keep the TCP socket open between queries", group = DigPayload.QUERY_OPTIONS)
    private boolean keepTcpSocketOpen;
    @BooleanParam(displayName = "Mapped IPv4-over-IPv6 Addresses", param = "+mapped", paramType = ParameterType.ONLY_PARAM, description = "Allow or do not allow mapped IPv4-over-IPv6 addresses to be used", group = DigPayload.QUERY_OPTIONS)
    private boolean mappedAddresses;
    @BooleanParam(displayName = "Verbose Multi-Line Format", param = "+multiline", paramType = ParameterType.ONLY_PARAM, description = "Print records, like the SOA records, in a verbose multi-line format with human-readable comments", group = DigPayload.QUERY_OPTIONS)
    private boolean verboseMultiLineFormat;
    @StringParam(displayName = "ndots", param = "+ndots", description = "Set the number of dots (D) that must appear in name for it to be considered absolute", group = DigPayload.QUERY_OPTIONS)
    private String ndots;
    @BooleanParam(displayName = "EDNS Name Server ID Request", param = "+nsid", paramType = ParameterType.ONLY_PARAM, description = "Include or do not include an EDNS name server ID request when sending a query", group = DigPayload.QUERY_OPTIONS)
    private boolean nsidRequest;
    @BooleanParam(displayName = "Find Authoritative Name Servers", param = "+nssearch", paramType = ParameterType.ONLY_PARAM, description = "Find authoritative name servers for the zone containing the name being looked up", group = DigPayload.QUERY_OPTIONS)
    private boolean findAuthoritativeNameServers;
    @BooleanParam(displayName = "One SOA Record", param = "+onesoa", paramType = ParameterType.ONLY_PARAM, description = "Print only one (starting) SOA record when performing an AXFR", group = DigPayload.QUERY_OPTIONS)
    private boolean oneSoaRecord;
    @StringParam(displayName = "Message Opcode", param = "+opcode", paramType = ParameterType.ONLY_PARAM, description = "Set the DNS message opcode to the specified value", group = DigPayload.QUERY_OPTIONS)
    private String messageOpcode;
    @StringParam(displayName = "Padding", param = "+padding", description = "Pad the size of the query packet using the EDNS Padding option to blocks of value bytes", group = DigPayload.QUERY_OPTIONS)
    private String padding;
    @BooleanParam(displayName = "Query Message Display", param = "+qr", paramType = ParameterType.ONLY_PARAM, description = "Toggle the display of the query message as it is sent", group = DigPayload.QUERY_OPTIONS)
    private boolean queryMessageDisplay;
    @BooleanParam(displayName = "Question Section Display", param = "+question", paramType = ParameterType.ONLY_PARAM, description = "Toggle the display of the question section of a query when an answer is returned", group = DigPayload.QUERY_OPTIONS)
    private boolean questionSectionDisplay;
    @BooleanParam(displayName = "RA Flag", param = "+raflag", paramType = ParameterType.ONLY_PARAM, description = "Set the RA (Recursion Available) bit in the query", group = DigPayload.QUERY_OPTIONS)
    private boolean raFlag;
    @BooleanParam(displayName = "RD Flag", param = "+rdflag", paramType = ParameterType.ONLY_PARAM, description = "Toggle the setting of the RD (recursion desired) bit in the query", group = DigPayload.QUERY_OPTIONS)
    private boolean rdFlag;
    @StringParam(displayName = "Retry", param = "+retry", paramType = ParameterType.ONLY_PARAM, description = "Set the number of times to retry UDP and TCP queries to server", group = DigPayload.QUERY_OPTIONS)
    private String retry;
    @BooleanParam(displayName = "Record Comments Display", paramType = ParameterType.ONLY_PARAM, param = "+rrcomments", description = "Toggle the display of per-record comments in the output", group = DigPayload.QUERY_OPTIONS)
    private boolean recordCommentsDisplay;
    @BooleanParam(displayName = "Use Search List", param = "+search", paramType = ParameterType.ONLY_PARAM, description = "Use or do not use the search list defined by the searchlist or domain directive in resolv.conf", group = DigPayload.QUERY_OPTIONS)
    private boolean useSearchList;
    @BooleanParam(displayName = "Short Answer", param = "+short", paramType = ParameterType.ONLY_PARAM, description = "Toggle whether a terse answer is provided", group = DigPayload.QUERY_OPTIONS)
    private boolean shortAnswer;
    @BooleanParam(displayName = "Show Search Results", param = "+showsearch", paramType = ParameterType.ONLY_PARAM, description = "Perform or do not perform a search showing intermediate results", group = DigPayload.QUERY_OPTIONS)
    private boolean showSearchResults;
    @BooleanParam(displayName = "SIG Chase", param = "+sigchase", paramType = ParameterType.ONLY_PARAM, description = "Obsolete and removed; use delv instead", group = DigPayload.QUERY_OPTIONS)
    @StringParam(displayName = "Split", param = "+split", description = "Split long hex- or base64-formatted fields in resource records into chunks of W characters", group = DigPayload.QUERY_OPTIONS)
    private String split;
    @BooleanParam(displayName = "Statistics Display", param = "+stats", paramType = ParameterType.ONLY_PARAM, description = "Toggle the printing of statistics", group = DigPayload.QUERY_OPTIONS)
    private String displayStats;
    @StringParam(displayName = "Subnet", param = "+subnet", description = "Send an EDNS CLIENT-SUBNET option with the specified IP address or network prefix", group = DigPayload.QUERY_OPTIONS)
    private String ednsClientSubnet;
    @BooleanParam(displayName = "TC Flag", param = "+tcflag", paramType = ParameterType.ONLY_PARAM, description = "Set the TC (TrunCation) bit in the query", group = DigPayload.QUERY_OPTIONS)
    private boolean tcpFlag;
    @BooleanParam(displayName = "Use TCP for Queries", param = "+tcp", paramType = ParameterType.ONLY_PARAM, description = "Use or do not use TCP when querying name servers", group = DigPayload.QUERY_OPTIONS)
    @StringParam(displayName = "Timeout", param = "+timeout", description = "Set the timeout for a query to T seconds", group = DigPayload.QUERY_OPTIONS)
    private String timeout;
    @BooleanParam(displayName = "Topdown Query", param = "+topdown", paramType = ParameterType.ONLY_PARAM, description = "Related to dig +sigchase, which is obsolete and has been removed; use delv instead", group = DigPayload.QUERY_OPTIONS)
    private boolean topDown;
    @BooleanParam(displayName = "Trace Delegation Path", param = "+trace", paramType = ParameterType.ONLY_PARAM, description = "Toggle tracing of the delegation path from the root name servers for the name being looked up", group = DigPayload.QUERY_OPTIONS)
    private boolean tradeDeligation;
    @StringParam(displayName = "Tries", param = "+tries", description = "Set the number of times to try UDP and TCP queries to server", group = DigPayload.QUERY_OPTIONS)
    private String tries;
    @BooleanParam(displayName = "Display TTL", param = "+ttlid", paramType = ParameterType.ONLY_PARAM, description = "Toggle the display of the TTL when printing the record", group = DigPayload.QUERY_OPTIONS)
    private boolean displayTTL;
    @BooleanParam(displayName = "TTL in Human-Readable Units", param = "+ttlunits", paramType = ParameterType.ONLY_PARAM, description = "Toggle the display of the TTL in friendly human-readable time units", group = DigPayload.QUERY_OPTIONS)
    private boolean ttlHr;
    @BooleanParam(displayName = "Accept Answers from Unexpected Sources", param = "+unexpected", paramType = ParameterType.ONLY_PARAM, description = "Accept or do not accept answers from unexpected sources", group = DigPayload.QUERY_OPTIONS)
    private boolean acceptUnexpected;
    @BooleanParam(displayName = "Display Unknown RR Type Presentation Format", param = "+unknownformat", paramType = ParameterType.ONLY_PARAM, description = "Display all RDATA in unknown RR type presentation format (RFC 3597)", group = DigPayload.QUERY_OPTIONS)
    private boolean dispalyUnknown;
    @BooleanParam(displayName = "YAML Format", param = "+yaml", paramType = ParameterType.ONLY_PARAM, description = "Print the responses in a detailed YAML format", group = DigPayload.QUERY_OPTIONS)
    private boolean yamlFormat;
    @BooleanParam(displayName = "Last Unassigned DNS Header Flag", param = "+zflag", paramType = ParameterType.ONLY_PARAM, description = "Set or do not set the last unassigned DNS header flag in a DNS query", group = DigPayload.QUERY_OPTIONS)
    private boolean lastDS;
 
    @Override
    public String getCacheString() {
        return server;
    }
}
