package equipe;

public class Evaluateur extends Personne {
    private final TypeCout specialisation;
    public Evaluateur(String nom, String prenom, int age,TypeCout specialisation) {
        super(nom, prenom, age);
        this.specialisation=specialisation;
    }
    public double evaluerCout(Projet p){
        double cout = Math.random()*100;
        switch(specialisation){
            case economique -> p.setCout_economique(cout);
            case social -> p.setCout_social(cout);
            case environnemental -> p.setCout_environnemental(cout);
        }
        return cout;
    }

    public TypeCout getSpecialisation() {
        return specialisation;
    }
}
