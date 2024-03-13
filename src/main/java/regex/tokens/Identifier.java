package regex.tokens;

public class Identifier {

  private static int serial = 400;
  private int id;
  private String identifier;
  private int line;
  private int column;

  public String toString() {
    return "IDENTIFIER " + id + " " + identifier + " " + line + "," + column;
  }

  public Identifier(String identifier, int line, int column) {
    this.id = serial;
    this.identifier = identifier;
    this.line = line;
    this.column = column;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    Identifier that = (Identifier) o;
    return that.identifier == this.identifier;
  }
}
