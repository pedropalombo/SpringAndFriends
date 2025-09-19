## 'Limits' Microservice ##
> Dependencies
    \-> Spring Web
        ^-> HTTP methods
    \-> DevTools
        ^-> live reload and extra development configs (if needed)
    \-> Actuator
        ^-> monitor/managing tool
    \-> Config Client
        ^-> connects to a Spring Cloud Server to fetch the app's configs

> [Main idea](/resources/section4/springCloudCommunication.png)
    \-> make the variables' values come from config files, and not the application itself

## Steps ##
> create the microservice's project as shown above
    \-> class for the structure (Limits), for the HTTP methods (LimitsController), and the config class (Configuration)
    \-> config 'application.properties' accordingly

> create the Spring Cloud Server project
    \-!> PS: !!! use the same 'Group' name as the Microservice's on Spring Initializr !!!
    \-!> OBS: when running the server, select the class for the server and "Run As -> Java Application"
    \-> DevTools + Config Server for the dependencies

> set-up the GIT repo (git-scm.com) [aka_environments]
    \-!> PS: should be a directory that's initialized outside the client/server's, but still on the same macro package
    \-> link it to Spring Cloud Server
        ^-> on the Server's 'application.properties', set the path there
            +-> spring.cloud.config.server.git.uri=<local_GIT_repo_path>
                )-> try it here: localhost:<server_port>/limits-service/default

> link Spring Cloud Server to the Client (microservice project)
    \-> already did with the dependency, but needs to link to GIT's repo as well
        ^-> on 'application.properties' for 'Limits':
            +-> # sets the environment to be used by the application (GIT's, in this case)
            +-> spring.application.name=limits-service

> if multiple environments (config files) are needed
    \-> add them to the repo on GIT (use VS Code)
        ^-!> PS: don't forget to use the same naming scheme
        ^-> to access them, use the same link as before, just change the 'default' part on the URL to the used name for the file
            +-> e.g: localhost:<server_port>/limits-service/dev

## Profiles ##
> determines which environment from GIT is going to be used as the main one
    \-!> PS: settings for such are on 'Limit's 'application.properties'

## Where do I put the config files / environments for the other microservices?? ##
> just spam them in the GIT repo!
    \-> that's why it's there for ;)