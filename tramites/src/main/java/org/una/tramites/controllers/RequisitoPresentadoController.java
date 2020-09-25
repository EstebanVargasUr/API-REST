package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.services.IRequisitoPresentadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author adrian
 */
@RestController
@RequestMapping("/requisitoPresentado") 
@Api(tags = {"Requisitos Presentados"})
public class RequisitoPresentadoController {
    
    @Autowired
    private IRequisitoPresentadoService requisitoPresentadoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los requisitos presentados", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(requisitoPresentadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un requisito presentado por su Id", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(requisitoPresentadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fecha}")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un requisito presentado entre fechas", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fecha") Date fecha1, Date fecha2) {
        try {
            return new ResponseEntity(requisitoPresentadoService.findByFechaRegistroBetween(fecha1, fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
      @PostMapping("/")
    @ApiOperation(value = "Permite crear un requisito presentado", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody RequisitoPresentadoDTO requisitoPresentadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(requisitoPresentadoService.create(requisitoPresentadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un requisito presentado a partir de su Id", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RequisitoPresentadoDTO requisitoPresentadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RequisitoPresentadoDTO> requisitoPresentadoUpdated = requisitoPresentadoService.update(requisitoPresentadoDTO, id);
                if (requisitoPresentadoUpdated.isPresent()) {
                    return new ResponseEntity(requisitoPresentadoUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar un requisito presentado a partir de su Id", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitoPresentadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los requisitos presentados", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            requisitoPresentadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

