# Mars Rover Kata

## Your Task

You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop an API that translates the commands sent from earth to instructions that are understood by the rover.

### Requirements

- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping at edges. But be careful, planets are spheres.
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.
- Hardcore TDD. No Excuses!

## Documentation

You can view the endpoints documentation [here](src/main/java/com/example/marsroverkata/docs/APIDocumentation.pdf).

Alternatively you can use the following url when the project is running: http://localhost:8080/docs

## Development server

1. Clone the repository.
2. Open your project folder with some IDE (like IntelliJ IDEA).
3. Use the Spring Boot Maven plugin like so: mvn spring-boot:run
4. Use the url: http://localhost:8080/
5. The application will automatically reload if you change any of the source files.
