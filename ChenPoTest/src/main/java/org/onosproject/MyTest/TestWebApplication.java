package org.onosproject.MyTest;

import org.onlab.rest.AbstractWebApplication;

public class TestWebApplication extends AbstractWebApplication {
    @Override
    public Set<Class<?>> getClasses() {
        return getClasses(TestWebResource.class);
    }
}