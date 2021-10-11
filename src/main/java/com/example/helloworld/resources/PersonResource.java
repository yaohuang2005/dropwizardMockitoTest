package com.example.helloworld.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import com.example.helloworld.data.Person;
import com.example.helloworld.dal.*;
import com.codahale.metrics.annotation.Timed;

@Path("/person")
public class PersonResource {
    private PersonDB personDB;

    public PersonResource(PersonDB _personDB) {
        personDB = _personDB;
    }

    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") int id) {
        return personDB.getById(id);
    }

    @GET
    @Timed
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String removePerson() {
        personDB.remove();
        return "Last person remove. Total count: " + personDB.getCount();
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return personDB.getAll();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(Person person) {
        return personDB.save(person);
    }
}