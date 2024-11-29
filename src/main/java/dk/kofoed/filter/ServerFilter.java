package dk.kofoed.filter;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import dk.kofoed.user.UserData;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;

public class ServerFilter {

    @Inject
    Logger logger;

    @Inject
    UserData userData;

    @ServerRequestFilter(priority = Priorities.AUTHENTICATION)
    public void requestFilter(ContainerRequestContext ctx) {
        logger.info("User data: " + userData.getId());  // null at this point
    }
    
    @PostConstruct
    public void init() {
        logger.info("Instance created");
    }
    
}
