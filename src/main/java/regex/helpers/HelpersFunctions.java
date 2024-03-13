package regex.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelpersFunctions {
  // aqui estaran todos los queries de testeos
  public static String[] queriesTest = new String[20];

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
