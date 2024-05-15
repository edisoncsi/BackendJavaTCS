package eco.com.spring.mcsv.account.mov.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class ReporteDto {
    private String fecha;

    private String cliente;

    private String numeroCuenta;

    private  String tipoCuenta;

    private String saldoInicial;

    private String estado;

    private String movimiento;

    private String saldo;
}
