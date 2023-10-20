package io.github.johannesschaefer.webnettools.payload.traceroute;

import io.github.johannesschaefer.webnettools.annotation.BooleanParam;
import io.github.johannesschaefer.webnettools.annotation.MainParameter;
import io.github.johannesschaefer.webnettools.annotation.ParameterType;
import io.github.johannesschaefer.webnettools.annotation.Tool;
import io.github.johannesschaefer.webnettools.payload.Payload;
import io.github.johannesschaefer.webnettools.payload.mtr.MtrPayload;
import lombok.Data;

@Data
@Tool(name="traceroute", displayName="Trace Route", cmd="traceroute", description="Standard Linux traceroute tooling.")
public class TraceroutePayload implements Payload {
    @MainParameter(displayName ="IP / Hostname", description="trace route hostname")
    private String host;

    @BooleanParam(displayName = "DNS", param = "-n", paramType = ParameterType.ONLY_PARAM, description = "Do not resolve host names", labelFalse = "Resolve", labelTrue = "Do not resolve")
    private Boolean noDns = false;

    @Override
    public String getCacheString() {
        return host;
    }
}