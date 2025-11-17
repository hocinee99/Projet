package sacADos;

import java.util.*;

public class Objet {

    private int utilite;
    private int[] couts;

    public Objet(int utilite, int[] couts) {
        this.utilite = utilite;
        if (couts != null) {
            this.couts = couts;
        } else {
            throw new IllegalArgumentException("Le tableau des coûts ne peut pas être nul.");
        }

    }

    public void setUtilite(int utilite) {
        this.utilite = utilite;
    }

    public int getUtilite() {
        return utilite;
    }

    public int[] getCouts() {
        return couts;
    }

    public void setCouts(int[] couts) {
        if (couts != null) {
            this.couts = couts;
        } else {
            throw new IllegalArgumentException("Le tableau des coûts ne peut pas être nul.");
        }
    }

    @Override
    public String toString() {
        return "Objet{"
                + "utilite=" + utilite
                + ", couts=" + Arrays.toString(couts)
                + '}';
    }

}
