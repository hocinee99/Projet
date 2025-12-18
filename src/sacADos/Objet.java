package sacADos;

import java.util.*;

/**
 * Represents an item that can be placed in a knapsack. Each item has a utility
 * value and associated costs.
 *
 * @author Slimane
 * @version 1.0
 */
public class Objet {

    private int utilite;
    private int[] couts;

    /**
     * Constructor of the Objet class, with it we initialize the attributes of
     * an object.
     *
     * @param utilite the utility value of the object
     * @param couts an array representing the costs associated with the object
     * @throws IllegalArgumentException if the costs array is null
     */
    public Objet(int utilite, int[] couts) {
        this.utilite = utilite;
        if (couts != null) {
            this.couts = couts;
        } else {
            throw new IllegalArgumentException("Le tableau des coûts ne peut pas être nul.");
        }

    }

    /**
     * Sets the utility value of the object.
     *
     * @param utilite the utility value to set
     */
    public void setUtilite(int utilite) {
        this.utilite = utilite;
    }

    /**
     * Getter for the utility value of the object.
     *
     * @return the utility value
     */
    public int getUtilite() {
        return utilite;
    }

    /**
     * Getter for the costs associated with the object.
     *
     * @return an array representing the costs
     */
    public int[] getCouts() {
        return couts;
    }

    /**
     * Setter for the costs associated with the object.
     *
     * @param couts an array representing the costs to set
     * @throws IllegalArgumentException if the costs array is null
     */
    public void setCouts(int[] couts) {
        if (couts != null) {
            this.couts = couts;
        } else {
            throw new IllegalArgumentException("Le tableau des coûts ne peut pas être nul.");
        }
    }

    /**
     * Provides a string representation of the object, including its utility and
     * costs.
     */
    @Override
    public String toString() {
        return "Objet{"
                + "utilite=" + utilite
                + ", couts=" + Arrays.toString(couts)
                + '}';
    }

}
