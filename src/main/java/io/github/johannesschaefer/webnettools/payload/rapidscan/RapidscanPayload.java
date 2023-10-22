package io.github.johannesschaefer.webnettools.payload.rapidscan;

import io.github.johannesschaefer.webnettools.annotation.*;
import io.github.johannesschaefer.webnettools.payload.Payload;
import lombok.Data;

@Data
@Tool(name = "rapidscan", displayName = "RapidScan", cmd = "rapidscan", description = "Multi-Tool Web Vulnerability Scanner. Uses the <a href=\"https://github.com/skavngr/rapidscan\">skavngr/rapidscan</a> toolkit.")
public class RapidscanPayload implements Payload {
    @MainParameter(displayName = "IP / Hostname", description = "ping ip")
    private String host;

    @FixedParam(param = "--nospinner", paramType = ParameterType.ONLY_PARAM)
    private Boolean noSpinner = true;

    @Override
    public String getCacheString() {
        return host;
    }
}
