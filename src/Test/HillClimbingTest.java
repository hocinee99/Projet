package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sacADos.*;
import solveurHillclimbing.HillClimbing;

/**
 * Test class for {@link HillClimbing}. This class verifies the correct behavior
 * of the Hill Climbing algorithm in various scenarios, including:
 * <ul>
 * <li>Local plateaus</li>
 * <li>Multi-dimensional knapsack problems</li>
 * <li>Local maxima</li>
 * <li>Already optimal solutions</li>
 * <li>Saturated constraints</li>
 * </ul>
 *
 * @author Hocine
 * @version 1.0
 *
 */
public class HillClimbingTest {

    private HillClimbing hillClimbing;
    private SacADos sac1D, sac2D;
    private Objet o1, o2, o3, o4, o5, o6;

    /**
     * Sets up the test environment before each test
     */
    @BeforeEach
    public void init() {
        o1 = new Objet(5, new int[]{3});
        o2 = new Objet(5, new int[]{3});
        o3 = new Objet(5, new int[]{3});
        o4 = new Objet(1, new int[]{1});
        sac1D = new SacADos(new int[]{6});
        sac1D.addObjet(o1);
        sac1D.addObjet(o2);
        sac1D.addObjet(o3);
        sac1D.addObjet(o4);
        o5 = new Objet(4, new int[]{2, 1});
        o6 = new Objet(4, new int[]{1, 2});
        sac2D = new SacADos(new int[]{3, 3});
        sac2D.addObjet(o5);
        sac2D.addObjet(o6);

    }

    /**
     * Cleans up the test environment after each test
     */
    @AfterEach
    public void tearDown() {
        o1 = null;
        o2 = null;
        o3 = null;
        o4 = null;
        o5 = null;
        o6 = null;
        sac1D = null;
        sac2D = null;
        hillClimbing = null;
    }

    // Test plateau local (aucun voisin meilleur)
    @Test
    public void resoudre_PlateauLocal() {
        hillClimbing = new HillClimbing(sac1D);
        List<Objet> plateau = Arrays.asList(o1, o2);
        List<Objet> sol = hillClimbing.resoudre(plateau);
        assertEquals(10, sac1D.utiliteTotale(sol), "L'algorithme doit s'arrêter sur plateau local");
    }

    // Test multi-dimensionnel
    @Test
    public void resoudre_MultiDimension() {
        hillClimbing = new HillClimbing(sac2D);
        List<Objet> initiale = Arrays.asList(o5);
        List<Objet> sol = hillClimbing.resoudre(initiale);
        int utilite = sac2D.utiliteTotale(sol);
        assertTrue(utilite >= 4, "Solution multi-dimensionnelle doit respecter les contraintes");
    }

    // Test local maximum (pas l'optimum global)
    @Test
    public void resoudre_LocalMaximum() {
        hillClimbing = new HillClimbing(sac1D);
        List<Objet> initiale = Arrays.asList(o1, o2);
        List<Objet> sol = hillClimbing.resoudre(initiale);
        assertEquals(10, sac1D.utiliteTotale(sol), "L'algorithme doit s'arrêter au local maximum");
    }

    // Test solution déjà optimale
    @Test
    public void resoudre_SolutionDejaOptimale() {
        hillClimbing = new HillClimbing(sac1D);
        List<Objet> optimale = Arrays.asList(o1, o2, o3);
        List<Objet> sol = hillClimbing.resoudre(optimale);
        assertEquals(15, sac1D.utiliteTotale(sol), "Solution optimale ne doit pas être modifiée");
    }

    // Test contrainte saturée
    @Test
    public void resoudre_ContrainteSaturee() {
        hillClimbing = new HillClimbing(sac1D);
        List<Objet> saturée = Arrays.asList(o1, o2, o3);
        List<Objet> sol = hillClimbing.resoudre(saturée);
        assertEquals(15, sac1D.utiliteTotale(sol), "Contrainte saturée : pas d'amélioration");
    }

}
