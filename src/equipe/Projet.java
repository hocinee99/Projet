package equipe;

/**
 * A Projet is characterized by a title, a description, a sector, a benefice and
 * various costs (economic, social, environmental).
 *
 * @author Hocine
 * @version 1.0
 */
public class Projet {

    private String titre;
    private String description;
    private final Secteur secteur;
    private int benefice;
    private int cout_economique;
    private int cout_social;
    private int cout_environnemental;

    /**
     * Constructor of the Projet class, with it we initialize the attributes of
     * a project.
     *
     * @param titre the title of the project
     * @param description the description of the project
     * @param secteur the sector of the project
     */
    public Projet(String titre, String description, Secteur secteur) {
        if (secteur == null) {
            throw new IllegalArgumentException("Le secteur ne peut pas etre nul");
        }
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }

    // Setters pour les couts et le bénéfice :
    /**
     * Sets the benefice of the project.
     *
     * @param benefice the benefice to set
     */
    public void setBenefice(int benefice) {
        this.benefice = benefice;
    }

    /**
     * Sets the economic cost of the project.
     *
     * @param cout_economique the economic cost to set
     * @throws IllegalArgumentException if the cost is negative
     */
    public void setCout_economique(int cout_economique) throws IllegalArgumentException {
        if (cout_economique < 0) {
            throw new IllegalArgumentException("Le cout ne peut pas etre négatif");
        } else {
            this.cout_economique = cout_economique;
        }
    }

    /**
     * Sets the social cost of the project.
     *
     * @param cout_social the social cost to set
     * @throws IllegalArgumentException if the cost is negative
     */
    public void setCout_social(int cout_social) throws IllegalArgumentException {
        if (cout_social < 0) {
            throw new IllegalArgumentException("Le cout ne peut pas etre négatif");
        } else {
            this.cout_social = cout_social;
        }
    }

    /**
     * Sets the environmental cost of the project.
     *
     * @param cout_environnemental the environmental cost to set
     * @throws IllegalArgumentException if the cost is negative
     */
    public void setCout_environnemental(int cout_environnemental) throws IllegalArgumentException {
        if (cout_environnemental < 0) {
            throw new IllegalArgumentException("Le cout ne peut pas etre négatif");
        } else {
            this.cout_environnemental = cout_environnemental;
        }
    }
    // Getters:

    /**
     * Getter for the title of the project.
     *
     * @return the title of the project
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Getter for the description of the project.
     *
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the sector of the project.
     *
     * @return the sector of the project
     */
    public Secteur getSecteur() {
        return secteur;
    }

    /**
     * Getter for the benefice of the project.
     *
     * @return the benefice of the project
     */
    public int getBenefice() {
        return benefice;
    }

    /**
     * Getter for the economic cost of the project.
     *
     * @return the economic cost of the project
     */
    public int getCout_economique() {
        return cout_economique;
    }

    /**
     * Getter for the social cost of the project.
     *
     * @return the social cost of the project
     */
    public int getCout_social() {
        return cout_social;
    }

    /**
     * Getter for the environmental cost of the project.
     *
     * @return the environmental cost of the project
     */
    public int getCout_environnemental() {
        return cout_environnemental;
    }
}
