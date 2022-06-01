package edu.javagroup.jcalc.lines;

import edu.javagroup.jcalc.digits.Addition;
import edu.javagroup.jcalc.digits.Division;
import edu.javagroup.jcalc.digits.Multiplication;
import edu.javagroup.jcalc.digits.Subtraction;

/**
 * Класс для операции над строкой
 */


public class LineOperation {

    /**
     * получает строку, возвращает её же, но без последнего символа
     */
    public static String removeLastSymbol(String source) {
        return source.substring(0, source.length() - 1);
    }

    /**
     * получает строку, возвращает её же, но добавляет символ минуса в начало этой строки
     */
    public static String addMinusPrefix(String source) {
        return "-" + source;
    }

    /**
     * получает строки, возвращает результат конкатенации этих строк
     */
    public static String concatLines(String sourceOne, String sourceTwo) {
        return sourceOne + sourceTwo;
    }

    /**
     * получает строку, если она null, пуста или ее длина равна нулю, то вернуть пустую строку,
     * иначе:
     * 1. в строку записать результат работы метода LinePreparing.linePreparing
     * 2. если метод LineCheck.isLineCorrect вернул true, выполнить п.3, если нет, вернуть строку
     * 3. начать операции над строкой:
     * 3.1. если строка прошла проверку методом LineParsing.isFinalNumber, то вернуть эту строку,
     * иначе:
     * 3.2. если строка содержит любой из этих символов '(' или ')', вернуть результат работы метода
     * getResultWithRoundBrackets, иначе:
     * 3.3. вернуть результат работы метода getResultWithoutRoundBrackets
     */
    public static String getResult(String source) {

        if (source == null && source.isEmpty()) {
            return "";
        } else {
            source = LinePreparing.linePreparing(source);
            if (LineCheck.isLineCorrect(source)) {
                if (LineParsing.isFinalNumber(source)) {
                    return source;
                }
                return (source.contains("(") || source.contains(")")) ?
                        getResultWithRoundBrackets(source) :
                        getResultWithoutRoundBrackets(source);
            }
            return source;
        }
    }

    /**
     * получает строку, если она null, пуста или ее длина равна нулю, то вернуть пустую строку,
     * иначе:
     * 1. в строку записать результат работы метода LinePreparing.linePreparing
     * 2. если метод LineCheck.isLineCorrect вернул true, выполнить п.3, если нет, вернуть строку
     * 3. начать операции над строкой:
     * 3.1. если строка прошла проверку методом LineParsing.isFinalNumber, то вернуть эту строку,
     * иначе:
     * 3.2. если строка содержит любой из этих символов '(' или ')', вернуть результат работы метода
     * getResultWithRoundBrackets, иначе:
     * 3.3. вернуть результат работы метода getResultWithoutRoundBrackets
     */
    public static String getResultWithRoundBrackets(String source) {

        while (source.contains("(") || source.contains(")")) {
            if (LineParsing.isFinalNumber(source)) {
                return source;
            }
            int openBracketIndex = source.lastIndexOf("(");
            int closeBracketIndex = source.indexOf(")", openBracketIndex);
            String stringInBrackets = source.substring(openBracketIndex + 1, closeBracketIndex);
            stringInBrackets = LinePreparing.linePreparing(stringInBrackets);
            stringInBrackets = getResultWithoutRoundBrackets(stringInBrackets);
            if (stringInBrackets.isEmpty()) {
                break;

            }
            source = collectLines(source, stringInBrackets, openBracketIndex, closeBracketIndex);
        }
        return getResultWithoutRoundBrackets(source);
    }

    /**
     * пока строка содержит символы / * + - или не прошла проверку методом LineParsing.isFinalNumber,
     * выполнять следующее:
     * 1.1. получить первый символ воспользовавшись методом LineParsing.findFirstMathSymbol
     * 1.2. если в результате мы получили пустую строку (символа нет), прервать операцию вернув
     * пустую строку
     * 1.3. записать координату этого символа
     * 1.4. в зависимости от того какой это символ вызвать нужный метод:
     * 1.4.1. '*' записать в переменную типа String результат работы метода multiplication
     * 1.4.2. '/' записать в переменную типа String результат работы метода division
     * 1.4.3. '+' записать в переменную типа String результат работы метода addition
     * 1.4.4. '-' записать в переменную типа String результат работы метода subtraction
     * 1.5. полученный результат использовать в п.1.7
     * 1.6. в итоге, записать в строку результат работы метода collectLines(строка, результат,
     * координата символа)
     * 1.7. если в строке встречается '--' заменить на '+'
     * 2. если в строке есть символ точки, но после этого символа идут нули, то вернуть только ту
     * часть строки, которая находится левее символа точки
     */
    public static String getResultWithoutRoundBrackets(String stringWithoutBrackets) {

        while (stringWithoutBrackets.contains("/") || stringWithoutBrackets.contains("-") ||
                stringWithoutBrackets.contains("*") || (stringWithoutBrackets.contains("+"))) {

            if (LineParsing.isFinalNumber(stringWithoutBrackets)) {
                return stringWithoutBrackets;
            }
            String symbol = LineParsing.findFirstMathSymbol(stringWithoutBrackets);
            if (symbol.isEmpty()) {
                return "";
            }
            stringWithoutBrackets = stringWithoutBrackets.startsWith("-") ?
                    stringWithoutBrackets.substring(1) : stringWithoutBrackets;
            int symbolIndex = stringWithoutBrackets.indexOf(symbol);
            String result = "";

            switch (symbol) {
                case "*":
                    result = multiplication(stringWithoutBrackets, symbolIndex);
                    break;
                case "/":
                    result = division(stringWithoutBrackets, symbolIndex);
                    break;
                case "+":
                    result = addition(stringWithoutBrackets, symbolIndex);
                    break;
                case "-":
                    result = subtraction(stringWithoutBrackets, symbolIndex);
            }
            stringWithoutBrackets = collectLines(stringWithoutBrackets, result, symbolIndex);
            stringWithoutBrackets = stringWithoutBrackets.contains("--") ?
                    stringWithoutBrackets.replace("--", "+") : stringWithoutBrackets;
            if (stringWithoutBrackets.contains(".")) {
                int pointIndex = stringWithoutBrackets.indexOf(".");
                String s = stringWithoutBrackets.substring(pointIndex + 1);
                int zero = Integer.parseInt(s);
                stringWithoutBrackets = zero == 0 ?
                        stringWithoutBrackets.substring(0, pointIndex) : stringWithoutBrackets;
            }
        }
        return stringWithoutBrackets;
    }

    /**
     * в метод приходят два параметра, строка с математическим выражением и координата символа
     * 1. необходимо получить строку содержащую число левее от этого символа используя метод:
     * LineParsing.getNumberFromLeftPart(строка, координата символа)
     * 2. необходимо получить строку содержащую число правее от этого символа используя метод:
     * LineParsing.getNumberFromRightPart(строка, координата символа)
     * 3. используя эти две строки, вернуть результат работы метода Multiplication.multiplication
     */
    public static String multiplication(String source, int symbolIndex) {
        return Multiplication.multiplication(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    /**
     * в метод приходят два параметра, строка с математическим выражением и координата символа
     * 1. необходимо получить строку содержащую число левее от этого символа используя метод:
     * LineParsing.getNumberFromLeftPart(строка, координата символа)
     * 2. необходимо получить строку содержащую число правее от этого символа используя метод:
     * LineParsing.getNumberFromRightPart(строка, координата символа)
     * 3. используя эти две строки, вернуть результат работы метода Division.division
     */
    public static String division(String source, int symbolIndex) {
        return Division.division(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    /**
     * в метод приходят два параметра, строка с математическим выражением и координата символа
     * 1. необходимо получить строку содержащую число левее от этого символа используя метод:
     * LineParsing.getNumberFromLeftPart(строка, координата символа)
     * 2. необходимо получить строку содержащую число правее от этого символа используя метод:
     * LineParsing.getNumberFromRightPart(строка, координата символа)
     * 3. используя эти две строки, вернуть результат работы метода Addition.addition
     */

    public static String addition(String source, int symbolIndex) {
        return Addition.addition(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    /**
     * в метод приходят два параметра, строка с математическим выражением и координата символа
     * 1. необходимо получить строку содержащую число левее от этого символа используя метод:
     * LineParsing.getNumberFromLeftPart(строка, координата символа)
     * 2. необходимо получить строку содержащую число правее от этого символа используя метод:
     * LineParsing.getNumberFromRightPart(строка, координата символа)
     * 3. используя эти две строки, вернуть результат работы метода Subtraction.subtraction
     */
    public static String subtraction(String source, int symbolIndex) {
        return Subtraction.subtraction(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    /**
     * в метод приходят три параметра: строка с математическим выражением (1), результат
     * математический операции (2), координата символа (3)
     * 1. необходимо получить число (число1) левее от символа (3)
     * (использовать LineParsing.getNumberFromLeftPart, куда передать (1) и (3))
     * 2. необходимо получить число (число2) правее от символа (3)
     * (использовать LineParsing.getNumberFromRightPart, куда передать (1) и (3))
     * 3. получить часть строки (часть1) до числа (число1)
     * 4. получить часть строки (часть2) после числа (число2)
     * 5. вернуть строку: часть строки (часть1) + результат (2) + часть строки (часть2)
     */
    public static String collectLines(String sourceWithoutBrackets, String result, int symbolIndex) {
        StringBuilder stringBuilder = new StringBuilder(sourceWithoutBrackets);
        int leftCount = 0;
        int rightCount = 0;
        if (sourceWithoutBrackets.contains(".")) {

            for (int i = symbolIndex - 1; i >= 0; i--) {
                if (Character.isDigit(sourceWithoutBrackets.charAt(i)) ||
                        sourceWithoutBrackets.charAt(i) == '.') {
                    leftCount++;
                }
            }
            for (int i = symbolIndex + 1; i < sourceWithoutBrackets.length(); i++) {
                if (Character.isDigit(sourceWithoutBrackets.charAt(i)) ||
                        sourceWithoutBrackets.charAt(i) == '.') {
                    rightCount++;
                }
            }
            return stringBuilder.replace(symbolIndex - leftCount, symbolIndex + rightCount + 1, result).toString();
        }
        return stringBuilder.replace(symbolIndex - 1, symbolIndex + 2, result).toString();
    }

    /**
     * в метод приходят четыре параметра: строка с математическим выражением (1), результат математический
     * операции (2), координата символа скобки '(' (3), координата символа скобки ')' (4)
     * 1. необходимо получить часть строки (часть1) до символа (3)
     * 2. необходимо получить часть строки (часть2) после символа (4)
     * 3. вернуть строку: часть строки (часть1) + результат (2) + часть строки (часть2)
     */
    public static String collectLines(String source, String result, int openBracketIndex, int closeBracketIndex) {
        StringBuilder stringBuilder = new StringBuilder(source);
        stringBuilder.replace(openBracketIndex, closeBracketIndex + 1, result);

        return stringBuilder.toString();
    }
}
