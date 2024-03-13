package regex.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import regex.helpers.HelpersFunctions;
import regex.tokens.Identifier;
import regex.tokens.Keywords;
import regex.tokens.Constant;
import regex.tokens.Delimiters;
import regex.tokens.Operators;
import regex.tokens.Relationals;

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
    ArrayList<String> everythingArrayList = new ArrayList<>();
    ArrayList<Identifier> identifiersArrayList = new ArrayList<>();
    ArrayList<Constant> constantsArrayList = new ArrayList<>();
    ArrayList<Keywords> keywordsArrayList = new ArrayList<>();
    ArrayList<Operators> operatorsArrayList = new ArrayList<>();
    ArrayList<Relationals> realtionalsArrayList = new ArrayList<>();
    ArrayList<Delimiters> delimitersArrayList = new ArrayList<>();
    int columnNumber = 0;

    for (String word : wordsArray) {
      String category = Lexer.MatchWordType(word);
      switch (category) {
        case KEYWORD:
          Keywords keyword = new Keywords(word);
          System.out.println(keyword);
          break;
        case OPERATOR:
          Operators operator = new Operators(word);
          System.out.println(operator);
          break;
        case RELATIONAL:
          Relationals relational = new Relationals(word);
          System.out.println(relational);
          break;
        case DELIMITER:
          Delimiters delimiter = new Delimiters(word);
          System.out.println(delimiter);
          break;
        case null:
          String regex = "^(‘|’|').*(’|'|‘)$";
          Pattern pattern = Pattern.compile(regex);
          Matcher matcher = pattern.matcher(word);
          boolean isConstant = matcher.matches();
          if (!isConstant) {
            Constant constant = new Constant(word, lineNumber, columnNumber);
            constantsArrayList.add(constant);
          } else {
            Identifier identifier = new Identifier(word, lineNumber, columnNumber);
            identifiersArrayList.add(identifier);
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

  public static boolean containsIdentifier(ArrayList<Identifier> identifiersArrayList, Object that) {
    Object[] identifierArray = identifiersArrayList.toArray();
    boolean containsIdentifier = false;
    for (Object objectIdentifier : identifierArray) {
      Identifier identifier = (Identifier) objectIdentifier;
      if (identifier.equals(that)) {
        containsIdentifier = true;
        break;
      }
    }
    return containsIdentifier;
  }
}
