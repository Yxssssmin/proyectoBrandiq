package brandiq.brandiq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandiq.brandiq.exception.ResourceNotFoundException;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.JugadorSalaDb;
import brandiq.brandiq.model.db.JugadorSalaEditDb;
import brandiq.brandiq.model.db.JugadorSalaNombreDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.JugadorInfo;
import brandiq.brandiq.model.dto.JugadorSalaEdit;
import brandiq.brandiq.model.dto.Mensaje;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroInfo;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.srv.JugadorSalaService;
import brandiq.brandiq.srv.JugadorService;
import brandiq.brandiq.srv.TableroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class TableroController {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private JugadorSalaService jugadorSalaService;

    private TableroService tableroService;

    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    // @PostMapping("/crear-tablero")
    // public TableroEdit crearTablero(@Valid @RequestBody TableroEdit tableroEdit, BindingResult bindingResult) {
    //     return tableroService.save(tableroEdit);
    // }

    @PostMapping("/crear-tablero")
    public ResponseEntity<?> crearTablero(@Valid @RequestBody TableroEdit tableroEdit, BindingResult validacion) {
        if (validacion.hasErrors()) {
			return ResponseEntity.badRequest().body("Error al crear TABLERO");
		} else {
            return ResponseEntity.ok(tableroService.addTableroEdit(tableroEdit));
        }
    }

    @GetMapping("/tableros")
    public ResponseEntity<List<TableroList>> getAllTableros() {
        List<TableroList> tablerosList = tableroService.findAllTableroList();
        System.out.println(tablerosList);
        if (!tablerosList.isEmpty()) {
            return ResponseEntity.ok(tablerosList);
        } else {
            return ResponseEntity.noContent().build(); // Codigo 204 si la lista esta vacia
        }
    }

    @PutMapping("/tablero/{id}")
    public ResponseEntity<TableroEdit> updateTableroEdit(@PathVariable(value = "id") int id,
            @Valid @RequestBody TableroEdit tableroEdit) throws RuntimeException {
        Optional<TableroEdit> tableroEditOriginal = tableroService.getTableroEditById(id);

        if (tableroEditOriginal.isPresent()) {
            Optional<TableroEdit> tableroEditModificado = tableroService.update(tableroEdit);

            return new ResponseEntity<>(tableroEditModificado.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("TABLERO_NOT_FOUND" + id);
        }
    }

    @GetMapping("/tablero/{id}/info")
    public ResponseEntity<TableroInfo> getTableroInfoById(@PathVariable(value = "id") Integer id){
        Optional<TableroInfo> tableroInfo = tableroService.getTableroInfoById(id);
        if (tableroInfo.isPresent()) 
            return ResponseEntity.ok().body(tableroInfo.get());
        else throw new ResourceNotFoundException("TABLERO NOT FOUND"+id);
    }

}
