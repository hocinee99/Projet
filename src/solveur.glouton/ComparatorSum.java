package solveur.glouton;

import sacADos.Objet;

import java.util.Comparator;

public class ComparatorSum implements Comparator<Objet> {
    public int compare(Objet o1,Objet o2){
        double f1 = fsum(o1);
        double f2 = fsum(o2);
        return Double.compare(f1,f2);
    }
    public double fsum(Objet o){
        int ui = o.getUtilite();
        int sum=0;
        for(int cout : o.getCouts()){
            sum+=cout;
        }
        return (sum==0) ? 0 : (double)ui/sum;
    }
}
