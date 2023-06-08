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

    // En la clase VentaProductoServiceImpl
    @Override
    @Transactional
    public void guardarVentaProducto(VentaProducto ventaProducto) {
        ventaProducto.setEstadoVentaProducto(true);
        // Obtenemos el producto y la cantidad que se está vendiendo
        InventarioProducto producto = ventaProducto.getInventarioProducto();
        int cantidadVendida = ventaProducto.getCantidadProducto();
        // Buscamos el producto en el inventario
        InventarioProducto inventario = inventarioProductoDao.findByDetalleProductoAndEstadoInventarioProductoIsTrue(producto.getDetalleProducto());
        if (inventario != null) {
            // Restamos la cantidad vendida a la cantidad final del producto
            inventario.setCantidadFinalProducto(inventario.getCantidadFinalProducto() - cantidadVendida);
            // Sumamos la cantidad vendida a la cantidad salida y a la cantidad vendida hasta hoy del producto
            inventario.setCantidadSalidaProducto(inventario.getCantidadSalidaProducto() + cantidadVendida);
            inventario.setCantidadVendidaHastaHoy(inventario.getCantidadVendidaHastaHoy() + cantidadVendida);
            // Antes de guardar el inventario
            System.out.println("Cantidad final antes de la venta: " + inventario.getCantidadFinalProducto());
            System.out.println("Cantidad salida antes de la venta: " + inventario.getCantidadSalidaProducto());
            System.out.println("Cantidad vendida hasta hoy antes de la venta: " + inventario.getCantidadVendidaHastaHoy());
            // Actualizamos el producto en el inventario con los nuevos valores
            inventarioProductoDao.save(inventario);
            // Después de guardar el inventario
            System.out.println("Cantidad final después de la venta: " + inventario.getCantidadFinalProducto());
            System.out.println("Cantidad salida después de la venta: " + inventario.getCantidadSalidaProducto());
            System.out.println("Cantidad vendida hasta hoy después de la venta: " + inventario.getCantidadVendidaHastaHoy());
        }
        // Guardamos la venta
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
