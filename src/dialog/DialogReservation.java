/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialog;

import interface_noyau_fonctionnel.AdaptateurDuNoyauFonctionnel;
import java.awt.EventQueue;
import java.time.LocalDate;
import presentation.FrameReservation;

public class DialogReservation {

    private FrameReservation frameReservation;
    private AdaptateurDuNoyauFonctionnel inf;
    private int numClient;

    private LocalDate date;
    private String time;
    private int nbPersons;
    private int numTable;

    public DialogReservation(AdaptateurDuNoyauFonctionnel inf) {
        this.inf = inf;
    }

    public void handleUserConnected(int numClient) {
        this.numClient = numClient;
        frameReservation.setVisible(true);
    }

    public void initDialog() {
        frameReservation = new FrameReservation();
        frameReservation.initFrame();
        frameReservation.setDialog(this);
        frameReservation.setVisible(true);
    }

    public void handleDateSelectedEvent(LocalDate date) {
        System.out.println("Date selected " + date);
        this.date = date;
        frameReservation.enableHeure();
        // On actualise les tables dispo en fonction de la nouvelle date selectionnee
        // (dans le cas ou le formulaire a deja ete rempli et valide)
        refreshTables();
    }

    public void handleTimeSelectedEvent(String time) {
        System.out.println("Time selected " + time);
        this.time = time;
        frameReservation.enableNbPersonnes();
    }

    public void handleNumOfPersonsSelectedEvent(int nbPersons) {
        System.out.println("Nb personnes selected " + nbPersons);
        frameReservation.enableTables();
        this.nbPersons = nbPersons;
        refreshTables();
    }

    public void handleTableSelectedEvent(String table) {
        System.out.println("Table selected " + table);
        frameReservation.enableValider();
        this.numTable = Integer.parseInt(table);
    }

    public void handleCancelEvent() {
        System.exit(0);
    }

    public void handleValidationEvent() {
        inf.reserverTable(numClient, numTable);
        frameReservation.afficherConfirmation(date, time, nbPersons, numTable);
        // On actualise les tables dispo
        refreshTables();
    }

    private void refreshTables() {
        if (nbPersons == 0) {
            return;
        }
        String[] optionsTables = inf.trouverTableDisponible(date.getDayOfMonth(), date.getMonthValue(), nbPersons, time);
        frameReservation.afficherTables(optionsTables);
    }

    public static void main(String[] args) {
        DialogReservation dialog = new DialogReservation(null);
        EventQueue.invokeLater(() -> {
            dialog.initDialog();
        });
    }

}
