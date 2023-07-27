package gt.com.granjasantamaria.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "raza_ganado", schema = "granja_santa_maria", catalog = "")
public class RazaGanadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_raza_ganado")
    private int idRazaGanado;
    @Basic
    @Column(name = "nombre_raza_ganado")
    private String nombreRazaGanado;
    @Basic
    @Column(name = "estado_raza_ganado")
    private byte estadoRazaGanado;

    public int getIdRazaGanado() {
        return idRazaGanado;
    }

    public void setIdRazaGanado(int idRazaGanado) {
        this.idRazaGanado = idRazaGanado;
    }

    public String getNombreRazaGanado() {
        return nombreRazaGanado;
    }

    public void setNombreRazaGanado(String nombreRazaGanado) {
        this.nombreRazaGanado = nombreRazaGanado;
    }

    public byte getEstadoRazaGanado() {
        return estadoRazaGanado;
    }

    public void setEstadoRazaGanado(byte estadoRazaGanado) {
        this.estadoRazaGanado = estadoRazaGanado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RazaGanadoEntity that = (RazaGanadoEntity) o;
        return idRazaGanado == that.idRazaGanado && estadoRazaGanado == that.estadoRazaGanado && Objects.equals(nombreRazaGanado, that.nombreRazaGanado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRazaGanado, nombreRazaGanado, estadoRazaGanado);
    }
}
