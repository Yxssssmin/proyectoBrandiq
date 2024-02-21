package brandiq.brandiq.srv.impl;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.JugadorSalaInfo;
import brandiq.brandiq.repository.JugadorSalaEditRepository;
import brandiq.brandiq.repository.JugadorSalaRepository;
import brandiq.brandiq.srv.JugadorSalaService;
import brandiq.brandiq.srv.mapper.JugadorMapper;
import brandiq.brandiq.srv.mapper.JugadorSalaMapper;

@Service
public class JugadorSalaServiceImpl implements JugadorSalaService {

    private final JugadorSalaRepository jugadorSalaRepository;
    private final JugadorSalaEditRepository jugadorSalaEditRepository;

    public JugadorSalaServiceImpl(JugadorSalaRepository jugadorSalaRepository,
            JugadorSalaEditRepository jugadorSalaEditRepository) {
        this.jugadorSalaRepository = jugadorSalaRepository;
        this.jugadorSalaEditRepository = jugadorSalaEditRepository;
    }

    @Override
    public Optional<JugadorSalaInfo> getJugadorSalaInfoById(Integer id) {
        Optional<JugadorSalaDb> jugadorSalaDb = jugadorSalaRepository.findById(id);

        if (jugadorSalaDb.isPresent()) {
            return Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaDbToJugadorSalaInfo(jugadorSalaDb.get()));
        }

        return Optional.empty();
    }

    @Override
    public void save(JugadorSalaDb jugadorSalaDb) {
        jugadorSalaRepository.save(jugadorSalaDb);
    }

    @Override
    public JugadorSalaEdit save(JugadorSalaEdit jugadorSalaEdit) {
        return JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
                jugadorSalaEditRepository
                        .save(JugadorSalaMapper.INSTANCE.jugadorSalaEditToJugadorSalaEditDb(jugadorSalaEdit)));
    }

    @Override
    public Optional<JugadorSalaEdit> getJugadorSalaEditById(Integer id) {
        Optional<JugadorSalaDb> jugadorSalaDb = jugadorSalaRepository.findById(id);

        if (jugadorSalaDb.isPresent()) {
            return Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaDbToJugadorSalaEdit(jugadorSalaDb.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<JugadorSalaEdit> update(JugadorSalaEdit jugadorSalaEdit) {

        throw new UnsupportedOperationException("Unimplemented method 'updatePosiciones'");
    }

    /*
     * @Override
     * public Optional<JugadorSalaEdit> updatePosicionJugador(String id_jugador,
     * Integer id_tablero) {
     * int[][] movimientos = {
     * { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0
     * },
     * { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, { 6, 7
     * },
     * { 5, 7 }, { 4, 7 }, { 3, 7 }, { 2, 7 }, { 1, 7 }, { 0, 7 }, { 0, 6 }, { 0, 5
     * },
     * { 0, 4 }, { 0, 3 }, { 0, 2 }, { 0, 1 }
     * };
     * 
     * int posicionActual = 0;
     * 
     * Optional<JugadorSalaEditDb> jugadorSalaEditDb = jugadorSalaEditRepository
     * .findByIdJugadorAndIdTablero(id_jugador, id_tablero);
     * System.out.println(jugadorSalaEditDb);
     * 
     * if (jugadorSalaEditDb.isPresent()) {
     * 
     * JugadorSalaEdit jugadorSalaEditar = JugadorSalaMapper.INSTANCE
     * .jugadorSalaEditDbToJugadorSalaEdit(jugadorSalaEditDb.get());
     * 
     * System.out.println(jugadorSalaEditar);
     * //
     * JugadorSalaMapper.INSTANCE.updateJugadorSalaEditDbFromJugadorSalaEdit(null,
     * // null);
     * 
     * int posicionX = jugadorSalaEditDb.get().getPosicionX();
     * int posicionY = jugadorSalaEditDb.get().getPosicionY();
     * 
     * int[] posiciones = { posicionX, posicionY };
     * 
     * int dado = tirarDado();
     * System.out.println(dado);
     * for (int i = 0; i < movimientos.length; i++) {
     * if (Arrays.equals(movimientos[i], posiciones)) {
     * System.out.println(movimientos[i].toString());
     * posicionActual = i;
     * break;
     * }
     * }
     * 
     * int nuevaPosicion = posicionActual + dado;
     * 
     * int[][] posicionesNuevas = { movimientos[nuevaPosicion] };
     * System.out.println(posicionesNuevas);
     * 
     * return
     * Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
     * jugadorSalaEditDb));
     * }
     * return Optional.empty();
     * }
     */

    @Override
    public int updatePosicionJugador(String id_jugador, Integer id_tablero) {
        int[][] movimientos = {
                { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 },
                { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, { 6, 7 },
                { 5, 7 }, { 4, 7 }, { 3, 7 }, { 2, 7 }, { 1, 7 }, { 0, 7 }, { 0, 6 }, { 0, 5 },
                { 0, 4 }, { 0, 3 }, { 0, 2 }, { 0, 1 }
        };

        int posicionActual = 0;

        Optional<JugadorSalaEditDb> jugadorSalaEditDb = jugadorSalaEditRepository
                .findByIdJugadorAndIdTablero(id_jugador, id_tablero);
        System.out.println(jugadorSalaEditDb);

        if (jugadorSalaEditDb.isPresent()) {

            JugadorSalaEdit jugadorSalaEditar = JugadorSalaMapper.INSTANCE
                    .jugadorSalaEditDbToJugadorSalaEdit(jugadorSalaEditDb.get());

            System.out.println(jugadorSalaEditar);

            int posicionX = jugadorSalaEditar.getPosicionX();
            int posicionY = jugadorSalaEditar.getPosicionY();

            int[] posiciones = { posicionX, posicionY };

            int dado = tirarDado();
            System.out.println(dado);
            for (int i = 0; i < movimientos.length; i++) {
                if (Arrays.equals(movimientos[i], posiciones)) {
                    System.out.println(Arrays.toString(movimientos[i]));
                    posicionActual = i;
                    break;
                }
            }

            int nuevaPosicion = posicionActual + dado;

            int[][] posicionesNuevas = { movimientos[nuevaPosicion] };
            jugadorSalaEditar.setPosicionX(posicionesNuevas[0][0]);
            jugadorSalaEditar.setPosicionY(posicionesNuevas[0][1]);

            // deepToString muestra el contenido de una matriz
            /* System.out.println(Arrays.deepToString(posicionesNuevas)); */

            Optional.of(JugadorSalaMapper.INSTANCE.jugadorSalaEditDbToJugadorSalaEdit(
                    jugadorSalaEditRepository
                            .save(JugadorSalaMapper.INSTANCE.jugadorSalaEditToJugadorSalaEditDb(jugadorSalaEditar))));
            return dado;
        }
        return 0;
    }

    @Override
    public int tirarDado() {
        Random random = new Random();
        // La expresión (6 - 1 + 1) genera un número aleatorio entre 1 y 6 (ambos
        // incluidos)
        int numeroAleatorio = random.nextInt(6 - 1 + 1) + 1;
        return numeroAleatorio;
    }

    @Override
    public Optional<JugadorSalaEditDb> findByIdJugadorAndIdTablero(String id_jugador, Integer id_tablero) {
        return jugadorSalaEditRepository.findByIdJugadorAndIdTablero(id_jugador, id_tablero);
    }

}
