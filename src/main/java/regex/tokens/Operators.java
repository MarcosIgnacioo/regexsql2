package regex.tokens;

public class Operators extends Token {
  public static int serial = 71;
  private int id = 0;
  public String operator;
  public int number;
  public int tokenType = 7;
  public int line;
  public int value;

  public String toString() {
    return number + " | " + line + " | " + operator + " | " + tokenType + " | " + value;
  }

  public void setValue(String operator) {
    switch (operator) {
      case "+":
        value = 70;
        break;
      case "-":
        value = 71;
        break;
      case "*":
        value = 72;
        break;
      case "/":
        value = 73;
        break;
    }
  }

  public Operators(String operator, int number, int line) {
    super(operator, String.valueOf(line));
    this.id = serial++;
    this.number = number;
    this.operator = operator;
    this.line = line;
    setValue(operator);
  }
}
