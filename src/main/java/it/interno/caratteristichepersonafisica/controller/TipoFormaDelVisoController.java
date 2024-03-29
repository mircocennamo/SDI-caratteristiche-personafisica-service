package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TFormaDelVisoDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoFormaDelVisoService;
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
public class TipoFormaDelVisoController {

    @Autowired
    TipoFormaDelVisoService tipoFormaDelVisoService ;

    @Operation(description = "API per recuperare tipi forma del viso filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/forma-viso")
    public ResponseEntity<ResponseDto<List<TFormaDelVisoDto>>> getTipoFormaDelViso(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TFormaDelVisoDto> listResult = tipoFormaDelVisoService.getTipoFormaDelVisoByDataRif(dataRiferimento) ;
        ResponseDto<List<TFormaDelVisoDto>> responseDto = ResponseDto.<List<TFormaDelVisoDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo dente per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/forma-viso/{idFormaViso}/")
    public ResponseEntity<ResponseDto<TFormaDelVisoDto>> getTipiFormaDelVisoByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Forma Viso' {errore.campo.obbligatorio}") String idFormaViso,
            @RequestParam (required = false) String descrizioneFormaViso,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TFormaDelVisoDto tFormaDelVisoDto = tipoFormaDelVisoService.getFormaDelVisoByIdAndDescrizioneAndData(idFormaViso, descrizioneFormaViso, dataRiferimento) ;

        ResponseDto<TFormaDelVisoDto> responseDto = ResponseDto.<TFormaDelVisoDto>builder().code(HttpStatus.OK.value()).body(tFormaDelVisoDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
