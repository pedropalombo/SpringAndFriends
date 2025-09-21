## Monolith Application ##
> large app (1 app & 1 db)
    \-> hundreds of thousands of lines each

> minor updates == complete redeployment

> Tightly Coupled components
    \-> one change may affect the other components
        ^-> longer release cycles due to refactoring/testing

> scalability problems
    \-> one component needs more capacity == entire application needs to scale together
        ^-> very high cost

> technology lock-in
    \-> usually reliant on a single stack for the whole system


## Introduction to Microservices ##
> [small autonomous services that work together](/resources/section4/introMicroservices.png)
    \-> are language independent, and their deployment is fully automated

> [in short](/resources/section4/summary.png)
    \-> built with REST, small (and well-defined) units that are independent from one another, and can be dynamically scalled (+/- instances)

> difference between [monolithic apps](/resources/section4/introMicroservices.png) and [microservice-based apps](/resources/section4/microserviceApp.png)

> key advantages
    \-> flexibility
        ^-> choose best frameworks/languages for each service
    \-> innovation
        ^-> easier to experiment / use new tech
    \-> efficiency
        ^-> scale the services on demand, lowering the cost of maintanance
    \-> agility
        ^-> each service can be updated independently, allowing for faster release cycles

## Microservices Solutions ##
> Spring Boot
    \-> rapid development for REST APIs

> Spring Cloud
    \-> umbrella project that provides essential microservice needs (Microsoft)
    \-> Centralized Configuration
        ^-> manages the configuration for multiple microservices in the central GIT repo
    \-> Load Balancing
        ^-> dynamically distributes the requests across active instances of microservices
    \-> Service Discovery
        ^-> enables automatic discovery of microservices
            +-> eases the connection from one microservice to the other, when needed be
    \-> Distributed Tracing
        ^-> traces the requests across microservices
    \-> Edge Server
        ^-> Single Entry Point: implementation of common features across microservices
            +-> e.g: authentication
    \-> Fault Tolerance
        ^-> ensures that there are no cascade of failures whenever a microservice fails

> Docker
    \-> enables for a constant-deployment approach for microservices
        ^-> language/enviroment independent

> Kubernetes
    \-> helps to orchestrate microservices with advanced features
        ^-> Service Discovery, Load Balancing, Release Mgmt, ...

## Centralized Configuration ##
> using Spring Cloud Config Server to funel/link the configs for the many microservices into one GIT repository

## Load Balancer - Multiple Instances for the Same Microservice ##
> sends requests based on the amount of instances for each microservices ('Eureka')
    \-> hand-in-hand with 'Naming Server' logic
        ^-!> PS: load balancing is done by the [API Gateway](/classes/Section-5/2-ApiGateway.md)

> go to 'Run Configurations' and set the instances there
    \-> 'Duplicate' and change the name there
        ^-> you can change the port the instance would be running on the "Arguments/VM arguments" section
            +-> -Dserver.port=<port>

## Naming Server ##
)) application can be found at 'Naming-Server' project ))

> also known as a "Service Registry"
    \-> hand-in-hand with 'Load Balancer's logic
    \-!> PS: it's a service, not logic, so needs to be created
    \-!> OBS: it's also a dependency for the other microservices, so should be added as well

> what's needed:
    \-> Spring Boot DevTools
        ^-> fastReloads and whatnot
    \-> Spring Boot Actuator
        ^-> for monitor/managing the application
    \-> Eureka Server
        ^-> registry for the active instances of the app's microservices
            +-!> OBS: can be accessed in localhost:<port>/eureka

> flow:
    \-> 1: microservice_A asks the 'Load Balancer' where should the request go to
    \-> 2: 'Load Balancer' ('API Gateway') requests said info to 'Naming Service' ('Eureka')
    \-> 3: 'Naming Server' returns with the desired port (instance) for the wanted microservice_B
    \-> 4: 'Load Balancer' then directly shoot a request for the right instance of microservice_B, as requested by microservice_A 