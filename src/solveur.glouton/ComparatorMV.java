package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;

import java.util.*;

/**
 * Comparator for {@link Objet} based on a "utility-to-max-over-budget cost"
 * (FMV) ratio.
 *
 * This comparator is used to sort items in a SacADos problem according to their
 * potential value relative to the most critical budget dimension.
 *
 * @author Slimane
 * @version
 */
public class ComparatorMV implements Comparator<Objet> {

    private final SacADos s;

    /**
     * Constructs a ComparatorMV with the given SacADos context.
     *
     * @param s the SacADos instance providing budget and item information
     */
    public ComparatorMV(SacADos s) {
        this.s = s;
    }

    /**
     * Compares two Objet instances based on their FMV ratio.
     *
     * @param o1 the first Objet to compare
     * @param o2 the second Objet to compare
     * @return a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second
     */
    public int compare(Objet o1, Objet o2) {
        double fmv1 = fmv(o1);
        double fmv2 = fmv(o2);
        return Double.compare(fmv1, fmv2);
    }

    /**
     * Calculates the FMV (utility-to-max-over-budget cost) ratio for an Objet.
     *
     * @param o the Objet for which to calculate the FMV ratio
     * @return the FMV ratio as a double
     */
    public double fmv(Objet o) {
        int ui = o.getUtilite();
        int k = s.getDimension();
        int[] sommeCouts = new int[k];

        for (Objet obj : s.getObjets()) {
            int[] coutsObj = obj.getCouts();
            for (int d = 0; d < k; d++) {
                sommeCouts[d] += coutsObj[d];
            }
        }
        int maxDep = Integer.MIN_VALUE;
        List<Integer> L = new ArrayList<>();

        for (int d = 0; d < k; d++) {
            int dep = sommeCouts[d] - s.getBudgets()[d];
            if (dep > maxDep) {
                maxDep = dep;
                L.clear();
                L.add(d);
            } else if (dep == maxDep) {
                L.add(d);
            }
        }
        int coutMax = 0;
        int[] coutsO = o.getCouts();
        for (int dim : L) {
            if (coutsO[dim] > coutMax) {
                coutMax = coutsO[dim];
            }
        }
        return (coutMax == 0) ? 0 : (double) ui / coutMax;

    }
}
