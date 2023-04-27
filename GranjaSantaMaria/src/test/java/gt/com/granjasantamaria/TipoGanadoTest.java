package gt.com.granjasantamaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import gt.com.granjasantamaria.dao.TipoGanadoDao;
import gt.com.granjasantamaria.modelo.TipoGanado;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gerso
 */
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TipoGanadoTest {

    //Inyeccion de TipoGanado
    @Autowired
    private TipoGanadoDao tipoGanadoDao;

    @Test
    public void testCrearTipoGanado() {
        TipoGanado crearTipoGanado = tipoGanadoDao.save(new TipoGanado("Toro razero"));
        assertThat(crearTipoGanado.getIdTipoGanado()).isGreaterThan(0);
    }
}
