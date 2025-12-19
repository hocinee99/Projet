package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sacADos.*;
import solveur.glouton.ComparatorSum;
import solveur.glouton.GloutonAjouterSolveur;
import solveurHillclimbing.HillClimbing;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== APPLICATION GESTION BUDGET DAUPHINE CITY ===");
        boolean running = true;

        while (running) {
            afficherMenuPrincipal();
            int choix = readEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    lancerScenarioComplet();
                    break;
                case 2:
                    System.out.println("Au revoir !");
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void afficherMenuPrincipal() {
        System.out.println("------------------------------------------------");
        System.out.println("1. Lancer une simulation (Génération + Résolution)");
        System.out.println("2. Quitter");
        System.out.println("--------------------------------------------------");
    }

    private static void lancerScenarioComplet() {
        SacADos probleme = null;
        System.out.println("\n--- CONFIGURATION DE L'INSTANCE ---");
        System.out.println("Comment souhaitez-vous créer l'instance du problème ?");
        System.out.println("1. Générer aléatoirement (Simulation simple)");
        System.out.println("2. Charger depuis un fichier Benchmark (VersaCados)");

        int sourceChoix = readEntier("Votre choix : ");

        if (sourceChoix == 2) {
            System.out.println("Veuillez entrer le chemin du fichier  :");
            String chemin = scanner.nextLine();
            if(chemin.isEmpty()) chemin = scanner.nextLine();

            try {
                probleme = VersSacADos.creerSacAvecFichier(chemin);
                System.out.println(">> Fichier chargé avec succès !");
            } catch (Exception e) {
                System.out.println(">> ERREUR : Impossible de lire le fichier.");
                System.out.println(">> Détail : " + e.getMessage());
                return;
            }

        } else {
            System.out.println("\n1. Scénario 'Budgets' (3 contraintes : Eco, Social, Env)");
            System.out.println("2. Scénario 'Secteurs' (5 contraintes : Sport, Santé...)");
            int type = readEntier("Choix du scénario : ");
            probleme = genererInstanceAleatoire(type == 2 ? 5 : 3);
            System.out.println(">> Instance aléatoire générée.");
        }

        System.out.println("\n--- RÉCAPITULATIF DU PROBLÈME ---");
        System.out.println("Nombre d'objets : " + probleme.getObjets().size());
        System.out.println("Nombre de contraintes (dimensions) : " + probleme.getBudgets().length);
        System.out.print("Budgets : [ ");
        for(int b : probleme.getBudgets()) System.out.print(b + " ");
        System.out.println("]");

        // 2. Résolution Gloutonne (Solution Initiale)
        System.out.println("\n--- ETAPE 1 : RÉSOLUTION GLOUTONNE ---");
        GloutonAjouterSolveur solveurGlouton = new GloutonAjouterSolveur();
        List<Objet> solutionInitiale = solveurGlouton.resoudre(probleme, new ComparatorSum());

        int scoreGlouton = calculerScore(solutionInitiale);
        System.out.println(">> Solution Gloutonne trouvée.");
        System.out.println(">> Utilité totale : " + scoreGlouton);
        System.out.println(">> Nombre d'objets sélectionnés : " + solutionInitiale.size());

        // 3. Amélioration Hill Climbing 
        System.out.println("\n--- ETAPE 2 : AMÉLIORATION HILL CLIMBING ---");
        HillClimbing hc = new HillClimbing(probleme);

        System.out.println("Quel type de Hill Climbing appliquer ?");
        System.out.println("1. Standard (explore tous les voisins)");
        System.out.println("2. Aléatoire (nombre de voisins limité) ");

        int typeHC = readEntier("Votre choix : ");
        List<Objet> solutionFinale;
        long debut = System.currentTimeMillis();

        if (typeHC==2) {
            int k = readEntier("Nombre de voisins à tester à chaque étape : ");
            solutionFinale = hc.resoudreAleatoire(solutionInitiale, k);
        } else {
            System.out.println("Lancement du Hill Climbing Standard...");
            solutionFinale = hc.resoudre(solutionInitiale);
        }
        long fin = System.currentTimeMillis();

        // 4. Résultats Finaux
        int scoreFinal = calculerScore(solutionFinale);
        System.out.println("\n>>> RÉSULTATS FINAUX <<<");
        System.out.println("Temps de calcul : " + (fin - debut) + " ms");
        System.out.println("Utilité Glouton : " + scoreGlouton);
        System.out.println("Utilité Hill Climbing : " + scoreFinal);

        int gain = scoreFinal-scoreGlouton;
        if (gain > 0) {
            System.out.println("BRAVO ! Gain d'utilité : +" + gain);
        } else {
            System.out.println("Pas d'amélioration trouvée (Optimum local atteint).");
        }
    }

    // methodes 

    private static int readEntier(String message) {
        int valeur = 0;
        boolean valide = false;
        while (!valide) {
            try {
                System.out.print(message);
                valeur = scanner.nextInt();
                scanner.nextLine();
                valide = true;
            } catch (Exception e) {
                System.out.println("erreur : Veuillez entrer un nombre entier valide.");
                scanner.nextLine();
            }
        }
        return valeur;
    }

    private static int calculerScore(List<Objet> solution) {
        int score= 0;
        for (Objet o : solution) {
            score+= o.getUtilite();}
        return score;
    }

    private static SacADos genererInstanceAleatoire(int nbDimensions) {
        Random rand = new Random();
        int nbObjets = 50;
        int[] budgets = new int[nbDimensions];

        for(int i=0; i<nbDimensions; i++){ budgets[i] = 50 + rand.nextInt(150);}

        SacADos sac = new SacADos(budgets);
        for (int i = 0; i < nbObjets; i++) {
            int utilite = 10 + rand.nextInt(90);
            int[] couts = new int[nbDimensions];
            for (int j = 0; j < nbDimensions; j++) {
                couts[j] = rand.nextInt(30);}

            Objet o = new Objet(utilite, couts);
            sac.addObjet(o);
        }

        return sac;
    }
}