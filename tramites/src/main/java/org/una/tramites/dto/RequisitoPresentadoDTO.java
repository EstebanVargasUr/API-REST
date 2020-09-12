package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.entities.Requisito;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author adria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class RequisitoPresentadoDTO {
 
    private Long id; 
  
    private Date fechaRegistro; 
  
    private TramiteRegistrado tramiteRegistrado;
    private Requisito requisito;
}
