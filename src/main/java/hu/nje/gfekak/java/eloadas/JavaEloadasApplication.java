package hu.nje.gfekak.java.eloadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class JavaEloadasApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEloadasApplication.class, args);
    }
}
