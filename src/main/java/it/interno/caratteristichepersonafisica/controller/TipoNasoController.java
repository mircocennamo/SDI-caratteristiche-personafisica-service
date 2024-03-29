package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TNasoDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoNasoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "tipo")
public class TipoNasoController {

    @Autowired
    TipoNasoService tipoNasoService ;

    @Operation(description = "API per recuperare tipi naso filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/naso")
    public ResponseEntity<ResponseDto<List<TNasoDto>>> getTipiNaso(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TNasoDto> listResult = tipoNasoService.getTipoNasoByDataRif(dataRiferimento) ;
        ResponseDto<List<TNasoDto>> responseDto = ResponseDto.<List<TNasoDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/naso/{idNaso}/")
    public ResponseEntity<ResponseDto<TNasoDto>> getTipiNasoByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Naso' {errore.campo.obbligatorio}") String idNaso,
            @RequestParam (required = false) String descrizioneNaso,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TNasoDto tNasoDto = tipoNasoService.getNasoByIdAndDescrizioneAndData(idNaso, descrizioneNaso, dataRiferimento) ;

        ResponseDto<TNasoDto> responseDto = ResponseDto.<TNasoDto>builder().code(HttpStatus.OK.value()).body(tNasoDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
