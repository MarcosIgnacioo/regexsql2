package regex.helpers;

public class HelpersFunctions {
  // aqui estaran todos los queries de testeos
  public static String[] queriesTest = new String[20];

  public static String[] toStringArray(Object[] array) {
    String[] stringArray = new String[array.length];
    for (int i = 0; i < array.length; i++) {
      if (array[i] == null) {
        stringArray[i] = "Undefined";
        continue;
      }
      stringArray[i] = array[i].toString();
    }
    return stringArray;
  }
}
