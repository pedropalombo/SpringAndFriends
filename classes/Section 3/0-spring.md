## Spring Framework - Terminology ##
> Tight/Loose Coupling

> IOC Container

> Application Context

> Component Scan

> Dependency Injection

> Spring Beans

> Auto Wiring

## Springboot Actuator ##
-!> OBS: needs to be added as a dependency!

> monitor/manage the application in production sites
    \-> spring-boot-starter-actuator
        ^-!> PS: can be accessed at '<url>/actuator'

> provides endpoints to be monitored
    \-> the endpoints can be configured at 'application.properties'
        ^-> management.endpoints.web.exposure.include=*
        
    \-> endpoints
        ^-> beans
            +-> full list of Spring beans that are used in the app
        ^-> health
            +-> app's health info
        ^-> metrics
        ^-> mappings
            +-> details around the RequestMapping's being used
        ^-> many more

## Spring Security ##
)) examples of this is in 'SpringSecurityConfiguration' ))
> it intercepts the request before hitting the application and run filters through it
    ^-!> needs to be added to the dependencies before usage
    ^-> filters
        +-> all requests should be authenticated
        +-> enables CSRF (Cross-site Request Forgery) protection

> UI: adding authenticators for the system (aka login page)

> doesn't allow any requests to come through without the proper credentials
    ^-!> PS: passowd is sent to the console when running the application, and the username is "user" by default
        +-> can be customisable on 'application.properties'
    ^-!> OBS: requests require an "Authorization Header" with the credentials