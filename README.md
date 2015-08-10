# Central Ticket

Ticket printing web service -- useful for printing from the browser
directly from javascript.

Depends on [sealink/ticket_printer](https://github.com/sealink/ticket_printer) which must be installed into your
local maven repository.

## Requirements

See [sealink/ticket_printer](https://github.com/sealink/ticket_printer)


## Building

* Eclipse:
Run -> Run configurations -> Maven build -> New -> Set base directory and set goals to "clean install assembly:single"

OR

Command-line Maven:

    cd central_ticket
    mvn clean install assembly:single


## Running

Run with '1337 keystore.jks password' as command line args

E.G.

    java -jar central_ticket-1.0-SNAPSHOT-jar-with-dependencies.jar 1337 keystore.jks password


## Troubleshooting

* If you get the error `Illegal option: j`

Please ensure that you are running `java -jar`, and not `jar -jar`.
