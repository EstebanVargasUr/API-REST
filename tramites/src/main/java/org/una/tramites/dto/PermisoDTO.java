package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author adria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class PermisoDTO {
 
    private Long id; 
    private String codigo;
    private String descripcion;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean estado;
}

