package com.example.tp_lumiere.controleur;

import com.example.tp_lumiere.modele.Grille;
import com.example.tp_lumiere.vue.VueGraphique;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControleurBoutton_Quit implements EventHandler<MouseEvent> {
    private Grille modele;
    private VueGraphique vue;

    public ControleurBoutton_Quit(Grille modele, VueGraphique vue) {
        this.modele = modele;
        this.vue = vue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Platform.exit(); //Sortie du programme
        vue.update();
    }
}
