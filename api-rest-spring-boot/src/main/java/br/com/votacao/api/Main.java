/*
 * www.votacao.com.br
 */
package br.com.votacao.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal do spring boot
 * @author Ederson Melo
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
        System.out.println("\n\n\n\n\n-----------------------------------------------------------------------------------");
        System.out.println("API REST VOTACAO INICIADA");
        System.out.println("www.votacao.com.br");
        System.out.println("-----------------------------------------------------------------------------------\n\n");
    }
}
