package brandiq.brandiq.srv.impl;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.CasillasEditDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.repository.JugadorRepository;
import brandiq.brandiq.repository.JugadorSalaEditRepository;
import brandiq.brandiq.repository.CasillasRepository;
import brandiq.brandiq.repository.JugadorSalaRepository;
import brandiq.brandiq.repository.TableroEditRepository;
import brandiq.brandiq.repository.TableroRepository;
import brandiq.brandiq.srv.TableroService;
import brandiq.brandiq.srv.mapper.TableroMapper;
import lombok.NonNull;

@Service
public class TableroServiceImpl implements TableroService {
    private final TableroRepository tableroRepository;
    private final TableroEditRepository tableroEditRepository;
    private final JugadorSalaRepository jugadorSalaRepository;
    private final JugadorRepository jugadorRepository;
    private final CasillasRepository casillasRepository;

    public TableroServiceImpl(TableroRepository tableroRepository, TableroEditRepository tableroEditRepository,
            JugadorSalaRepository jugadorSalaRepository, JugadorSalaEditRepository jugadorSalaEditRepository,
            JugadorRepository jugadorRepository, CasillasRepository casillasRepository) {
        this.tableroRepository = tableroRepository;
        this.tableroEditRepository = tableroEditRepository;
        this.jugadorSalaRepository = jugadorSalaRepository;
        this.jugadorRepository = jugadorRepository;
        this.casillasRepository = casillasRepository;
    }

    @Override
    public List<TableroList> findAllTableroList() {
        List<TableroEditDb> tablerosEditDbs = tableroEditRepository.findAll();
        return TableroMapper.INSTANCE.tablerosEditDbToTablerosList(tablerosEditDbs);
    }

    @Override
    public TableroEdit save(TableroEdit tableroEdit) {
        return TableroMapper.INSTANCE.tableroEditDbToTableroEdit(
                tableroEditRepository.save(TableroMapper.INSTANCE.tableroEditToTableroEditDb(tableroEdit)));
    }

    @Override
    public Optional<TableroEdit> update(TableroEdit tableroEdit) {
        Optional<TableroEditDb> tableroEditDb = tableroEditRepository.findById(tableroEdit.getId());

        if (tableroEditDb.isPresent()) {
            TableroMapper.INSTANCE.updateTableroEditDbFromTableroEdit(tableroEdit, tableroEditDb.get());
            return Optional.of(
                    TableroMapper.INSTANCE.tableroEditDbToTableroEdit(tableroEditRepository.save(tableroEditDb.get())));
        } else {
            throw new ResourceNotFoundException("Tablero NOT FOUND: " + tableroEdit.getId_jugador());
        }

    }

    @Override
    public Optional<TableroEdit> getTableroEditById(int id) {
        Optional<TableroEditDb> tableroEditDb = tableroEditRepository.findById(id);
        if (tableroEditDb.isPresent())
            return Optional.of(TableroMapper.INSTANCE.tableroEditDbToTableroEdit(tableroEditDb.get()));
        else
            return Optional.empty();
    }

    public Optional<TableroInfo> getTableroInfoById(@NonNull Integer id) {
        Optional<TableroDb> tableroDb = tableroRepository.findById(id);
        if (tableroDb.isPresent()) {
            return Optional.of(TableroMapper.INSTANCE.tableroDbToTableroInfo(tableroDb.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public TableroEdit addTableroEdit(TableroEdit tableroEdit) {
        int cont = 0, x = -1, y = 0;
        String[] nombreImagenes = {
                "yahoo.png", "wolsvagen.jpeg", "whassasp.jpeg", "warnerbrothers.png", "victoriaSecret.png",
                "unicef.png", "toyota.jpeg", "tiktok.png", "starbucks.jpeg", "sportify.png",
                "snapchat.png", "skoda.png", "ryanair.jpeg", "rolex.png", "renault.jpeg",
                "reedit.png", "playstation.png", "pinterest.png", "pepsi.png", "paramount+.png",
                "opel.jpeg", "netflix.png", "nasa.jpeg", "msi.png", "monster.jpeg",
                "microsoft.png", "michelin.png", "Meta_Platforms.png", "mercedes.jpeg",
                "mcdonalds.jpg", "mastercard.jpeg", "maserati.png", "linkedin.png", "levi.jpg",
                "lacoste.png", "kfc.png", "instagram.jpeg", "ikea.jpeg", "iberia.jpeg",
                "hp.png", "hellokitty.png", "google.png", "ferrari.jpeg", "dreamworks.png",
                "correos.png", "cocochanel.png", "chatgpt.png", "calvinklein.png",
                "bosch.jpeg", "barcelona.jpeg", "bancosantander.png", "audi.jpeg", "apple.jpeg",
                "amazon.png", "allianz_seguros.png", "adobeillustrator.png", "adidas.png"
        };

        Set<Integer> numerosGenerados = new HashSet<>();
        TableroEditDb tableroDb = TableroMapper.INSTANCE.tableroEditToTableroEditDb(tableroEdit);
        TableroEditDb savedTablero = tableroEditRepository.save(tableroDb);

        tableroEdit = TableroMapper.INSTANCE.tableroEditDbToTableroEdit(savedTablero);

        JugadorSalaEditDb jugadorSalaEditDb = new JugadorSalaEditDb(null, tableroEdit.getId_jugador(),
                tableroEdit.getId(), 0, 0, 0, 0, 0, true);
        jugadorSalaRepository.save(jugadorSalaEditDb);

        for (int i = 0; i < 29; i++) {

            int indice = generarNumerosAleatoriosSinRepeticion(numerosGenerados);
            try {
                byte[] imagenSerializada = serializarImagen(nombreImagenes[indice]);

                if (cont < 8) {
                    x++;
                } else if (cont >= 8 && cont <= 14) {
                    y++;
                    x = 7;
                } else if (cont > 14 && cont < 22) {
                    x--;
                    y = 7;
                } else {
                    x = 0;
                    y--;
                }

                CasillasEditDb casillasEditDb = new CasillasEditDb(tableroEdit.getId(), imagenSerializada,
                        nombreImagenes[indice], x, y);

                cont++;
                casillasRepository.save(casillasEditDb);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return tableroEdit;
    }

    // @Override
    // public ResponseEntity<?> joinTablero(Integer idTablero, Integer idJugador) {
    // try {
    // TableroDb tableroEdit = tableroRepository.findById(idTablero)
    // .orElseThrow(() -> new IllegalArgumentException("Tablero no encontrado"));

    // JugadorSalaEditDb jugadorSalaEditDb =
    // jugadorSalaEditRepository.findById(idJugador)
    // .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));

    // // Lógica para unir al jugador al tablero (puedes modificar esto según tu
    // modelo de datos)
    // jugadorSalaEditDb.setId_tablero(idTablero);

    // // Guardar los cambios en el jugador
    // jugadorSalaRepository.save(jugadorSalaEditDb);

    // return ResponseEntity.ok("Jugador unido al tablero exitosamente");
    // } catch (IllegalArgumentException e) {
    // return ResponseEntity.badRequest().body(e.getMessage());
    // }
    // }

    @Override
    public ResponseEntity<?> joinTablero(Integer idTablero, String idJugador) {
        try {
            TableroDb tableroEdit = tableroRepository.findById(idTablero)
                    .orElseThrow(() -> new IllegalArgumentException("Tablero no encontrado"));

            JugadorDb jugadorDb = jugadorRepository.findById(idJugador)
                    .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));

            // Lógica para unir al jugador al tablero (puedes modificar esto según tu modelo
            // de datos)
            JugadorSalaEditDb jugadorSalaEditDb = new JugadorSalaEditDb(null, idJugador, idTablero, 0, 0, 0, 0, 0,
                    true);
            jugadorSalaEditDb.setId_tablero(idTablero);

            // Guardar los cambios en el jugador
            jugadorSalaRepository.save(jugadorSalaEditDb);

            // Crear un objeto JSON con el mensaje
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Jugador unido al tablero exitosamente");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Si hay una excepción, devolver un objeto JSON con el mensaje de error
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @Override
    public byte[] serializarImagen(String nombreImagen) {
        Path directorioImagen = Paths.get("src/main/resources/Logos/" + nombreImagen);
        String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            // Lee la imagen como un array de bytes
            byte[] imageData = Files.readAllBytes(Path.of(rutaAbsoluta));

            // Escribe los datos de la imagen al ObjectOutputStream
            oos.writeObject(imageData);
            oos.flush();

            // Devuelve el array de bytes resultante
            return bos.toByteArray();

        } catch (IOException e) {
            // Maneja la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int generarNumerosAleatoriosSinRepeticion(Set<Integer> numerosGenerados) {
        int rango = 29; // Rango de números permitidos (0 al 28)
        int numeroGenerado;

        do {
            numeroGenerado = (int) (Math.random() * rango);
        } while (numerosGenerados.contains(numeroGenerado));

        numerosGenerados.add(numeroGenerado);
        return numeroGenerado;
    }
    // @Override
    // public Long obtenerUltimoIdParaJugador(String id_jugador) {
    // return tableroEditRepository.findLastTableroIdByJugador(id_jugador);
    // }

   
}
