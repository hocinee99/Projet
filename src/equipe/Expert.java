package equipe;

import java.util.ArrayList;

public class Expert extends Personne{
    private final ArrayList<Secteur> liste;
    public Expert(String nom, String prenom, int age){
        super(nom,prenom,age);
        liste = new ArrayList<>();
    }
    public void addSecteurCompetences(Secteur s){
        this.liste.add(s);
    }
    public Projet proposeProjet(){
        int a = liste.size();
        int indexsecteur=(int)(Math.random()*a);
        Secteur s = liste.get(indexsecteur); // On prend un secteur aléatoirement ou il y'a des compétences
        String titre = "Projet"+ s;
        String desc = "Description du projet";
        return new Projet(titre,desc,s);
    }

    public ArrayList<Secteur> getListe() {
        return liste;
    }
}
