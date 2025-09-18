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