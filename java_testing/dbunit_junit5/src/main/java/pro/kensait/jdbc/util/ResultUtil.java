package pro.kensait.jdbc.util;

import java.util.List;

public class ResultUtil {
    private static final String RESULT_HEADER = System.getProperty("line.separator") +
            "=== Query Execution Result ===";
    private static final String RESULT_FOOTER = System.getProperty("line.separator");
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final int LINE_LENGTH = 90;

    public static void showSingleResult(Object obj) {
        System.out.println(RESULT_HEADER);
        System.out.println(formatLine(obj.toString()));
        System.out.println(RESULT_FOOTER);
    }

    public static void showSingleResult(String keyInfo, Object obj) {
        System.out.println(RESULT_HEADER);
        System.out.println(keyInfo + " => " + obj.toString());
    }

    public static void showEntity(Object obj) {
        System.out.println(RESULT_HEADER);
        if (obj == null) {
            System.out.println("The entity is null.");
        } else {
            System.out.println(formatLine(obj.toString()));
        }
        System.out.println(RESULT_FOOTER);
    }

    public static void showEntityList(List<?> objList) {
        System.out.println(RESULT_HEADER);
        for (Object obj : objList) {
            System.out.println(formatLine(obj.toString()));
        }
        System.out.println(RESULT_FOOTER);
    }

    public static void showColumns(Object[] columns) {
        System.out.println(RESULT_HEADER);
        System.out.println(formatLineRemovedLastComma(columns));
    }

    public static void showColumnsList(List<Object[]> columnsList) {
        System.out.println(RESULT_HEADER);
        for (Object[] columns : columnsList) {
            System.out.println(formatLineRemovedLastComma(columns));
        }
        System.out.println(RESULT_FOOTER);
    }

    private static String formatLineRemovedLastComma(Object[] columns) {
        String str = "";
        for (Object column : columns) {
            if (column == null)
                continue;
            str = str + column.toString() + ", ";
        }
        return formatLineWithLineBreaks(str.replaceAll(", $", ""));
    }

    private static String formatLine(String input) {
        StringBuilder formattedOutput = new StringBuilder();
        int indentLevel = 0;

        for (char ch : input.toCharArray()) {
            switch (ch) {
                case '[':
                    formattedOutput.append("[" + LINE_SEP);
                    indentLevel++;
                    formattedOutput.append("    ".repeat(indentLevel));
                    break;
                case ']':
                    formattedOutput.append(LINE_SEP);
                    if (0 < indentLevel) indentLevel--;
                    formattedOutput.append("    ".repeat(indentLevel));
                    formattedOutput.append("]");
                    break;
                case ',':
                    formattedOutput.append("," + LINE_SEP);
                    formattedOutput.append("   ");
                    if (0 < indentLevel)
                        formattedOutput.append("    ".repeat(indentLevel - 1));
                    break;
                default:
                    formattedOutput.append(ch);
                    break;
            }
        }

        return formattedOutput.toString();
    }

    private static String formatLineWithLineBreaks(String line) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < line.length()) {
            int end = index + LINE_LENGTH;
            if (line.length() < end) {
                end = line.length();
            } else {
                // LINE_LENGTHを超えた次のカンマの位置を探す
                int commaIndex = line.indexOf(",", end);
                if (commaIndex != -1) {
                    end = commaIndex + 2; // カンマの直後で改行
                }
            }
            result.append(line.substring(index, end));
            if (end < line.length()) {
                result.append(System.getProperty("line.separator"));
                result.append("    ");
            }
            index = end;
        }
        return result.toString();
    }
}