package com.javacourse.RESTfulExperiment;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;


public class AppTest extends JerseyTest
{
     
	@Override
	protected Application configure() {
		return new ResourceConfig(HelloResource.class);
	}
	
	@Test
    public void testHello() throws Exception{
       final String result = target("hello/greetme").request().get(String.class);
       assertEquals("Hello", result);
    }
}
