package io.github.johannesschaefer.webnettools.payload.wcvs;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name="wcvs", displayName = "wcvs", cmd="wcvs", description="Web Cache Vulnerability Scanner. Uses the <a href=\"https://github.com/Hackmanit/Web-Cache-Vulnerability-Scanner\">Hackmanit/Web-Cache-Vulnerability-Scanner</a> toolkit.")
public class WcvsPayload implements Payload {
    @MainParameter(displayName ="IP / Hostname", description="wcvs host")
    private String host;

    @FixedParam(param = "-u", paramType = ParameterType.ONLY_PARAM)
    private Boolean target = true;

    @Override
    public String getCacheString() {
        return host;
    }
}