package sacADos;

import equipe.*;
import java.io.*;
import java.util.*;

/**
 * Utility class for creating SacADos instances from various data sources,
 * including project lists and files. This class provides methods to convert
 * project data into knapsack objects with appropriate costs and budgets.
 *
 * @author Slimane
 * @version 1.0
 */
public class VersSacADos {

    /**
     * Creates a SacADos instance from a list of projects and specified budgets.
     * Each project's costs are extracted and used to create corresponding Objet
     * instances which are then added to the SacADos.
     *
     * @param listeProjets List of projects to be converted into knapsack
     * objects
     * @param budgets Array of budgets corresponding to the knapsack constraints
     * @return A SacADos instance populated with objects derived from the
     * provided projects
     * @throws IllegalArgumentException if the project list is null or empty, or
     * if the budgets array is not of size 3
     *
     */
    public static SacADos creerSacAvecCouts(List<Projet> listeProjets, int[] budgets) {
        if (listeProjets == null || listeProjets.isEmpty()) {
            throw new IllegalArgumentException("La liste de projets ne peut pas etre vide");
        }
        if (budgets == null || budgets.length != 3) {
            throw new IllegalArgumentException("Le tableau des budgets doit etre de taille 3");
        }
        List<Objet> objets = new ArrayList<>();
        for (Projet p : listeProjets) {
            int[] couts = new int[3];
            couts[0] = p.getCout_economique();
            couts[1] = p.getCout_social();
            couts[2] = p.getCout_environnemental();
            objets.add(new Objet(p.getBenefice(), couts));
        }
        SacADos sac = new SacADos(budgets);
        for (Objet o : objets) {
            sac.addObjet(o);
        }
        return sac;
    }

    /**
     * Creates a SacADos instance from a list of projects and specified budgets.
     * Each project's cost is assigned to the budget corresponding to its
     * sector, while other costs are set to zero. The projects are converted
     * into Objet instances which are then added to the SacADos.
     *
     * @param listeProjets List of projects to be converted into knapsack
     * objects
     * @param budgets Array of budgets corresponding to the knapsack constraints
     * @return A SacADos instance populated with objects derived from the
     * provided projects
     * @throws IllegalArgumentException if the project list is null or empty, or
     * if the budgets array is not of size 5
     *
     */
    public static SacADos creerSacAvecBudgetsSeteurs(List<Projet> listeProjets, int[] budgets) {
        if (listeProjets == null || listeProjets.isEmpty()) {
            throw new IllegalArgumentException("La liste de projets ne peut pas etre vide");
        }
        if (budgets == null || budgets.length != 5) {
            throw new IllegalArgumentException("Le tableau des budgets doit etre de taille 5");
        }
        List<Objet> objets = new ArrayList<>();
        for (Projet p : listeProjets) {
            int[] couts = new int[5];
            couts[p.getSecteur().ordinal()] = p.getCout_economique();
            objets.add(new Objet(p.getBenefice(), couts));
        }
        SacADos sac = new SacADos(budgets);
        for (Objet o : objets) {
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

    /**
     * Creates a SacADos instance by reading data from a specified file. The
     * file should contain the number of items, number of constraints, item
     * utilities, cost matrix, and budget constraints in a specific format.
     *
     * @param fichier The path to the file containing the knapsack data
     * @return A SacADos instance populated with objects derived from the file
     * data
     * @throws RuntimeException if there is an error reading the file or if the
     * file format is incorrect
     */
    public static SacADos creerSacAvecFichier(String fichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String line = br.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Fichier vide");
            }
            String[] header = line.trim().split("\\s+");
            int n = Integer.parseInt(header[0]);
            int k = Integer.parseInt(header[1]);
            List<Integer> util = new ArrayList<>();
            lireEntiers(br, util, n);
            int[] utilites = new int[n];
            for (int i = 0; i < n; i++) {
                utilites[i] = util.get(i);
            }
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
            for (int l = 0; l < k; l++) {
                budgets[l] = budg.get(l);
            }
            SacADos sac = new SacADos(budgets);
            for (int i = 0; i < n; i++) {
                int[] cout = new int[k];
                for (int l = 0; l < k; l++) {
                    cout[l] = couts[l][i];
                }
                sac.addObjet(new Objet(utilites[i], cout));
            }
            return sac;

        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture " + fichier + "manque de droits d'accés au fichier");
        }
    }

}
