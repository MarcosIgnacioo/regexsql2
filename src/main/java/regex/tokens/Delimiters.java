package regex.tokens;

public class Delimiters {
  private static int serial = 50;
  private int id = 0;
  public int number;
  public String delimiter;
  public int tokenType = 5;
  public int line;
  public int value;

  public String toString() {
    return number + " | " + line + " | " + delimiter + " | " + tokenType + " | " + value;
  }

  public void setValue(String delimiter) {
    switch (delimiter) {
      case ",":
        value = 50;
        break;
      case ".":
        value = 51;
        break;
      case "(":
        value = 52;
        break;
      case ")":
        value = 53;
        break;
      case "‘":
        value = 54;
        break;
      default:
        value = 55;
        break;
    }
  }

  public Delimiters(String delimiter, int number, int line) {
    this.id = serial++;
    this.number = number;
    this.delimiter = delimiter;
    this.line = line;
    setValue(delimiter);
  }

}
