package sacADos;
import java.util.*;
import java.io.*;
import equipe.*;
public class VersSacADos {
    public static SacADos creerSacAvecCouts(List<Projet> listeProjets,int[]budgets){
        if(listeProjets == null || listeProjets.isEmpty()){
            throw new IllegalArgumentException("La liste de projets ne peut pas etre vide");
        }
        if(budgets==null || budgets.length != 3){
            throw new IllegalArgumentException("Le tableau des budgets doit etre de taille 3");
        }
        List<Objet> objets = new ArrayList<>();
        for(Projet p : listeProjets){
            int[] couts = new int[3];
            couts[0]=p.getCout_economique();
            couts[1]=p.getCout_social();
            couts[2]=p.getCout_environnemental();
            objets.add(new Objet(p.getBenefice(),couts));
        }
        SacADos sac=new SacADos(budgets);
        for (Objet o : objets){
            sac.addObjet(o);
        }
        return sac;
    }


    public static SacADos creerSacAvecBudgetsSeteurs(List<Projet> listeProjets,int[]budgets){
        if(listeProjets == null || listeProjets.isEmpty()){
            throw new IllegalArgumentException("La liste de projets ne peut pas etre vide");
        }
        if(budgets==null || budgets.length != 5){
            throw new IllegalArgumentException("Le tableau des budgets doit etre de taille 5");
        }
        List<Objet> objets = new ArrayList<>();
        for(Projet p : listeProjets){
            int[] couts = new int[5];
            couts[p.getSecteur().ordinal()]=p.getCout_economique();
            objets.add(new Objet(p.getBenefice(),couts));
        }
        SacADos sac=new SacADos(budgets);
        for (Objet o : objets){
            sac.addObjet(o);
        }
        return sac;
    }

    private static void lireEntiers(BufferedReader br, List<Integer> all, int count) throws IOException {
        while (all.size() < count) {
            String ligne = br.readLine();
            if (ligne == null) {
                throw new IllegalArgumentException("Pas assez d'entiers dans le fichier");
            }
            String[] tokens = ligne.trim().split("\\s+");
            for (String t : tokens) {
                if (!t.isEmpty()) {
                    all.add(Integer.parseInt(t));
                }
            }
        }
    }

    public static SacADos creerSacAvecFichier(String fichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String line = br.readLine();
            if (line == null) throw new IllegalArgumentException("Fichier vide");
            String[] header = line.trim().split("\\s+");
            int n = Integer.parseInt(header[0]);
            int k = Integer.parseInt(header[1]);
            List<Integer> util = new ArrayList<>();
            lireEntiers(br, util, n);
            int[] utilites = new int[n];
            for (int i = 0; i < n; i++) utilites[i] = util.get(i);
            List<Integer> matrice = new ArrayList<>();
            lireEntiers(br, matrice, n * k);
            int[][] couts = new int[k][n];
            int index = 0;
            for (int l = 0; l < k; l++) {
                for (int i = 0; i < n; i++) {
                    couts[l][i] = matrice.get(index);
                    index++;
                }
            }
            List<Integer> budg = new ArrayList<>();
            lireEntiers(br, budg, k);
            int[] budgets = new int[k];
            for (int l = 0; l < k; l++) budgets[l] = budg.get(l);
            SacADos sac = new SacADos(budgets);
            for (int i = 0; i < n; i++) {
                int[] cout = new int[k];
                for (int l = 0; l < k; l++) cout[l] = couts[l][i];
                sac.addObjet(new Objet(utilites[i], cout));
            }
            return sac;

        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture " + fichier+ "manque de droits d'accés au fichier");
        }
    }


}
