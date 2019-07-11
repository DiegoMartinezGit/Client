package cl.diegomartinez.Client;

import lombok.Data;
import java.util.UUID;
@Data
public class User {
    private Long id;
    private UUID uuid;
    private String email;
    private String username;
    private String password;

}
