package regex.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import regex.helpers.HelpersFunctions;
import regex.lexer.Lexer;
import regex.parser.*;

public class Main {
  public static void main(String[] args) throws Exception {
    for (int j = 0; j < HelpersFunctions.queriesError.length; j++) {
      String regex =
          "(‘|’|') ?[a-zA-Z@!\\$\\%#\\\\\\(\\)_\\-0-9\\[\\]\\{\\}?"
              + " ]*(’|'|‘)|>=|<=|[a-zA-Z0-9\\$@#!\\-\\*]+|(,|.)";
      String txt = HelpersFunctions.queriesError[j];
      String[] textSplitted = txt.split("\n");

      Lexer lexedText = new Lexer();
      for (int i = 0; i < textSplitted.length; i++) {
        String[] words = GetFilteredWords(regex, textSplitted[i]);
        lexedText.MatchWordArrayType(words, i + 1);
      }
      System.out.println("");
      System.out.println("QUERY NO: " + (j + 1));
      // ArrayList<Token> all = lexedText.everythingArrayList;
      System.out.println("");
      System.out.println(txt);
      Parser p = new Parser(lexedText.everythingArrayList, j + 1);
      // System.out.println("------------------------");
    }
  }

  public static String[] GetFilteredWords(String regex, String text) {
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
