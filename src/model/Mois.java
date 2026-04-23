package model;

public class Mois {
	private String nom;
	private boolean[] jours;

	public Mois(String nom, int nbJours) {
		this.nom = nom;
		jours = new boolean[nbJours];
		for (int i = 0; i < jours.length; i++) {
			jours[i] = false;
		}
	}

	public boolean estLibre(int jour) {
		return !jours[jour - 1];
	}

	public String getNom() {
		return nom;
	}

	public void reserver(int jour) {
		if (!estLibre(jour))
			throw new IllegalStateException();
		jours[jour - 1] = true;
	}
}
