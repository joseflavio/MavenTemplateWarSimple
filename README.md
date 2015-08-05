# MavenTemplateWarSimple

## Needed Software

### Ubuntu

`sudo apt-get install openjdk-7-jdk`

## Running the development server

mvn jetty:run

 - Accessing the server: 
    `http://localhost:8080/`
    `http://localhost:8080/hello`

## Validating the code before compiling

`mvn findbugs:check`

### Alternatives

 - if you want to generate reports: `mvn findbugs:findbugs ()`
 - To launch the UI: `mvn findbugs:gui`

## Building

mvn compile

## Creating the war

mvn package

## Reports

`mvn site:site`


