package model;

public class Restaurant {
  private static final int CAPACITE_MAX_TABLES = 100;
  private CentraleReservation<Table, FormulaireRestaurant> centrale;

  public Restaurant() {
    centrale = new CentraleReservation<>(CAPACITE_MAX_TABLES);
  }

  public int ajouterTable(int nbChaises) {
    Table table = new Table(nbChaises);
    return centrale.ajouterEntite(table);
  }

  public int[] donnerPossibilites(FormulaireRestaurant formulaire) {
    return centrale.donnerPossibilites(formulaire);
  }

  public Reservation reserver(int numeroTable, FormulaireRestaurant formulaire) {
    return centrale.reserver(numeroTable, formulaire);
  }

  private class Table extends EntiteReservable<FormulaireRestaurant> {
    private CalendrierAnnuel calendrierDeuxiemeService;
    private int nbChaises;

    private Table(int nbChaises) {
      super();
      this.nbChaises = nbChaises;
      calendrierDeuxiemeService = new CalendrierAnnuel();
    }

    private boolean estLibrePourService(FormulaireRestaurant formulaire) {
      if (formulaire.getNumService() == 2) {
        return calendrierDeuxiemeService.estLibre(formulaire.getJour(), formulaire.getMois());
      }
      return calendrier.estLibre(formulaire.getJour(), formulaire.getMois());
    }

    @Override
    public boolean compatible(FormulaireRestaurant formulaire) {
      boolean capaciteCompatible = nbChaises == formulaire.getNombrePersonnes()
          || nbChaises == formulaire.getNombrePersonnes() + 1;
      return capaciteCompatible && estLibrePourService(formulaire);
    }

    @Override
    public Reservation reserver(FormulaireRestaurant formulaire) {
      if (!compatible(formulaire)) {
        return null;
      }

      boolean reservationEffectuee;
      if (formulaire.getNumService() == 2) {
        reservationEffectuee = calendrierDeuxiemeService.reserver(formulaire.getJour(), formulaire.getMois());
      } else {
        reservationEffectuee = calendrier.reserver(formulaire.getJour(), formulaire.getMois());
      }

      if (!reservationEffectuee) {
        return null;
      }
      return new ReservationRestaurant(formulaire.getJour(), formulaire.getMois(), formulaire.getNumService(),
          getNumero());
    }
  }
}