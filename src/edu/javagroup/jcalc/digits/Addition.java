package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

public class Addition {

    public static String addition(String firstsStr, String secondStr) {

        if (!firstsStr.contains(".") && !secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        }
        if (!firstsStr.contains(".") && secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && !secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        }

        return "";
    }


    private static int addition(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    private static double addition(int firstNumber, double secondNumber) {
        return Round.round(firstNumber + secondNumber);
    }

    private static double addition(double firstNumber, int secondNumber) {
        return Round.round(firstNumber + secondNumber);
    }

    private static double addition(double firstNumber, double secondNumber) {
        return Round.round(firstNumber + secondNumber);
    }
}
