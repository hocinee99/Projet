package equipe;

public class Projet {
    private String titre;
    private String description;
    private final Secteur secteur;
    private double benefice;
    private double cout_economique;
    private double cout_social;
    private double cout_environnemental;

    public Projet(String titre, String description, Secteur secteur) {
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }









    // Setters pour les couts et le bénéfice :


    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

    public void setCout_economique(double cout_economique) {
        this.cout_economique = cout_economique;
    }

    public void setCout_social(double cout_social) {
        this.cout_social = cout_social;
    }

    public void setCout_environnemental(double cout_environnemental) {
        this.cout_environnemental = cout_environnemental;
    }
    // Getters:
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public double getBenefice() {
        return benefice;
    }

    public double getCout_economique() {
        return cout_economique;
    }

    public double getCout_social() {
        return cout_social;
    }

    public double getCout_environnemental() {
        return cout_environnemental;
    }
}
