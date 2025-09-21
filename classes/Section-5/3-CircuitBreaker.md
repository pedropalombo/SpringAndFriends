## Circuit Breaker ##
)) can be found in 'currency-exchange-service' ))

> prevents system outage/slow-down in case a microservice is down/slow
    \-!> they have to be independent, right? ;)
    \-> through annotations
        ^-> it envelops a method/URL, and protects it from attacks, critical failures, etc.
            +->  [it allows request if all is fine, blocks them if not, and allow some when failures happened but not anymore](/resources/section5/circuitBreaker.png)
                )-> also known as the 3 states [CLOSED, OPEN, HALF_OPEN](https://resilience4j.readme.io/docs/circuitbreaker)

> also know as "Resilience" ("Resilience4J")
    ^-!> PS: needs a dependency + controller

## Rate Limiting ##
> limits the rate (amount x times) that the requests can come in
    \-!> PS: it's an annotation, so it uses configuration from 'application.properties'

> Bulkhead
    \-> allows concurrent requests to be receive, and defines how many are possible at a time