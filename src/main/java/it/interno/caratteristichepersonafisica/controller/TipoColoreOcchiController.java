package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TColoreOcchiDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoColoreOcchiService;
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
public class TipoColoreOcchiController {

    @Autowired
    TipoColoreOcchiService tipoColoreOcchiService ;

    @Operation(description = "API per recuperare tipi colore occhi filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/colore-occhi")
    public ResponseEntity<ResponseDto<List<TColoreOcchiDto>>> getTipoColoreOcchi(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TColoreOcchiDto> listResult = tipoColoreOcchiService.getTipoColoreOcchiByDataRif(dataRiferimento) ;
        ResponseDto<List<TColoreOcchiDto>> responseDto = ResponseDto.<List<TColoreOcchiDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo colore occhi per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/colore-occhi/{idColoreOcchi}/")
    public ResponseEntity<ResponseDto<TColoreOcchiDto>> getTipiColoreOcchiByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Colore Occhi' {errore.campo.obbligatorio}") String idColoreOcchi,
            @RequestParam (required = false) String descrizioneColoreOcchi,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TColoreOcchiDto tColoreOcchiDto = tipoColoreOcchiService.getColoreOcchiByIdAndDescrizioneAndData(idColoreOcchi, descrizioneColoreOcchi, dataRiferimento) ;

        ResponseDto<TColoreOcchiDto> responseDto = ResponseDto.<TColoreOcchiDto>builder().code(HttpStatus.OK.value()).body(tColoreOcchiDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
