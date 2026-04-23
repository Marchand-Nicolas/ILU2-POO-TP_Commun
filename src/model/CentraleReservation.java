package model;

public class CentraleReservation<E extends EntiteReservable<F>, F extends Formulaire> {
  private E[] entites;
  private int nbEntites;

  public CentraleReservation(int capaciteMax) {
    entites = (E[]) new EntiteReservable[capaciteMax];
  }

  public int ajouterEntite(E entite) {
    nbEntites++;
    entite.setNumero(nbEntites);
    entites[nbEntites - 1] = entite;
    return nbEntites;
  }

  public int[] donnerPossibilites(F formulaire) {
    int[] possibilites = new int[nbEntites];
    for (int i = 0; i < nbEntites; i++) {
      if (entites[i].compatible(formulaire)) {
        possibilites[i] = entites[i].getNumero();
      }
    }
    return possibilites;
  }

  public Reservation reserver(int numeroEntite, F formulaire) {
    if (numeroEntite <= 0 || numeroEntite > nbEntites) {
      return null;
    }
    E entite = entites[numeroEntite - 1];
    formulaire.setIdentificationEntite(entite.getNumero());
    return entite.reserver(formulaire);
  }
}