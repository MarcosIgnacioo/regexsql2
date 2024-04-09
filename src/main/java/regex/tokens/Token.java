package regex.tokens;

import java.util.HashMap;

public class Token {
  public String tokenParser;
  public int tokenValue;
  public String line;
  public HashMap<String, Integer> keywordCode =
      new HashMap<String, Integer>() {
        {
          put("SELECT", 10);
          put("FROM", 11);
          put("WHERE", 12);
          put("IN", 13);
          put("AND", 14);
          put("OR", 15);
          put("CREATE", 16);
          put("TABLE", 17);
          put("CHAR", 18);
          put("NUMERIC", 19);
          put("NOT", 20);
          put("NULL", 21);
          put("CONSTRAINT", 22);
          put("KEY", 23);
          put("PRIMARY", 24);
          put("FOREIGN", 25);
          put("REFERENCES", 26);
          put("INSERT", 27);
          put("INTO", 28);
          put("VALUES", 29);
          put(",", 50);
          put(".", 51);
          put("(", 52);
          put(")", 53);
          put("‘", 54);
          put("+", 83);
          put("-", 82);
          put("*", 80);
          put("/", 81);
          put("<", 81);
          put(">", 82);
          put("=", 83);
          put(">=", 84);
          put("<=", 85);
        }
      };
  ;

  public Token(String tokenParser, String line) {
    this.tokenParser = tokenParser;
    this.line = line;
  }

  public Token() {}

  public void setValue(String token) {

    switch (token) {
      case "SELECT":
        tokenValue = 10;
        break;
      case "FROM":
        tokenValue = 11;
        break;
      case "WHERE":
        tokenValue = 12;
        break;
      case "IN":
        tokenValue = 13;
        break;
      case "AND":
        tokenValue = 14;
        break;
      case "OR":
        tokenValue = 15;
        break;
      case "CREATE":
        tokenValue = 16;
        break;
      case "TABLE":
        tokenValue = 17;
        break;
      case "CHAR":
        tokenValue = 18;
        break;
      case "NUMERIC":
        tokenValue = 19;
        break;
      case "NOT":
        tokenValue = 20;
        break;
      case "NULL":
        tokenValue = 21;
        break;
      case "CONSTRAINT":
        tokenValue = 22;
        break;
      case "KEY":
        tokenValue = 23;
        break;
      case "PRIMARY":
        tokenValue = 24;
        break;
      case "FOREIGN":
        tokenValue = 25;
        break;
      case "REFERENCES":
        tokenValue = 26;
        break;
      case "INSERT":
        tokenValue = 27;
        break;
      case "INTO":
        tokenValue = 28;
        break;
      case "VALUES":
        tokenValue = 29;
        break;
      case ",":
        tokenValue = 50;
        break;
      case ".":
        tokenValue = 51;
        break;
      case "(":
        tokenValue = 52;
        break;
      case ")":
        tokenValue = 53;
        break;
      case "‘":
        tokenValue = 54;
        break;
      case "+":
        tokenValue = 83;
        break;
      case "-":
        tokenValue = 82;
        break;
      case "*":
        tokenValue = 80;
        break;
      case "/":
        tokenValue = 81;
        break;
      case "<":
        tokenValue = 81;
        break;
      case ">":
        tokenValue = 82;
        break;
      case "=":
        tokenValue = 83;
        break;
      case ">=":
        tokenValue = 84;
        break;
      case "<=":
        tokenValue = 85;
        break;
      case "ERROR CON DELIMITER":
        tokenValue = 999;
        break;
      case "ERROR CON IDENTIFIER":
        tokenValue = 999;
        break;
      case "ERROR CON CONSTANTE":
        tokenValue = 999;
        break;
      case "ERROR ILEGAL":
        tokenValue = 999;
        break;
      case "$":
        tokenValue = 199;
        break;
      case "":
        tokenValue = 1000;
        break;
      default:
        tokenValue = 4;
        break;
    }
  }
}
