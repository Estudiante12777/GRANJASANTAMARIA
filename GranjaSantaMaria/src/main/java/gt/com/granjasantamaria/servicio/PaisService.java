package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Pais;
import java.util.List;

public interface PaisService {

     List<Pais> listadoPais();

     void guardarPais(Pais pais);

     void eliminarPais(Pais pais);

     Pais encontrarPais(Pais pais);

     void darBajaPais(Pais pais);

}
