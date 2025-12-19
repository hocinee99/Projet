package Test;

import equipe.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import sacADos.Objet;
import sacADos.SacADos;
import solveur.glouton.ComparatorMV;
import solveur.glouton.ComparatorMax;
import solveur.glouton.ComparatorSum;
import solveur.glouton.GloutonRetraitSolveur;
import java.util.*;

class GloutonRetraitSolveurTest {
    private GloutonRetraitSolveur solveur;
    private Objet o1;
    private Objet o2;
    private Objet o3;
    private SacADos sac;

    @BeforeEach
    void setUp() {
        solveur = new GloutonRetraitSolveur();
        o1 = new Objet(8, new int[]{3, 2});
        o2 = new Objet(1, new int[]{8, 5});
        o3 = new Objet(10, new int[]{2, 1});
        sac = new SacADos(new int[]{6, 4});
    }

    @AfterEach
    void tearDown() {
        solveur = null;
        o1 = null;
        o2 = null;
        o3 = null;
        sac = null;
    }

    @Test
    void resoudreRetireObjetTropCher(){
        sac.addObjet(o1);
        sac.addObjet(o2);
        sac.addObjet(o3);
        List<Objet> solution = solveur.resoudre(sac,new ComparatorMV(sac),new ComparatorSum());
        Assertions.assertEquals(2,solution.size());
    }
    @Test
    void resoudreRetireTousLesObjetsSiAucuneSolution() {
        sac.addObjet(o2); // trop gros pour les budgets {6,4}
        List<Objet> solution = solveur.resoudre(sac, new ComparatorMV(sac), new ComparatorSum());
        Assertions.assertTrue(solution.isEmpty());
    }
    @Test
    void resoudreRespecteContraintesApresRetrait() {
        sac.addObjet(o1);
        sac.addObjet(o2);
        sac.addObjet(o3);
        List<Objet> solution = solveur.resoudre(sac, new ComparatorMV(sac), new ComparatorSum());
        Assertions.assertTrue(sac.respecteContraintes(solution));
    }

}
