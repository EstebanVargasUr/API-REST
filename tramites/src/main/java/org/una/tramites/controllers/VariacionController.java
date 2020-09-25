package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.services.IVariacionService;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/variaciones") 
@Api(tags = {"Variaciones"})
public class VariacionController {
    
    @Autowired
    private IVariacionService variacionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
        
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las Variaciones", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(variacionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una Variacion por su Id", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(variacionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{termino}") 
    @ApiOperation(value = "Obtiene una lista de las Variaciones por estado", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado){
        try {
            return new ResponseEntity(variacionService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/grupo/{termino}") 
    @ApiOperation(value = "Obtiene una lista de las Variaciones por grupo", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CONSULTAR')")
    public ResponseEntity<?> findByGrupo(@PathVariable(value = "grupo") boolean grupo){
         try {
            return new ResponseEntity(variacionService.findByGrupo(grupo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fecha/{termino}") 
    @ApiOperation(value = "Obtiene una lista de Variaciones entre la fecha especificada", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CONSULTAR')")
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(variacionService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tramite/{id}")
    @ApiOperation(value = "Obtiene una lista con las Variaciones por Tipo de Tramite", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CONSULTAR')")
    public ResponseEntity<?> findByTramiteTipoId(@PathVariable(value = "id") long id) {
        try {
            return new ResponseEntity(variacionService.findByTramiteTipoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear una Variacion", response = VariacionDTO.class, tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody VariacionDTO variacionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(variacionService.create(variacionDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar una Variacion a partir de su Id", response = VariacionDTO.class, tags = "Variaciones")
    @PreAuthorize("hasAuthority('VARIACION_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody VariacionDTO variacionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<VariacionDTO> usuarioUpdated = variacionService.update(variacionDTO, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}") 
    @ApiOperation(value = "Permite eliminar una Variacion a partir de su Id", response = VariacionDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('VARIACION_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            variacionService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todas las Variaciones", response = VariacionDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('VARIACION_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
 	try {
            variacionService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
}
