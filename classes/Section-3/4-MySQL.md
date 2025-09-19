## Using MySQL (+ Docker) in Springboot ##
> local dbs would usually use DAO/JPA logic, but MySQL is more consistent
    \-> relies on POST requests to get/insert data ("SQL Injections")
    \-!> PS: 'mysqlsh' is needed to verify data on CMD 

> it'll be set up together w/ Docker
    ))-> {
        docker run --detach #running the docker in the background
        #setting enviromental variables for MySQL, such as ...
        --env MYSQL_ROOT_PASSWORD=dummypassword #admin password
        --env MYSQL_USER=social-media-user  #user's name
        --env MYSQL_PASSWORD=dummypassword #user's password
        --env MYSQL_DATABASE=social-media-database #db name
        --name mysql #as well as the docker's name
        --publish 3306:3306 #maps the host's port to the dockers
        mysql:8-oracle  #oracle version (8)
    }
        +-> check the active containers
            )-> docker container ls
