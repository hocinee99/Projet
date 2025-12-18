package sacADos;

import java.util.*;

/**
 * Represents a multi-dimensional sacados problem. a knapsack has a certain
 * dimension, budgets for each dimension, and a list of items (objects) that can
 * be placed in the sacados.
 *
 * @author Slimane
 */
public class SacADos {

    private final int dimension;
    private final int[] budgets;
    private List<Objet> objets;

    /**
     * Constructor of the SacADos class, with it we initialize the attributes of
     * a Sacados.
     *
     * @param budgets an array representing the budgets for each dimension
     * @throws IllegalArgumentException if the budgets array is null
     */
    public SacADos(int[] budgets) {
        if (budgets != null) {
            this.budgets = budgets;
            this.dimension = budgets.length;
        } else {
            throw new IllegalArgumentException("Le tableau budgets ne doit pas etre vide");
        }
        this.objets = new ArrayList<>();
    }

    /**
     * Adds an object to the sacados if its costs match the sacados dimension.
     *
     * @param o the object to add
     * @throws IllegalArgumentException if the object's costs do not match the
     * sacados dimension
     */
    public void addObjet(Objet o) {
        if (o.getCouts().length == dimension) {
            this.objets.add(o);
        } else {
            throw new IllegalArgumentException("L'objet doit avoir des coûts correspondant à la dimension du sac à dos.");
        }

    }

    /**
     * Getter for the dimension of the sacados.
     *
     * @return the dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Getter for the budgets of the sacados.
     *
     * @return an array representing the budgets
     */
    public int[] getBudgets() {
        return budgets;
    }

    /**
     * Getter for the list of objects in the sacados.
     *
     * @return a list of objects
     */
    public List<Objet> getObjets() {
        return objets;
    }

    /**
     * Checks if a given list of objects respects the sacados constraints.
     *
     * @param liste the list of objects to check
     * @return true if the list respects the constraints, false otherwise
     */
    public boolean respecteContraintes(List<Objet> liste) {
        int[] couts = new int[dimension];

        for (Objet o : liste) {
            for (int i = 0; i < dimension; i++) {
                couts[i] += o.getCouts()[i];
                if (couts[i] > budgets[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
