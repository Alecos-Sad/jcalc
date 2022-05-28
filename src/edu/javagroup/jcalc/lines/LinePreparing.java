package edu.javagroup.jcalc.lines;

public class LinePreparing {

    public static String linePreparing(String str) {
// Таск 4 - Обработка пришедшей строки, с ее подготовкой для дальнейших операций в классах рассчетов.

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

    //work
    public static String removeSpaces(String str) {
        //возврат пришедшей строки с удаленными пробелами
        if (str.contains(" ")) {
            str = str.replace(" ", "");
        }
        return str;
    }

    //work
    public static String replaceCommas(String str) {
        // возврат пришедшей строки в которой все запятые заменены на точки
        if (str.contains(",")) {
            str = str.replace(",", ".");
        }
        return str;
    }

    //work
    public static String leaveMathSymbol(String str) {
        //возврат пришедшей строки содержащей только 0123456789.()*/+- и обработанной в методе trimTails
        StringBuilder result = new StringBuilder();
        String value = "0123456789.()*/+-";
        for (int i = 0; i < str.length(); i++) {
            if (value.contains(String.valueOf(str.charAt(i)))) {
                result.append(str.charAt(i));
            }
        }
        return removeDuplicates(trimTails(result.toString()));
    }

    //work
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






