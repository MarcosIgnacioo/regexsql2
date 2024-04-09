package regex.main;

public class Tests {
  public static void main(String[] args) {
    String regex = ">=|<=|[a-zA-Z0-9\\$@#!\\-\\*]+|(,|.)|('+|([^']*))";
    String text =
        "SELECT M#,MNOMBRE\n"
            + "FROM MATERIAS\n"
            + "WHERE M# IN (SELECT M#\n"
            + "FROM INSCRITOS\n"
            + "WHERE A# IN (SELECT A#\n"
            + "FROM ALUMNOS\n"
            + "WHERE ANOMBRE='MESSI LIONEL'))";
    String[] words = Main.GetFilteredWords(regex, text);
    for (String w : words) {
      System.out.println(w);
    }
  }
}
