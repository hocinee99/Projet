package solveurHillclimbing;

import sacADos.SacADos;
import sacADos.Objet;
import java.util.*;

public class HillClimbing {

    private SacADos probleme;
    private Random random;

    public HillClimbing(SacADos probleme) {
        this.probleme = probleme;
        this.random = new Random();
    }

    public List<Objet> resoudre(List<Objet> solutionInitiale) {
        List<Objet> solutionCourante = new ArrayList<>(solutionInitiale);
        int utiliteCourante = calculerUtiliteTotale(solutionCourante);

        boolean ameliorationTrouvee = true;

        while (ameliorationTrouvee) {
            ameliorationTrouvee = false;
            List<Objet> meilleurVoisin = null;
            int meilleurGain = 0; // Le gain d'utilité par rapport à la solution courante

            List<Objet> objetsNonInclus = getObjetsNonInclus(solutionCourante);

            // si le sac n'est pas pleinn (cas 1) (esseai 1)
            // S'= S U {a} 
            for (Objet ajout : objetsNonInclus) {
                if (estAjoutValide(solutionCourante, ajout)) {
                    if (ajout.getUtilite()>meilleurGain) {
                        meilleurGain = ajout.getUtilite();
                        meilleurVoisin = new ArrayList<>(solutionCourante);
                        meilleurVoisin.add(ajout);
                    }
                }
            }

            //cas 2 (2eme tentative)
            // S'= (S\{E}) U {a} (|E|=1 et|A|=1)
            for (Objet retrait : solutionCourante) {
                for (Objet ajout : objetsNonInclus) {
                    // Calcul du gain potentiel : Utilité Ajout - Utilité Retrait
                    int gainPotentiel=ajout.getUtilite()-retrait.getUtilite();

                    // On ne teste la validité que si le gain est intéressant
                    if (gainPotentiel>meilleurGain) {
                        if (estEchangeValide(solutionCourante, retrait, ajout)) {
                            meilleurGain = gainPotentiel;
                            meilleurVoisin = new ArrayList<>(solutionCourante);
                            meilleurVoisin.remove(retrait);
                            meilleurVoisin.add(ajout);
                        }
                    }
                }
            }

            if (meilleurVoisin!=null) {
                solutionCourante=meilleurVoisin;
                utiliteCourante+=meilleurGain;
                ameliorationTrouvee = true;
                System.out.println("Amélioration trouvée:"+ utiliteCourante);
            }
        }

        return solutionCourante; // C'est un optimum local
    }



    // version 2 : random (La variante )
    public List<Objet> resoudreAleatoire(List<Objet> solutionInitiale, int nbVoisinsMax) {
    List<Objet> solutionCourante = new ArrayList<>(solutionInitiale);
    boolean ameliorationTrouvee = true;

    while (ameliorationTrouvee) {
        ameliorationTrouvee = false;
        
        List<Objet> meilleurVoisinEchantillon = null;
        int meilleurGain = 0;

        List<Objet> objetsNonInclus = getObjetsNonInclus(solutionCourante);
        
        // On boucle le nombre de fois demandé par le paramètre
        for (int i=0;i<nbVoisinsMax;i++) {

            // on tire au sort : 0=>(tentative d'ajout) , 1=> (tentative d'échange)
            boolean tentativeAjout = random.nextBoolean(); 
            
            // cas aleatoire 1: ajout 
            if (tentativeAjout && !objetsNonInclus.isEmpty()) {
                Objet objAjout = objetsNonInclus.get(random.nextInt(objetsNonInclus.size()));
                if (estAjoutValide(solutionCourante, objAjout)) {
                    if (objAjout.getUtilite() > meilleurGain) {
                        meilleurGain = objAjout.getUtilite();
                        meilleurVoisinEchantillon = new ArrayList<>(solutionCourante);
                        meilleurVoisinEchantillon.add(objAjout);}
                }
            } 
            // cas 2 (echange)
            else if (!solutionCourante.isEmpty() && !objetsNonInclus.isEmpty()) {
                Objet objRetrait = solutionCourante.get(random.nextInt(solutionCourante.size()));
                Objet objAjout = objetsNonInclus.get(random.nextInt(objetsNonInclus.size()));
                
                int gain=objAjout.getUtilite()-objRetrait.getUtilite();
                if (gain> meilleurGain) {
                    if (estEchangeValide(solutionCourante, objRetrait, objAjout)) {
                        meilleurGain = gain;
                        meilleurVoisinEchantillon = new ArrayList<>(solutionCourante);
                        meilleurVoisinEchantillon.remove(objRetrait);
                        meilleurVoisinEchantillon.add(objAjout);}
                }
            }
        }

        // sii dans notre echantillon aléatoire on a trouvé mieux , on se déplace
        if (meilleurVoisinEchantillon!=null) {
            solutionCourante = meilleurVoisinEchantillon;
            ameliorationTrouvee = true;
            System.out.println("voisin aléatoire trouvé avec gain:"+meilleurGain);
        }
    }
    return solutionCourante;
    }





    private boolean estAjoutValide(List<Objet> solution, Objet aAjouter) {
        int[] budgets = probleme.getBudgets();
        int nbDimensions = budgets.length;
        int[] coutsActuels = sommeCouts(solution);

        for (int i = 0;i <nbDimensions;i++) {
            if (coutsActuels[i]+aAjouter.getCouts()[i] > budgets[i]) {
                return false;}
        }
        return true;
    }


    private boolean estEchangeValide(List<Objet> solution, Objet retrait, Objet ajout) {
        int[] budgets = probleme.getBudgets();
        int nbDimensions = budgets.length;
        
        int[] coutsActuels = sommeCouts(solution);

        for (int i = 0; i <nbDimensions; i++) {
            int nouveauCout = coutsActuels[i]-retrait.getCouts()[i] + ajout.getCouts()[i];
            if (nouveauCout>budgets[i]) {return false;}}
        return true;
    }

    private List<Objet> getObjetsNonInclus(List<Objet> solution) {
        List<Objet> nonInclus = new ArrayList<>(probleme.getObjets());
        nonInclus.removeAll(solution);
        return nonInclus;
    }

    private int calculerUtiliteTotale(List<Objet> solution) {
        int total = 0;
        for (Objet o : solution)
             {total+= o.getUtilite();}
        return total;}

    private int[] sommeCouts(List<Objet> solution) {
        int nbDimensions = probleme.getBudgets().length;
        int[] total = new int[nbDimensions];
        for (Objet o : solution) {
            for (int i = 0; i<nbDimensions; i++) {
                total[i]+= o.getCouts()[i];}}
        return total;
    }
}