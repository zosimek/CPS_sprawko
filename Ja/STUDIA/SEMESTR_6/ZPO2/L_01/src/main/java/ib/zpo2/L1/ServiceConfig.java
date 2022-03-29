package ib.zpo2.L1;

// Create a helper class ServiceConfig, a simple class that stores configuration information about
// configuration information, including its id. It is best to return our id here (usually config
// is stored in a similar form, often as an environment variable, but this will suffice for now).
// (Your service has id = 12)

// I'm not sure if what follows is what it should be, but that's how I understood it.

import java.util.Arrays;
import java.util.List;

public class ServiceConfig {
    private List<String> id = Arrays.asList("12");

    public List<String> getId() {
        return id;
    }
}
