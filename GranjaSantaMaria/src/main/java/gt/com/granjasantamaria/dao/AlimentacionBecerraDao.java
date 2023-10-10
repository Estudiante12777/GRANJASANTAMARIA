package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.AlimentacionBecerra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlimentacionBecerraDao extends JpaRepository<AlimentacionBecerra, Long> {

}
