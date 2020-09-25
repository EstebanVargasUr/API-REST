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
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.services.IPermisoService;
import org.una.tramites.utils.MapperUtils;




@RestController
@RequestMapping("/permisos") 
@Api(tags = {"Permisos"})
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los Permisos", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
                return new ResponseEntity<>(permisoService.findAll(), HttpStatus.OK);
           
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    @ApiOperation(value = "Obtiene el permiso por medio del Id", response =  PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
                return new ResponseEntity<>(permisoService.findById(id), HttpStatus.OK);
          
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{codigo}") 
    @ApiOperation(value = "Obtiene una lista con los codigos de los permisos", response =  PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "term") String codigo) {
        try {
                return new ResponseEntity<>(permisoService.findByCodigo(codigo), HttpStatus.OK);
           
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista de los permisos por medio del estado", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean st) {
        try {

            
                return new ResponseEntity<>(permisoService.findByEstado(st), HttpStatus.OK);
           
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de Permisos entre la fecha especificada", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
                return new ResponseEntity<>(permisoService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
           
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un permiso", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody PermisoDTO permisoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
        try {
            return new ResponseEntity(permisoService.create(permisoDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }else{
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un permiso", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,@Valid @RequestBody PermisoDTO permisoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
        try {
            Optional<PermisoDTO> permisoUpdated = permisoService.update(permisoDTO, id);
            if (permisoUpdated.isPresent()) {
                return new ResponseEntity<>(permisoUpdated, HttpStatus.OK);

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
    @ApiOperation(value = "Permite eliminar un permiso a partir de su Id", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
            try {
            permisoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/") 
    @ApiOperation(value = "Permite eliminar todos los Usuarios", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
       try {
            permisoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    } 


