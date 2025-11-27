package solveur.glouton;
import java.util.*;
import sacADos.*;
public class GloutonAjouterSolveur {
    public List<Objet> resoudre(SacADos sac,Comparator<Objet> comparator){
        List<Objet> sol = new ArrayList<>();
        List<Objet> listeObj = new ArrayList<>(sac.getObjets());
        listeObj.sort(comparator.reversed()); // ordre decroissant
        int k = sac.getDimension();
        int[] coutsTotaux=new int[k];
        for(Objet o : listeObj){
            int[] couts = o.getCouts();
            boolean peutAjouter= true;
            for(int i=0;i<k;i++){
                if(coutsTotaux[i]+couts[i]>sac.getBudgets()[i]){
                    peutAjouter=false;
                    break;
                }
                if(peutAjouter){
                    sol.add(o);
                    for(int j=0;i<k;i++){
                        coutsTotaux[i]+=couts[i];
                    }
                }
            }
        }
    return sol;
    }


}
