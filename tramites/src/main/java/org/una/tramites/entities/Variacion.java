package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Esteban Vargas
 */
@Entity
@Table(name = "variaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Variacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean grupo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column
    private boolean estado;
      
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @ManyToOne 
    @JoinColumn(name="tramites_tipos_id")
    private TramiteTipo tramiteTipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variacion") 
    private List<Requisito> requisitos= new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date();
    }

    @PreUpdate
    public void preUpdate() {
    }
}
