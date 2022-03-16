# phone-validation-server
This is the server side application for phone validation services

## Description

This project is mainly for the server-side App for phone-validation application where it spins up on docker image to serve locally on port 8080

## Project Structure

The project stucture is pretty simple N-Tier app in regards to the project structure which consists of the following components

- controllers => contains the main REST API classes.

- service => Service interfaces & implementations classes 

- repository => classes interfaces for JPARepository

- dto => Enitites used for quereing the API

- models => DB Enitites definition

## Development server



Run `docker build --pull --rm -f "Dockerfile" -t phonevalidationserver:latest "."` for adding needed packages.

If Build fails for any reason , you can just run this command first to build the jar file

Run `./mvnw clean package` for project build

Run `docker-compose up` for running the image.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.


## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

