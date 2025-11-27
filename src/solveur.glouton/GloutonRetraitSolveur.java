package solveur.glouton;
import sacADos.Objet;
import sacADos.SacADos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GloutonRetraitSolveur {
    public List<Objet> resoudre(SacADos sac, Comparator<Objet> comparatorRet,Comparator<Objet> ComparatorAj){
        List<Objet> sol = new ArrayList<>(sac.getObjets());
        sol.sort(comparatorRet);
        for(Objet o :new ArrayList<>(sol)){
            if(!sac.respecteContraintes(sol)){
                sol.remove(o);
            }
            else{
                break;
            }
            if(!sac.respecteContraintes(sol)){
                return new ArrayList<>();
            }
        }
    }
}
