
package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Etienne LANDURE
 * @version 0.1
 */
public class Appli {

  /**
   * Taille maximale de la liste et valeur maximale présente dans la liste
   */
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
   * Méthode permettant de créer un fichier
   * 
   * @param pathOfFile
   *          String: le chemin relatif
   */
  private static void createFile(String pathOfFile) {
    try {

      File file = new File(pathOfFile);

      if (file.createNewFile()) {
        System.out.println("File is created!");
      } else {
        System.out.println("File already exists.");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Méthode cherchant un nombre dans une liste de manière linéaire
   * 
   * @param liste
   *          List<Integer>: la liste à analyser
   * @param value
   *          int: le nombre recherché
   * @return int: index indiquant le placement dans la liste du nombre recherché
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
   * Méthode cherchant un nombre dans une liste de manière binaire
   * 
   * @param liste
   *          List<Integer>: la liste à analyser
   * @param value
   *          int: le nombre recherché
   * @return int: index indiquant le placement dans la liste du nombre recherché
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
   * Méthode permettant de vérifier s'il y a un temps présent dans le fichier de
   * sauvegarde et, si il existe, le retourne
   * 
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
      if (e instanceof FileNotFoundException) {
        createFile(PATH);
      }
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
   * Méthode permettant de changer le temps présent dans le fichier de
   * sauvegarde
   * 
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
   *          long: le temps à comparer avec la valeur déjà présente (ou pas)
   *          dans le fichier de sauvegarde
   */
  private static void testIfTimeIsBetter(long actualTime) {

    long valueOfTimeInFile = getValueOfTime();

    if (valueOfTimeInFile == -1 || actualTime < getValueOfTime()) {
      setValueOfTime(actualTime);
    }

  }

  /**
   * @param value
   *          int: nombre à analyser
   * @return int: la valeur positive du nombre passé en paramètre
   */
  private static int testIfIntIsNegative(int value) {
    return value < 0 ? -1 * value : value;
  }

  /**
   * @param args
   *          String[]: arguments
   */
  public static void main(String[] args) {

    System.out.println(System.getProperty("java.runtime.version"));
    
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
