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
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;
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
     
    @GetMapping() 
     
    @ApiOperation(value = "Obtiene una lista de todos los Archivos Relacionados", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ArchivoRelacionadoDTO>> result = archivoRelacionadoService.findAll();
            if (result.isPresent()) {
                List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista de los Archivos Relacionados por medio del titulo", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ArchivoRelacionadoDTO>> result = archivoRelacionadoService.findByNombreAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista de los Archivos Relacionados por estado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @ResponseBody
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado){
        try {
            Optional<List<ArchivoRelacionadoDTO>> result = archivoRelacionadoService.findByEstado(estado);
            if (result.isPresent()) {
                List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{tipo}") 
    @ApiOperation(value = "Obtiene una lista de los Archivos Relacionados por tipo", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @ResponseBody
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "tipo") boolean tipo){
        try {
            Optional<List<ArchivoRelacionadoDTO>> result = archivoRelacionadoService.findByTipo(tipo);
            if (result.isPresent()) {
                List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los Archivos Relacionados, entre las fechas especificadas", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<ArchivoRelacionadoDTO>> archivoRelacionadoFound = archivoRelacionadoService.findByFechaRegistroBetween(FechIni,FechFin);
            if (archivoRelacionadoFound.isPresent()) {
                ArchivoRelacionadoDTO archivoRelacionadoDto = MapperUtils.DtoFromEntity(archivoRelacionadoFound.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tramiteRegistrado/{id}")
    @ApiOperation(value = "Obtiene una lista con los Archivos Relacionados por Tramite Registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findByTramiteRegistradoId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<ArchivoRelacionadoDTO>> result = archivoRelacionadoService.findByTramiteRegistradoId(term);
            if (result.isPresent()) {
                List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Archivo Relacionado", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CREAR')")
    public ResponseEntity<?> create(@RequestBody ArchivoRelacionadoDTO archivoRelacionado) {
        try {
            ArchivoRelacionadoDTO archivoRelacionadoCreated = archivoRelacionadoService.create(archivoRelacionado);
            ArchivoRelacionadoDTO archivoRelacionadoDto = MapperUtils.DtoFromEntity(archivoRelacionadoCreated, ArchivoRelacionadoDTO.class);
            return new ResponseEntity<>(archivoRelacionadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un Archivo Relacionado a partir de su Id", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ArchivoRelacionadoDTO archivoRelacionadoModified) {
        try {
            Optional<ArchivoRelacionadoDTO> archivoRelacionadoUpdated = archivoRelacionadoService.update(archivoRelacionadoModified, id);
            if (archivoRelacionadoUpdated.isPresent()) {
                ArchivoRelacionadoDTO parametroGeneralDto = MapperUtils.DtoFromEntity(archivoRelacionadoUpdated.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(parametroGeneralDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
//TODO: Implementar este método
    }

    @DeleteMapping("/") 
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        return null;
 	//TODO: Implementar este método
    } 
}
