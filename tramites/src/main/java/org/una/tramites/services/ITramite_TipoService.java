/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Tramite_Tipo;

/**
 *
 * @author acer
 */
public interface ITramite_TipoService {
    public Optional<List<Tramite_Tipo>> findAll();

    public Optional<Tramite_Tipo> findById(Long id);

    public Optional<List<Tramite_Tipo>> findByEstado(boolean estado);
    
    public Optional<List<Tramite_Tipo>> findByFechaRegistroBetween(Date fechaRegitro,Date fechafin);

    public Optional<List<Tramite_Tipo>>  findByDepartamentoId(Long id);

    public Tramite_Tipo create(Tramite_Tipo tramite_tipo);

    public Optional<Tramite_Tipo> update(Tramite_Tipo tramite_tipo, Long id);

    public void delete(Long id);

    public void deleteAll();


    
}
