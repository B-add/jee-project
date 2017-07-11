package com.mti.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Created by val on 17/06/17.
 */

@ApplicationPath("/api")
public class API extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return getRestResourceClasses();
    }
    private Set<Class<?>> getRestResourceClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        resources.add(UserWebServices.class);
        resources.add(BlogWebServices.class);
        resources.add(PostWebServices.class);
        resources.add(CommentWebServices.class);
        return resources;
    }
}