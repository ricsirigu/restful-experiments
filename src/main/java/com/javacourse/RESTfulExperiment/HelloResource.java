package com.javacourse.RESTfulExperiment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javacourse.RESTfulExperiment.entity.User;

@Singleton
@Path("/hello")
public class HelloResource {
	
    private final Map<Integer, User> usersStore = new ConcurrentHashMap<>();
    private final AtomicInteger userId = new AtomicInteger();
    
	@Context
	ServletContext context;
	
	@GET
	@Path("/greetme")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello";
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchUser() {
		return Response.ok(new User("Kevin", "Mitnik", 56).toJson()).build();
	}	
	
	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		return Response.ok(user.toJson()).build();
	}

	@GET
	@Path("/consume")
	public JsonObject consume() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://randomuser.me/api/");
		JsonObject result = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
		return result;
	}
}
