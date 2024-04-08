package regex.tokens;

public class Relationals extends Token {
  public static int serial = 81;
  private int id = 0;
  public String relational;
  public int number;
  public int tokenType = 8;
  public int line;
  public int value;

  public void setValue(String relational) {
    switch (relational) {
      case "<":
        value = 81;
        break;
      case ">":
        value = 82;
        break;
      case "=":
        value = 83;
        break;
      case ">=":
        value = 84;
        break;
      case "<=":
        value = 85;
        break;
    }
  }

  public String toString() {
    return number + " | " + line + " | " + relational + " | " + tokenType + " | " + value;
  }

  public Relationals(String relational, int number, int line) {
    super(relational);
    this.id = serial++;
    this.number = number;
    this.relational = relational;
    this.line = line;
    setValue(relational);
  }
}
