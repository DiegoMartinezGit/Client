package cl.diegomartinez.Client;

import cl.vicenterivera.SOAClientLib.SoaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ChatClient extends SoaClient {

    public ChatClient(
            @Value("${soa.bus.address}") String address,
            @Value("${soa.bus.port}") int busPort,
            @Value("${application.name}") String name,
            @Value("${application.bus_port}") int port
    ) {
        super(address, busPort, port, name);
    }

    @PostConstruct
    public void initialize() {
        init();
    }

}
