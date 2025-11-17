package solveur.glouton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import sacADos.Objet;
import sacADos.SacADos;

public class Glouton {

    public static List<Objet> gloutonAjouter(SacADos sac) {
        List<Objet> selection = new ArrayList<>();
        List<Objet> objets = sac.getObjets();
        objets.sort(Comparator.comparingDouble(Glouton::Comparaison_fun).reversed()
                .thenComparing(Objet::getUtilite, Comparator.reverseOrder()));

        int[] budgetsRestants = new int[sac.getBudgets().length];
        for (int i = 0; i < sac.getBudgets().length; i++) {
            budgetsRestants[i] = sac.getBudgets()[i];
        }

        for (Objet o : objets) {
            int[] couts = o.getCouts();
            boolean Ajoutable = true;
            for (int i = 0; i < budgetsRestants.length; i++) {
                if (couts[i] > budgetsRestants[i]) {
                    Ajoutable = false;
                    break;
                }
            }
            if (Ajoutable) {
                selection.add(o);
                for (int i = 0; i < budgetsRestants.length; i++) {
                    budgetsRestants[i] -= couts[i];
                }
            }
        }
        return selection;
    }

    public static double Comparaison_fun(Objet O) {
        int somme = 0;
        for (int i = 0; i < O.getCouts().length; i++) {
            somme += O.getCouts()[i];
        }
        return (somme == 0.0) ? 0.0 : (double) O.getUtilite() / somme;
    }

    public static List<Objet> gloutonRetirer(SacADos sac) {
        List<Objet> selection = new ArrayList<>(sac.getObjets());

        int[] budgetsRestants = new int[sac.getBudgets().length];
        for (int i = 0; i < sac.getBudgets().length; i++) {
            budgetsRestants[i] = sac.getBudgets()[i];
        }

    }
}
