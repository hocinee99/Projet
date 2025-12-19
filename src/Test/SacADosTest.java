package Test;

import equipe.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import sacADos.Objet;
import sacADos.SacADos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SacADosTest {
    private SacADos sac;
    private Objet o1;
    private Objet o2;
    private Objet o3;

    @BeforeEach
    void setUp() {
        sac = new SacADos(new int[]{1, 3, 5});
        o1 = new Objet(8, new int[]{3, 2, 8});
        o2 = new Objet(1, new int[]{8, 5, 4});
        o3 = new Objet(10, new int[]{2, 1, 6});
    }

    @AfterEach
    void tearDown() {
        o1 = null;
        o2 = null;
        o3 = null;
        sac = null;
    }

    @Test
    void testAddObjetAjouteObjetsAuSac() {
        sac.addObjet(o1);
        sac.addObjet(o2);
        sac.addObjet(o3);
        Assertions.assertEquals(3, sac.getObjets().size());
    }

    @Test
    void testSacADosConstructeurBudgetsNegatifsRenvoieException() {
        int[] budgetsNegatifs = {-1, 4};
        try {
            new SacADos(budgetsNegatifs);
            Assertions.fail("On devrait avoir une exception pour les budgets négatifs");
        } catch (IllegalArgumentException e) {
            // Exception levée donc on a detecté les budgets négatifs
        }
    }
    @Test
    void testSacADosRespecteContraintesListeValide() {
        Objet o1 = new Objet(5, new int[]{1, 2, 3});
        Objet o2 = new Objet(3, new int[]{0, 1, 2});
        List<Objet> liste = new ArrayList<>();
        liste.add(o1);liste.add(o2);
        Assertions.assertTrue(sac.respecteContraintes(liste));
    }

}
