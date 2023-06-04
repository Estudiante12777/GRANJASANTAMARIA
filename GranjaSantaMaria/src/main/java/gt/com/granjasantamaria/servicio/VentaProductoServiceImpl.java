package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

    @Autowired
    private VentaProductoDao ventaProductoDao;

    @Autowired
    private InventarioProductoDao inventarioProductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<VentaProducto> obtenerListadoVentaProductos() {
        return ventaProductoDao.findByEstadoVentaProductoIsTrue();
    }

    @Override
    @Transactional
    public void guardarVentaProducto(VentaProducto ventaProducto) {
        ventaProducto.setEstadoVentaProducto(true);
        // Obtener el inventario asociado a la venta       
        InventarioProducto inventario = ventaProducto.getInventarioProducto();
        // Restar la cantidad de producto vendido de la cantidad final del inventario
        inventario.setCantidadFinalProducto(inventario.getCantidadFinalProducto() - ventaProducto.getCantidadProducto());
        // Actualizar el inventario en la base de datos
        inventarioProductoDao.save(inventario);
        // Guardar la venta en la base de datos
        ventaProductoDao.save(ventaProducto);
    }

    @Override
    @Transactional
    public void eliminarVentaProducto(VentaProducto ventaProducto) {
        ventaProductoDao.delete(ventaProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public VentaProducto encontrarVentaProducto(VentaProducto ventaProducto) {
        return ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaVentaProducto(VentaProducto ventaProducto) {
        VentaProducto ventaProductoExistente = ventaProductoDao.findById(ventaProducto.getIdVentaProducto()).orElse(null);
        if (ventaProductoExistente != null) {
            ventaProductoExistente.setEstadoVentaProducto(false);
            ventaProductoDao.save(ventaProductoExistente);
        }
    }

}
