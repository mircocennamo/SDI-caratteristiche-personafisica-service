package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TCarnagioneDto;
import it.interno.caratteristichepersonafisica.service.TipoCarnagioneService;
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
public class TipoCarnagioneController {

    @Autowired
    TipoCarnagioneService tipoCarnagioneService ;

    @Operation(description = "API per recuperare tipi carnagioni filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/carnagione")
    public ResponseEntity<ResponseDto<List<TCarnagioneDto>>> getTipiCarnagione(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TCarnagioneDto> listResult = tipoCarnagioneService.getTipoCarnagioneByDataRif(dataRiferimento) ;
        ResponseDto<List<TCarnagioneDto>> responseDto = ResponseDto.<List<TCarnagioneDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo carnagione per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/carnagione/{idCarnagione}/")
    public ResponseEntity<ResponseDto<List<TCarnagioneDto>>> getTipiCarnagioneByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Carnagione' {errore.campo.obbligatorio}") List<String> idCarnagione,
            @RequestParam (required = false) String descrizioneCarnagione,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TCarnagioneDto> tCarnagioneDto = tipoCarnagioneService.getCarnagioneByIdAndDescrizioneAndData(idCarnagione, descrizioneCarnagione, dataRiferimento) ;
        ResponseDto<List<TCarnagioneDto>> responseDto = ResponseDto.<List<TCarnagioneDto>>builder().code(HttpStatus.OK.value()).body(tCarnagioneDto).build() ;

        return ResponseEntity.ok(responseDto);
    }

}
