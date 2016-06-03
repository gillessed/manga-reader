package com.gillessed.daedalus;

import com.gillessed.daedalus.rest.resources.AuthResource;
import com.gillessed.daedalus.rest.resources.CatalogueResource;
import com.gillessed.daedalus.rest.resources.LocalResource;
import com.gillessed.daedalus.rest.services.Services;
import com.gillessed.daedalus.rest.resources.MangaResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by gcole on 4/16/16.
 */
public class MangaReaderServerMain extends Application<MangaReaderConfiguration> implements LifeCycle.Listener {

    public static void main(String[] args) throws Exception {
        new MangaReaderServerMain().run(args);
    }

    private Services services;

    @Override
    public String getName() {
        return "manga-reader";
    }

    @Override
    public void initialize(Bootstrap<MangaReaderConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(MangaReaderConfiguration configuration, Environment environment) throws Exception {
        services = new Services(configuration);
        enableCors(environment);

        environment.jersey().register(new MangaResource(services));
        environment.jersey().register(new CatalogueResource(services));
        environment.jersey().register(new LocalResource(services));
        environment.jersey().register(new AuthResource(services));
        environment.lifecycle().addLifeCycleListener(this);
    }

    private void enableCors(Environment environment) {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,X-Auth-Token");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    @Override
    public void lifeCycleStarting(LifeCycle event) {

    }

    @Override
    public void lifeCycleStarted(LifeCycle event) {

    }

    @Override
    public void lifeCycleFailure(LifeCycle event, Throwable cause) {

    }

    @Override
    public void lifeCycleStopping(LifeCycle event) {
        services.shutdown();
    }

    @Override
    public void lifeCycleStopped(LifeCycle event) {

    }
}
