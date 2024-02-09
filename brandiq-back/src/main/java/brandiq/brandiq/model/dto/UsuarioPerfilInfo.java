package brandiq.brandiq.model.dto;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioPerfilInfo implements Serializable {

    // private Long id;

    private String email;

    // private String password;

    private String nickname;

    private String nombre;

}
