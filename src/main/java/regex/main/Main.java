package regex.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regex.helpers.HelpersFunctions;
import regex.lexer.Lexer;
import regex.tokens.Identifier;

public class Main {
  public static void main(String[] args) {
    Identifier id = new Identifier("asdf", 1, 1);
    Identifier id2 = new Identifier("asdf", 1, 1);
    Identifier id3 = new Identifier("asdf", 1, 1);
    System.out.println(id);
    System.out.println(id2);
    System.out.println(id3);

    String txt = "SELECT";
    String regex = "(‘|’|') ?[a-zA-Z@!\\$\\%#\\\\\\(\\)_\\-0-9\\[\\]\\{\\} ?]*(’|'|‘)|\\b\\w+\\b|(,|.)";

    String[] words = GetFiltereWords(regex, txt);

    String[] categoriesArray = Lexer.MatchWordArrayType(words);
    printArray(categoriesArray);
  }

  public static void printArray(Object[] array) {
    for (Object element : array) {
      System.out.println(element);
    }
  }

  public static String[] GetFiltereWords(String regex, String text) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    ArrayList<String> words = new ArrayList<>();
    while (matcher.find()) {
      if (!matcher.group().isBlank()) {
        words.add(matcher.group());
      }
    }
    return HelpersFunctions.toStringArray(words.toArray());
  }
}
