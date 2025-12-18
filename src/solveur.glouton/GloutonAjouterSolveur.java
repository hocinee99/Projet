package solveur.glouton;

import java.util.*;
import sacADos.*;

/**
 * GloutonAjouterSolveur implements a greedy algorithm to select items for a
 * SacADos problem by adding items based on a specified comparator.
 *
 * The class contains a method to add items to the solution: - Items are sorted
 * according to the provided comparator in descending order. - Items are added
 * if they fit within the remaining budgets.
 *
 * @author Slimane
 * @version 1.0
 */
public class GloutonAjouterSolveur {

    /**
     * Selects items to add to the SacADos using a greedy strategy based on the
     * provided comparator.
     *
     * @param sac the SacADos containing items and budgets
     * @param comparator the comparator used to sort items
     * @return a list of items selected to fit in the SacADos
     */
    public List<Objet> resoudre(SacADos sac, Comparator<Objet> comparator) {
        List<Objet> sol = new ArrayList<>();
        List<Objet> listeObj = new ArrayList<>(sac.getObjets());
        listeObj.sort(comparator.reversed()); // ordre decroissant
        int k = sac.getDimension();
        int[] coutsTotaux = new int[k];
        for (Objet o : listeObj) {
            int[] couts = o.getCouts();
            boolean peutAjouter = true;
            for (int i = 0; i < k; i++) {
                if (coutsTotaux[i] + couts[i] > sac.getBudgets()[i]) {
                    peutAjouter = false;
                    break;
                }
            }
            if (peutAjouter) {
                sol.add(o);
                for (int j = 0; j < k; j++) {
                    coutsTotaux[j] += couts[j];
                }
            }
        }
        return sol;
    }

}
