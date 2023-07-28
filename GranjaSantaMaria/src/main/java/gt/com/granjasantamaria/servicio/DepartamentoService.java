package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartamentoService {

    List<Departamento> listadoDepartamento();

    Page<Departamento> listadoDepartamentoPaginado(Pageable pageable);

    void guardarDepartamento(Departamento departamento);

    Departamento encontrarDepartamento(Departamento departamento);

    void darBajaDepartamento(Departamento departamento);

}
