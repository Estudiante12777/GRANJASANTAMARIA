package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.Departamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoDao extends JpaRepository<Departamento, Long> {

    Page<Departamento> findAllByEstadoDepartamentoIsTrue(Pageable pageable);

    List<Departamento> findByEstadoDepartamentoIsTrue();

}
