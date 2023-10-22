package io.github.johannesschaefer.webnettools.payload.nuclei;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name="nuclei", displayName = "nuclei", cmd="nuclei", description="Vulnerability Scanner. Uses the <a href=\"https://github.com/projectdiscovery/nuclei\">projectdiscovery/nuclei</a> toolkit.")
public class NucleiPayload implements Payload {
    @MainParameter(displayName ="IP / Hostname", description="nuclei host")
    private String host;

    @FixedParam(param = "-target", paramType = ParameterType.ONLY_PARAM)
    private Boolean target = true;

    @Override
    public String getCacheString() {
        return host;
    }
}