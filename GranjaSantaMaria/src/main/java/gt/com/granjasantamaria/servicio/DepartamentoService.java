package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Departamento;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface DepartamentoService {

    // METODOS A IMPLEMENTAR
    public List<Departamento> listadoDepartamento();

    public void guardarDepartamento(Departamento departamento);

    public void eliminarDepartamento(Departamento departamento);

    public Departamento encontrarDepartamento(Departamento departamento);

    public void darBajaDepartamento(Departamento departamento);

}
