package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * Используется для вычисления результата деления двух чисел
 *
 * @author SadovNick
 */

public class Division {

    public static String division(String firstsStr, String secondStr) {

        if (!firstsStr.contains(".") && !secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(division(firstNum, secondNum));
        }
        if (!firstsStr.contains(".") && secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(division(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && !secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(division(firstNum, secondNum));
        }
        if (firstsStr.contains(".") && secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(division(firstNum, secondNum));
        }
        return "";
    }


    private static double division(int firstNumber, int secondNumber) {
        return Round.round((double) firstNumber / secondNumber);
    }

    private static double division(int firstNumber, double secondNumber) {
        return Round.round(firstNumber / secondNumber);
    }

    public static double division(double firstNumber, int secondNumber) {
        return Round.round(firstNumber / secondNumber);
    }

    public static double division(double firstNumber, double secondNumber) {
        return Round.round(firstNumber / secondNumber);
    }

}
