package org.una.tramites.dto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Departamento;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramiteTipoDTO {
 
    private Long id; 
    private String descripcion;   
    private boolean estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion;
    private Departamento departamento; 
}
