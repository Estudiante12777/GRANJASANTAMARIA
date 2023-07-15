package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.VentaProducto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaProductoDao extends JpaRepository<VentaProducto, Long> {

    List<VentaProducto> findByFechaVentaProductoAndEstadoVentaProductoIsTrue(LocalDate fechaActual);

    List<VentaProducto> findByFechaVentaProductoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Page<VentaProducto> findByFechaVentaProductoBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    @Query("SELECT v FROM VentaProducto v JOIN v.inventarioProducto i JOIN i.detalleProducto d " +
            "WHERE v.fechaVentaProducto BETWEEN :fechaInicio AND :fechaFin " +
            "AND (d.idDetalleProducto = :idDetalleProducto OR :idDetalleProducto IS NULL)")
    Page<VentaProducto> findByFechaVentaProductoAndDetalleProducto(@Param("fechaInicio") LocalDate fechaInicio,
                                                                   @Param("fechaFin") LocalDate fechaFin,
                                                                   @Param("idDetalleProducto") Long idDetalleProducto,
                                                                   Pageable pageable);

    @Query("SELECT v FROM VentaProducto v JOIN v.inventarioProducto i JOIN i.detalleProducto d " +
            "WHERE v.fechaVentaProducto BETWEEN :fechaInicio AND :fechaFin " +
            "AND (d.idDetalleProducto = :idDetalleProducto OR :idDetalleProducto IS NULL)")
    List<VentaProducto> findByFechaVentaProductoAndDetalleProducto(@Param("fechaInicio") LocalDate fechaInicio,
                                                                   @Param("fechaFin") LocalDate fechaFin,
                                                                   @Param("idDetalleProducto") Long idDetalleProducto);

    @Query("SELECT SUM(v.totalPrecioProducto) FROM VentaProducto v WHERE v.fechaVentaProducto = CURRENT_DATE ")
    Double obtenerTotalVentas();

}
