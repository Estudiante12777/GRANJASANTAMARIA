package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Municipio")
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMunicipio;

    @NotNull
    @Column(name = "nombre_municipio", nullable = false)
    private String nombreMunicipio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @NotNull
    @Column(name = "estado_municipio", nullable = false)
    private boolean estadoMunicipio;

}
