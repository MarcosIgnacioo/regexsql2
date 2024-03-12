package regex.tokens;

public class Constant {
  private static int serial = 600;
  private int id;
  private int number;
  private String constants;
  private int type;
  private int column;

  public String toString() {
    return  number + " "+ id + " " + constants + " " + type + "," + column;
  }

  public Constant(int number, String constants, int type, int column) {
    this.id = serial++;
    this.number = number;
    this.constants = constants;
    this.type = type;
    this.column = column;
  }
}
