package equipe;

import java.util.ArrayList;

/**
 * An Expert is a type of Personne who has competencies in various sectors and
 * can propose projects based on those competencies.
 *
 * @author Hocine
 * @version 1.0
 */
public class Expert extends Personne {

    private final ArrayList<Secteur> liste;

    /**
     * Constructor of the Expert class, with it we initialize the attributes of
     * an expert. the liste of his competencies is initialized as an empty list.
     *
     * @param nom the surname of the expert
     * @param prenom the name of the expert
     * @param age the of the expert
     */
    public Expert(String nom, String prenom, int age) {
        super(nom, prenom, age);
        liste = new ArrayList<>();
    }

    /**
     * Adds a sector of competence to the expert's list of competencies.
     *
     * @param s the sector of competence to add
     */
    public void addSecteurCompetences(Secteur s) {
        this.liste.add(s);
    }

    /**
     * Proposes a project in a random sector from the expert's list of
     * competencies.
     *
     * @return the proposed project
     */
    public Projet proposeProjet() {
        int a = liste.size();
        int indexsecteur = (int) (Math.random() * a);
        Secteur s = liste.get(indexsecteur); // On prend un secteur aléatoirement ou il y'a des compétences
        String titre = "Projet" + s;
        String desc = "Description du projet";
        return new Projet(titre, desc, s);
    }

    /**
     * Getter for the list of sectors of competence of the expert.
     *
     * @return the list of sectors of competence
     */
    public ArrayList<Secteur> getListe() {
        return liste;
    }
}
