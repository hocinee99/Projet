package Test;

import equipe.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

/**
 * Test class for {@link EquipeMunicipale}. This class verifies the correct
 * behavior of the municipal team during a simulation cycle, including:
 * <ul>
 * <li>Project creation</li>
 * <li>Validity of costs and benefits</li>
 * </ul>
 *
 * @author Hocine
 * @version 1.0
 */
class EquipeMunicipaleTest {

    private Elu elu;
    private Expert e1;
    private Expert e2;
    private ArrayList<Expert> experts;
    private Evaluateur ev1;
    private Evaluateur ev2;
    private Evaluateur ev3;
    private EquipeMunicipale eq;

    /**
     * Sets up the test environment before each test
     */
    @BeforeEach
    void setUp() {
        elu = new Elu("Computing", "Claude", 19);
        e1 = new Expert("Toto", "Titi", 22);
        e2 = new Expert("Tutu", "Toto", 33);
        e1.addSecteurCompetences(Secteur.sport);
        e2.addSecteurCompetences(Secteur.attractivite_economique);
        e2.addSecteurCompetences(Secteur.education);
        experts = new ArrayList<>();
        experts.add(e1);
        experts.add(e2);
        ev1 = new Evaluateur("Green", "Robin", 22, TypeCout.economique);
        ev2 = new Evaluateur("Beauchamps", "Jay", 24, TypeCout.social);
        ev3 = new Evaluateur("Paré", "Gustave", 25, TypeCout.environnemental);
        eq = new EquipeMunicipale(elu, experts, ev1, ev3, ev2);
    }

    /**
     * Cleans up the test environment after each test
     */
    @AfterEach
    void tearDown() {
        elu = null;
        e1 = null;
        e2 = null;
        experts = null;
        ev1 = null;
        ev2 = null;
        ev3 = null;
        eq = null;
    }

    /**
     * Tests that a simulation cycle creates projects correctly.
     */
    @Test
    void testSimulationCycle_creeProjects() {
        // Act
        eq.simulerCycle();
        ArrayList<Projet> projets = eq.getListeProjets();
        // Assert
        Assertions.assertFalse(projets.isEmpty(), "La liste de projets devrait contenir au moins un projet après simulation.");
    }

    /**
     * Tests that the costs and benefits of projects are valid after a
     * simulation cycle.
     */
    @Test
    void testSimulationCycle_Couts_et_BeneficeValides() {
        // Act
        eq.simulerCycle();
        ArrayList<Projet> projets = eq.getListeProjets();
        //Assert
        for (Projet p : projets) {
            Assertions.assertTrue(p.getBenefice() > 0);
            Assertions.assertTrue(p.getCout_economique() > 0);
            Assertions.assertTrue(p.getCout_environnemental() > 0);
            Assertions.assertTrue(p.getCout_social() > 0);
        }

    }
}
