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
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.services.IArchivoRelacionadoService;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/archivosRelacionados") 
@Api(tags = {"Archivos Relacionados"})
public class ArchivoRelacionadoController {
    
    @Autowired
    private IArchivoRelacionadoService archivoRelacionadoService;
     
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los archivos relacionados", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR4')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(archivoRelacionadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/id/{termino}")
    @PreAuthorize("hasAuthority('AR3')")
    @ApiOperation(value = "Obtiene un archivo relacionado por su Id", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    public ResponseEntity<?> findById(@PathVariable(value = "termino") Long id) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{termino}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por el nombre", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR3')")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "termino") String nombre) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByNombreAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/estado/{termino}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por el estado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR3')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "termino") boolean estado) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo/{termino}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por el tipo", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR3')")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "termino") boolean tipo) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByTipo(tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fecha/{termino}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por medio de la fecha", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR3')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "termino") Date fecha1, Date fecha2) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByFechaRegistroBetween(fecha1,fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tramiteId/{termino}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por medio del id de un tramite registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR3')")
    public ResponseEntity<?> findByTramiteRegistradoId(@PathVariable(value = "termino") Long id) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByTramiteRegistradoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un archivo relacionado", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR1')")
    public ResponseEntity<?> create(@Valid @RequestBody ArchivoRelacionadoDTO archivoRelacionadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(archivoRelacionadoService.create(archivoRelacionadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un archivo relacionado a partir de su Id", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ArchivoRelacionadoDTO archivoRelacionadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ArchivoRelacionadoDTO> archivoRelacionadoUpdated = archivoRelacionadoService.update(archivoRelacionadoDTO, id);
                if (archivoRelacionadoUpdated.isPresent()) {
                    return new ResponseEntity(archivoRelacionadoUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar un archivo relacionado a partir de su Id", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR5')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            archivoRelacionadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los archivos relacionados", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('AR6')")
    public ResponseEntity<?> deleteAll() {
        try {
            archivoRelacionadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
