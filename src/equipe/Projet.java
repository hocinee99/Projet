package equipe;

public class Projet {
    String titre;
    String description;
    Secteur secteur;
    double benefice;
    double cout_eco;
    double cout_social;
    double cout_environemental;

    public Projet(String titre, String description, Secteur secteur, double benefice) {
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }
}
