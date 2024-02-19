package brandiq.brandiq.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import brandiq.brandiq.model.db.CasillasDb;
import brandiq.brandiq.model.db.CasillasEdit;
import brandiq.brandiq.model.db.JugadorDb;
import brandiq.brandiq.model.db.TableroDb;
import brandiq.brandiq.model.db.TableroEditDb;
import brandiq.brandiq.model.dto.CasillasInfo;
import brandiq.brandiq.model.dto.CasillasList;
import brandiq.brandiq.model.dto.JugadorInfo;
import brandiq.brandiq.model.dto.Mensaje;
import brandiq.brandiq.model.dto.TableroEdit;
import brandiq.brandiq.model.dto.TableroList;
import brandiq.brandiq.srv.CasillasService;
import brandiq.brandiq.srv.JugadorService;
import brandiq.brandiq.srv.TableroService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class TableroController {

    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private TableroService tableroService;
    
    @Autowired
    private CasillasService casillasService;


    public TableroController(){

    }

    public TableroController(TableroService tableroService,CasillasService casillasService) {
        this.tableroService = tableroService;
        this.casillasService=casillasService;
    }
   
    @PostMapping("/crear-tablero")
    public TableroEdit crearTablero(@Valid @RequestBody TableroEdit tableroEdit, BindingResult bindingResult) throws IOException {

        casillasService.crearAllCasillasDb(tableroEdit.getId());
            
        return tableroEdit;
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
