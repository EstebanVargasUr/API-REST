package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/permisos_otorgados") 
@Api(tags = {"Permisos Otorgados"})
public class PermisoOtorgadoController {

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;


  /*  @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista del permiso otorgado, por medio del Id", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<PermisoOtorgado> permisoOtorgadoFound = permisoOtorgadoService.findById(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    @GetMapping("/usuario/{id}") 
    @ApiOperation(value = "Obtiene una lista con los permisos otorgados a partir del Id de un usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {

            Optional<List<PermisoOtorgado>> permisoOtorgadoFound = permisoOtorgadoService.findByUsuarioId(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con los permisos otorgados a partir del Id", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "id") Long id) {
        try {

            Optional<List<PermisoOtorgado>> permisoOtorgadoFound = permisoOtorgadoService.findByPermisoId(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/usuario/{id}{estado}") 
    @ApiOperation(value = "Obtiene una lista con los permisos otorgados a partir del Id del usuario y el estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    public ResponseEntity<?> findByUsuarioIdAndEstado(@PathVariable(value = "id") Long id,@PathVariable(value = "estado") boolean est) {
        try {

            Optional<List<PermisoOtorgado>> permisoOtorgadoFound = permisoOtorgadoService.findByUsuarioIdAndEstado(id,est);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}{estado}") 
    @ApiOperation(value = "Obtiene una lista con los permisos otorgados a partir del Id del permiso y el estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    public ResponseEntity<?> findByPermisoIdAndEstado(@PathVariable(value = "id") Long id,@PathVariable(value = "estado") boolean est) {
        try {

            Optional<List<PermisoOtorgado>> permisoOtorgadoFound = permisoOtorgadoService.findByPermisoIdAndEstado(id,est);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los permisos otorgados, entre las fechas especificadas", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos Otorgados")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<PermisoOtorgado>> permisoOtorgadoFound = permisoOtorgadoService.findByFechaRegistroBetween(FechIni,FechFin);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody PermisoOtorgado permisoOtorgado) {
        try {
            PermisoOtorgado permisoOtorgadoCreated = permisoOtorgadoService.create(permisoOtorgado);
            PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoCreated, PermisoOtorgadoDTO.class);
            return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 
    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un permiso otorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos Otorgados")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PermisoOtorgado permisoOtorgadoModified) {
        try {
            Optional<PermisoOtorgado> permisoOtorgadoUpdated = permisoOtorgadoService.update(permisoOtorgadoModified, id);
            if (permisoOtorgadoUpdated.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoUpdated.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
//TODO: Implementar este método
    }

    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
        return null;
 	//TODO: Implementar este método
    } 
}

