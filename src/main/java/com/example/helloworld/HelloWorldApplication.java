package com.example.helloworld;

import com.example.helloworld.dal.PersonDB;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.*;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        final PersonDB personDB = new PersonDB();
        final PersonResource personResource = new PersonResource(personDB);

        environment.jersey().register(personResource);
    }

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }
}
