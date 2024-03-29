package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TMentoDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoMentoService;
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
public class TipoMentoController {

    @Autowired
    TipoMentoService tipoMentoService ;

    @Operation(description = "API per recuperare tipi mento filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/mento")
    public ResponseEntity<ResponseDto<List<TMentoDto>>> getTipiMento(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TMentoDto> listResult = tipoMentoService.getTipoMentoByDataRif(dataRiferimento) ;
        ResponseDto<List<TMentoDto>> responseDto = ResponseDto.<List<TMentoDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/mento/{idMento}/")
    public ResponseEntity<ResponseDto<TMentoDto>> getTipiMentoByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Mento' {errore.campo.obbligatorio}") String idMento,
            @RequestParam (required = false) String descrizioneMento,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TMentoDto tMentoDto = tipoMentoService.getMentoByIdAndDescrizioneAndData(idMento, descrizioneMento, dataRiferimento) ;

        ResponseDto<TMentoDto> responseDto = ResponseDto.<TMentoDto>builder().code(HttpStatus.OK.value()).body(tMentoDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
