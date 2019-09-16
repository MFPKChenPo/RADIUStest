package org.onosproject.MyTest;

import org.onlab.rest.AbstractWebApplication;
import java.util.Set;

public class TestWebApplication extends AbstractWebApplication {
    @Override
    public Set<Class<?>> getClasses() {
        return getClasses(TestWebResource.class);
    }
}