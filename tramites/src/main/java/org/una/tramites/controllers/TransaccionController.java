package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.services.ITransaccionService;

/**
 *
 * @author adria
 */
@RestController
@RequestMapping("/transacciones") 
@Api(tags = {"Transacciones"})
public class TransaccionController {
    
    @Autowired
    private ITransaccionService transaccionService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todas las Transacciones", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(transaccionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    @ApiOperation(value = "Obtiene una Transaccion por su Id", response = TransaccionDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/usuario/{id}{fecha}") 
    @ApiOperation(value = "Obtiene una Transaccion por el Id del Usuario entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    ResponseEntity<?> findByUsuarioIdAndFechaRegistroBetween(@PathVariable(value = "id") Long idUsu, @PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByUsuarioIdAndFechaRegistroBetween(idUsu, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/permiso/{id}{fecha}") 
    @ApiOperation(value = "Obtiene una transaccion por medio del Id del Permiso entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    ResponseEntity<?> findByPermisoIdAndFechaRegistroBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByPermisoIdAndFechaRegistroBetween(id, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{objeto}{fecha}") 
    @ApiOperation(value = "Obtiene una Transaccion por medio de un objeto entre la fecha especificada", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    ResponseEntity<?> findByObjetoAndFechaRegistroBetween(@PathVariable(value = "Objeto") String objeto, @PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fecha/{termino}") 
    @ApiOperation(value = "Obtiene una lista de las Transacciones entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear una Transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody TransaccionDTO transaccionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(transaccionService.create(transaccionDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
}

