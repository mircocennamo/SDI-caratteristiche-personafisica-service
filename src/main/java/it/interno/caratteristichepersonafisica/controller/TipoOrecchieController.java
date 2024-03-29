package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TOrecchieDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoOrecchieService;
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
public class TipoOrecchieController {

    @Autowired
    TipoOrecchieService tipoOrecchieService ;

    @Operation(description = "API per recuperare tipi orecchie filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/orecchie")
    public ResponseEntity<ResponseDto<List<TOrecchieDto>>> getTipiOrecchie(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TOrecchieDto> listResult = tipoOrecchieService.getTipoOrecchieByDataRif(dataRiferimento) ;
        ResponseDto<List<TOrecchieDto>> responseDto = ResponseDto.<List<TOrecchieDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/orecchie/{idOrecchie}/")
    public ResponseEntity<ResponseDto<TOrecchieDto>> getTipiOrecchieByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Orecchie' {errore.campo.obbligatorio}") String idOrecchie,
            @RequestParam (required = false) String descrizioneOrecchie,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TOrecchieDto tOrecchieDto = tipoOrecchieService.getOrecchieByIdAndDescrizioneAndData(idOrecchie, descrizioneOrecchie, dataRiferimento) ;

        ResponseDto<TOrecchieDto> responseDto = ResponseDto.<TOrecchieDto>builder().code(HttpStatus.OK.value()).body(tOrecchieDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
