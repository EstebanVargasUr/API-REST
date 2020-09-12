package org.una.tramites.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    
    @Column(name = "estado_sucesor", length = 10)
    private String estado_sucesor;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
       
    }

    @PreUpdate
    public void preUpdate() {
       
    }
    
}

