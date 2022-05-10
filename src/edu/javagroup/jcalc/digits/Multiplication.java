package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

public class Multiplication {

    public static String multiplication(String firstsStr, String secondStr) {

        if (!firstsStr.contains(".") && !secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(multiplication(firstNum, secondNum));
        }
        if (!firstsStr.contains(".") && secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(multiplication(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && !secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(multiplication(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(multiplication(firstNum, secondNum));
        }
        return "";
    }


    private static int multiplication(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }

    private static double multiplication(int firstNumber, double secondNumber) {
        return Round.round(firstNumber * secondNumber);
    }

    public static double multiplication(double firstNumber, int secondNumber) {
        return Round.round(firstNumber * secondNumber);
    }

    public static double multiplication(double firstNumber, double secondNumber) {
        return Round.round(firstNumber * secondNumber);
    }
}
