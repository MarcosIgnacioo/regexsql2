package regex.helpers;

public class HelpersFunctions {

  public static String[] queriesError = {
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS;INSCRITOS,CARRERAS\n"
        + "WHERE ALUMNOS.A#=INSCRITOS.A# AND ALUMNOS.C#=CARRERAS.C#\n"
        + "AND INSCRITOS.SEMESTRE='2010I'\n"
        + "AND CARRERAS.CNOMBRE='ISC'\n"
        + "AND ALUMNOS.GENERACION='2010'\n",
    "SELECT *\n" + "FROM PROFESORES\n" + "WHERE EDAD >45 AND GRADO=MAE' OR GRADO='DOC'\n",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS,INSCRITOS,\n"
        + "WHERE ALUMNOS.A#=INSCRITOS.A# AND\n"
        + "INSCRITOS.SEMESTRE='2010I'\n",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS\n"
        + "WHERE A# IN(SELECT A#\n"
        + "FROM INSCRITOS\n"
        + "WHERE P# IN (SELECT P#\n"
        + "FROM PROFESORES\n"
        + "WHERE GRADO='MAE'))\n"
        + "AND C# IN (SELECT C#\n"
        + "FROM\n"
        + "WHERE CNOMBRE='ISC')\n",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS A,INSCRITOS I,CARRERAS C\n"
        + "WHERE A.A#=I.A# AND A.C#=C.C#\n"
        + "AND I.SEMESTRE='2010I' C.CNOMBRE='ITC'\n",
    "SELECT A#,ANOMBRE\n"
        + "FROM ALUMNOS\n"
        + "WHERE C# IN (SELECT C#\n"
        + "FROM CARRERAS\n"
        + "WHERE SEMESTRES=9)\n"
        + "AND A# (SELECT A#\n"
        + "FROM INSCRITOS\n"
        + "WHERE SEMESTRE='2010I')\n",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS,INSCRITOS,CARRERAS\n"
        + "WHERE ALUMNOS.A#=INSCRITOS.A# AND ALUMNOS.C#=CARRERAS.C#\n"
        + "AND INSCRITOS.SEMESTRE='2010I'\n"
        + "AND CARRERAS.CNOMBRE='ISC\n"
        + "AND ALUMNOS.GENERACION='2010'\n",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS,INSCRITOS,CARRERAS\n"
        + "WHERE ALUMNOS.A#=INSCRITOS.A# AND ALUMNOS.C#=CARRERAS.C#\n"
        + "AND INSCRITOS.SEMESTRE '2010I'\n"
        + "AND CARRERAS.CNOMBRE='ISC'\n"
        + "AND ALUMNOS.GENERACION='2010'\n",
  };
  public static String[] queries = {
    "SELECT ANOMBRE"
        + "FROM ALUMNOS,INSCRITOS,CARRERAS"
        + "WHERE ALUMNOS.A#=INSCRITOS.A# AND ALUMNOS.C#=CARRERAS.C#\n"
        + "AND INSCRITOS.SEMESTRE='2010I'\n"
        + "AND CARRERAS.CNOMBRE='ISC'\n"
        + "AND ALUMNOS.GENERACION='2010'",
    "SELECT ANOMBRE, CALIFICACION, TURNO\n"
        + "FROM ALUMNOS, INSCRITOS, MATERIAS, CARRERAS\n"
        + "WHERE MNOMBRE=’LENAUT2’ AND TURNO = ‘TM’ \n"
        + "AND CNOMBRE=’ISC’ AND SEMESTRE=’2023I’ AND CALIFICACION >= 70", // q1 ./
    "SELECT *\n" + "FROM PROFESORES\n" + "WHERE EDAD >45 AND GRADO='MAE' OR GRADO='DOC'", // q2 ./
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS , INSCRITOS\n"
        + "WHERE ALUMNOS.A#=INSCRITOS.A# AND INSCRITOS.SEMESTRE='2010I'",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS A,INSCRITOS I,CARRERAS C\n"
        + "WHERE A.A#=I.A# AND A.C#=C.C# AND I.SEMESTRE='2010I'\n"
        + "AND C.CNOMBRE='ISC' AND A.GENERACION='2010'",
    "SELECT MNOMBRE, CNOMBRE\n"
        + "FROM CARRERAS C,DEPARTAMENTOS D,MATERIAS M\n"
        + "WHERE C.C#=M.C# AND C.D#=D.D# AND D.DNOMBRE='CIECOM'",
    "SELECT M#,MNOMBRE\n"
        + "FROM MATERIAS\n"
        + "WHERE M# IN (SELECT M#\n"
        + "FROM INSCRITOS\n"
        + "WHERE A# IN (SELECT A#\n"
        + "FROM ALUMNOS\n"
        + "WHERE ANOMBRE='MESSI LIONEL'))",
    "SELECT A#,ANOMBRE\n"
        + "FROM ALUMNOS\n"
        + "WHERE C# IN (SELECT C#\n"
        + "FROM CARRERAS\n"
        + "WHERE SEMESTRES=9)\n"
        + "AND A# IN (SELECT A#\n"
        + "FROM INSCRITOS\n"
        + "WHERE SEMESTRE='2010I')",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS\n"
        + "WHERE A# IN (SELECT A#\n"
        + "FROM INSCRITOS\n"
        + "WHERE SEMESTRE='2010I'\n"
        + "AND M# IN (SELECT M#\n"
        + "FROM MATERIAS\n"
        + "WHERE MNOMBRE='ESTDAT'))",
    "SELECT ANOMBRE\n"
        + "FROM ALUMNOS\n"
        + "WHERE A# IN(SELECT A#\n"
        + "FROM INSCRITOS\n"
        + "WHERE P# IN (SELECT P#\n"
        + "FROM PROFESORES\n"
        + "WHERE GRADO='MAE'))\n"
        + "AND C# IN (SELECT C#\n"
        + "FROM CARRERAS\n"
        + "WHERE CNOMBRE='ISC')",
    "SELECT ANOMBRE \n"
        + "FROM ALUMNOS \n"
        + "WHERE A# IN (SELECT A#\n"
        + "FROM INSCRITOS\n"
        + "WHERE CALIFICACION < 70\n"
        + "AND M# IN (SELECT M#\n"
        + "FROM MATERIAS\n"
        + "WHERE C# IN (SELECT C#\n"
        + "FROM CARRERAS\n"
        + "WHERE D# IN (SELECT D#\n"
        + "FROM DEPARTAMENTOS\n"
        + "WHERE DNOMBRE = 'CIECOM'))))"
  };

  public static String[] toStringArray(Object[] array) {
    String[] stringArray = new String[array.length];
    for (int i = 0; i < array.length; i++) {
      if (array[i] == null) {
        stringArray[i] = "UNDEFINED";
        continue;
      }
      stringArray[i] = array[i].toString();
    }
    return stringArray;
  }

  public static void printArray(Object[] array) {
    for (Object element : array) {
      System.out.println(element);
    }
  }
}
