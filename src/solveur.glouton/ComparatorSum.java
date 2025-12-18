package solveur.glouton;

import sacADos.Objet;

import java.util.Comparator;

/**
 * Comparator that compares {@link Objet} based on the ratio of utility to the
 * sum of its costs across all dimensions.
 *
 * This comparator is useful for greedy selection in SacADos problems,
 * prioritizing items with high utility relative to their total cost.
 *
 * @author Slimane
 * @version 1.0
 */
public class ComparatorSum implements Comparator<Objet> {

    /**
     * Compares two Objet instances based on their utility-to-sum-of-costs ratio
     *
     * @param o1 the first Objet to compare
     * @param o2 the second Objet to compare
     * @return a negative integer, zero, or a positive integer as the first
     */
    public int compare(Objet o1, Objet o2) {
        double f1 = fsum(o1);
        double f2 = fsum(o2);
        return Double.compare(f1, f2);
    }

    /**
     * Calculates the utility-to-sum-of-costs ratio for an Objet.
     *
     * @param o the Objet for which to calculate the ratio
     * @return the utility-to-sum-of-costs ratio as a double
     */
    public double fsum(Objet o) {
        int ui = o.getUtilite();
        int sum = 0;
        for (int cout : o.getCouts()) {
            sum += cout;
        }
        return (sum == 0) ? 0 : (double) ui / sum;
    }
}
