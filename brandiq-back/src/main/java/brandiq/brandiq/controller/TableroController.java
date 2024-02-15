package brandiq.brandiq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.JugadorInfo;
import brandiq.brandiq.model.dto.Mensaje;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.srv.JugadorService;
import brandiq.brandiq.srv.TableroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class TableroController {

    @Autowired
    private JugadorService jugadorService;

    private TableroService tableroService;

    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @PostMapping("/crear-tablero")
    public TableroEdit crearTablero(@Valid @RequestBody TableroEdit tableroEdit, BindingResult bindingResult) {
       return tableroService.save(tableroEdit);
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

}
