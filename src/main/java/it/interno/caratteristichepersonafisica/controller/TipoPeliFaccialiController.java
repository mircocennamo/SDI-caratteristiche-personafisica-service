package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TPeliFaccialiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoPeliFaccialiService;
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
public class TipoPeliFaccialiController {

    @Autowired
    TipoPeliFaccialiService tipoPeliFaccialiService ;

    @Operation(description = "API per recuperare tipi peli facciali filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/peli-facciali")
    public ResponseEntity<ResponseDto<List<TPeliFaccialiDto>>> getTipiPeliFacciali(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TPeliFaccialiDto> listResult = tipoPeliFaccialiService.getTipoPeliFaccialiByDataRif(dataRiferimento) ;
        ResponseDto<List<TPeliFaccialiDto>> responseDto = ResponseDto.<List<TPeliFaccialiDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/peli-facciali/{idPeliFacciali}/")
    public ResponseEntity<ResponseDto<TPeliFaccialiDto>> getTipiPeliFaccialiByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Peli Facciali' {errore.campo.obbligatorio}") String idPeliFacciali,
            @RequestParam (required = false) String descrizionePeliFacciali,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TPeliFaccialiDto tPeliFaccialiDto = tipoPeliFaccialiService.getPeliFaccialiByIdAndDescrizioneAndData(idPeliFacciali, descrizionePeliFacciali, dataRiferimento) ;

        ResponseDto<TPeliFaccialiDto> responseDto = ResponseDto.<TPeliFaccialiDto>builder().code(HttpStatus.OK.value()).body(tPeliFaccialiDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
