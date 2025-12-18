package equipe;

/**
 * An Evaluator is a {@link Person} who assesses the economic, social, or
 * environmental cost of a {@link Project} depending on their specialization.
 *
 *
 * @author Hocine
 * @version 1.0
 */

public class Evaluateur extends Personne {

    private final TypeCout specialisation;

    /**
     * Constructor of the Evaluateur class, with it we initialize the attributes
     * of an evaluator.
     *
     * @param nom the surname of the evaluator
     * @param prenom the name of the evaluator
     * @param age the of the evaluator
     * @param specialisation the specialization of the evaluator (economic,
     * social, or environmental)
     */
    public Evaluateur(String nom, String prenom, int age, TypeCout specialisation) {
        super(nom, prenom, age);
        this.specialisation = specialisation;
    }

    /**
     * used to evaluate the cost of a project based on the evaluator's
     * specialization.
     *
     * @param p the project to evaluate
     * @return the cost evaluated by the evaluator
     */
    public double evaluerCout(Projet p) {
        double cout = Math.random() * 100;
        switch (specialisation) {
            case economique ->
                p.setCout_economique(cout);
            case social ->
                p.setCout_social(cout);
            case environnemental ->
                p.setCout_environnemental(cout);
        }
        return cout;
    }

    /**
     * Getter for the specialization of the evaluator.
     *
     * @return the specialization of the evaluator
     */
    public TypeCout getSpecialisation() {
        return specialisation;
    }
}
