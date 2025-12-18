package equipe;

/**
 * Represents a municipal team responsible for evaluating and managing projects.
 *
 * A municipal team consists of:
 * <ul>
 * <li>An elU ({@link Elu}) who evaluates the overall benefit of projects</li>
 * <li>Experts ({@link Expert}) who propose projects</li>
 * <li>Evaluators ({@link Evaluateur}) specialized in economic, social, and
 * environmental costs</li>
 * </ul>
 *
 * The team can simulate a project cycle in which each expert proposes a
 * project, the evaluators assess the different costs, and the elU evaluates the
 * benefit.
 *
 * @author Hocine
 * @version 1.0
 */
import java.util.ArrayList;

public class EquipeMunicipale {

    private final Elu elu;
    private final ArrayList<Expert> liste_experts;
    private final Evaluateur evaluateur_economique;
    private final Evaluateur evaluateur_social;
    private final Evaluateur evaluateur_environnemental;
    private final ArrayList<Projet> listeProjets;

    /**
     * Constructs a new municipal team with the given elected official, experts,
     * and evaluators.
     *
     * @param elu the elected official
     * @param liste_experts the list of experts
     * @param evaluateur_economique the evaluator specialized in economic costs
     * @param evaluateur_environnemental the evaluator specialized in
     * environmental costs
     * @param evaluateur_social the evaluator specialized in social costs
     */
    public EquipeMunicipale(Elu elu, ArrayList<Expert> liste_experts, Evaluateur evaluateur_economique, Evaluateur evaluateur_environnemental, Evaluateur evaluateur_social) {
        this.elu = elu;
        this.evaluateur_economique = evaluateur_economique;
        this.evaluateur_environnemental = evaluateur_environnemental;
        this.evaluateur_social = evaluateur_social;
        this.liste_experts = liste_experts;
        this.listeProjets = new ArrayList<>();
    }

    /**
     * Simulates a project cycle in which each expert proposes a project,
     * evaluators assess its costs, and the elu evaluates the benefit. The
     * evaluated project is then added to the internal list of projects.
     */
    public void simulerCycle() {
        for (Expert expert : liste_experts) {
            Projet p = expert.proposeProjet();
            evaluateur_social.evaluerCout(p);
            evaluateur_economique.evaluerCout(p);
            evaluateur_environnemental.evaluerCout(p);
            elu.evaluerBenefice(p);
            listeProjets.add(p);
        }
    }

    // Getters
    /**
     * Getter for the elected official of the municipal team.
     *
     * @return the elected official
     */
    public Elu getElu() {
        return elu;
    }

    /**
     * Getter for the list of experts in the municipal team.
     *
     * @return the list of experts
     */
    public ArrayList<Expert> getListe_experts() {
        return liste_experts;
    }

    /**
     * Getter for the evaluator specialized in economic costs.
     *
     * @return the economic evaluator
     */
    public Evaluateur getEvaluateur_economique() {
        return evaluateur_economique;
    }

    /**
     * Getter for the evaluator specialized in social costs.
     *
     * @return the social evaluator
     */
    public Evaluateur getEvaluateur_social() {
        return evaluateur_social;
    }

    /**
     * Getter for the evaluator specialized in environmental costs.
     *
     * @return the environmental evaluator
     */
    public Evaluateur getEvaluateur_environnemental() {
        return evaluateur_environnemental;
    }

    /**
     * Getter for the list of projects evaluated by the municipal team.
     *
     * @return the list of projects
     */
    public ArrayList<Projet> getListeProjets() {
        return listeProjets;
    }
}
