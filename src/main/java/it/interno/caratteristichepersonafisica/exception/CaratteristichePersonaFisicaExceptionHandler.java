package it.interno.caratteristichepersonafisica.exception;

import it.interno.caratteristichepersonafisica.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CaratteristichePersonaFisicaExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = messageSource.getMessage("errore.generico", null, LocaleContextHolder.getLocale());
        ResponseDto responseDto = ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(message).build();

        return ResponseEntity.internalServerError().body(responseDto);
    }

    @ExceptionHandler(NoDataException.class)
    protected ResponseEntity<Object> handleNoDataExceptionInternal(NoDataException ex) {

        String message = messageSource.getMessage(ex.getMessage(),
                new Object[] { ex.getParam() != null ? ex.getParam() : "" }, LocaleContextHolder.getLocale());
        ResponseDto<String> responseDto = ResponseDto.<String>builder().code(HttpStatus.NOT_FOUND.value()).error(message).build();

        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }

}
