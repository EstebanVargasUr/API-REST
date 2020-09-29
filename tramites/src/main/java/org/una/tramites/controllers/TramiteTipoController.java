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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.services.ITramiteTipoService;

@RestController
@RequestMapping("/tramitesTipos") 
@Api(tags = {"Tramites Tipos"})
public class TramiteTipoController {

    @Autowired
    private ITramiteTipoService tramiteTipoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de Tramites", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA6')")
    ResponseEntity<?> findAll() {
         try {
            return new ResponseEntity(tramiteTipoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un Tipo de Tramite por su Id", response = TramiteTipoDTO.class, tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramiteTipoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{termino}") 
    @ApiOperation(value = "Obtiene una lista de Tipos de Tramites por estado", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "termino") boolean st) {
        try {
            return new ResponseEntity(tramiteTipoService.findByEstado(st), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/fecha") 
    @ApiOperation(value = "Obtiene una lista de Tipos de Tramites entre la fecha especificada", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA5')")
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(tramiteTipoService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista con los Tipos de Tramites por Id del Departamento", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") long id) {
        try {
            return new ResponseEntity(tramiteTipoService.findByDepartamentoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un Tipo de Tramite", response = TramiteTipoDTO.class, tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA1')")
    public ResponseEntity<?> create(@Valid @RequestBody TramiteTipoDTO tramiteTipoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramiteTipoService.create(tramiteTipoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Tipo de Tramite", response = TramiteTipoDTO.class, tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRA2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteTipoDTO tramiteTipoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteTipoDTO> usuarioUpdated = tramiteTipoService.update(tramiteTipoDTO, id);
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
}

