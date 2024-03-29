package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TColoreCapelliDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoColoreCapelliService;
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
public class TipoColoreCapelliiController {

    @Autowired
    TipoColoreCapelliService tipoColoreCapelliService ;

    @Operation(description = "API per recuperare tipi colore capelli filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/colore-capelli")
    public ResponseEntity<ResponseDto<List<TColoreCapelliDto>>> getTipoColoreCapelli(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TColoreCapelliDto> listResult = tipoColoreCapelliService.getTipoColoreCapelliByDataRif(dataRiferimento) ;
        ResponseDto<List<TColoreCapelliDto>> responseDto = ResponseDto.<List<TColoreCapelliDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo colore capelli per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/colore-capelli/{idColoreCapelli}/")
    public ResponseEntity<ResponseDto<TColoreCapelliDto>> getTipiColoreCapelliByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Colore Capelli' {errore.campo.obbligatorio}") String idColoreCapelli,
            @RequestParam (required = false) String descrizioneColoreCapelli,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TColoreCapelliDto tColoreCapelliDto = tipoColoreCapelliService.getColoreCapelliByIdAndDescrizioneAndData(idColoreCapelli, descrizioneColoreCapelli, dataRiferimento) ;

        ResponseDto<TColoreCapelliDto> responseDto = ResponseDto.<TColoreCapelliDto>builder().code(HttpStatus.OK.value()).body(tColoreCapelliDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
