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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.services.IRequisitoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/requisitos") 
@Api(tags = {"Requisitos"})
public class RequisitoController {
    
    @Autowired
    private IRequisitoService requisitoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los requisitos", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(requisitoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un requisito por su Id", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(requisitoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un requisito por su estado", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(requisitoService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un requisito entre fechas", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fecha") Date fecha1, Date fecha2) {
        try {
            return new ResponseEntity(requisitoService.findByFechaRegistroBetween(fecha1, fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{variacion}")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un requisito por medio del id de la variacion", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findByVariacionId(@PathVariable(value = "id") long id) {
        try {
            return new ResponseEntity(requisitoService.findByVariacionId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un requisito", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody RequisitoDTO requisitoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(requisitoService.create(requisitoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un requisito a partir de su Id", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RequisitoDTO requisitoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RequisitoDTO> requisitoUpdated = requisitoService.update(requisitoDTO, id);
                if (requisitoUpdated.isPresent()) {
                    return new ResponseEntity(requisitoUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar un requisito a partir de su Id", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los requisito", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            requisitoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
