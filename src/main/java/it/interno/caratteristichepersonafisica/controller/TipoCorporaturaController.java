package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TCorporaturaDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoCorporaturaService;
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
public class TipoCorporaturaController {

    @Autowired
    TipoCorporaturaService tipoCorporaturaService ;

    @Operation(description = "API per recuperare tipi corporatura filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/corporatura")
    public ResponseEntity<ResponseDto<List<TCorporaturaDto>>> getTipiCorporatura(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TCorporaturaDto> listResult = tipoCorporaturaService.getTipoCorporaturaByDataRif(dataRiferimento) ;
        ResponseDto<List<TCorporaturaDto>> responseDto = ResponseDto.<List<TCorporaturaDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo corporatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/corporatura/{idCorporatura}/")
    public ResponseEntity<ResponseDto<TCorporaturaDto>> getTipiCorporaturaByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Corporatura' {errore.campo.obbligatorio}") String idCorporatura,
            @RequestParam (required = false) String descrizioneCorporatura,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TCorporaturaDto tCorporaturaDto = tipoCorporaturaService.getCorporaturaByIdAndDescrizioneAndData(idCorporatura, descrizioneCorporatura, dataRiferimento) ;

        ResponseDto<TCorporaturaDto> responseDto = ResponseDto.<TCorporaturaDto>builder().code(HttpStatus.OK.value()).body(tCorporaturaDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
