package regex.tokens;

public class Illegal {
  String word;
  int line;

  public Illegal(String word, int line) {
    this.word = word;
    this.line = line;
  }

  public String toString() {
    return "ILEGAL WORD/CHARACTER: " + word;
  }
}
