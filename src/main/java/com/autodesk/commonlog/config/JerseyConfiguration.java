package com.autodesk.commonlog.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfiguration  extends ResourceConfig{ 

  public JerseyConfiguration(){
    packages("com.autodesk.commonlog.resource");
    register(JacksonFeature.class);
    register(JacksonContextResolver.class);
    registerEndpoints();
  }
  
  private void registerEndpoints() {
    register(WadlResource.class);
  }

}
