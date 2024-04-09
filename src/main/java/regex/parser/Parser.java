package regex.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import regex.tokens.*;

public class Parser {
  static int[][] tableColumnRowRules = new int[16][1];
  static HashMap<Integer, HashMap<Integer, Integer[]>> syntaxTable = new HashMap<>();

  static HashMap<Integer, ArrayList<Integer>> columnRules = new HashMap<>();

  public ArrayList<Token> tokensArrayList = new ArrayList<>();
  public int position = 0;
  public Token currentToken;
  public Token nextToken;

  public Parser(ArrayList<Token> tokensArrayList) {
    fillTable();
    this.tokensArrayList = tokensArrayList;
    Stack<Integer> rulesStack = new Stack<>();
    startGamer2(rulesStack, 0);
    // readFullToken();
  }

  public Parser(ArrayList<Token> tokensArrayList, int query) {
    fillTable();
    this.tokensArrayList = tokensArrayList;
    Stack<Integer> rulesStack = new Stack<>();
    startGamer2(rulesStack, query);
    // readFullToken();
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

  public Stack<Integer> startGamer2(Stack<Integer> stack, int query) {
    stack.push(199);
    stack.push(300);
    readNextToken();
    Integer stackState = null;
    do {
      // System.out.println("");
      // System.out.println("CURRENT TOKEN: " + currentToken.tokenParser);
      // System.out.println("+++");
      // System.out.println("stack");
      // System.out.println("+++");
      // stack.forEach(System.out::println);
      stackState = stack.pop();
      int k = this.currentToken.tokenValue;
      // if ((currentToken.tokenValue == 199 && stackState != 199)
      //     || (currentToken.tokenValue != 199 && stackState == 199)) {
      //   System.out.println("ERROR");
      //   break;
      // }
      // System.out.println("");
      // System.out.println("StackSTATE");
      // System.out.println("");
      // stack.forEach(
      //     s -> {
      //       System.out.println("===");
      //       System.out.println(s);
      //     });
      // System.out.println("===");
      // System.out.println("");
      final Object[][] table = new String[5][];
      table[0] = new String[] {"TOKEN", "|", "VALUE"};
      table[1] =
          new String[] {
            "------------------------------", "----------------------------", "",
          };
      table[2] =
          new String[] {String.valueOf(currentToken.tokenValue), "|", currentToken.tokenParser};
      table[3] =
          new String[] {
            "------------------------------", "----------------------------", "",
          };
      table[4] = new String[] {String.valueOf("CURRENT RULE"), "|", String.valueOf(stackState)};
      // for (final Object[] row : table) {
      //   System.out.format("%15s%15s%15s%n", row);
      // }

      if (stackState < 300 || stackState == 199) {
        if (stackState == k) {
          readNextToken();
        } else {
          System.out.println(
              "ERROR EN LINEA: "
                  + currentToken.line
                  + " SE ESPERABA "
                  + stackState
                  + " SE RECIBIO "
                  + currentToken.tokenParser);
          System.out.println(currentToken.tokenValue);
          System.out.println(stackState);
        }
      } else {
        if (syntaxTable.get(stackState).get(k) != null) {
          Integer[] rulesInCurrentInstruction = syntaxTable.get(stackState).get(k);

          if (rulesInCurrentInstruction != null && !checkIfFinal(rulesInCurrentInstruction)) {
            addToStackInReverse(stack, rulesInCurrentInstruction);
          }
        } else {
          System.out.println("ERROR EN LINEA: " + currentToken.line);
          System.out.println("SE ESPERABAN LAS SIGUIENTES REGLAS");
          stack.forEach(System.out::println);
        }
      }
    } while (stackState != 199);
    return stack;
  }

  public Stack<Integer> startGamer(Stack<Integer> stack) {
    stack.add(199);
    stack.add(300);
    readNextToken();
    Integer currentRule = stack.pop();
    while (currentRule != 199) {
      System.out.println(currentRule);
      HashMap<Integer, Integer[]> rulesRow = syntaxTable.get(currentRule);
      int tokenValue = currentToken.tokenValue;

      if (rulesRow != null) {
        Integer[] rules = rulesRow.get(tokenValue);
        if (rules != null) {
          addToStackInReverse(stack, rulesRow.get(tokenValue));
        }
      }
      if (currentRule >= 300) {
        readNextToken();
      }
      currentRule = stack.pop();
    }

    // do {
    //
    //   System.out.println("PREV RULE: " + currentRule);
    //   currentRule = stack.pop();
    //   System.out.println("CURR RULE: " + currentRule);
    //   int tokenValue = currentToken.tokenValue;
    //   HashMap<Integer, Integer[]> rulesRow = syntaxTable.get(currentRule);
    //   if (rulesRow != null) {
    //     if (rulesRow.get(tokenValue) != null) {
    //       addToStackInReverse(stack, rulesRow.get(tokenValue));
    //     }
    //   }
    //   readNextToken();
    // } while (currentRule != 199);
    System.out.println("STopped");
    return null;
  }

  public Stack<Integer> start(Stack<Integer> stack) {
    readNextToken();
    stack.add(199); // Se agrega el valor de $
    stack.add(300); // La instruccion de Query
    Integer stackState = null;
    do {
      System.out.println("STACK");
      stack.forEach(
          s -> {
            System.out.println("    ------");
            System.out.println("    |" + s);
            System.out.println("    |" + matchRule(s));
          });
      System.out.println("    ------");
      System.out.println("STACK STATE ANTES: " + stackState);
      stackState = stack.pop();
      int k = currentToken.tokenValue;
      System.out.println("STACK STATE DESPUES: " + stackState);
      System.out.println("TOKEN VALUE: " + currentToken.tokenParser);
      System.out.println("========================");
      if (stackState >= 300) {
        Integer[] rules = syntaxTable.get(stackState).get(currentToken.tokenValue);
        if (rules != null) {
          System.out.println("RULES BEING ADDED: ");
          for (Integer var : rules) {
            System.out.println("     " + var);
          }
          addToStackInReverse(stack, rules);
        } else {
          // System.out.println("No hay ya reglas para esta coordenada");
        }
      } else {
        if (stackState == 99) {
          System.out.println("whasta");
        }
        readNextToken();
      }
    } while (stackState != 199);
    System.out.println("wdasf");
    tokensArrayList.forEach(System.out::println);
    return stack;
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
        return "s A f F J";
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
    return "No rule para esto";
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
