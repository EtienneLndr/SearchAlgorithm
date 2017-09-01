
package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Appli {

  private static final int MAXIMUM_INT = 1500;

  /**
   * Path to the file
   */
  private static final String PATH = "E:\\Users\\localadmin\\Documents\\Etienne_Stage\\valueOfTime.txt";

  /**
   * Empty constructor
   */
  public Appli() {
  }

  /**
   * @param liste
   *          List<Integer>
   * @param value
   *          int
   * @return int
   */
  private static int searchOccurenceInListWithLinearMethod(List<Integer> liste, int value) {
    int index = -1;
    Operations operations = new Operations();
    List<Integer> list = null;

    if (value < MAXIMUM_INT / 2) {
      if (!operations.isListeOrdreCroissant(liste)) {
        list = operations.classementOrdreCroissant(liste);
      }
    } else if (value >= MAXIMUM_INT / 2) {
      if (!operations.isListeOrdreDecroissant(liste)) {
        list = operations.classementOrdreDecroissant(liste);
      }
    }

    index = operations.rechercheLineaire(list, value);

    return index;
  }

  /**
   * @param liste
   *          List<Integer>
   * @param value
   *          int
   * @return int
   */
  private static int searchOccurenceInListWithBinaryMethod(List<Integer> liste, int value) {
    int index = -1;
    Operations operations = new Operations();
    List<Integer> list = null;

    if (value < MAXIMUM_INT / 2) {
      if (!operations.isListeOrdreCroissant(liste)) {
        list = operations.classementOrdreCroissant(liste);

        index = operations.rechercheBinaireCroissante(list, value);
      }
    } else if (value >= MAXIMUM_INT / 2) {
      if (!operations.isListeOrdreDecroissant(liste)) {
        list = operations.classementOrdreDecroissant(liste);

        index = operations.rechercheBinaireDecroissante(list, value);
      }
    }

    return index;
  }

  /**
   * @return long: the time which is present in the file
   */
  private static long getValueOfTime() {

    long retour = -1;

    BufferedReader br = null;
    FileReader fr = null;

    try {
      fr = new FileReader(PATH);
      br = new BufferedReader(fr);

      String sCurrentLine;

      if ((sCurrentLine = br.readLine()) != null) {
        try {
          retour = Long.parseLong(sCurrentLine);
        } catch (NumberFormatException e) {
          retour = -1;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {

        if (br != null)
          br.close();
        if (fr != null)
          fr.close();

      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    return retour;
  }

  /**
   * @param newTime
   *          long: the new time to set in the file
   */
  private static void setValueOfTime(long newTime) {

    FileWriter fw = null;
    BufferedWriter bw = null;

    try {
      fw = new FileWriter(PATH);
      bw = new BufferedWriter(fw);

      bw.write(String.valueOf(newTime));

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bw != null)
          bw.close();
        if (fw != null)
          fw.close();

      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

  }

  /**
   * Simple test to know if the execution time is better than before
   * 
   * @param actualTime
   *          long
   */
  private static void testIfTimeIsBetter(long actualTime) {

    long valueOfTimeInFile = getValueOfTime();

    if (valueOfTimeInFile == -1 || actualTime < getValueOfTime()) {
      setValueOfTime(actualTime);
    }

  }

  private static int testIfIntIsNegative(int value) {
    return value < 0 ? -1 * value : value;
  }

  public static void main(String[] args) {

    Random random = new Random();

    long startTime = System.nanoTime();

    // List<Integer> liste = Arrays.asList(0, 1, 7, 3, 45, 18, 9, 78, 175, 76);
    List<Integer> liste = new ArrayList<Integer>();

    int listeSize = testIfIntIsNegative(random.nextInt() % MAXIMUM_INT);

    System.out.println("listeSize = " + listeSize);

    for (int i = 0; i < listeSize; i++) {
      /*
       * As of now, we're only working on positive int
       */
      liste.add(testIfIntIsNegative(random.nextInt() % MAXIMUM_INT));
    }

    int randomInt = testIfIntIsNegative(random.nextInt(liste.size()));

    System.out.println("index = " + randomInt);
    System.out.println("value = " + liste.get(randomInt));

    long linearMethodStart = System.nanoTime();

    /*
     * Beginning of the linear method
     */
    int index1 = searchOccurenceInListWithLinearMethod(liste, liste.get(randomInt));

    if (index1 == -1) {
      System.out.println("Erreur: valeur invalide");
    } else {
      System.out.println("Linear method -> index = " + index1);
    }
    /*
     * End of the linear method
     */
    long linearMethodEnd = System.nanoTime();

    System.out.println("Il s'est écoulé " + (linearMethodEnd - linearMethodStart) + "ns durant la recherche linéaire, soit "
        + (linearMethodEnd - startTime) / 1000000 + "ms.");

    /*
     * Beginning of the binary method
     */
    int index2 = searchOccurenceInListWithBinaryMethod(liste, liste.get(randomInt));

    if (index2 == -1) {
      System.out.println("Erreur: valeur invalide");
    } else {
      System.out.println("Binary method -> index = " + index2);
    }
    /*
     * End of the binary method
     */

    long binaryMethodTime = System.nanoTime();

    System.out.println("Il s'est écoulé " + (binaryMethodTime - linearMethodEnd) + "ns durant la recherche binaire, soit "
        + (binaryMethodTime - linearMethodEnd) / 1000000 + "ms.");

    long stopTime = System.nanoTime();
    long elapsedTime = stopTime - startTime;

    if (elapsedTime <= 1)
      System.out.print("Depuis le lancement du programme " + elapsedTime + "ns s'est écoulée, ce qui équivaut à "
          + elapsedTime / 1000000 + "ms.");
    else
      System.out.println("Depuis le lancement du programme " + elapsedTime + "ns se sont écoulées, ce qui équivaut à "
          + elapsedTime / 1000000 + "ms.");

    /*
     * Simple test to know if the execution time is better than before
     */
    testIfTimeIsBetter(elapsedTime);

  }

}
