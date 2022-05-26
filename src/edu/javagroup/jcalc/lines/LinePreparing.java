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
    public static String trimTails(String str) {
        //возврат пришедшей строки с удаленными лишними символами в начале и в конце строки
        int countStart = 0;
        int countFinish = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                countStart = i;
                break;
            }
        }
        for (int j = str.length() - 1; j >= 0; j--) {
            if (Character.isDigit(str.charAt(j))) {
                countFinish = j + 2;
                break;
            }
        }
        if ((str.charAt(countStart - 1) == '-') || (str.charAt(countStart - 1) == '(')) {
            --countStart;
        } else if ((str.charAt(countStart - 1) == '-') & (str.charAt(countStart - 2) == '(')) {
            countStart += 2;
        } else if ((str.charAt(countFinish + 1) == ')')) {
            ++countFinish;
        }

        return removeDuplicates(str.substring(countStart, countFinish));
    }

    public static String removeDuplicates(String str) {
        //возврат пришедшей строки в которой все дубликаты символов * / + - заменены на единичные
        for (int i = 1; i < str.length(); i++) {
            if (str.contains("++")) {
                str = str.replace("++", "+");
            }
            if (str.contains("--")) {
                str = str.replace("--", "+");
            }
            if (str.contains("+-")) {
                str = str.replace("+-", "-");
            }
            if (str.contains("-+")) {
                str = str.replace("-+", "-");
            }
            if (str.contains("**")) {
                str = str.replace("**", "*");
            }
            if (str.contains(")(")) {
                str = str.replace(")(", ")*(");
            }
            if (str.contains("()")) {
                str = str.replace("()", "");
            }
            if (str.contains("//")) {
                str = str.replace("//", "/");
            }
        }
        return str;
    }
}






