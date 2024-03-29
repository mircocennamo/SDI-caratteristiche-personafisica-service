package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TDentiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoDentiService;
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
public class TipoDentiController {

    @Autowired
    TipoDentiService tipoDentiService ;

    @Operation(description = "API per recuperare tipi denti filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/denti")
    public ResponseEntity<ResponseDto<List<TDentiDto>>> getTipiDenti(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TDentiDto> listResult = tipoDentiService.getTipoDentiByDataRif(dataRiferimento) ;
        ResponseDto<List<TDentiDto>> responseDto = ResponseDto.<List<TDentiDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo dente per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/denti/{idDente}/")
    public ResponseEntity<ResponseDto<TDentiDto>> getTipiDentiByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Denti' {errore.campo.obbligatorio}") String idDente,
            @RequestParam (required = false) String descrizioneDente,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TDentiDto tDentiDto = tipoDentiService.getDentiByIdAndDescrizioneAndData(idDente, descrizioneDente, dataRiferimento) ;

        ResponseDto<TDentiDto> responseDto = ResponseDto.<TDentiDto>builder().code(HttpStatus.OK.value()).body(tDentiDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
