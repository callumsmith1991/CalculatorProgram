package inputvalidator;

import java.util.List;

public class InputValidator {

    private String input;

    public InputValidator(String input) {
        this.input = input;
        this.validate();
    }

    public boolean validate() {

        // first we check if string contains a mathematical symbol
        boolean passed = true;

        if(this.input.contains("+") || this.input.contains("-") || this.input.contains("x") || this.input.contains("÷")) {
            // next we check if their is two plus numbers entered by the user
            boolean enoughNumbersPresent = checkForNumbersPresent();

            if(enoughNumbersPresent == true) {
                passed = true;
            } else {
                passed = false;
            }

        } else {
            passed = false;
        }

        return passed;


    }

    private boolean checkForNumbersPresent() {

        String[] inputSplit = this.input.split("\\s+");
        int numberCount = 0;

        if(inputSplit.length > 0) {
            for(int i = 0; i < inputSplit.length; i++) {

               boolean isNumeric = checkIfStringIsNumeric(inputSplit[i]);

               if(isNumeric == true) {
                   numberCount++;
               }

            }

        }

        if(numberCount >= 2) {
            return true;
        } else {
            return false;
        }

    }

    private boolean checkIfStringIsNumeric(String value) {
        try {
            int isInt = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}
