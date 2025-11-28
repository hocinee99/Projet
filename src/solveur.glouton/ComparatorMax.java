package solveur.glouton;

import sacADos.Objet;

import java.util.Comparator;

public class ComparatorMax implements Comparator<Objet> {

    public int compare(Objet o1,Objet o2){
        double f1 = fmax(o1);
        double f2 = fmax(o2);
        return Double.compare(f1,f2);
    }

    public double fmax(Objet o) {
        int ui = o.getUtilite();
        int[] couts = o.getCouts();
        int max = Integer.MIN_VALUE;
        for (int cout : couts) {
            if (cout > max) {
                max = cout;
            }
        }
        if(max==0) {
            return 0;
        }
        else{
            return (double)ui/max;
        }
    }
}