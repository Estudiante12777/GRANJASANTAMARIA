package gt.com.granjasantamaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GranjaSantaMariaApplication {

    //private static final Logger logger = LoggerFactory.getLogger(GranjaSantaMariaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GranjaSantaMariaApplication.class, args);

        // ABRIR EL NAVEGADOR CON LA URI DE LA APLICACION
        /*
        //try {
        //    Runtime runtime = Runtime.getRuntime();
        //    runtime.exec("cmd /c start http://localhost:8080");
        //} catch (IOException e) {
        //    logger.error("An error occurred while opening the browser.", e);
        //}
        */
    }

}
