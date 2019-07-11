package cl.diegomartinez.Client;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDto {
    private String content;
    private Long id_usr1 ;
    private Long id_usr2 ;
    private Timestamp timestamp;


}
