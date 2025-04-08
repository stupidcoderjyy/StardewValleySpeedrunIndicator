package svsl.env;

import svsl.Id;
import svsl.Resource;

import java.util.HashMap;
import java.util.Map;

public class Container {
    protected final Map<Id, Resource> resources = new HashMap<>();
    
    public void modifyResource(Id id, int delta) {
        if (!resources.containsKey(id)) {
            throw new IllegalArgumentException("Resource not found: " + id);
        }
        
        resources.get(id).
    }
}
