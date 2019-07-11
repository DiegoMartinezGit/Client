package cl.diegomartinez.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"cl.diegomartinez.Client"})
public class SoaChat {

    @Autowired
    private ChatClient busClient;

    public static void main(String[] args) {
        SpringApplication.run(SoaChat.class, args);
    }

}