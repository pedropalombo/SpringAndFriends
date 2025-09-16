## Beans ##
> are used by Spring instead of Java
    \-> injects & manages methods/states automatically, without the need of manually doing so
        ^-> aka let Spring handle object management, and not you B) 
        ^-> ex:
            +-> Java [no beans]: ClassExample example = new ClassExample();
            +-> Spring [w/ beans]: @Autowired ClassExample example; 