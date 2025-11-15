package equipe;

import java.util.ArrayList;

public class EquipeMunicipale {
    private final Elu elu;
    private final ArrayList<Expert> liste_experts;
    private final Evaluateur evaluateur_economique;
    private final Evaluateur evaluateur_social;
    private final Evaluateur evaluateur_environnemental;
    private final ArrayList<Projet> listeProjets;

    public EquipeMunicipale(Elu elu,ArrayList<Expert> liste_experts,Evaluateur evaluateur_economique, Evaluateur evaluateur_environnemental, Evaluateur evaluateur_social){
        this.elu=elu;
        this.evaluateur_economique=evaluateur_economique;
        this.evaluateur_environnemental=evaluateur_environnemental;
        this.evaluateur_social=evaluateur_social;
        this.liste_experts=liste_experts;
        this.listeProjets=new ArrayList<>();
    }

    public void simulerCycle(){
        for(Expert expert : liste_experts){
            Projet p = expert.proposeProjet();
            evaluateur_social.evaluerCout(p);
            evaluateur_economique.evaluerCout(p);
            evaluateur_environnemental.evaluerCout(p);
            elu.evaluerBenefice(p);
            listeProjets.add(p);
        }
    }





    // Getters

    public Elu getElu() {
        return elu;
    }

    public ArrayList<Expert> getListe_experts() {
        return liste_experts;
    }

    public Evaluateur getEvaluateur_economique() {
        return evaluateur_economique;
    }

    public Evaluateur getEvaluateur_social() {
        return evaluateur_social;
    }

    public Evaluateur getEvaluateur_environnemental() {
        return evaluateur_environnemental;
    }

    public ArrayList<Projet> getListeProjets() {
        return listeProjets;
    }
}
