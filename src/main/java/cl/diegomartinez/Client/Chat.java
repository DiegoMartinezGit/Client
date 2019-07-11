package cl.diegomartinez.Client;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Chat {
    private Long id;
    private Long id_usr1;
    private Long id_usr2;
    private String msj;
    private Timestamp timestamp;
}
