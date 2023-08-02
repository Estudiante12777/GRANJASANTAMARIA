package gt.com.granjasantamaria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class GranjaSantaMariaApplication {

    private static final Logger logger = LoggerFactory.getLogger(GranjaSantaMariaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GranjaSantaMariaApplication.class, args);

        // ABRIR EL NAVEGADOR CON LA URI DE LA APLICACION
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("http://localhost:8080"));
        } catch (Exception e) {
            logger.error("An error occurred while opening the browser.", e);
        }
    }

}
