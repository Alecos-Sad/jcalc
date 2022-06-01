package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * Используется для вычисления суммы двух чисел
 *
 * @author SadovNick
 */

public class Addition {
    /**
     * 1. если строка1 и строка2 не содержат точек, то вернуть результат работы метода addition
     * (строка1 как int, строка2 как int)
     * 2. иначе, если только строка2 из двух содержит точку, то вернуть результат работы метода
     * addition(строка1 как int, строка2 как double)
     * 3. иначе, если только строка1 из двух содержит точку, то вернуть результат работы метода
     * addition(строка1 как double, строка2 как int)
     * 4. иначе, если строка1 и строка2 содержат точки, то вернуть результат работы метода
     * addition(строка1 как double, строка2 как double)
     * 5. иначе, вернуть пустую строку (можно заявить о проблеме, но нет смысла без обработки исключений)
     * для приведения строк в нужный формат используй методы:
     * LineParsing.getInteger и LineParsing.getDouble
     *
     * @param firstsStr
     * @param secondStr
     * @return
     */

    public static String addition(String firstsStr, String secondStr) {

        if (!firstsStr.contains(".") && !secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        } else if (!firstsStr.contains(".") && secondStr.contains(".")) {
            int firstNum = LineParsing.getInteger(firstsStr);
            double secondNum = LineParsing.getDouble(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        } else if (firstsStr.contains(".") && !secondStr.contains(".")) {
            double firstNum = LineParsing.getDouble(firstsStr);
            int secondNum = LineParsing.getInteger(secondStr);
            return String.valueOf(addition(firstNum, secondNum));
        } else if (firstsStr.contains(".") && secondStr.contains(".")) {
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
