package regex.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {
  public static int serial = 600;
  public int id;
  public int number;
  public String constants;
  public int type;
  public int tokenType = 6;
  public String line;

  public String toString() {
    return number + " | " + line + " | " + constants + " | " + tokenType + " | " + id;
  }

  public String tableFormat() {
    return number + " | " + constants + " | " + type + " | " + id;
  }

  public Constant(String constants, int number, String line) {
    this.id = serial++;
    this.constants = constants;
    this.number = number;
    this.line = line;
    generateType();
  }

  @Override
  public boolean equals(Object o) {
    Constant that = (Constant) o;
    return that.constants.equals(this.constants);
  }

  public void generateType() {
    String regex = "\\d+";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(constants);
    type = (matcher.matches()) ? 61 : 62;
  }
}
