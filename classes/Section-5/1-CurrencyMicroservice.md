## 'Currency' Microservices ##
> composed by 'Conversion' & 'Exchange'
    \-> Conversion -> Exchange -> DB
        ^-> Exchange
            +-> returns the exchange rate from a currency to another
                )-> e.g: localhost:8000/currency-exchange/from/USD/to/INR
                    ]-> {
                        "id":10001,
                        "from":"USD"
                        "to":"INR"
                        "conversionMultiple":65.00,
                        "environment":"8000 instance-id"
                    }
        ^-> Conversion
            +-> converts one currency to another
                )-> e.g: localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
                    ]-> {
                        "id":10001,
                        "from":"USD"
                        "to":"INR"
                        "conversionMultiple":65.00,
                        "quantity":10,
                        "totalCalculatedAmount":650.00
                        "environment":"8000 instance-id"
                    }

## Linking One Microservice to the Other ##
> send a request to it!
    \-!> PS: look at 'CurrencyConversionController' for examples
    \-!> OBS: make sure both microservices are running!

> ... or use 'Feign'
    \-!> PS: needs to be added as a dependency first
    \-> for that, a Proxy is needed

> if nothing is working: ))) KILL ALL PROCESSES and run them again (((