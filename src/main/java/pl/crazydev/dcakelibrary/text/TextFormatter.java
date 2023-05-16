package pl.crazydev.dcakelibrary.text;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TextFormatter {
    public static String color(String text) {
        Pattern pattern = Pattern.compile("<#[a-fA-F0-9]{6}>");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, ChatColor.of(color.substring(1, color.length() - 1)) + "");
            matcher = pattern.matcher(text);
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String removeFileExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    public static String toRoman(int number) {

        if (number < 1 || number > 3999) {
            return "";
        }

        String[] romanSymbols = {
                "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        int[] arabicValues = {
                1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        };

        StringBuilder romanNumber = new StringBuilder();

        for (int i = 0; i < arabicValues.length; i++) {
            while (number >= arabicValues[i]) {
                romanNumber.append(romanSymbols[i]);
                number -= arabicValues[i];
            }
        }

        return romanNumber.toString();
    }
}
