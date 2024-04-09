package regex.tokens;

public class Identifier extends Token {

  public static int serial = 401;
  public int id;
  public String identifier;
  public String line;
  public int number;
  public int tokenType = 4;

  public String toString() {
    return number
        + " | "
        + line.charAt(0)
        + " | "
        + " | "
        + identifier
        + " | "
        + tokenType
        + " | "
        + id
        + " | ";
  }

  public String tableFormat() {
    return identifier + " | " + id + " | " + line;
  }

  public Identifier(String identifier, String line, int number) {
    super(identifier, line);
    this.id = serial++;
    this.identifier = identifier;
    this.number = number;
    this.line = line;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Identifier that = (Identifier) o;
    return that.identifier == this.identifier;
  }
}
