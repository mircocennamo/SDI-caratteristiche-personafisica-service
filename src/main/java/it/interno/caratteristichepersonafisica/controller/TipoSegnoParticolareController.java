package it.interno.caratteristichepersonafisica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import it.interno.caratteristichepersonafisica.dto.TSegnoParticolareDto;
import it.interno.caratteristichepersonafisica.service.TipoSegnoParticolareService;
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
public class TipoSegnoParticolareController {

    @Autowired
    TipoSegnoParticolareService tipoSegnoParticolareService ;

    @Operation(description = "API per recuperare tipi segni particolari filtrando per data ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/segni-particolari")
    public ResponseEntity<ResponseDto<List<TSegnoParticolareDto>>> getTipiSegnoParticolare(
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TSegnoParticolareDto> listResult = tipoSegnoParticolareService.getTipoSegnoParticolareByDataRif(dataRiferimento) ;
        ResponseDto<List<TSegnoParticolareDto>> responseDto = ResponseDto.<List<TSegnoParticolareDto>>builder().code(HttpStatus.OK.value()).body(listResult).build() ;

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per recuperare un tipo andatura per id, descrizione e data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema")
    })
    @GetMapping(path = "/segni-particolari/{idSegnoParticolare}")
    public ResponseEntity<ResponseDto<List<TSegnoParticolareDto>>> getTipiSegnoParticolareByIdAndDescrizioneAndData(
            @PathVariable @NotBlank(message = "Il campo 'Id Segno Particolare' {errore.campo.obbligatorio}") List<String> idSegnoParticolare,
            @RequestParam (required = false) String descrizioneSegnoParticolare,
            @RequestParam @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataRiferimento) {

        List<TSegnoParticolareDto> tSegnoParticolareDto = tipoSegnoParticolareService.getSegnoParticolareByIdAndDescrizioneAndData(idSegnoParticolare, descrizioneSegnoParticolare, dataRiferimento);

        ResponseDto<List<TSegnoParticolareDto>> responseDto = ResponseDto.<List<TSegnoParticolareDto>>builder().code(HttpStatus.OK.value()).body(tSegnoParticolareDto).build() ;
        return ResponseEntity.ok(responseDto);
    }

}
