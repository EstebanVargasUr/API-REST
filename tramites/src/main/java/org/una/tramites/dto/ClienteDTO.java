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
public class ClienteDTO {
 
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private String telefono; 
    private String direccion;
    private String passwordEncriptado;  
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private boolean estado; 
}

