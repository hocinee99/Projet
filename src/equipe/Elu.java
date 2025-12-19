package equipe;

/**
 * An elu is a {@link Personne} who evaluates the economic benefit of a
 * {@link Projet}.
 *
 * @author Hocine
 * @version 1.0
 */

public class Elu extends Personne {

    /**
     * Constructor of the Elu class, with it we initialize the attributes of an
     * élu.
     *
     * @param nom the surname of the élu
     * @param prenom the name of the élu
     * @param age the of the élu
     */
    public Elu(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    /**
     * used to evaluate the economic benefit of a project.
     *
     * @param p the project to evaluate
     */
    public void evaluerBenefice(Projet p) {
        int benef =(int) (Math.random() * 10000);
        p.setBenefice(benef);
    }
}
