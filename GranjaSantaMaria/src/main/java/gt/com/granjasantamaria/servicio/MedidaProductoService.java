package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.MedidaProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedidaProductoService {

    public List<MedidaProducto> obtenerListadoMedidaProductos();

    public Page<MedidaProducto> obtenerListadoMedidaProductoPaginado(Pageable pageable);

    public void guardarMedidaProducto(MedidaProducto medidaProducto);

    public void eliminarMedidaProducto(MedidaProducto medidaProducto);

    public MedidaProducto encontrarMedidaProducto(MedidaProducto medidaProducto);

    public void darBajaMedidaProducto(MedidaProducto medidaProducto);

}
