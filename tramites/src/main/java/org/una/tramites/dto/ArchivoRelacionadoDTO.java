package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author Esteban Vargas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class ArchivoRelacionadoDTO {
    
    private Long id;
    private String nombre;   
    private String etiquetas;
    private String rutaArchivo;
    private Boolean estado;
    private Boolean tipo;
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private TramiteRegistrado tramiteRegistrado;
}
