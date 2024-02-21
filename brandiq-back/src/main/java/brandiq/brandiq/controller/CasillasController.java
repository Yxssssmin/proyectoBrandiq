package brandiq.brandiq.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brandiq.brandiq.srv.CasillasService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class CasillasController {
    CasillasService casillasService;

    private CasillasController(CasillasService casillasService) {
        this.casillasService = casillasService;
    }    
}
