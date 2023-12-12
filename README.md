# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = Henrik Halvorsen Kvamme
STUDENT ID = 111794

## Project description
The TrainDispatchSystem is a project that manages information about train departures. The system allows users to view departure times, line information, destinations, and any delays for trains.

The project is structured with a modular approach, where each component of the system is divided into separate classes and packages to promote code reuse and maintenance.

It uses Semantic Versioning (SemVer) to manage versions of the project. This means that each version is divided into "MAJOR.MINOR.PATCH", where:

MAJOR version is increased when there have been incompatible API changes
MINOR version is increased when functionality is added in a backwards-compatible manner
PATCH version is increased when we make backwards-compatible bug fixes
Before release, the version is in the format 0.x.y to signify that it is in an early unstable phase.

To run the project, see the instructions in the section "How to run the project".

## Project structure

Prosjektet følger en Model-View struktur, som er en forenklet versjon av Model-View-Controller (MVC) arkitekturen. Her er en kort beskrivelse av hvordan dette fungerer i vårt prosjekt:

Model: Dette er dataene og forretningslogikken i applikasjonen. I prosjektet er dette representert av klassene TrainDeparture og TrainRegister.

View: Dette er brukergrensesnittet som presenterer data til brukeren og samler inn brukerinput. Dette er klasser som UserInterface, UserInput, Menu og TableFormatter.

## Link to repository

[//]: # (TODO: Include a link to your repository here.)

## How to run the project

Run this in terminal:
```mvn compile```

```cd target/classes```

```java edu.ntnu.stud.Main```

## How to run the tests

```mvn test```

