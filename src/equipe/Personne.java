package equipe;

public abstract class Personne {
    private final String nom;
    private final String prenom;
    private int age;
    public  Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
    public String getNom(){
        return nom;
    }

    public int getAge() {
        return age;
    }

    public String getPrenom(){
        return prenom;
    }
}
