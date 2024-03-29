package it.interno.caratteristichepersonafisica.exception;

import lombok.Getter;

@Getter
public class NoDataException extends Exception {

    private static final long serialVersionUID = -2704298221329055416L;

    private String message ;
    private String param ;


    public NoDataException(String message, String param) {
        super(message);
        this.message = message ;
        this.param = param ;
    }

}
