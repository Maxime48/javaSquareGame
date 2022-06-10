package com.example.tp_lumiere.controleur;

import com.example.tp_lumiere.modele.Grille;
import com.example.tp_lumiere.vue.VueGraphique;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class ControleurBoutton_Aleatoire implements EventHandler<MouseEvent> {
    private Grille modele;
    private VueGraphique vue;

    public ControleurBoutton_Aleatoire(Grille modele, VueGraphique vue) {
        this.modele = modele;
        this.vue = vue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int nbCasesX = this.vue.vueX / VueGraphique.TAILLE_CASE;
        int nbCasesY = this.vue.vueY / VueGraphique.TAILLE_CASE;
        int cnb = 8; //Nombre de cases aléatoires configurables
        Random rdm = new Random();

        for (int y = 0; y < nbCasesY; y++) { //Remets la grille a 0 pour faire un nouvel appel de l'aléatoire
            for (int x = 0; x < nbCasesX; x++) {
                if (modele.getGrid(x, y)) {
                    modele.setGrid(x, y);
                }
            }
        }

        while (cnb > 0) { // Tant que tout les cases ne sont pas placés
            for (int y = 0; y < nbCasesY; y++) { //Parcours des cases
                for (int x = 0; x < nbCasesX; x++) {
                    if (rdm.nextInt(2) == 1) { //Si l'aléatoire est égal à 1 placer la case sinon continuer le placement
                        cnb--; //Mettre à jour le conteur
                        if (cnb < 0) break; //Sortir en avance
                        modele.setGrid(x, y); //Mise à jour du modèle
                    }
                }
            }
        }

        vue.update();
    }
}
