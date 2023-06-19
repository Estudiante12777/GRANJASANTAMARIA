package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartamentoService {

    // METODOS A IMPLEMENTAR
    public List<Departamento> listadoDepartamento();

    public Page<Departamento> listadoDepartamentoPaginado(Pageable pageable);

    public void guardarDepartamento(Departamento departamento);

    public void eliminarDepartamento(Departamento departamento);

    public Departamento encontrarDepartamento(Departamento departamento);

    public void darBajaDepartamento(Departamento departamento);

}
