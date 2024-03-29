package it.interno.caratteristichepersonafisica;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CaratteristichePersonaFisicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaratteristichePersonaFisicaApplication.class, args);
    }

}
