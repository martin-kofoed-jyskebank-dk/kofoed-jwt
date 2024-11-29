package dk.kofoed.jwt;

import org.jboss.logging.Logger;
import org.jose4j.jwt.JwtClaims;

import dk.kofoed.user.UserData;
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
import io.smallrye.jwt.auth.principal.JWTAuthContextInfo;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipalFactory;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;

@ApplicationScoped
@Alternative
@Priority(1)
public class CustomJwtCallerPrincipalFactory extends JWTCallerPrincipalFactory {

    @Inject
    Logger logger;

    @Inject
    UserData userData;

    @Override
    @ActivateRequestContext
    public JWTCallerPrincipal parse(String token, JWTAuthContextInfo authContextInfo) throws ParseException {

        userData.setId("id1");

        return new DefaultJWTCallerPrincipal("test", new JwtClaims());
    }
    
    @PostConstruct
    public void init() {
        logger.info("Instance created");
    }
}
