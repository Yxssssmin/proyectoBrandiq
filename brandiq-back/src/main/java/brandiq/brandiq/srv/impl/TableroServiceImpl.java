package brandiq.brandiq.srv.impl;

import java.util.HashMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.CasillasEditDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.CasillasEdit;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.repository.JugadorRepository;
import brandiq.brandiq.repository.JugadorSalaEditRepository;
import brandiq.brandiq.repository.CasillasEditRepository;
import brandiq.brandiq.repository.CasillasRepository;
import brandiq.brandiq.repository.JugadorSalaRepository;
import brandiq.brandiq.repository.TableroEditRepository;
import brandiq.brandiq.repository.TableroRepository;

import brandiq.brandiq.srv.TableroService;
import brandiq.brandiq.srv.mapper.JugadorSalaMapper;
import brandiq.brandiq.srv.mapper.TableroMapper;
import lombok.NonNull;

@Service
public class TableroServiceImpl implements TableroService {

    private final TableroRepository tableroRepository;
    private final TableroEditRepository tableroEditRepository;
    private final JugadorSalaRepository jugadorSalaRepository;
    private final JugadorSalaEditRepository jugadorSalaEditRepository;
    private final JugadorRepository jugadorRepository;
    private final CasillasRepository casillasRepository;
    private final CasillasEditRepository casillasEditRepository;

    public TableroServiceImpl(TableroRepository tableroRepository, TableroEditRepository tableroEditRepository,
            JugadorSalaRepository jugadorSalaRepository, JugadorSalaEditRepository jugadorSalaEditRepository,
            JugadorRepository jugadorRepository, CasillasRepository casillasRepository,
            CasillasEditRepository casillasEditRepository) {
        this.tableroRepository = tableroRepository;
        this.tableroEditRepository = tableroEditRepository;
        this.jugadorSalaRepository = jugadorSalaRepository;
        this.jugadorRepository = jugadorRepository;
        this.casillasRepository = casillasRepository;
        this.casillasEditRepository = casillasEditRepository;
        this.jugadorSalaEditRepository = jugadorSalaEditRepository;
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
                "yahoo.png", "volkswagen.jpeg", "whatsapp.jpeg", "warnerbrothers.png", "victoriasecret.png",
                "unicef.png", "toyota.jpeg", "tiktok.png", "starbucks.jpeg", "spotify.png",
                "snapchat.png", "skoda.png", "ryanair.jpeg", "rolex.png", "renault.jpeg",
                "reddit.png", "playstation.png", "pinterest.png", "pepsi.png", "paramount.png",
                "opel.jpeg", "netflix.png", "nasa.jpeg", "msi.png", "monster.jpeg",
                "microsoft.png", "michelin.png", "meta.png", "mercedes.jpeg",
                "mcdonalds.jpg", "mastercard.jpeg", "maserati.png", "linkedin.png", "levis.jpg",
                "lacoste.png", "kfc.png", "instagram.jpeg", "ikea.jpeg", "iberia.jpeg",
                "hp.png", "hellokitty.png", "google.png", "ferrari.jpeg", "dreamworks.png",
                "correos.png", "cocochanel.png", "chatgpt.png", "calvinklein.png",
                "bosch.jpeg", "barcelona.jpeg", "santander.png", "audi.jpeg", "apple.jpeg",
                "amazon.png", "allianz.png", "illustrator.png", "adidas.png"
        };

        Set<Integer> numerosGenerados = new HashSet<>();
        TableroEditDb tableroDb = TableroMapper.INSTANCE.tableroEditToTableroEditDb(tableroEdit);
        TableroEditDb savedTablero = tableroEditRepository.save(tableroDb);

        tableroEdit = TableroMapper.INSTANCE.tableroEditDbToTableroEdit(savedTablero);

        JugadorSalaEditDb jugadorSalaEditDb = new JugadorSalaEditDb(null, tableroEdit.getId_jugador(),
                tableroEdit.getId(), 0, 0, 0, 0, 0, true);
        jugadorSalaRepository.save(jugadorSalaEditDb);

        for (int i = 0; i < 28; i++) {

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

                /* String[] nombreImagen = nombreImagenes[indice].split("\\."); */

                /*
                 * CasillasEditDb casillasEditDb = new CasillasEditDb(tableroEdit.getId(),
                 * imagenSerializada,
                 * nombreImagenes[indice], x, y);
                 */

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

    @Override
    public ResponseEntity<?> joinTablero(Integer idTablero, String idJugador) {
        try {
            JugadorSalaEditDb jugadorSalaEditDb = new JugadorSalaEditDb(null, idJugador, idTablero, 0, 0, 0, 0, 0,
                    false);

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
        Path directorioImagen = Paths.get("src/main/resources/static/Logos/" + nombreImagen);
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

    @Override
    public byte[] deserializarImagen(byte[] imagenSerializada) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imagenSerializada);
                ObjectInputStream ois = new ObjectInputStream(bis)) {

            // Lee el objeto serializado que contiene los bytes de la imagen
            return (byte[]) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            // Maneja la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String obtenerCasillasParaElTablero(String id_jugador, Integer id_tablero) {
        Optional<JugadorSalaEditDb> jugadorSalaEditDb = jugadorSalaEditRepository
                .findByIdJugadorAndIdTablero(id_jugador, id_tablero);

        Optional<CasillasEditDb> casillasEditDb = casillasEditRepository.findCasillasByPosicionAndTablero(
                jugadorSalaEditDb.get().getPosicionX(), jugadorSalaEditDb.get().getPosicionY(), id_tablero);

        byte[] imagenDeserializada = deserializarImagen(casillasEditDb.get().getImagen());
        String nombreImagen = casillasEditDb.get().getNombre();

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("imagenDeserializada", imagenDeserializada);
        resultado.put("nombreImagen", nombreImagen);

        return (String) resultado.get("nombreImagen");
    }

    @Override // ESTE METODO COMPARA EL NOMBRE INTRODUCIDO POR EL USUARIO Y ACTUALIZA LOS
    public String updateDatosJugador(String id_jugador, Integer id_tablero, String nombre) {
        Optional<JugadorSalaEditDb> jugadorSalaEditDb = jugadorSalaEditRepository
                .findByIdJugadorAndIdTablero(id_jugador, id_tablero);

        Optional<CasillasEditDb> casillasEditDb = casillasEditRepository.findCasillasByPosicionAndTablero(
                jugadorSalaEditDb.get().getPosicionX(), jugadorSalaEditDb.get().getPosicionY(), id_tablero);

        if (casillasEditDb.isPresent()) {

            JugadorSalaEdit jugadorSalaEditar = JugadorSalaMapper.INSTANCE
                    .jugadorSalaEditDbToJugadorSalaEdit(jugadorSalaEditDb.get());

            String[] nombreImagenDb = casillasEditDb.get().getNombre().split("\\.");

            System.out.println(nombre);
            if (nombreImagenDb[0].equals(nombre)) {

                jugadorSalaEditar.setPuntos(jugadorSalaEditar.getPuntos() + 10);
                jugadorSalaEditar.setAciertos(jugadorSalaEditar.getAciertos() + 1);
                jugadorSalaEditar.setTurno(false);
                Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
                        jugadorSalaEditRepository
                                .save(JugadorSalaMapper.INSTANCE
                                        .jugadorSalaEditToJugadorSalaEditDb(jugadorSalaEditar))));

                return (String) "Acierto";
            } else {
                jugadorSalaEditar.setFallos(jugadorSalaEditar.getFallos() + 1);
                jugadorSalaEditar.setTurno(false);
                Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
                        jugadorSalaEditRepository
                                .save(JugadorSalaMapper.INSTANCE
                                        .jugadorSalaEditToJugadorSalaEditDb(jugadorSalaEditar))));
            }
        }

        return (String) "Fallo";
    }

    @Override
    public String cambiarTurno(String id_jugador, Integer id_tablero) {

        Optional<JugadorSalaEditDb> jugadorSalaEditDb = jugadorSalaEditRepository
                .findByIdJugadorAndIdTablero(id_jugador, id_tablero);

        if (jugadorSalaEditDb.isPresent()) {
            JugadorSalaEdit jugadorSalaEdit = JugadorSalaMapper.INSTANCE
                    .jugadorSalaEditDbToJugadorSalaEdit(jugadorSalaEditDb.get());

            jugadorSalaEdit.setTurno(true);
            Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
                    jugadorSalaEditRepository
                            .save(JugadorSalaMapper.INSTANCE
                                    .jugadorSalaEditToJugadorSalaEditDb(jugadorSalaEdit))));
            return "Turno cambiado";
        }

        return "Error";
    }

}
