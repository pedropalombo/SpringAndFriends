## Spring Cloud API Gateway ##
)) application can be found in 'ApiGateway' ))

> applies common traits across the aimed microservices + does the load balancing
    \-> e.g: rate limiting, authentication, etc.
    \-!> PS: not a logic but a service, so needs to be created
        ^-> DevTools
            +-> FastRealoads and all
        ^-> Actuator
            +-> for monitoring and managing the app
        ^-> Eureka Discovery Client
            +-> to instance its path on Eureka's registry
        ^-> Reactive Gateway
            +-> sets the project as an API Gateway + routes the requests to the respective microservices' instances through 'Eureka'

> requests may be routed to the ApiGateway first to ensure the features are being used
    \-> [like this!](/resources/section5/apiGatewayFlow.png)

## Custom Routes ##
)) exemplification is in 'ApiGatewayConfiguration ))

> reroute the requests based on url, and redirects them somewhere else
    \-!> OBS: it's basically a configuration file
    \-!> PS: needs to disable the auto-routing from Spring Cloud's (ApiGateway) 'application.properties'

## Logging Filters ##
> show or hide certain info from the logs
    ^-> Loggers