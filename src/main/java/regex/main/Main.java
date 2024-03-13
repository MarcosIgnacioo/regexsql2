package regex.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regex.helpers.HelpersFunctions;
import regex.lexer.Lexer;

public class Main {
  public static void main(String[] args) throws Exception {
    // Print the path
    String txt = "SELECT ANOMBRE, CALIFICACION , TURNO\n" +
        "FROM ALUMNOS, INSCRITOS, MATERIAS, CARRERAS\n" +
        "WHERE MNOMBRE=’LENAUT2’ AND TURNO = ‘TM’\n" +
        "AND CNOMBRE=’ISC’ AND SEMESTRE=’2023I’ AND CALIFICACION >= 70\n" +
        "\n" +
        "SELECT *\n" +
        "FROM PROFESORES\n" +
        "WHERE EDAD >45 AND GRADO='MAE' OR GRADO='DOC'\n" +
        "\n" +
        "SELECT ANOMBRE\n" +
        "FROM ALUMNOS,INSCRITOS,CARRERAS\n" +
        "WHERE ALUMNOS.A#=INSCRITOS.A# AND ALUMNOS.C#=CARRERAS.C<#\n" +
        "AND INSCRITOS.SEMESTRE='2010I'\n" +
        "AND CARRERAS.CNOMBRE='ISC'\n" +
        "AND ALUMNOS.GENERACION='2010'\n" +
        "\n" +
        "SELECT ANOMBRE\n" +
        "FROM ALUMNOS A,INSCRITOS I,CARRERAS C\n" +
        "WHERE A.A#=I.A# AND A.C#=C.C# AND I.SEMESTRE='2010I'\n" +
        "AND C.CNOMBRE='ISC' AND A.GENERACION='2010'\n" +
        "\n" +
        "SELECT MNOMBRE, CNOMBRE\n" +
        "FROM CARRERAS C,DEPARTAMENTOS D,MATERIAS M\n" +
        "WHERE C.C#=M.C# AND C.D#=D.D# AND D.DNOMBRE='CIECOM'\n" +
        "\n" +
        "SELECT M#,MNOMBRE\n" +
        "FROM MATERIAS\n" +
        "WHERE M# IN (SELECT M#\n" +
        "FROM INSCRITOS\n" +
        "WHERE A# IN (SELECT A#\n" +
        "FROM ALUMNOS\n" +
        "WHERE ANOMBRE='MESSI@ LIONEL'))\n" +
        "\n" +
        "SELECT ANOMBRE, CALIFICACION, TURNO\n" +
        "FROM ALUMNOS, INSCRITOS, MATERIAS, CARRERAS\n" +
        "WHERE MNOMBRE=’LENAUT2’ AND TURNO = ‘TM’\n" +
        "AND CNOMBRE=’ISC’ AND SEMESTRE=’%@!$2$#\\()%02-_3?{}[]I’ AND CALIFICACION >= 70\n";
    // String txt = "SELECT";
    String regex = "(‘|’|') ?[a-zA-Z@!\\$\\%#\\\\\\(\\)_\\-0-9\\[\\]\\{\\} ?]*(’|'|‘)|\\b\\w+\\b|(,|.)";

    String[] textSplitted = txt.split("\n");

    for (int i = 0; i < textSplitted.length; i++) {
      String[] words = GetFilteredWords(regex, textSplitted[i]);
      String[] categoriesArray = Lexer.MatchWordArrayType(words, i);
    }
  }

  public static String[] GetFilteredWords(String regex, String text) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    ArrayList<String> words = new ArrayList<>();
    while (matcher.find()) {
      if (!matcher.group().isBlank()) {
        words.add(matcher.group());
      }
    }
    return HelpersFunctions.toStringArray(words.toArray());
  }
}
