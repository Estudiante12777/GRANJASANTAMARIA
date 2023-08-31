package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlimentacionBecerraDao extends JpaRepository<AlimentacionBecerra, Long> {

    @Query(value = "SELECT ab.id_alimentacion_becerra, ab.fecha_alimentacion_becerra, " +
            "gh.nombre_ganado_hembra, ab.cantidad_maniana_alimentacion, " +
            "ab.cantidad_tarde_alimentacion, ab.total_alimentacion_becerra, " +
            "rm.nombre_ganado_hembra AS nombre_madre, ab.estado_alimentacion_becerra " +
            "FROM alimentacion_becerra ab " +
            "INNER JOIN produccion_diaria_leche pdl ON ab.id_produccion_diaria_leche = pdl.id_produccion_diaria_leche " +
            "INNER JOIN ganado_hembra gh ON ab.id_becerra = gh.id_ganado_hembra " +
            "LEFT JOIN relacion_madre_becerra rmb ON gh.id_ganado_hembra = rmb.id_becerra " +
            "LEFT JOIN ganado_hembra rm ON rmb.id_madre = rm.id_ganado_hembra " +
            "WHERE ab.estado_alimentacion_becerra = true",
            nativeQuery = true)
    Page<Object[]> obtenerDatosAlimentacionConRelaciones(Pageable pageable);

    @Modifying
    @Query("UPDATE AlimentacionBecerra a SET a.estadoAlimentacionBecerra = false WHERE a.idAlimentacionBecerra = :id")
    void darDeBajaPorId(@Param("id") Long id);

}
