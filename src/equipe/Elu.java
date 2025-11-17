package equipe;

public class Elu extends Personne {

    public Elu(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public double evaluerBenefice(Projet p) {
        double benef = Math.random() * 10000;
        p.setBenefice(benef);
        return benef;
    }
}
