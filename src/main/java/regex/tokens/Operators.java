package regex.tokens;

public class Operators {
  private static int serial = 71;
  private int id = 0;
  public String operator;
  public int number;
  public int tokenType = 7;
  public int line;
  private int value;

  public String toString() {
    return number + " | " + line + " | " + operator + " | " + tokenType + " | " + value;
  }

  public void setValue(String operator) {
    switch (operator) {
      case "+":
        value = 83;
        break;
      case "-":
        value = 82;
        break;
      case "*":
        value = 80;
        break;
      case "/":
        value = 81;
        break;
    }
  }

  public Operators(String operator, int number, int line) {
    this.id = serial++;
    this.number = number;
    this.operator = operator;
    this.line = line;
    setValue(operator);
  }
}
