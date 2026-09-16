package com.logiexpress.quiz5.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.logiexpress.quiz5.business.PaqueteService;
import com.logiexpress.quiz5.domain.Paquete;
import com.logiexpress.quiz5.dto.PaqueteRequestDto;
import com.logiexpress.quiz5.dto.PaqueteResponseDto;


@RestController
@RequestMapping("/api/v1/paquetes")
@Tag(name = "Envios", description = "Endpoints para la gestion de paquetes")
@CrossOrigin(origins = "*")
public class PaqueteController {

    private final PaqueteService service;

    public PaqueteController(PaqueteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo paquete")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paquete creado"),
            @ApiResponse(responseCode = "400", description = "Error en validación")
    })
    public ResponseEntity<?> save(
            @Valid @RequestBody PaqueteRequestDto dto) {
        Paquete saved = service.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirRespuesta(saved));
    }

    @GetMapping("/obtener")
    @Operation(summary = "Obtener todos los paquetes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paquetes Obtenidos"),
            @ApiResponse(responseCode = "400", description = "Error")
    })
    public List<PaqueteResponseDto> obtenerTodos(){
        List<Paquete> paquetes = service.findAll();
        List<PaqueteResponseDto> list = new ArrayList<>();
         
        for(Paquete paqute : paquetes){
            list.add(convertirRespuesta(paqute));
        }
        return list;
    }
    
    
    private PaqueteResponseDto convertirRespuesta(Paquete paquete) {
        return new PaqueteResponseDto(paquete);
    }
}
