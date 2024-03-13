package regex.tokens;

public class Keywords {

  public String keyword;

  private String symbol;

  private int value;

  public int number;

  public int tokenType = 1;

  public int line;

  public void getSymbol(String keyword) {
    switch (keyword) {
      case "SELECT":
        symbol = "s";
        break;
      case "FROM":
        symbol = "f";
        break;
      case "WHERE":
        symbol = "w";
        break;
      case "IN":
        symbol = "n";
        break;
      case "AND":
        symbol = "y";
        break;
      case "OR":
        symbol = "o";
        break;
      case "CREATE":
        symbol = "c";
        break;
      case "TABLE":
        symbol = "t";
        break;
      case "CHAR":
        symbol = "h";
        break;
      case "NUMERIC":
        symbol = "u";
        break;
      case "NOT":
        symbol = "e";
        break;
      case "NULL":
        symbol = "g";
        break;
      case "CONSTRAINT":
        symbol = "b";
        break;
      case "KEY":
        symbol = "k";
        break;
      case "PRIMARY":
        symbol = "p";
        break;
      case "FOREIGN":
        symbol = "j";
        break;
      case "REFERENCES":
        symbol = "l";
        break;
      case "INSERT":
        symbol = "m";
        break;
      case "INTO":
        symbol = "q";
        break;
      case "VALUES":
        symbol = "v";
        break;
    }
  }

  public void getValue(String keyword) {
    switch (keyword) {
      case "SELECT":
        value = 10;
        break;
      case "FROM":
        value = 11;
        break;
      case "WHERE":
        value = 12;
        break;
      case "IN":
        value = 13;
        break;
      case "AND":
        value = 14;
        break;
      case "OR":
        value = 15;
        break;
      case "CREATE":
        value = 16;
        break;
      case "TABLE":
        value = 17;
        break;
      case "CHAR":
        value = 18;
        break;
      case "NUMERIC":
        value = 19;
        break;
      case "NOT":
        value = 20;
        break;
      case "NULL":
        value = 21;
        break;
      case "CONSTRAINT":
        value = 22;
        break;
      case "KEY":
        value = 23;
        break;
      case "PRIMARY":
        value = 24;
        break;
      case "FOREIGN":
        value = 25;
        break;
      case "REFERENCES":
        value = 26;
        break;
      case "INSERT":
        value = 27;
        break;
      case "INTO":
        value = 28;
        break;
      case "VALUES":
        value = 29;
        break;
    }
  }

  public String toString() {
    return number + " | " + line + " | " + keyword + " | " + tokenType + " | " + value;
  }

  public Keywords(String keyword, int number, int line) {
    this.keyword = keyword;
    this.number = number;
    this.line = line;
    getSymbol(keyword);
    getValue(keyword);

  }
}
