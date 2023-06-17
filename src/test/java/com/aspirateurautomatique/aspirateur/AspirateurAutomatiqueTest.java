package com.aspirateurautomatique.aspirateur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AspirateurAutomatiqueTest {

	@Test
	public void test_passant() {
		AspirateurAutomatique aspirateur = new AspirateurAutomatique(10, 10, 5, 5, 'N');
		aspirateur.executerInstructions("DADADADAA");
		assertEquals(5, aspirateur.getPositionX());
		assertEquals(6, aspirateur.getPositionY());
		assertEquals('N', aspirateur.getOrientation());
	}

	@Test
	public void test_tourner_droite() {
		AspirateurAutomatique aspirateur = new AspirateurAutomatique(10, 10, 5, 5, 'N');
		aspirateur.tournerDroite();
		assertEquals('E', aspirateur.getOrientation());
	}

	@Test
	public void test_tourner_gauche() {
		AspirateurAutomatique aspirateur = new AspirateurAutomatique(10, 10, 5, 5, 'N');
		aspirateur.tournerGauche();
		assertEquals('W', aspirateur.getOrientation());
	}

	@Test
	public void test_avancer() {
		AspirateurAutomatique aspirateur = new AspirateurAutomatique(10, 10, 5, 5, 'N');
		aspirateur.avancer();
		assertEquals(5, aspirateur.getPositionX());
		assertEquals(6, aspirateur.getPositionY());
	}

	@Test
	public void test_nombre_appel_des_methodes() {
		AspirateurAutomatique aspirateur = Mockito.spy(new AspirateurAutomatique(10, 10, 5, 5, 'N'));
		Mockito.doNothing().when(aspirateur).tournerDroite();
		Mockito.doNothing().when(aspirateur).tournerGauche();
		Mockito.doNothing().when(aspirateur).avancer();
		aspirateur.executerInstructions("DADADADAA");
		assertEquals('N', aspirateur.getOrientation());
		Mockito.verify(aspirateur, Mockito.times(4)).tournerDroite();
		Mockito.verify(aspirateur, Mockito.times(5)).avancer();
	}

	@Test
	public void test_aspirateur_tente_de_sortir() {
		AspirateurAutomatique aspirateur = new AspirateurAutomatique(5, 5, 3, 3, 'N');

		// Exécution des instructions qui feront sortir l'aspirateur de la grille
		Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
			aspirateur.executerInstructions("AAA");
		});

		String expectedMessage = "L'aspirateur tente de sortir de la grille.";
		String actualMessage = exception.getMessage();

		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void test_instruction_invalide() {
		AspirateurAutomatique aspirateur = new AspirateurAutomatique(5, 5, 3, 3, 'N');

		// Exécution des instructions avec une instruction invalide "Z"
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aspirateur.executerInstructions("DZG");
		});

		String expectedMessage = "Instruction invalide : Z";
		String actualMessage = exception.getMessage();

		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void test_mauvaise_position_intiale() {
		// Tentative de création d'un aspirateur avec une position initiale en dehors de la grille
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			AspirateurAutomatique aspirateur = new AspirateurAutomatique(5, 5, 10, 10, 'N');
		});

		String expectedMessage = "Position initiale en dehors de la grille.";
		String actualMessage = exception.getMessage();

		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}
}
