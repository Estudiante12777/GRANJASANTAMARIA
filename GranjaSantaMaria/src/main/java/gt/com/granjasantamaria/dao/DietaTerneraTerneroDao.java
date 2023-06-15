package gt.com.granjasantamaria.dao;

import gt.com.granjasantamaria.modelo.DietaTerneraTernero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaTerneraTerneroDao extends JpaRepository<DietaTerneraTernero, Long> {

    Page<DietaTerneraTernero> findAllByEstadoDietaTerneraTerneroIsTrue(Pageable pageable);

}
