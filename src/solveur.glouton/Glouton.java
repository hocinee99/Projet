package solveur.glouton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import sacADos.Objet;
import sacADos.SacADos;

/**
 * Provides greedy algorithms to select items for a SacADos problem.
 *
 * The class contains methods to add or remove items based on a greedy strategy:
 * - Items are sorted according to a utility-to-cost ratio. - Items are added if
 * they fit within the remaining budgets. - Items are removed if they cause
 * budget violations.
 *
 * @author Slimane
 * @version
 */
public class Glouton {

    /**
     * Selects items to add to the SacADos using a greedy strategy. Items are
     * sorted by utility-to-cost ratio in descending order.
     *
     * @param sac the SacADos containing items and budgets
     * @return a list of items selected to fit in the SacADos
     */
    public static List<Objet> gloutonAjouter(SacADos sac) {
        List<Objet> selection = new ArrayList<>();
        List<Objet> objets = sac.getObjets();
        objets.sort(Comparator.comparingDouble(Glouton::Comparaison_fun).reversed()
                .thenComparing(Objet::getUtilite, Comparator.reverseOrder()));

        int[] budgetsRestants = new int[sac.getBudgets().length];
        for (int i = 0; i < sac.getBudgets().length; i++) {
            budgetsRestants[i] = sac.getBudgets()[i];
        }

        for (Objet o : objets) {
            int[] couts = o.getCouts();
            boolean Ajoutable = true;
            for (int i = 0; i < budgetsRestants.length; i++) {
                if (couts[i] > budgetsRestants[i]) {
                    Ajoutable = false;
                    break;
                }
            }
            if (Ajoutable) {
                selection.add(o);
                for (int i = 0; i < budgetsRestants.length; i++) {
                    budgetsRestants[i] -= couts[i];
                }
            }
        }
        return selection;
    }

    /**
     * Computes the utility-to-total-cost ratio of an item.
     *
     * @param O the item to evaluate
     * @return the ratio of utility to the sum of costs
     */
    public static double Comparaison_fun(Objet O) {
        int somme = 0;
        for (int i = 0; i < O.getCouts().length; i++) {
            somme += O.getCouts()[i];
        }
        return (somme == 0.0) ? 0.0 : (double) O.getUtilite() / somme;
    }

    /**
     * Selects items to remove from the SacADos using a greedy strategy
     *
     * @param sac the SacADos containing items and budgets
     * @return a list of items after removing those that exceed budgets
     */
    public static List<Objet> gloutonRetirer(SacADos sac) {
        List<Objet> selection = new ArrayList<>(sac.getObjets());

        int[] budgetsRestants = new int[sac.getBudgets().length];
        for (int i = 0; i < sac.getBudgets().length; i++) {
            budgetsRestants[i] = sac.getBudgets()[i];
        }

    }
}
