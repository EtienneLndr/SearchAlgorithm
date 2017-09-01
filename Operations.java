
package com.test;

import java.util.ArrayList;
import java.util.List;

public class Operations {

  /**
   * Empty constructor
   */
  public Operations() {
  }

  /**
   * @param liste
   *          List<Integer>
   * @return List<Integer>
   */
  public List<Integer> classementOrdreCroissant(List<Integer> liste) {

    List<Integer> listeCroissante = new ArrayList<Integer>(liste);

    for (int i = 0; i < listeCroissante.size(); i++) {
      for (int j = i; j >= 0; j--) {
        if (listeCroissante.get(i) < listeCroissante.get(j)) {
          int temp = listeCroissante.get(i);
          listeCroissante.set(i, listeCroissante.get(j));
          listeCroissante.set(j, temp);
          i = j;
        }
      }
    }

    return listeCroissante;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return List<Integer>
   */
  public List<Integer> classementOrdreDecroissant(List<Integer> liste) {

    List<Integer> listeDecroissante = new ArrayList<Integer>(liste);

    for (int i = 0; i < listeDecroissante.size(); i++) {
      for (int j = i; j >= 0; j--) {
        if (listeDecroissante.get(i) > listeDecroissante.get(j)) {
          int temp = listeDecroissante.get(i);
          listeDecroissante.set(i, listeDecroissante.get(j));
          listeDecroissante.set(j, temp);
          i = j;
        }
      }
    }

    return listeDecroissante;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return List<Integer>
   */
  public List<Integer> triCroissantParEchange(List<Integer> liste) {
    List<Integer> listeTriee = new ArrayList<Integer>(liste);

    for (int i = 0; i < listeTriee.size() - 1; i++) {
      for (int j = i; j < listeTriee.size(); j++) {
        if (listeTriee.get(i) > listeTriee.get(j)) {
          int temp = listeTriee.get(i);
          listeTriee.set(i, listeTriee.get(j));
          listeTriee.set(j, temp);
        }
      }
    }

    return listeTriee;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return List<Integer>
   */
  public List<Integer> triDecroissantParEchange(List<Integer> liste) {
    List<Integer> listeTriee = new ArrayList<Integer>(liste);

    for (int i = 0; i < listeTriee.size() - 1; i++) {
      for (int j = i; j < listeTriee.size(); j++) {
        if (listeTriee.get(i) < listeTriee.get(j)) {
          int temp = listeTriee.get(i);
          listeTriee.set(i, listeTriee.get(j));
          listeTriee.set(j, temp);
        }
      }
    }

    return listeTriee;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return List<Integer>
   */
  public List<Integer> triCroissantABulle(List<Integer> liste) {
    List<Integer> listeTriee = new ArrayList<Integer>(liste);

    boolean permut = false;

    do {
      permut = false;
      for (int i = 0; i < listeTriee.size() - 1; i++) {
        if (listeTriee.get(i) > listeTriee.get(i + 1)) {
          int temp = listeTriee.get(i);
          listeTriee.set(i, listeTriee.get(i+1));
          listeTriee.set(i+1, temp);
          permut = true;
        }
      }
    } while (permut);

    return listeTriee;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return List<Integer>
   */
  public List<Integer> triDecroissantABulle(List<Integer> liste) {
    List<Integer> listeTriee = new ArrayList<Integer>(liste);

    boolean permut = false;

    do {
      permut = false;
      for (int i = 0; i < listeTriee.size() - 1; i++) {
        if (listeTriee.get(i) < listeTriee.get(i + 1)) {
          int temp = listeTriee.get(i);
          listeTriee.set(i, listeTriee.get(i+1));
          listeTriee.set(i+1, temp);
          permut = true;
        }
      }
    } while (permut);

    return listeTriee;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return boolean
   */
  public boolean isListeOrdreCroissant(List<Integer> liste) {

    for (int i = 0; i < liste.size(); i++) {
      if (i == liste.size() - 1)
        return true;
      else if (liste.get(i) > liste.get(i + 1))
        return false;
    }

    return true;
  }

  /**
   * @param liste
   *          List<Integer>
   * @return boolean
   */
  public boolean isListeOrdreDecroissant(List<Integer> liste) {
    for (int i = 0; i < liste.size(); i++) {
      if (i == liste.size() - 1)
        return true;
      else if (liste.get(i) < liste.get(i + 1))
        return false;
    }

    return true;
  }

  /**
   * @param liste
   *          List<Integer>
   * @param nombreRecherche
   *          int
   * @return int
   */
  public int rechercheLineaire(List<Integer> liste, int nombreRecherche) {
    int index = -1;

    for (int i = 0; i < liste.size(); i++) {
      if (liste.get(i) == nombreRecherche) {
        index = i;
        break;
      }
    }

    return index;
  }

  /**
   * @param liste
   *          List<Integer>
   * @param nombreRecherche
   *          int
   * @return int
   */
  public int rechercheBinaireCroissante(List<Integer> liste, int nombreRecherche) {
    int index = -1;

    int start = 0;
    int end = liste.size() - 1;

    while (start <= end) {
      int mid = (start + end) / 2;

      if (nombreRecherche == liste.get(mid)) {
        return mid;
      }
      if (nombreRecherche < liste.get(mid)) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }

    return index;
  }

  /**
   * @param liste
   *          List<Integer>
   * @param nombreRecherche
   *          int
   * @return int
   */
  public int rechercheBinaireDecroissante(List<Integer> liste, int nombreRecherche) {
    int index = -1;

    int start = 0;
    int end = liste.size() - 1;
    while (start <= end) {
      int mid = (start + end) / 2;
      if (nombreRecherche == liste.get(mid)) {
        return mid;
      }
      if (nombreRecherche > liste.get(mid)) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }

    return index;
  }

}
