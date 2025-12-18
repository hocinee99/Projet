package equipe;

/**
 * A Personne is characterized by a name, a surname, and an age. This class
 * serves as a base for more specific types of people. these types are
 * elu,evaluateur and expert
 *
 * @author Hocine
 * @version 1.0
 */
public abstract class Personne {

    private final String nom;
    private final String prenom;
    private int age;

    /**
     * Constructor of the Personne class, with it we initialize the attributes
     * of a person.
     *
     * @param nom the surname of the person
     * @param prenom the name of the person
     * @param age the of the person
     */
    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.setAge(age);
    }

    /**
     * Getter for the name of the person.
     *
     * @return the name of the person
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter for the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for the surname of the person.
     *
     * @return the surname of the person
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter for the age of the person.
     *
     * @param age the new age of the person
     * @throws IllegalArgumentException if the age is negative
     */
    public void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("L'age ne peut pas etre négatif");
        } else {
            this.age = age;
        }
    }
}
