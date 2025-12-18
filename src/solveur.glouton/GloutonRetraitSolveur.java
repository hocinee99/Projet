package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * GloutonRetraitSolveur implements a greedy algorithm to select items for a
 * SacADos problem by removing items based on a specified comparator. The class
 * contains a method to remove items from the solution: - Items are sorted
 * according to the provided removal comparator in ascending order. Items are
 * removed if they do not fit within the remaining budgets. Items are added if
 * they fit within the remaining budgets.
 *
 * @author Slimane
 * @version 1.0
 */
public class GloutonRetraitSolveur {

    /**
     * Selects items to add to the SacADos using a greedy strategy based on the
     * provided comparators for removal and addition.
     *
     * @param sac the SacADos containing items and budgets
     * @param comparatorRet the comparator used to sort items for removal
     * @param comparatorAj the comparator used to sort items for addition
     * @return a list of items selected to fit in the SacADos
     */
    public List<Objet> resoudre(SacADos sac, Comparator<Objet> comparatorRet, Comparator<Objet> comparatorAj) {
        List<Objet> sol = new ArrayList<>(sac.getObjets());
        sol.sort(comparatorRet);
        for (Objet o : new ArrayList<>(sol)) {
            if (!sac.respecteContraintes(sol)) {
                sol.remove(o);
            }
            if (sac.respecteContraintes(sol)) {
                break;
            }
        }
        if (!sac.respecteContraintes(sol)) {
            return new ArrayList<>();
        }

        List<Objet> listeObj = new ArrayList<>(sac.getObjets());
        listeObj.sort(comparatorAj.reversed());
        for (Objet o : listeObj) {
            if (!sol.contains(o)) {
                List<Objet> listeAvecCandidats = new ArrayList<>(sol);
                listeAvecCandidats.add(o);
                if (sac.respecteContraintes(listeAvecCandidats)) {
                    sol.add(o);
                }
            }
        }
        return sol;
    }

}
