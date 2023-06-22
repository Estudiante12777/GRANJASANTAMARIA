package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Municipio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MunicipioDao extends JpaRepository<Municipio, Long> {

    List<Municipio> findByEstadoMunicipioIsTrue();

    Page<Municipio> findAllByEstadoMunicipioIsTrue(Pageable pageable);

    @Query("SELECT m FROM Municipio m WHERE m.departamento.idDepartamento = :idDepartamento")
    List<Municipio> obtenerMunicipiosPorDepartamento(@Param("idDepartamento") Long idDepartamento);

}
