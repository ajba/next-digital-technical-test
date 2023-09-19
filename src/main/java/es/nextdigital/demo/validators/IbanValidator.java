package es.nextdigital.demo.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class IbanValidator implements ConstraintValidator<Iban, String> {

    private static final long MAX = 999999999;
    private static final long MODULUS = 97;

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext context) {
        if (!validateIban(iban)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("INCORRECT IBAN STRUCTURE")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    /**
     * Check if an IBAN is valid
     *
     * @param iban - Spanish IBAN code (24 digits)
     * @return true or false en caso de ser válido o no el código
     */
    public boolean validateIban(String iban) {
        if (iban == null || iban.length() != 24) {
            return false;
        }
        try {
            return calculateModulus(iban) == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Calculate the modulus for a IBAN.
     *
     * @param iban The IBAN to calculate the modulus for.
     * @return The modulus value
     * @throws Exception if an error occurs calculating the modulus
     *                   for the specified code
     */
    private static int calculateModulus(String iban) throws Exception {
        String controlCode = iban.substring(0, 4);
        String bban = iban.substring(4);
        String reformattedCode = bban + controlCode;
        long total = 0;
        for (int i = 0; i < reformattedCode.length(); i++) {
            int charValue = Character.getNumericValue(reformattedCode.charAt(i));
            if (charValue < 0 || charValue > 35) {
                throw new Exception("Invalid Caracter[" + i + "]");
            }
            total = (charValue > 9 ? total * 100 : total * 10) + charValue;
            if (total > MAX) {
                total = (total % MODULUS);
            }
        }
        return (int) (total % MODULUS);
    }

}
