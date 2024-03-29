package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TStileCapelliDto;
import it.interno.caratteristichepersonafisica.exception.NoDataException;
import it.interno.caratteristichepersonafisica.service.TipoStileCapelliService;
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
public class TipoStileCapelliController {

    @Autowired
    TipoStileCapelliService tipoStileCapelliService ;

    @Operation(description = "API per recuperare tipi stili capelli filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/stili-capelli")
    public ResponseEntity<ResponseDto<List<TStileCapelliDto>>> getTipiStileCapelli(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TStileCapelliDto> listResult = tipoStileCapelliService.getTipoStileCapelliByDataRif(dataRiferimento) ;
        ResponseDto<List<TStileCapelliDto>> responseDto = ResponseDto.<List<TStileCapelliDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/stili-capelli/{idStileCapelli}/")
    public ResponseEntity<ResponseDto<TStileCapelliDto>> getTipiStileCapelliByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Stile Capelli' {errore.campo.obbligatorio}") String idStileCapelli,
            @RequestParam (required = false) String descrizioneStileCapelli,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) throws NoDataException {

        TStileCapelliDto tStileCapelliDto = tipoStileCapelliService.getStileCapelliByIdAndDescrizioneAndData(idStileCapelli, descrizioneStileCapelli, dataRiferimento) ;

        ResponseDto<TStileCapelliDto> responseDto = ResponseDto.<TStileCapelliDto>builder().code(HttpStatus.OK.value()).body(tStileCapelliDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
