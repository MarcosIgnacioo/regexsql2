package regex.tokens;

public class Illegal extends Token {
  public String word;
  public int line;
  public int value;

  public Illegal(String word, int line) {
    super(word, String.valueOf(line));
    this.word = word;
    this.line = line;
    this.value = 999;
  }

  public String toString() {
    return "ILEGAL WORD/CHARACTER: " + word;
  }
}
