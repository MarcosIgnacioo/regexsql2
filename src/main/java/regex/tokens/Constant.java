package regex.tokens;

public class Constant {
  private static int serial = 600;
  private static int numberCounter = 0;
  private int id;
  private int number;
  private String constants;
  private int type;
  private int column;

  public String toString() {
    return "CONSTANTE: " + number + " " + id + " " + constants + " " + type + "," + column;
  }

  public Constant(String constants, int type, int column) {
    this.id = serial++;
    this.number = numberCounter++;
    this.constants = constants;
    this.type = type;
    this.column = column;
  }
}
