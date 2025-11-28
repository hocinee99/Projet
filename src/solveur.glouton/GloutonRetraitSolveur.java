package solveur.glouton;
import sacADos.Objet;
import sacADos.SacADos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class GloutonRetraitSolveur{
public List<Objet> resoudre(SacADos sac, Comparator<Objet> comparatorRet, Comparator<Objet> comparatorAj) {
    List<Objet> sol = new ArrayList<>(sac.getObjets());
    sol.sort(comparatorRet);
    for (Objet o : new ArrayList<>(sol)) {
        if(!sac.respecteContraintes(sol)){
            sol.remove(o);
        }
        if (sac.respecteContraintes(sol)) {
            break;
        }
    }
    if (!sac.respecteContraintes(sol)) {
        return new ArrayList<>();
    }

    List<Objet> listeObj = new ArrayList<>(sac.getObjets());
    listeObj.sort(comparatorAj.reversed());
    for (Objet o : listeObj) {
        if (!sol.contains(o)) {
            List<Objet> listeAvecCandidats = new ArrayList<>(sol);
            listeAvecCandidats.add(o);
            if (sac.respecteContraintes(listeAvecCandidats)) {
                sol.add(o);
            }
        }
    }
    return sol;
}

}
