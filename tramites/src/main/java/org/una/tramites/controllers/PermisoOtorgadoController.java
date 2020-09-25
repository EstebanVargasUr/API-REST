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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/permisos_otorgados") 
@Api(tags = {"Permisos Otorgados"})
public class PermisoOtorgadoController {

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(permisoOtorgadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un permiso otorgado por su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{permiso}{id}")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un permiso otorgado por el id de un permiso", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{usuario}{id}{estado}")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un permiso otorgado por medio del id de un usuario y su estado", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    public ResponseEntity<?> findByUsuarioIdAndEstado(@PathVariable(value = "id") Long id, boolean estado) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByUsuarioIdAndEstado(id,estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/permiso/{id}{estado}")
    @ApiOperation(value = "Obtiene una lista de permisos otorgados por Id del permiso y el estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByPermisoIdAndEstado(@PathVariable(value = "id") Long id, boolean estado) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoIdAndEstado(id, estado), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fecha}")
    @ApiOperation(value = "Obtiene una lista de permisos otorgados por fechas", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "id") Date fech1, Date fech2) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByFechaRegistroBetween(fech1, fech2), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody PermisoOtorgadoDTO permisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(permisoOtorgadoService.create(permisoOtorgadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un permiso otorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PermisoOtorgadoDTO permisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PermisoOtorgadoDTO> permisoOtorgadoUpdated = permisoOtorgadoService.update(permisoOtorgadoDTO, id);
                if (permisoOtorgadoUpdated.isPresent()) {
                    return new ResponseEntity(permisoOtorgadoUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar un permiso otorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            permisoOtorgadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los permisos otorgados", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            permisoOtorgadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

