## [What's a Web Service](/resources/section2/webServDef.png) ##
> not just a service that's delivered over the web, but rather a service that's made for machine-to-machine communication
    \-> also needs to be platform/system independent
    \-> should allow communication over a network

## Communication ##
> since it needs to be platform-independent, the service should be able to run requests and responses no matter who's trying to reach for it
    \-> XML / JSON usage are go-to's for this

## Key Terminology ##
> Request
    \-> input sent to the web service

> Response
    \-> output sent from it

> Message Exchange Format
    \-> format used for the communication (XML, JSON, etc.)

> Service Provider (Server)
    \-> entity providing/hosting the web services

> Service Consumer
    \-> entity consuming the web service

## [Types of Services](/resources/section2/categories.png) ##
> SOAP
    \-> uses SOAP-XML as the format for request/response

> REST
    \-> since it's build using HTTP protocol, it uses its methods for the communication/operations
        ^-> GET: retrieves data (ex: fetches user's info)
        ^-> POST: sends data (ex: creates new user)
        ^-> DELETE: removes data (ex: delete a user)
        ^-> UPDATE: update an existing user
            +-> PATCH: updates specific data (ex: changes user's surname)