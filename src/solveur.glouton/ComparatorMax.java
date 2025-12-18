package solveur.glouton;

import sacADos.Objet;

import java.util.Comparator;

/**
 * Comparator that compares {@link Objet} based on the ratio of utility to the
 * maximum cost among its dimensions.
 *
 * This comparator is useful for greedy selection in SacADos problems,
 * prioritizing items with high utility relative to their maximum cost.
 *
 * @author Slimane
 * @version 1.0
 */
public class ComparatorMax implements Comparator<Objet> {

    /**
     * Compares two Objet instances based on their utility-to-max-cost ratio
     *
     * @param o1 the first Objet to compare
     * @param o2 the second Objet to compare
     * @return a negative integer, zero, or a positive integer as the first
     */
    public int compare(Objet o1, Objet o2) {
        double f1 = fmax(o1);
        double f2 = fmax(o2);
        return Double.compare(f1, f2);
    }

    /**
     * Calculates the utility-to-max-cost ratio for an Objet.
     *
     * @param o the Objet for which to calculate the ratio
     * @return the utility-to-max-cost ratio as a double
     */
    public double fmax(Objet o) {
        int ui = o.getUtilite();
        int[] couts = o.getCouts();
        int max = Integer.MIN_VALUE;
        for (int cout : couts) {
            if (cout > max) {
                max = cout;
            }
        }
        if (max == 0) {
            return 0;
        } else {
            return (double) ui / max;
        }
    }
}
