package com.example.tp_lumiere.controleur;

import com.example.tp_lumiere.modele.Grille;
import com.example.tp_lumiere.vue.VueGraphique;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class ControleurBoutton_Configure implements EventHandler<MouseEvent> {
    private Grille modele;
    private VueGraphique vue;

    public ControleurBoutton_Configure(Grille modele, VueGraphique vue) {
        this.modele = modele;
        this.vue = vue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.vue.configOn = !this.vue.configOn; //Changement du statut de configuration
        vue.update();
    }
}
