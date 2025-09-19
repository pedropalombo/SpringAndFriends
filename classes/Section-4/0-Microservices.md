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