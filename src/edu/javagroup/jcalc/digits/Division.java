package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * Используется для вычисления результата деления двух чисел
 *
 * @author SadovNick
 */

public class Division {
    /**
     * 1. если строка1 и строка2 не содержат точек, то вернуть результат работы метода
     * division(строка1 как int, строка2 как int)
     * 2. иначе, если только строка2 из двух содержит точку, то вернуть результат работы метода
     * division(строка1 как int, строка2 как double)
     * 3. иначе, если только строка1 из двух содержит точку, то вернуть результат работы метода
     * division(строка1 как double, строка2 как int)
     * 4. иначе, если строка1 и строка2 содержат точки, то вернуть результат работы метода
     * division(строка1 как double, строка2 как double)
     * 5. иначе, вернуть пустую строку (можно заявить о проблеме, но нет смысла без обработки исключений)
     * для приведения строк в нужный формат используй методы:
     * LineParsing.getInteger и LineParsing.getDouble
     *
     * @param firstsStr
     * @param secondStr
     * @return
     */

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
