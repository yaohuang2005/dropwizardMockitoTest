package com.example.helloworld.resources;

import com.example.helloworld.dal.PersonDB;
import com.example.helloworld.data.Person;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import java.util.*;

public class PersonResourceMockTest {
    static PersonDB personDBMock = Mockito.mock(PersonDB.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
                    .addResource(new PersonResource(personDBMock))
                    .build();

    Person person = new Person(1, "yao", "huang", "ya@ya.com");

    @Before
    public void setup() {
        Mockito.when(personDBMock.getById(1)).thenReturn(person);
    }

    @Test
    public void testGetOne() {
        Person onePerson = resources.target("/person/get/1").request().get(Person.class);
        String firstN = onePerson.getFirstName();
        Assert.assertEquals(firstN, "yao");
    }
}
