package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TDettagliOcchiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoDettagliOcchiService;
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
public class TipoDettagliOcchiController {

    @Autowired
    TipoDettagliOcchiService tipoDettagliOcchiService ;

    @Operation(description = "API per recuperare tipi dettagli occhi filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/dettagli-occhi")
    public ResponseEntity<ResponseDto<List<TDettagliOcchiDto>>> getTipoDettagliOcchi(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TDettagliOcchiDto> listResult = tipoDettagliOcchiService.getTipoDettagliOcchiByDataRif(dataRiferimento) ;
        ResponseDto<List<TDettagliOcchiDto>> responseDto = ResponseDto.<List<TDettagliOcchiDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo dente per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/dettagli-occhi/{idDettagliOcchi}/")
    public ResponseEntity<ResponseDto<TDettagliOcchiDto>> getTipiDettagliOcchiByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Dettaglio Occhi' {errore.campo.obbligatorio}") String idDettagliOcchi,
            @RequestParam (required = false) String descrizioneDettagliOcchi,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TDettagliOcchiDto tDettagliOcchiDto = tipoDettagliOcchiService.getDettagliOcchiByIdAndDescrizioneAndData(idDettagliOcchi, descrizioneDettagliOcchi, dataRiferimento) ;

        ResponseDto<TDettagliOcchiDto> responseDto = ResponseDto.<TDettagliOcchiDto>builder().code(HttpStatus.OK.value()).body(tDettagliOcchiDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
