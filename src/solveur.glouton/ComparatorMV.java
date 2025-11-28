package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;

import java.util.*;

public class ComparatorMV implements Comparator<Objet> {
    private final SacADos s;
    public ComparatorMV(SacADos s){
        this.s=s;
    }
    public int compare(Objet o1,Objet o2){
        double fmv1 = fmv(o1);
        double fmv2 = fmv(o2);
        return Double.compare(fmv1,fmv2);
    }
    public double fmv(Objet o){
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
            int coutMax=0;
            int[] coutsO = o.getCouts();
            for(int dim : L){
                if(coutsO[dim]>coutMax){
                    coutMax=coutsO[dim];
                }
            }
            return (coutMax==0) ? 0 : (double) ui/coutMax;

    }
}
