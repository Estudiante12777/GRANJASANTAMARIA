package gt.com.granjasantamaria.util;

import gt.com.granjasantamaria.modelo.GanadoHembra;
import gt.com.granjasantamaria.modelo.GanadoMacho;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImagenGanado {

    public static void guardarImagenAndActualizarObjetos(MultipartFile file, Object objeto) throws IOException {
        String jarDirectory = System.getProperty("user.dir");
        String imagesDirectory = jarDirectory + "/images/";
        Path imagesPath = Paths.get(imagesDirectory);
        if (!Files.exists(imagesPath)) {
            Files.createDirectories(imagesPath);
        }
        Path fileNameAndPath = Paths.get(imagesDirectory, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        if (objeto instanceof GanadoMacho) {
            ((GanadoMacho) objeto).setFotografia(file.getOriginalFilename());
        } else if (objeto instanceof GanadoHembra) {
            ((GanadoHembra) objeto).setFotografia(file.getOriginalFilename());
        }
    }

}
