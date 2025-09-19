## Project - Social Media Application (using REST API) ##
> Key resources
    \-> Users
        ^-> id
        ^-> name
        ^-> birthDate
    \-> Posts
        ^-> id
        ^-> description

> APIs needed
    \-> Users
        ^-> retrieve all users (GET /users)
            +-> retrieve one user (GET /users/{id})
        ^-> create a user (POST /users)   ==> needs API client
        ^-> delete a user (DELETE /users/{id})
    
    \-> Posts
        -!> a post is related to a user
        ^-> retrieve all posts from a user (GET /users/{id}/posts)
            +-> retrieve one post (GET /users/{id}/posts/{post_id})
        ^-> create a post (POST /users/{id}/posts)

## Building a REST API w/ Spring Boot ##
> it can be done without
    \-> but with it...
        ^-> 

## Needed tools ##
> Spring Web
    \-> for the HTTP protocols and web builds

> Spring Data JPA
    \-> for SQL data manipulation

> H2 Database
    \-> light (2mb) in-memory database to be used before SQL dbs
        ^-!> OBS: needs to be configured on 'application.properties' and be given an sql file to go with it
        ^-> URL: http://localhost:8080/h2-console/
    \-!> PS: data doesn't persist when migrating to MySQL, since it's only internal

> DevTools
    \-> for LiveReload and application restarts

## Steps ##
)) go to restAPI_Project's 'HelloWorld' for further understanding ))

> create a package inside the project's package, with your Controller class in it
    \-> Controller: handles/maps HTTP methods that'll be used on the application
        ^-> a bridge between application and the HTTP layer

> set your methods' functions (Controllers and Services) for the paths in your application
    -!> PS: remember to use the annotations to point where and what the method should go/do
        \-> @<method>Mapping(path="designated-path")

> GLHF!

## Path Parameters ##
)) go to restAPI_Project's 'User' for further understanding ))
> using dynamic values to create pages/paths
    ^-> ex: "/users/{id}/todos/{id}"

## Validation ##
)) go to restAPI_Project's 'User', 'UserNotFoundException', and 'ErrorDetails' for further understanding ))
> 200: all good
    \-> 201: created (ex: POST)
        ^-!> PS: validation for user input needs a dependency to be added to POM.XML
    \-> 204: no content (ex: PUT)

> 400: validation error
    \-> 401: unauthorized
    \-> 404: resource not found

> 500: server exception

## Documentation ##
> the APIs consumers should understand the following through it
    \-> resources being used
    \-> actions that the API do
    \-> request/response structure

> how to maintain all this updated?
    \-> Option 1: manually (labourous and faulty in nature)
    \-> Option 2: generate from code (precise and automatic)

> Automatic API documentation
    \-!> OBS: needs to be added as a dependency before usage (POM.XML)
    )-> Swagger: allows the documentation of how the API's methods interact with an application
    )-> OpenAPI: interface to visualize the documentation (once was 'Swagger Specification')
    )-> Swagger UI: lets the user visualize & interact with the REST API
        ^-> after building with the dependency, the documentation for the application can be accessed directly
            ^-> <app_url>/swagger-ui/index.html
                +-> aka localhost:8080/swagger-ui/index.html

## Content Negotiation ##
)) check 'HelloWorldController' for further understanding ))
> the different ways of representing the contents of an URI
    ^-!> OBS: this is set in the <header /> before making the request (Client API tester)

    \-> Content types: JSON, XML, ...
        ^-!> PS: needs Jackson dependency
        ^-> accept: application/json

    \-> Languages (i18n): English, Dutch, French,...
        ^-!> PS: needs a i18n.properties to store said translations (for each language)
            +-> same package as 'application.properties' (resources)
        ^-> accept-language: en

## Versioning ##
)) check 'VersioningPersonController' for further details ))
> the way of doing it should be thought and defined even before needing it!
    \-> whenever change on the API's structure is needed, versioning comes handly

> how to + pros/cons
    \-> URL
        ^-> create a separate path for the new API version, so no forced changes are required by the consumer
            +-!> OBS: can cause a URI polution

    \-> Request Parameter
        ^-> when doing a request to the API, the consumer indicates which version of it is going to be consumed

    \-> Header
        ^-> require the consumer to point to the wanted version of the API on its request
            +-!> OBS_1: HTTP headers were not made for this
            +-!> OBS_2: since there's no info to be used besides the request's, caching can be tricky
            +-!> OBS_3: API documentation, from the lack of parameters, can also be a pain


    \-> Media Type
        ^-> basing the reponse version on the type of media that was requested on the header (Content Negotiation)
            +-!> OBS_1: HTTP headers were not made for this
            +-!> OBS_2: since there's no info to be used besides the request's, caching can be tricky
            +-!> OBS_3: API documentation, from the lack of parameters, can also be a pain

## Customizing the Response [Serialization] ##
)) check 'User' for further exemplification ))
> converting given object to stream
    \-> stream == helps processing data
        ^-> e.g: looping methods to retrieve data (forEach, filter, map, ...)
    \-> tools for the job: Jackson!

> properties
    \-> change field names: @JSONProperty
    \-> filtering fields
        ^-> e.g: filter-out passwords from the response
            +-> Static
                )-> @JsonIgnoreProperties / @JsonIgnore
                )-> same filtering structure for a bean across different REST APIs
            +-> Dynamic
                )-> @JsonFilter (FilterProvider)
                )-> custom filter for a bean in a specific REST API