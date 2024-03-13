package regex.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import regex.helpers.HelpersFunctions;
import regex.tokens.Identifier;
import regex.tokens.Constant;

public class Lexer {
  private final static String KEYWORD = "KEYWORD";
  private final static String OPERATOR = "OPERATOR";
  private final static String DELIMITER = "DELIMITER";
  private final static String RELATIONAL = "RELATIONAL";

  static HashMap<String, String> keywords = new HashMap<String, String>() {
    {
      put("SELECT", KEYWORD);
      put("FROM", KEYWORD);
      put("IN", KEYWORD);
      put("WHERE", KEYWORD);
      put("AND", KEYWORD);
      put("OR", KEYWORD);
      put("CREATE", KEYWORD);
      put("TABLE", KEYWORD);
      put("CHAR", KEYWORD);
      put("NUMERIC", KEYWORD);
      put("NOT", KEYWORD);
      put("NULL", KEYWORD);
      put("CONSTRAINT", KEYWORD);
      put("KEY", KEYWORD);
      put("PRIMARY", KEYWORD);
      put("FOREIGN", KEYWORD);
      put("REFERENCES", KEYWORD);
      put("INSERT", KEYWORD);
      put("INTO", KEYWORD);
      put("VALUES", KEYWORD);

      put(",", DELIMITER);
      put(".", DELIMITER);
      put("(", DELIMITER);
      put(")", DELIMITER);
      put("'", DELIMITER);
      put("‘", DELIMITER);
      put("\"", DELIMITER);
      put("’", DELIMITER);

      put("+", OPERATOR);
      put("*", OPERATOR);
      put("/", OPERATOR);
      put("-", OPERATOR);

      put("=", RELATIONAL);
      put(">", RELATIONAL);
      put("<", RELATIONAL);
      put("<=", RELATIONAL);
      put(">=", RELATIONAL);
    }
  };

  public static String MatchWordType(String word) {
    return keywords.get(word);
  }

  public static String[] MatchWordArrayType(String[] wordsArray, int lineNumber) {
    ArrayList<String> categoryArrayList = new ArrayList<>();
    ArrayList<Identifier> identifiersArrayList = new ArrayList<>();
    ArrayList<Constant> constantsArrayList = new ArrayList<>();
    int columnNumber = 0;

    for (String word : wordsArray) {
      String category = Lexer.MatchWordType(word);
      switch (category) {
        case KEYWORD:
          break;

        case OPERATOR:
          break;

        case RELATIONAL:
          break;

        case DELIMITER:
          break;
        case null:
          String regex = "^'.*'$";

          Pattern pattern = Pattern.compile(regex);

          Matcher matcher = pattern.matcher(word);

          if (matcher.matches()) {
            System.out.println("La cadena comienza y termina con una comilla.");
          } else {
            System.out.println(word);
            System.out.println("La cadena no cumple con el patrón especificado.");
          }
          if (!word.contains("'")) {
            Identifier identifier = new Identifier(word, lineNumber, columnNumber);
            identifiersArrayList.add(identifier);
          } else {
            Constant constant = new Constant(word, lineNumber, columnNumber);
            constantsArrayList.add(constant);
          }
          break;
        default:
          break;
      }
      columnNumber += word.length() + 1;
    }
    HelpersFunctions.printArray(identifiersArrayList.toArray());
    HelpersFunctions.printArray(constantsArrayList.toArray());
    return HelpersFunctions.toStringArray(categoryArrayList.toArray());
  }
}
