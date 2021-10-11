package com.example.helloworld.resources;

import com.example.helloworld.dal.PersonDB;
import com.example.helloworld.data.Person;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import java.util.*;

public class PersonResourceRealTest {
    static PersonDB personDB = new PersonDB();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDB))
            .build();

    @Test
    public void testGetAll() {
        List<Person> all = resources.target("/person/all").request().get(List.class);
        int expect = 4;
        int count = all.size();
        Assert.assertEquals(expect, count);
    }
}
