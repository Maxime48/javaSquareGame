package com.example.tp_lumiere.controleur;

import com.example.tp_lumiere.modele.Grille;
import com.example.tp_lumiere.vue.VueGraphique;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class ControleurBoutton_Play implements EventHandler<MouseEvent> {
    private Grille modele;
    private VueGraphique vue;

    public ControleurBoutton_Play(Grille modele, VueGraphique vue) {
        this.modele = modele;
        this.vue = vue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        for(Node child:
                ((HBox) ( (Button) mouseEvent.getSource() ).getParent() ).getChildren()
        ){
            child.setDisable(true); //Tout éteindre
            if(child instanceof Button btn){
                if(Objects.equals(btn.getText(), "Quit")){
                    btn.setDisable(false); //Sauf le bouton Quit
                }
            }
        }
        this.vue.configOn = false;
        this.vue.jeuLance = true;
        vue.update();
    }
}
