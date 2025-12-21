package Test;

import equipe.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import sacADos.Objet;
import sacADos.SacADos;
import solveur.glouton.ComparatorMax;
import solveur.glouton.ComparatorSum;
import solveur.glouton.GloutonAjouterSolveur;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for {@link GloutonAjouterSolveur}. This class verifies the correct
 * behavior of the greedy addition solver in various scenarios, including:
 * <ul>
 * <li>Selecting the best items to add</li>
 * <li>Handling empty knapsacks</li>
 * <li>Ensuring correct order of item selection based on different
 * comparators</li>
 * </ul>
 *
 * @author Hocine
 * @version 1.0
 */
class TestGloutonAjouterSolveur {

    private GloutonAjouterSolveur solveur;
    private Objet o1;
    private Objet o2;
    private Objet o3;
    private Objet o4;
    private Objet o5;
    private Objet o6;
    private SacADos sac;

    /**
     */
    @BeforeEach
    void setUp() {
        solveur = new GloutonAjouterSolveur();
        o1 = new Objet(8, new int[]{3, 2});
        o2 = new Objet(1, new int[]{8, 5});
        o3 = new Objet(10, new int[]{2, 1});
        o4 = new Objet(5, new int[]{3, 2});
        o5 = new Objet(10, new int[]{8, 5});
        o6 = new Objet(3, new int[]{2, 1});
        sac = new SacADos(new int[]{6, 4});
    }

    /**
     * Cleans up the test environment after each test
     */
    @AfterEach
    void tearDown() {
        solveur = null;
        o1 = null;
        o2 = null;
        o3 = null;
        o4 = null;
        o5 = null;
        o6 = null;
        sac = null;
    }

    /**
     * Tests that the solver selects the two best items to add to the knapsack
     */
    @Test
    void testResoudreSelectionne2BonsObjets() {
        //Arrange
        sac.addObjet(o1);
        sac.addObjet(o2);
        sac.addObjet(o3);
        //Act
        List<Objet> solution = solveur.resoudre(sac, new ComparatorSum());
        //Assert
        Assertions.assertEquals(2, solution.size());
        Assertions.assertTrue(solution.contains(o1));
        Assertions.assertTrue(solution.contains(o3));
        Assertions.assertFalse(solution.contains(o2));
    }

    // Tests that the solver handles an empty knapsack correctly
    @Test
    void testResoudreSacVide() {
        // Pas besoin de arrange le sac est déja vide
        //Act
        List<Objet> solution = solveur.resoudre(sac, new ComparatorSum());
        //Assert
        Assertions.assertTrue(solution.isEmpty());
    }

    /**
     * Tests that the solver selects items in the correct order using
     * ComparatorMax
     */
    @Test
    void resoudre_ComparatorMaxSelectionneOrdreCorrect() {
        // Arrange
        sac.addObjet(o4);
        sac.addObjet(o5);
        sac.addObjet(o6);
        // Act
        List<Objet> solution = solveur.resoudre(sac, new ComparatorMax());
        // Assert
        Assertions.assertEquals(2, solution.size());
        Assertions.assertEquals(o4, solution.get(0));
    }
}
