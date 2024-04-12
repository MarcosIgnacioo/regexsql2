package regex.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import regex.tokens.*;

public class Parser {
  static int[][] tableColumnRowRules = new int[16][1];
  static HashMap<Integer, HashMap<Integer, Integer[]>> syntaxTable = new HashMap<>();

  static HashMap<Integer, String> keywordsCodes = new HashMap<>();

  static HashMap<Integer, ArrayList<Integer>> columnRules = new HashMap<>();

  public ArrayList<Token> tokensArrayList = new ArrayList<>();
  public int position = 0;
  public Token currentToken;
  public Token nextToken;

  public Parser(ArrayList<Token> tokensArrayList, int query) {
    fillTable();
    this.tokensArrayList = tokensArrayList;
    Stack<Integer> rulesStack = new Stack<>();
    begin(rulesStack, query);
  }

  public void readFullToken() {
    readNextToken();
    while (currentToken != null) {
      System.out.println(currentToken.tokenParser);
      readNextToken();
    }
    System.out.println(position);
    System.out.println(currentToken);
  }

  public Stack<Integer> begin(Stack<Integer> stack, int query) {
    stack.push(199);
    stack.push(300);
    readNextToken();
    Integer stackState = null;
    do {
      stackState = stack.pop();
      int k = this.currentToken.tokenValue;
      if (stackState < 300 || stackState == 199) {
        if (stackState == k) {
          readNextToken();
        } else {
          System.out.println(
              "ERROR EN LINEA: "
                  + currentToken.line
                  + " SE ESPERABA "
                  + keywordsCodes.get(stackState)
                  + " SE RECIBIO "
                  + currentToken.tokenParser);
          System.out.println(currentToken.tokenValue);
          System.out.println(stackState);
        }
      } else {
        // Si la celda es vacia
        if (syntaxTable.get(stackState).get(k) != null) {
          Integer[] rulesInCurrentInstruction = syntaxTable.get(stackState).get(k);
          // Checa si alguna de las reglas no es 99
          if (rulesInCurrentInstruction != null && !checkIfFinal(rulesInCurrentInstruction)) {
            addToStackInReverse(stack, rulesInCurrentInstruction);
          }
        } else {
          // Celda vacia signfica error
          System.out.println("===");
          stack.forEach(System.out::println);
          System.out.println(stackState);
          System.out.println("===");
          System.out.println("ERROR EN LINEA: " + currentToken.line);
          System.out.println("PALABRA O CARACTER ORIGEN DEL ERROR: " + currentToken.tokenParser);
          Set<Integer> expectedTokens = syntaxTable.get(stackState).keySet();
          System.out.println("SE ESPERABA ALGUNO DE LAS SIGUIENTES PALABRAS RESERVADAS O SIMBOLOS");
          System.out.println("/////////");
          expectedTokens.forEach(System.out::println);
          System.out.println("/////////");
          System.out.println(getErrors(expectedTokens));
          break;
        }
      }
    } while (stackState != 199);
    return stack;
  }

  public String getErrors(Set<Integer> expectedTokens) {
    String errorLog = "[";
    for (int i = 0; i < expectedTokens.size(); i++) {
      String match = matchRule((int) expectedTokens.toArray()[i]);
      String isFinal = i != expectedTokens.size() - 1 ? ", " : "";
      if (match != null) {
        errorLog += "`" + match + "`" + isFinal;
      }
    }
    return errorLog + "]";
  }

  // Lee el siguiente token que hay en la arraylist; Actualizar nuestro token actual (currentToken)
  // y el siguiente token (nextToken). Si ya se han leido todos los tokens (Es decir que la posicion
  // + 1 es mayor al tama;o de la arraylist) retorna null, que en este contexto lo contaremos como
  // nuestro $ (Final del query)
  public void readNextToken() {
    if (position == tokensArrayList.size() - 1) {
      this.currentToken = tokensArrayList.get(position++);
      this.nextToken = null;
      return;
    }
    if (position + 1 >= tokensArrayList.size()) {
      Token lastToken = new Token("$", "");
      lastToken.tokenValue = 199;
      this.currentToken = lastToken;
      this.nextToken = null;
      return;
    } else {
      this.currentToken = tokensArrayList.get(position++);
      this.nextToken = tokensArrayList.get(position);
      return;
    }
  }

  public String matchRule(int code) {
    switch (code) {
      case 300:
        return "LA SENTENCIA NO CUENTA CON SELECT";
      case 301:
        return "B | *";
      case 302:
        return "C D";
      case 303:
        return ", B | λ";
      case 304:
        return "i E";
      case 305:
        return ". i | λ";
      case 306:
        return "G H";
      case 307:
        return ", F | λ";
      case 308:
        return "i I";
      case 309:
        return "i | λ";
      case 310:
        return "w K | λ";
      case 311:
        return "L V";
      case 312:
        return "P K | λ";
      case 313:
        return "C M";
      case 314:
        return "N O | n ( Q )";
      case 315:
        return "< | > | = | ...";
      case 316:
        return "C | ‘ R ‘ | T";
      case 317:
        return "y | o";
      case 318:
        return "a";
      case 319:
        return "d";
      default:
        break;
    }
    return keywordsCodes.get(code);
  }

  // Funcion que sirve para llenar el hashmap de las Reglas
  // El cual tiene de clave: el numero de la regla (Integer)
  // Y de valor: una ArrayList de tipo Integer que tiene los valores de las tablas (Terminales u
  // otras reglas)
  private static void fillTable() {
    syntaxTable.put(
        300,
        new HashMap<Integer, Integer[]>() {
          {
            put(10, new Integer[] {10, 301, 11, 306, 310});
          }
        });
    syntaxTable.put(
        301,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {302});
            put(72, new Integer[] {72});
          }
        });
    syntaxTable.put(
        302,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {304, 303});
          }
        });
    syntaxTable.put(
        303,
        new HashMap<Integer, Integer[]>() {
          {
            put(11, new Integer[] {99});
            put(50, new Integer[] {50, 302});
            put(199, new Integer[] {99});
          }
        });
    syntaxTable.put(
        304,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {4, 305});
          }
        });
    syntaxTable.put(
        305,
        new HashMap<Integer, Integer[]>() {
          {
            put(8, new Integer[] {99});
            put(11, new Integer[] {99});
            put(13, new Integer[] {99});
            put(14, new Integer[] {99});
            put(15, new Integer[] {99});
            put(50, new Integer[] {99});
            put(51, new Integer[] {51, 4});
            put(53, new Integer[] {99});
            put(199, new Integer[] {99});
          }
        });
    syntaxTable.put(
        306,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {308, 307});
          }
        });
    syntaxTable.put(
        307,
        new HashMap<Integer, Integer[]>() {
          {
            put(12, new Integer[] {99});
            put(50, new Integer[] {50, 306});
            put(53, new Integer[] {99});
            put(199, new Integer[] {99});
          }
        });
    syntaxTable.put(
        308,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {4, 309});
          }
        });
    syntaxTable.put(
        309,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {4});
            put(12, new Integer[] {99});
            put(50, new Integer[] {99});
            put(53, new Integer[] {99});
            put(199, new Integer[] {99});
          }
        });
    syntaxTable.put(
        310,
        new HashMap<Integer, Integer[]>() {
          {
            put(12, new Integer[] {12, 311});
            put(53, new Integer[] {99});
            put(199, new Integer[] {99});
          }
        });

    syntaxTable.put(
        311,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {313, 312});
          }
        });

    syntaxTable.put(
        312,
        new HashMap<Integer, Integer[]>() {
          {
            put(14, new Integer[] {317, 311});
            put(15, new Integer[] {317, 311});
            put(53, new Integer[] {99});
            put(199, new Integer[] {99});
          }
        });

    syntaxTable.put(
        313,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {304, 314});
          }
        });

    syntaxTable.put(
        314,
        new HashMap<Integer, Integer[]>() {
          {
            put(8, new Integer[] {315, 316});
            put(13, new Integer[] {13, 52, 300, 53});
          }
        });

    syntaxTable.put(
        315,
        new HashMap<Integer, Integer[]>() {
          {
            put(8, new Integer[] {8});
          }
        });

    syntaxTable.put(
        316,
        new HashMap<Integer, Integer[]>() {
          {
            put(4, new Integer[] {304});
            put(54, new Integer[] {54, 318, 54});
            put(62, new Integer[] {318});
            put(61, new Integer[] {319});
          }
        });

    syntaxTable.put(
        317,
        new HashMap<Integer, Integer[]>() {
          {
            put(14, new Integer[] {14});
            put(15, new Integer[] {15});
          }
        });

    syntaxTable.put(
        318,
        new HashMap<Integer, Integer[]>() {
          {
            put(62, new Integer[] {62});
          }
        });

    syntaxTable.put(
        319,
        new HashMap<Integer, Integer[]>() {
          {
            put(61, new Integer[] {61});
          }
        });

    // ctrl-v g-ctrl-a
    keywordsCodes.put(4, "IDENTIFICADOR");
    keywordsCodes.put(10, "SELECT");
    keywordsCodes.put(11, "FROM");
    keywordsCodes.put(12, "WHERE");
    keywordsCodes.put(13, "IN");
    keywordsCodes.put(14, "AND");
    keywordsCodes.put(15, "OR");
    keywordsCodes.put(16, "CREATE");
    keywordsCodes.put(17, "TABLE");
    keywordsCodes.put(18, "CHAR");
    keywordsCodes.put(19, "NUMERIC");
    keywordsCodes.put(20, "NOT");
    keywordsCodes.put(21, "NULL");
    keywordsCodes.put(22, "CONSTRAINT");
    keywordsCodes.put(23, "KEY");
    keywordsCodes.put(24, "PRIMARY");
    keywordsCodes.put(25, "FOREIGN");
    keywordsCodes.put(26, "REFERENCES");
    keywordsCodes.put(27, "INSERT");
    keywordsCodes.put(28, "INTO");
    keywordsCodes.put(29, "VALUES");

    keywordsCodes.put(50, ",");
    keywordsCodes.put(51, ".");
    keywordsCodes.put(52, "( o )");
    keywordsCodes.put(53, "( o )");
    keywordsCodes.put(54, "‘");
    keywordsCodes.put(55, "'");

    keywordsCodes.put(61, "CONSTANTE (NUMERICA)");
    keywordsCodes.put(62, "CONSTANTE (ALFANUMERICA)");

    keywordsCodes.put(70, "+");
    keywordsCodes.put(71, "-");
    keywordsCodes.put(72, "*");
    keywordsCodes.put(73, "/");

    keywordsCodes.put(81, ">");
    keywordsCodes.put(82, "<");
    keywordsCodes.put(83, "=");
    keywordsCodes.put(84, ">=");
    keywordsCodes.put(85, "<=");
    keywordsCodes.put(8, "RELACIONAL");
  }

  public boolean isUpperCase(String text) {
    return text.equals(text.toUpperCase());
  }

  public void addToStackInReverse(Stack<Integer> stack, Integer[] array) {
    for (int i = array.length - 1; i >= 0; i--) {
      stack.push(array[i]);
    }
  }

  public boolean checkIfFinal(Integer[] array) {
    boolean isFinal = false;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == 99) {
        isFinal = true;
        break;
      }
    }
    return isFinal;
  }

  public boolean isEndOfQuery(String character) {
    return (int) character.charAt(0) + 63 == 99;
  }
}
