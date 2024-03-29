package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TPericolositaDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoPericolositaService;
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
public class TipoPericolositaController {

    @Autowired
    TipoPericolositaService tipoPericolositaService ;

    @Operation(description = "API per recuperare tipi pericolosita filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/pericolosita")
    public ResponseEntity<ResponseDto<List<TPericolositaDto>>> getTipiPericolosita(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TPericolositaDto> listResult = tipoPericolositaService.getTipoPericolositaByDataRif(dataRiferimento) ;
        ResponseDto<List<TPericolositaDto>> responseDto = ResponseDto.<List<TPericolositaDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/pericolosita/{idPericolosita}")
    public ResponseEntity<ResponseDto<List<TPericolositaDto>>> getTipiPericolositaByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Pericolosita' {errore.campo.obbligatorio}") List<String> idPericolosita,
            @RequestParam (required = false) String descrizionePericolosita,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        List<TPericolositaDto> tPericolositaDto = tipoPericolositaService.getPericolositaByIdAndDescrizioneAndData(idPericolosita, descrizionePericolosita, dataRiferimento) ;
        ResponseDto<List<TPericolositaDto>> responseDto = ResponseDto.<List<TPericolositaDto>>builder().code(HttpStatus.OK.value()).body(tPericolositaDto).build() ;

        return ResponseEntity.ok(responseDto);
    }

}
