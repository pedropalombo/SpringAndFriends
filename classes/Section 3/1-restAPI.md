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
    \-> 204: no content (ex: PUT)

> 400: validation error
    \-> 401: unauthorized
    \-> 404: resource not found

> 500: server exception

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