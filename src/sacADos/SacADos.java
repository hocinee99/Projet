package sacADos;

import java.util.*;

public class SacADos {

    private int dimension;
    private int[] budgets;
    List<Objet> objets;

    public SacADos(int dimension, int[] budgets) {
        this.dimension = dimension;
        if (budgets != null && budgets.length == dimension) {
            this.budgets = budgets;
        } else {
            throw new IllegalArgumentException("Le tableau de budgets doit avoir exactement " + dimension + " valeurs.");
        }
        this.objets = new ArrayList<>();
    }

    public void addObjet(Objet o) {
        if (o.getCouts().length == dimension) {
            this.objets.add(o);
        } else {
            throw new IllegalArgumentException("L'objet doit avoir des coûts correspondant à la dimension du sac à dos.");
        }

    }

    public int getDimension() {
        return dimension;
    }

    public int[] getBudgets() {
        return budgets;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setBudget(int[] budgets) {
        if (budgets != null && budgets.length == dimension) {
            this.budgets = budgets;
        } else {
            throw new IllegalArgumentException("Le tableau de budgets doit avoir exactement " + dimension + " valeurs.");
        }
    }

}
