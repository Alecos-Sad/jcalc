package edu.javagroup.jcalc.lines;


/**
 * Класс для проверки строк
 */

public class LineCheck {

    /**
     * возвращает результаты работы методов isRoundBracketsCorrect и isMathSymbolsCorrect
     */
    public static boolean isLineCorrect(String source) {
        return isRoundBracketsCorrect(source) && isMathSymbolsCorrect(source);
    }

    /**
     * если входящая строка содержит символы открывающих или закрывающих скобок,
     * то возвращает результат работы метода isRoundBracketsCountCorrect, если нет, возвращает true
     */
    public static boolean isRoundBracketsCorrect(String source) {
        return !source.contains("(") && !source.contains(")") || isRoundBracketsCountCorrect(source);
    }

    /**
     * если входящая строка содержит одинаковое количество открывающих и закрывающих скобок,
     * то возвращает результат работы метода isRoundBracketsPositionCorrect,
     * если нет, возвращает false
     */
    public static boolean isRoundBracketsCountCorrect(String source) {
        int openBracket = 0;
        int closeBracket = 0;
        for (int i = 0; i < source.length(); i++) {
            openBracket = source.charAt(i) == '(' ? ++openBracket : openBracket;
            closeBracket = source.charAt(i) == ')' ? ++closeBracket : closeBracket;
        }
        return openBracket == closeBracket && isRoundBracketsPositionsCorrect(source);
    }

    /**
     * проверка на корректное расположение скобок в строке
     */
    public static boolean isRoundBracketsPositionsCorrect(String source) {
        return !(source.indexOf(")") < source.indexOf("(")) || !(source.lastIndexOf("(") > source.lastIndexOf(")"));
    }

    /**
     * возвращает результат работы методов (одной строкой):
     * isFirstMathSymbol для символа *
     * isFirstMathSymbol для символа /
     * isLastMathSymbol для символа *
     * isLastMathSymbol для символа /
     * isFirstMathSymbol для символа +
     * isLastMathSymbol для символа +
     * isLastMathSymbol для символа -
     */
    public static boolean isMathSymbolsCorrect(String source) {
        return isFirstMathSymbol(source, "*") && isFirstMathSymbol(source, "/") && isLastMathSymbol(source, "*") &&
                isLastMathSymbol(source, "/") && isFirstMathSymbol(source, "+") && isLastMathSymbol(source, "+") &&
                isLastMathSymbol(source, "-");
    }

    /**
     * метод получает две входные строки, первая содержит математическое выражение, вторая
     * содержит символ на наличие которого надо проверить строку (* / + -) выяснить, начинается
     * ли строка с указанного символа, вернуть инвертированный boolean (если true вернуть
     * false и наоборот)
     */
    public static boolean isFirstMathSymbol(String source, String symbol) {
        return !(source.trim().startsWith(symbol));
    }

    /**
     * метод получает две входные строки, первая содержит математическое выражение, вторая содержит
     * символ на наличие которого надо проверить строку (* / + -) выяснить, заканчивается ли строка
     * указанным символом, вернуть инвертированный boolean (если true вернуть false и наоборот)
     */
    public static boolean isLastMathSymbol(String source, String symbol) {
        return !(source.trim().endsWith(symbol));
    }
}
