package calculator;

import inputvalidator.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    public Scanner scannerObj = new Scanner(System.in);
    private boolean running = true;

    public void run() {
        System.out.println("Calculator program, please enter valid sum to begin, enter 'exit' to exit program");
        while(this.running == true) {
            String userInput = scannerObj.nextLine();
            if(userInput.equals("exit") || userInput.equals("EXIT")) {
                System.out.println("Goodbye!");
                this.running = false;
            } else {
                this.handleUserInput(userInput);
            }
        }
    }

    private void handleUserInput(String userInput) {
        InputValidator validationObj = new InputValidator(userInput);

        if(validationObj.validate() == true) {
            String[] splitInput = splitString(userInput);
            if(splitInput.length == 3) {
                if (userInput.contains("+")) {
                    System.out.println("Answer:" + this.additionSum(splitInput));
                } else if (userInput.contains("-")) {
                    System.out.println("Answer:" + this.subtractionSum(splitInput));
                } else if (userInput.contains("x")) {
                    System.out.println("Answer:" + this.multiplicationSum(splitInput));
                } else if (userInput.contains("÷")) {
                    System.out.println("Answer:" + this.divisionSum(splitInput));
                }
            } else {
                System.out.println("Answer:" + longSum(splitInput));
            }
        } else {
            System.out.println("Not valid input, please try again");
        }
    }

    private int divisionSum(String[] sumInput) {
        return Integer.parseInt(sumInput[0]) / Integer.parseInt(sumInput[2]);
    }

    private int multiplicationSum(String[] sumInput) {
        return Integer.parseInt(sumInput[0]) * Integer.parseInt(sumInput[2]);
    }

    private int subtractionSum(String[] sumInput) {
        return Integer.parseInt(sumInput[0]) - Integer.parseInt(sumInput[2]);
    }

    private int additionSum(String[] sumInput) {
        return Integer.parseInt(sumInput[0]) + Integer.parseInt(sumInput[2]);
    }

    private String[] splitString(String userInput) {
        String[] splitList = userInput.split("\\s+");
        return splitList;
    }

    private int longSum(String[] splitList) {

        int answer = 0;

        // get initial answer form the first sum
        int firstSumAnswer = 0;
        if (splitList[1].contains("+")) {
            firstSumAnswer = this.additionSum(splitList);
        } else if (splitList[1].contains("-")) {
            firstSumAnswer = this.subtractionSum(splitList);
        } else if (splitList[1].contains("x")) {
            firstSumAnswer = this.multiplicationSum(splitList);
        } else if (splitList[1].contains("÷")) {
            firstSumAnswer = this.divisionSum(splitList);
        }

        for(int i = 3; i < splitList.length; i++) {

            List<String> newSum = new ArrayList<String>();

            if(splitList[i].contains("x")) {
                newSum.add("*");
            } else if(splitList[i].contains("÷")) {
                newSum.add("/");
            } else {
                newSum.add(splitList[i]);
            }
            i++;
            newSum.add(splitList[i]);

            if(newSum.get(0).contains("+")) {
                firstSumAnswer = firstSumAnswer + Integer.parseInt(newSum.get(1));
            } else if(newSum.get(0).contains("-")) {
                firstSumAnswer = firstSumAnswer - Integer.parseInt(newSum.get(1));
            } else if(newSum.get(0).contains("*")) {
                firstSumAnswer = firstSumAnswer * Integer.parseInt(newSum.get(1));
            } else if(newSum.get(0).contains("/")) {
                firstSumAnswer = firstSumAnswer / Integer.parseInt(newSum.get(1));
            }

        }

        return firstSumAnswer;

    }


}
