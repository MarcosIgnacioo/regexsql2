package regex.lexer;

import java.util.ArrayList;
import java.util.HashMap;

import regex.helpers.HelpersFunctions;

public class Lexer {

  static HashMap<String, String> keywords = new HashMap<String, String>() {
    {
      put("SELECT", "KEYWORD");
      put("FROM", "KEYWORD");
      put("IN", "KEYWORD");
      put("WHERE", "KEYWORD");
      put("AND", "KEYWORD");
      put("OR", "KEYWORD");
      put("CREATE", "KEYWORD");
      put("TABLE", "KEYWORD");
      put("CHAR", "KEYWORD");
      put("NUMERIC", "KEYWORD");
      put("NOT", "KEYWORD");
      put("NULL", "KEYWORD");
      put("CONSTRAINT", "KEYWORD");
      put("KEY", "KEYWORD");
      put("PRIMARY", "KEYWORD");
      put("FOREIGN", "KEYWORD");
      put("REFERENCES", "KEYWORD");
      put("INSERT", "KEYWORD");
      put("INTO", "KEYWORD");
      put("VALUES", "KEYWORD");

      put(",", "DELIMITER");
      put(".", "DELIMITER");
      put("(", "DELIMITER");
      put(")", "DELIMITER");
      put("'", "DELIMITER");
      put("‘", "DELIMITER");
      put("\"", "DELIMITER");
      put("’", "DELIMITER");

      put("+", "OPERATOR");
      put("*", "OPERATOR");
      put("/", "OPERATOR");
      put("-", "OPERATOR");

      put("=", "RELATIONAL");
      put(">", "RELATIONAL");
      put("<", "RELATIONAL");
      put("<=", "RELATIONAL");
      put(">=", "RELATIONAL");
    }
  };

  public static String MatchWordType(String word) {
    return keywords.get(word);
  }

  public static String[] MatchWordArrayType(String[] wordsArray) {
    ArrayList<String> categoryArrayList = new ArrayList<>();
    ;
    // Hacer un array o un hashmap de cada uno de los tipos de cosas que pueen salir
    // en el query (delimitadores palabras claves enternos alfanumericos etc), luego
    // hacer un switch case de que dependiendo del tipo que sea cada categoria se
    // cree un nuevo elemento de ese tipo a la arraylist, luego al final se imprimen
    // cada arraylist una por una de manera que se aproveche el toString que tiene
    // cada clase para dar el formato deseado a como quiere que lo haga el profe
    //
    for (String word : wordsArray) {
      String category = Lexer.MatchWordType(word);
      categoryArrayList.add(category);
    }
    return HelpersFunctions.toStringArray(categoryArrayList.toArray());
  }
}
