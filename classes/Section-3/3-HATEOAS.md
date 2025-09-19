## Hypermedia as the Engine of Application State ##
-!> OBS: needs to be added to the dependencies first

)) check 'UserResource' for further examples ))

> don't just give/fetch data, but teach the consumer on how to interact with the API
    -!> PS: needs 'EntityModel' and 'WebMvcLinkBuilder' to structure
    ^-> EntityModel
        +-> a class that envelops the JSON response from the API with further info
    ^-> WebMvcLinkBuilder
        +-> adds links to said info so the paths aren't hardcoded

> applications usually allow you to:
    \-> see the data
    \-> perform actions

## Types of Implementation ##
> Custom Format & Implementation
    \-!> OBS: difficult to maintain 

> Standard Implementation
    \-> based on HAL (JSON Hypertext Application Language)
        ^-> defines how a format to hyperlink across the API's resources
            +-!> PS: 'Spring HATEOAS' also generates HAL responses
        ^-> HAL Explorer
            +-> an API explorer for REST
                )-> API integration for non-technical personel
                    ]-> needs to be set as a dependency ==> spring-data-rest-hal-explorer

    \-> {
        "name": "Adam",
        "birthDate": "2022-08-06",
        "_links": {
            "all-users": {
                "href": "http://localhost:8080/users"
            }
        }
    }