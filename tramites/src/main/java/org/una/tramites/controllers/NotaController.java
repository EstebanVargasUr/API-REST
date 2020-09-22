package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.services.INotaService;
import org.una.tramites.utils.MapperUtils;

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
     
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todas las Notas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<NotaDTO>> result = notaService.findAll();
            if (result.isPresent()) {
                List<NotaDTO> notasDTO = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(notasDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista de Nota por medio del titulo", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findByTituloAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<NotaDTO>> result = notaService.findByTituloAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<NotaDTO> notasDTO = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(notasDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista de las Notas por estado", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @ResponseBody
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado){
        try {
            Optional<List<NotaDTO>> result = notaService.findByEstado(estado);
            if (result.isPresent()) {
                List<NotaDTO> notaDTO = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{tipo}") 
    @ApiOperation(value = "Obtiene una lista de las Notas por tipo", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @ResponseBody
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "tipo") boolean tipo){
        try {
            Optional<List<NotaDTO>> result = notaService.findByTipo(tipo);
            if (result.isPresent()) {
                List<NotaDTO> notaDTO = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con las Notas, entre las fechas especificadas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<NotaDTO>> notaFound = notaService.findByFechaRegistroBetween(FechIni,FechFin);
            if (notaFound.isPresent()) {
                NotaDTO notaDto = MapperUtils.DtoFromEntity(notaFound.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista con las Notas por Tramite Registrado", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findByTramiteRegistradoId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<NotaDTO>> result = notaService.findByTramiteRegistradoId(term);
            if (result.isPresent()) {
                List<NotaDTO> notaDTO = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    @ApiOperation(value = "Permite crear un Nota", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CREAR')")
    public ResponseEntity<?> create(@RequestBody NotaDTO nota) {
        try {
            NotaDTO notaCreated = notaService.create(nota);
            NotaDTO notaDto = MapperUtils.DtoFromEntity(notaCreated, NotaDTO.class);
            return new ResponseEntity<>(notaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar una Nota a partir de su Id", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody NotaDTO notaModified) {
        try {
            Optional<NotaDTO> notaUpdated = notaService.update(notaModified, id);
            if (notaUpdated.isPresent()) {
                NotaDTO notaDto = MapperUtils.DtoFromEntity(notaUpdated.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    @PreAuthorize("hasAuthority('NOTA_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
//TODO: Implementar este método
    }

    @DeleteMapping("/") 
    @PreAuthorize("hasAuthority('NOTA_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        return null;
 	//TODO: Implementar este método
    } 
}
