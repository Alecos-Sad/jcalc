package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * Используется для вычисления результата разности двух чисел
 *
 * @author SadovNick
 */

public class Subtraction {

    public static String subtraction(String firstsStr, String secondStr) {

        if (!firstsStr.contains(".") && !secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(subtraction(firstNum, secondNum));
        }
        if (!firstsStr.contains(".") && secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(subtraction(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && !secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(subtraction(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(subtraction(firstNum, secondNum));
        }

        return "";
    }


    private static int subtraction(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    private static double subtraction(int firstNumber, double secondNumber) {
        return Round.round(firstNumber - secondNumber);
    }

    private static double subtraction(double firstNumber, int secondNumber) {
        return Round.round(firstNumber - secondNumber);
    }

    private static double subtraction(double firstNumber, double secondNumber) {
        return Round.round(firstNumber - secondNumber);
    }
}
