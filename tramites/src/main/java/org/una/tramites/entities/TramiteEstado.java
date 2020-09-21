package org.una.tramites.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author acer
 */
@Entity
@Table(name = "tramites_estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteEstado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "estados_sucesores", length = 10)
    private String estadoSucesor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteEstado") 
    private List<TramiteCambioEstado> tramitesCambiosEstados= new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
       
    }

    @PreUpdate
    public void preUpdate() {
       
    }
    
}

