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
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.services.IArchivoRelacionadoService;
import org.una.tramites.utils.MapperUtils;

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
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(archivoRelacionadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un archivo relacionado por su Id", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por el nombre", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByNombreAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/{estado}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por el estado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{tipo}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por el tipo", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "tipo") boolean tipo) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByTipo(tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por medio de la fecha", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fecha") Date fecha1, Date fecha2) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByFechaRegistroBetween(fecha1,fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{tramite}")
    @ApiOperation(value = "Obtiene una lista de archivos relacionados por medio del id de un tramite registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByTramiteRegistradoId(@PathVariable(value = "tramiteId") Long id) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByTramiteRegistradoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un archivo relacionado", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CREAR')")
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
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_MODIFICAR')")
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
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_ELIMINAR')")
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
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            archivoRelacionadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
