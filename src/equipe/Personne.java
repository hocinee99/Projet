package equipe;

public abstract class Personne {

    private final String nom;
    private final String prenom;
    private int age;

    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.setAge(age);
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if(age<0){
            throw new IllegalArgumentException("L'age ne peut pas etre négatif");
        }
        else{
            this.age = age;
            }
        }
}