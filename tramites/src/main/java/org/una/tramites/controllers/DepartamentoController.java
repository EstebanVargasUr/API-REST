package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.services.IDepartamentoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/departamentos") 
@Api(tags = {"Departamentos"})
public class DepartamentoController {
    
    @Autowired
    private IDepartamentoService departamentoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(departamentoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un departamento por su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(departamentoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un departamento por su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(departamentoService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        @PostMapping("/")
    @ApiOperation(value = "Permite crear un departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTOS_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody DepartamentoDTO departamentoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(departamentoService.create(departamentoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un Departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @ResponseBody
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CREAR')")
    public ResponseEntity<?> create(@RequestBody DepartamentoDTO departamento) {
        try {
            DepartamentoDTO departamentoCreated = departamentoService.create(departamento);
            DepartamentoDTO departamentoDto = MapperUtils.DtoFromEntity(departamentoCreated, DepartamentoDTO.class);
            return new ResponseEntity<>(departamentoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Usuario a partir de su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DepartamentoDTO departamentoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<DepartamentoDTO> departamentoUpdated = departamentoService.update(departamentoDTO, id);
                if (departamentoUpdated.isPresent()) {
                    return new ResponseEntity(departamentoUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar un departamento a partir de su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            departamentoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            departamentoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
