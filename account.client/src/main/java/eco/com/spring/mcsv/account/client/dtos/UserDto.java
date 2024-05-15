package eco.com.spring.mcsv.account.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String password;
    private String estado;
}
