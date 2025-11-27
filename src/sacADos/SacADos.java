package sacADos;

import java.util.*;

public class SacADos {

    private final int dimension;
    private final int[] budgets;
    List<Objet> objets;

    public SacADos(int[] budgets) {
        if (budgets != null) {
            this.budgets = budgets;
            this.dimension = budgets.length;
        } else {
            throw new IllegalArgumentException("Le tableau budgets ne doit pas etre vide");
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