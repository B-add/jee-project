package com.mti.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Created by Zibe on 10/07/2017.
 */
@ApplicationPath("/api")
public class API extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return getRestResourceClasses();
    }
    private Set<Class<?>> getRestResourceClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        //WS Files
        // Example : resources.add(UserWebService.class);
        resources.add(UserServices.class);
        return resources;
    }
}