package org.example.dockermongotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerMongoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerMongoTestApplication.class, args);

        /*

        You're running a single MongoDB instance locally for integration tests.

        This instance is spun up via Docker Compose, along with any other dependencies.

        All your tests that involve real database operations (fetching, saving, deleting) will interact with this instance.

        The tests will not mock the repository layer—they'll exercise the full controller-to-repository path.

        You're using this for integration testing, not as a replacement for unit testing.


        */
    }

}
