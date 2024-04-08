package regex.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import regex.helpers.HelpersFunctions;
import regex.tokens.Constant;
import regex.tokens.Delimiters;
import regex.tokens.Identifier;
import regex.tokens.Illegal;
import regex.tokens.Keywords;
import regex.tokens.Operators;
import regex.tokens.Relationals;
import regex.tokens.Token;

public class Lexer {
  private static final String KEYWORD = "KEYWORD";
  private static final String OPERATOR = "OPERATOR";
  private static final String DELIMITER = "DELIMITER";
  private static final String RELATIONAL = "RELATIONAL";
  private static int wordNumber = 1;

  public static ArrayList<String> categoryArrayList = new ArrayList<>();
  public ArrayList<Token> everythingArrayList = new ArrayList<>();

  public static ArrayList<Identifier> identifiersArrayList = new ArrayList<>();
  public static ArrayList<Constant> constantsArrayList = new ArrayList<>();
  public static ArrayList<Keywords> keywordsArrayList = new ArrayList<>();
  public static ArrayList<Operators> operatorsArrayList = new ArrayList<>();
  public static ArrayList<Relationals> relationalsArrayList = new ArrayList<>();
  public static ArrayList<Delimiters> delimitersArrayList = new ArrayList<>();
  public static ArrayList<Illegal> illegalsArrayList = new ArrayList<>();

  static HashMap<String, String> keywords =
      new HashMap<String, String>() {
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

  public void MatchWordArrayType(String[] wordsArray, int lineNumber) {
    for (String word : wordsArray) {
      String category = Lexer.MatchWordType(word);
      Token register = new Token();
      switch (category) {
        case KEYWORD:
          Keywords keyword = new Keywords(word, wordNumber++, lineNumber);
          register = keyword;
          register.tokenValue = keyword.value;
          keywordsArrayList.add(keyword);
          break;
        case OPERATOR:
          Operators operator = new Operators(word, wordNumber++, lineNumber);
          register = operator;
          register.tokenValue = operator.value;
          operatorsArrayList.add(operator);
          break;
        case RELATIONAL:
          Relationals relational = new Relationals(word, wordNumber++, lineNumber);
          register = relational;
          register.tokenValue = relational.tokenType;
          relationalsArrayList.add(relational);
          break;
        case DELIMITER:
          Delimiters delimiter = new Delimiters(word, wordNumber++, lineNumber);
          register = delimiter;
          register.tokenValue = delimiter.value;
          delimitersArrayList.add(delimiter);
          break;
        case null:
          String regex = "^(‘|’|').*(’|'|‘)$|\\d*";
          Pattern pattern = Pattern.compile(regex);
          Matcher matcher = pattern.matcher(word);
          boolean isConstant = matcher.matches();
          if (isConstant) {
            Constant constant = new Constant(word, wordNumber++, String.valueOf(lineNumber));
            register = constant;
            register.tokenValue = constant.type;
            boolean isAlready =
                containsConstant(constantsArrayList, constant, String.valueOf(lineNumber));
            if (!isAlready) {
              constantsArrayList.add(constant);
            }
          } else {
            regex = "[a-zA-Z]*";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(word);
            boolean isLegal = matcher.matches();
            if (isLegal) {
              String lineNumberString = String.valueOf(lineNumber);
              Identifier identifier = new Identifier(word, lineNumberString, wordNumber++);
              boolean isAlready =
                  containsIdentifier(identifiersArrayList, identifier, String.valueOf(lineNumber));
              register = identifier;
              register.tokenValue = identifier.tokenType;
              if (!isAlready) {
                identifiersArrayList.add(identifier);
              }
            } else {
              // No sabemos la clasificacion de # por lo que lo marcara como ilegal pero lo
              // suprimimos porque no aparece en la tabla pero aparece en
              // los queries (que suponemos que son siempre correctos)
              if (word.equals("#")) {
                continue;
              }
              Illegal illegal = new Illegal(word, lineNumber);
              register = illegal;
              register.tokenValue = illegal.value;
              illegalsArrayList.add(illegal);
            }
          }
          break;
        default:
          break;
      }
      if (register != null) {
        everythingArrayList.add(register);
      }
    }
  }

  // refactorizar a que todas las clases tengan un equals y simplemente creamos un
  // metodo generico que utilice el equals de cada clase y como los object tienen
  // el metodo equals pues va afuncionar
  public static boolean containsDelimiter(
      ArrayList<Delimiters> delimitersArrayList, Delimiters delimiter, int lineNumber) {
    boolean isAlready = false;
    for (int i = 0; i < delimitersArrayList.size(); i++) {
      if (delimitersArrayList.get(i).delimiter.equals(delimiter.delimiter)) {
        isAlready = true;
        break;
      }
    }
    return isAlready;
  }

  public static boolean containsIdentifier(
      ArrayList<Identifier> identifiersArrayList, Identifier identifier, String lineNumber) {
    boolean isAlready = false;
    for (int i = 0; i < identifiersArrayList.size(); i++) {
      if (identifiersArrayList.get(i).identifier.equals(identifier.identifier)) {
        identifiersArrayList.get(i).line += ", " + lineNumber;
        identifier.id = identifiersArrayList.get(i).id;
        Identifier.serial--;
        isAlready = true;
        break;
      }
    }
    return isAlready;
  }

  public static boolean containsConstant(
      ArrayList<Constant> constantsArrayList, Constant constant, String lineNumber) {
    boolean isAlready = false;
    for (int i = 0; i < constantsArrayList.size(); i++) {
      if (constantsArrayList.get(i).equals(constant)) {
        constantsArrayList.get(i).line += lineNumber;
        constant.id = constantsArrayList.get(i).id;
        Constant.serial--;
        isAlready = true;
        break;
      }
    }
    return isAlready;
  }

  public void printAll() {
    System.out.println("NO | LINEA | TOKEN | TIPO | CODIGO");
    HelpersFunctions.printArray(everythingArrayList.toArray());
  }

  public void printIdentifiers() {
    System.out.println("Identificador | Valor | Lineas |");
    identifiersArrayList.forEach(identifier -> System.out.println(identifier.tableFormat()));
  }

  public void printConstants() {
    System.out.println("No | Constante | Tipo | Valor ");
    constantsArrayList.forEach(constant -> System.out.println(constant.tableFormat()));
  }
}
