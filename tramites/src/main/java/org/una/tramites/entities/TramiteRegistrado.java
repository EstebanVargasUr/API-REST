package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author adria
 */
@Entity
@Table(name = "tramites_registrados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteRegistrado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="tramites_tipos_id")
    private TramiteTipo tramiteTipo;
    
    @ManyToOne 
    @JoinColumn(name="clientes_id")
    private Cliente cliente;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistrado") 
    private List<Nota> notas= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistrado") 
    private List<ArchivoRelacionado> archivosRelacionados= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistrado") 
    private List<RequisitoPresentado> requisitosPresentados= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistrado") 
    private List<TramiteCambioEstado> tramiteCambiosEstados= new ArrayList<>();
    
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        
    }

    @PreUpdate
    public void preUpdate() {

    }
    
}