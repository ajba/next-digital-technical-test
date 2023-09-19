package es.nextdigital.demo.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationError implements Serializable {
    public String errorClass;
    public String errorMessage;
    public String errorCode;
    public String errorField;

    public ValidationError() {
    }

    public ValidationError(String errorClass, String errorMessage, String errorCode, String errorField) {
        this.errorClass = errorClass;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorField = errorField;
    }
}
