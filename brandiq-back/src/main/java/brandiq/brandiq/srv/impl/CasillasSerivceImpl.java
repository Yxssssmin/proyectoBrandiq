package brandiq.brandiq.srv.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;


import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.repository.CasillasRepository;
import brandiq.brandiq.repository.TableroRepository;
import brandiq.brandiq.srv.CasillasService;
import brandiq.brandiq.srv.mapper.CasillasMapper;


@Service
public class CasillasSerivceImpl implements CasillasService{

    private  final CasillasRepository casillasRepository;
    private final TableroRepository tableroRepository;
    
    private CasillasMapper casillasMapper;

    public CasillasSerivceImpl(CasillasRepository casillasRepository, TableroRepository tableroRepository) {
        this.casillasRepository = casillasRepository;
        this.tableroRepository=tableroRepository;
    }

    
    @Override
    public CasillasEdit save(CasillasEdit casillaseEdit) {
        return CasillasMapper.INSTANCE.cassillasEditDbToCasillasEdit(casillasRepository.save(casillasMapper.INSTANCE.casillasEditToCasillasEditDb(casillaseEdit)));
    } 
    
    @Override
    public CasillasDb crearAllCasillasDb(Integer id_tablero) throws IOException {
        String imgPath = "../../../resources/img/";
    
        int numCasillas=20;
    
        // Obtener la lista de archivos en el directorio
        File directory = new File(imgPath);
        File[] files = directory.listFiles();

        // Si no hay imágenes disponibles en el directorio, salir del método
        if (files == null || files.length == 0) {
            return null;
        }

        // Lista para mantener un registro de las imágenes seleccionadas
        List<File> selectedImages = new ArrayList<>();

        // Obtener el número de imágenes a seleccionar, limitado por el número de casillas
        int numImagesToSelect = Math.min(numCasillas, files.length);

        // Intentar seleccionar imágenes hasta alcanzar el límite o no haya más imágenes disponibles
        for (int i = 0; i < numImagesToSelect; i++) {
            // Seleccionar una imagen aleatoria del directorio que no haya sido seleccionada previamente
            File randomImage;
            byte[] fileContent;

            do {
                randomImage = selectRandomImage(files);
            } while (selectedImages.contains(randomImage));

            selectedImages.add(randomImage);

            try {
                fileContent = Files.readAllBytes(randomImage.toPath());
            
                // Convertir los bytes en una cadena Base64
                String base64Image = Base64.getEncoder().encodeToString(fileContent);

                // Calcular posX y posY
                int posicionX = i % 8;
                int posicionY = i / 8;

                // Crear una instancia de CasillasEdit con la ruta de la imagen seleccionada
                CasillasEdit casillasEdit = new CasillasEdit(id_tablero, base64Image, randomImage.getName().replace(".png", ""), posicionX, posicionY);

                save(casillasEdit);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Método para seleccionar una imagen aleatoria del directorio excluyendo las imágenes ya seleccionadas
    private File selectRandomImage(File[] files) throws IOException {
        // Lista para almacenar archivos de imagen válidos que no han sido seleccionados
        List<File> availableImages = new ArrayList<>();

        // Filtrar los archivos para incluir solo imágenes (PNG, JPEG, JPG)
        for (File file : files) {
            if (file.isFile() && isValidImageFile(file)) {
                availableImages.add(file);
            }
        }

        // Seleccionar aleatoriamente una imagen de la lista de imágenes disponibles
        if (!availableImages.isEmpty()) {
            Random random = new Random();
            return availableImages.get(random.nextInt(availableImages.size()));
        } else {
            throw new IOException("No hay imágenes disponibles en el directorio.");
        }
    }

    // Método para verificar si un archivo es una imagen válida (PNG, JPEG, JPG)
    private boolean isValidImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".jpg");
    }


}
