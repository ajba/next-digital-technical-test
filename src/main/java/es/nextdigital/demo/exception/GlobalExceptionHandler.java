package es.nextdigital.demo.exception;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ValidationError>> handleValidationErrors(BindException ex) {
        List<ValidationError> errors = this.buildErrorMap(ex.getBindingResult().getAllErrors());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<List<String>> handleGeneralExceptions(Exception ex) {
        log.error("An error has occurred :",ex);
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private List<ValidationError> buildErrorMap(List<ObjectError> errors){
        List<ValidationError> res = new ArrayList<>();
        errors.forEach((error) -> {
            String fieldName = null;
            String className = null;
            if(error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            }
            if (StringUtils.isNotBlank(error.getObjectName())) {
                className = error.getObjectName() + ".class";
            }

            ValidationError validationError = new ValidationError(className, error.getDefaultMessage(), error.getCode(), fieldName);
            res.add(validationError);
        });

        return res;
    }



}
