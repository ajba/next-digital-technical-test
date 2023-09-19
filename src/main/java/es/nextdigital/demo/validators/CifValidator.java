package es.nextdigital.demo.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CifValidator implements ConstraintValidator<Cif, String> {
    private final String cifValidCharacters = "ABCDEFGHJKLMNPQRSUVW";
    private String cifLetter;
    private String lastCifDigit;
    private String digitControl;
    private int pairSum;
    private int oddSum;
    private int totalSum;
    private int unitDigit;
    private int subResult;
    private final char[] controlLetters = new char[]
    {'J', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};;
    private final String firstCharacter ="NPQRSW";
    private String controlCharacter;
    private String oneDigitControl;
    private String validCif;
    private Pattern cifMask = Pattern.compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[0-9A-J]");

    @Override
    public void initialize(Cif constraintAnnotation) {
    }


    @Override
    public boolean isValid(String cif, ConstraintValidatorContext context) {
        final Matcher cifMatcher = cifMask.matcher(cif);
        if(cifMatcher.matches() && validCif(cif)){
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("INCORRECT CIF STRUCTURE").addConstraintViolation();
        return false;
    }

    private int getPairSum() {
        for (int pos = 1; pos < digitControl.length(); pos += 2) {
            pairSum = pairSum + Integer.parseInt(
                    digitControl.substring(pos, pos + 1));
        }
        return pairSum;
    }

    private int getOddSum() {
        Integer oddLenght;
        for (int pos = 0; pos < digitControl.length(); pos += 2) {
            oddLenght = 2 * Integer.parseInt(
                    digitControl.substring(pos, pos + 1));
            if (oddLenght.toString().length() > 1) {
                oddLenght = Integer.parseInt
                        (oddLenght.toString().substring(0, 1)) + Integer.parseInt(
                        oddLenght.toString().substring(1, 2));
            }
            oddSum = oddSum + oddLenght;
        }
        return oddSum;
    }

    public int getControlDigit() {
        totalSum = getPairSum() + getOddSum();
        unitDigit = totalSum % 10;
        if (unitDigit > 0) {
            this.subResult = 10 - unitDigit;
        } else {
            this.subResult = 0;
        }
        return subResult;
    }
    public boolean validCif(String cif){
        cif = cif.toUpperCase();
        cifLetter = cif.substring(0, 1).toUpperCase();
        digitControl = cif.substring(1, cif.length() - 1).toUpperCase();
        lastCifDigit = cif.substring(cif.length() - 1).toUpperCase();
        if (cifValidCharacters.indexOf(cif.charAt(0)) == -1) {
            return false;
        } else {
            oneDigitControl = String.valueOf(this.getControlDigit());
            if (this.digitControl.startsWith("00", 1) || firstCharacter.indexOf(cifLetter.charAt(0)) != -1) {
                controlCharacter = Character.toString(this.controlLetters[subResult]);
                validCif = cifLetter + digitControl + controlCharacter;
            } else {
                validCif = cifLetter + digitControl + oneDigitControl;
            }
            return cif.equals(validCif);
        }
    }
}
