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
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.services.INotaService;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/notas") 
@Api(tags = {"Notas"})
public class NotaController {
    
    @Autowired
    private INotaService notaService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";
     
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las Notas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOT4')")
    public ResponseEntity<?> findAll() {
        try {
                return new ResponseEntity(notaService.findAll(), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/titulo/{term}")
    @ApiOperation(value = "Obtiene una lista de Notas por medio del titulo", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOT3')")
    public ResponseEntity<?> findByTituloAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
             return new ResponseEntity(notaService.findByTituloAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{termino}") 
    @ApiOperation(value = "Obtiene una lista de las Notas por estado", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOT3')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "termino") boolean estado){
        try {
             return new ResponseEntity(notaService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo/{termino}") 
    @ApiOperation(value = "Obtiene una lista de las Notas por tipo", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOT3')")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "termino") boolean tipo){
        try {
            return new ResponseEntity(notaService.findByTipo(tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("fecha/{termino}") 
    @ApiOperation(value = "Obtiene una lista con las Notas, entre las fechas especificadas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOT3')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "termino") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {
            return new ResponseEntity(notaService.findByFechaRegistroBetween(FechIni, FechFin), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tramiteRegistrado/id/{term}")
    @ApiOperation(value = "Obtiene una lista con las Notas por Tramite Registrado", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOT3')")
    public ResponseEntity<?> findByTramiteRegistradoId(@PathVariable(value = "term") long term) {
        try {
             return new ResponseEntity(notaService.findByTramiteRegistradoId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un Nota", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOT1')")
        public ResponseEntity<?> create(@Valid @RequestBody NotaDTO notaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
        try {
                return new ResponseEntity(notaService.create(notaDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
        }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar una Nota a partir de su Id", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOT2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody NotaDTO notaDTO, BindingResult bindingResult) {
    if (!bindingResult.hasErrors()) {
        try {
            Optional<NotaDTO> notaUpdated = notaService.update(notaDTO, id);
            if (notaUpdated.isPresent()) {
                return new ResponseEntity(notaUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    else{
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}") 
    @ApiOperation(value = "Permite eliminar una Nota a partir de su Id", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOT5')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
        notaService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/") 
    @ApiOperation(value = "Permite eliminar todos los Nota", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOT6')")
    public ResponseEntity<?> deleteAll() {
        try {
            notaService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    } 
