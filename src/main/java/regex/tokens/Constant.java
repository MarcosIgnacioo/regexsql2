package regex.tokens;

public class Constant {
  private static int serial = 600;
  private int id;
  private int numero;
  private String constants;
  private int tipo;
  private int column;

  public String toString() {
    return id + " "+ numero + " " + constants + " " + tipo + "," + column;
  }

  public Constant(int numero, String constants, int tipo, int column) {
    this.id = serial++;
    this.numero = numero;
    this.constants = constants;
    this.tipo = tipo;
    this.column = column;
  }
}
