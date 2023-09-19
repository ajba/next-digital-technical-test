package es.nextdigital.demo.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NifValidator implements ConstraintValidator<Nif, String> {

    private Pattern nifMask = Pattern.compile("[0-9]{8}[A-Z]");
    private Pattern nieMask = Pattern.compile("[XYZ][0-9]{7}[A-Z]");
    private String letters = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public void initialize(Nif constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        if (validNif(value) || validNie(value)) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("INCORRECT NIF/NIE STRUCTURE").addConstraintViolation();

        return false;
    }

    private boolean validNif(String nif) {
        final Matcher nifMatcher = nifMask.matcher(nif);

        if (!nifMatcher.matches()) {
            return false;
        }

        return validControl(nif);
    }

    private boolean validControl(String document) {
        final String documentNumbers = document.substring(0, 8);
        final String control = document.substring(8, 9);
        final int position = Integer.parseInt(documentNumbers) % 23;
        final String controlCalculated = letters.substring(position, position + 1);

        return control.equalsIgnoreCase(controlCalculated);
    }

    private boolean validNie(String nie) {
        final Matcher nieMatcher = nieMask.matcher(nie);

        if (!nieMatcher.matches()) {
            return false;
        }
        String formattedNie = null;

        if (nie.startsWith("X")) {
            formattedNie = nie.replace('X', '0');
        } else if (nie.startsWith("Y")) {
            formattedNie = nie.replace('Y', '1');
        } else if (nie.startsWith("Z")) {
            formattedNie = nie.replace('Z', '2');
        }
        return validControl(formattedNie);
    }
}