## Game Runner ##
> build a system that runs games
    \-> Mario, Super Contra, PacMan, etc.
    \-> divide the development cycles into iterations
        ^-> 1: tightly coupled
            (-> GameRunner class
            (-> game classes
        ^-> 2: Loose Coupling [Interfaces]
            (-> + GamingConsole interface
        ^-> 3: Loose Coupling [Spring]
            (-> + make Spring manage all objects