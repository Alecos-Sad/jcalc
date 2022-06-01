package edu.javagroup.jcalc.lines;

/**
 * Класс для подготовки строки
 */

public class LinePreparing {

    /**
     * 1. если строка содержит пробелы, получить результат работы метода removeSpaces, записать
     * результат в вашу строку
     * 2. если строка содержит запятые, получить результат работы метода replaceCommas записать
     * результат в вашу строку
     * 3. записать в вашу строку результат работы метода leaveMathSymbols
     * 4. если строка начинается на символ +, убрать этот символ за ненадобностью
     * 5. вернуть строку
     */
    public static String linePreparing(String str) {
        if (str.contains(" ")) {
            str = removeSpaces(str);
        }
        if (str.contains(",")) {
            str = replaceCommas(str);
        }
        if (str.startsWith("+")) {
            str.replace("+", "");
        }
        str = leaveMathSymbol(str);
        return str;
    }

    /**
     * возвращает пришедшую строку без пробелов
     */
    public static String removeSpaces(String str) {
        if (str.contains(" ")) {
            str = str.replace(" ", "");
        }
        return str;
    }

    /**
     * возвращает пришедшую строку, в которой все запятые заменены на точки
     */
    public static String replaceCommas(String str) {
        if (str.contains(",")) {
            str = str.replace(",", ".");
        }
        return str;
    }

    /**
     * обрабатывает пришедшую строку, так, чтобы она содержала, только допустимые символы:
     * обработать полученную строку в методе trimTails
     * полученный результат вернуть в методе removeDuplicates
     */
    public static String leaveMathSymbol(String str) {
        StringBuilder result = new StringBuilder();
        String value = "0123456789.()*/+-";
        for (int i = 0; i < str.length(); i++) {
            if (value.contains(String.valueOf(str.charAt(i)))) {
                result.append(str.charAt(i));
            }
        }
        return removeDuplicates(trimTails(result.toString()));
    }

    /**
     * удаляет из строки лишние символы в начале и в конце строки
     * 1. учитывает минус перед самой первой цифрой
     * 2. учитывает наличие скобок
     */
    public static String trimTails(String source) {
        int indexFirstDigit = 0;
        int indexLastDigit = 0;

        for (int i = 0; i < source.length(); i++) {
            if (Character.isDigit(source.charAt(i))) {
                indexFirstDigit = i;
                break;
            }
        }
        for (int j = source.length() - 1; j > 0; j--) {
            if (Character.isDigit(source.charAt(j))) {
                indexLastDigit = j;
                break;
            }
        }
        int indexStart = source.indexOf('(') > -1 && indexFirstDigit > 0 && source.charAt(indexFirstDigit - 1) == '(' ?
                indexFirstDigit - 1 : indexFirstDigit;
        indexStart = indexFirstDigit > 0 && source.charAt(indexFirstDigit - 1) == '-' ?
                indexFirstDigit - 1 : indexStart;

        int indexFinish = source.indexOf(')', indexLastDigit) > -1 ?
                source.lastIndexOf(')') + 1 : indexLastDigit + 1;

        source = indexFirstDigit > 1 && source.charAt(indexFirstDigit - 1) == '(' && source.charAt(indexFirstDigit - 2) == '-' ?
                source.substring(indexFirstDigit - 2, indexFinish) : source.substring(indexStart, indexFinish);

        return source;
    }

    /**
     * получить строку, в которой все дубликаты символов * / + - заменены на единичные
     */
    public static String removeDuplicates(String source) {

        while (source.contains("++") || source.contains("--") || source.contains("+-") || source.contains("-+") ||
                source.contains("**") || source.contains(")(") || source.contains("()") || source.contains("//")) {
            source = source.replace("++", "+");
            source = source.replace("--", "-");
            source = source.replace("+-", "-");
            source = source.replace("-+", "-");
            source = source.replace("**", "*");
            source = source.replace(")(", ")*(");
            source = source.replace("()", "");
            source = source.replace("//", "/");
            source = source.replace("--", "+");
        }
        return source;
    }
}






