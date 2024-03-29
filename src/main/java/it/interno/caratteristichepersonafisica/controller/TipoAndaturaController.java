package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TAndaturaDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoAndaturaService;
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
public class TipoAndaturaController {

    @Autowired
    TipoAndaturaService tipoAndaturaService ;

    @Operation(description = "API per recuperare tipi andatura filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/andatura")
    public ResponseEntity<ResponseDto<List<TAndaturaDto>>> getTipiAndatura(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TAndaturaDto> listResult = tipoAndaturaService.getTipoAndaturaByDataRif(dataRiferimento) ;
        ResponseDto<List<TAndaturaDto>> responseDto = ResponseDto.<List<TAndaturaDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/andatura/{idAndatura}/")
    public ResponseEntity<ResponseDto<TAndaturaDto>> getTipiAndaturaByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Andatura' {errore.campo.obbligatorio}") String idAndatura,
            @RequestParam (required = false) String descrizioneAndatura,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TAndaturaDto tAndaturaDto = tipoAndaturaService.getTipiAndaturaByIdAndDescrizioneAndData(idAndatura, descrizioneAndatura, dataRiferimento);
        ResponseDto<TAndaturaDto> responseDto = ResponseDto.<TAndaturaDto>builder().code(HttpStatus.OK.value()).body(tAndaturaDto).build() ;

        return ResponseEntity.ok(responseDto);
    }

}
