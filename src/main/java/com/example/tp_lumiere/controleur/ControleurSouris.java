package com.example.tp_lumiere.controleur;

import com.example.tp_lumiere.GrilleMVC;
import com.example.tp_lumiere.modele.Grille;
import com.example.tp_lumiere.vue.VueGraphique;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.Objects;

public class ControleurSouris implements EventHandler<MouseEvent> {
    private Grille modele;
    private VueGraphique vue;

    public ControleurSouris(Grille modele, VueGraphique vue) {
        this.modele = modele;
        this.vue = vue;
    }

    public void resetGame(MouseEvent mouseEvent){
        //Remise a 0 des paramètres
        this.vue.clicksActuels = 0;
        this.vue.configOn = false;
        this.vue.jeuLance = false;

        //Réactivation des boutons
        for(Node child:
                ((Pane) ( (Rectangle) mouseEvent.getSource() ).getParent() ).getChildren()
        ){
            if(child instanceof HBox barre){ //La seule HBox est celle des boutons
                for(Node elm : barre.getChildren()){
                    elm.setDisable(false);
                }
            }
        }

        //Extinction des cases
        int nbCasesX = this.vue.vueX / VueGraphique.TAILLE_CASE;
        int nbCasesY = this.vue.vueY / VueGraphique.TAILLE_CASE;
        for (int y2 = 0; y2 < nbCasesY; y2++) {
            for (int x2 = 0; x2 < nbCasesX; x2++) {
                if (modele.getGrid(x2, y2)) {
                    modele.setGrid(x2, y2);
                }
            }
        }
    }

    public void showPopup(String string){
        //Création de la popup
        Popup popup = new Popup();
        Label lostTxt = new Label(string);
        popup.getContent().add(lostTxt);

        //Afficher la popup
        popup.show(GrilleMVC.stg);
        //Créer une pause
        PauseTransition pause = new PauseTransition(new Duration(2000));
        pause.setOnFinished(actionEvent -> {
            popup.hide();
        });
        //Lancer la "pause"
        pause.play();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        //Recherche des coordonées du Rectangle pour l'utilisation du modele
        for(int y = 0; y < vue.grid.length; y++) {
            for (int x = 0; x < vue.grid[y].length; x++) {
                if((Rectangle) mouseEvent.getSource() == vue.grid[y][x]) {
                    if(this.vue.configOn){
                        //Mode de configuration
                        modele.setGrid(x,y);
                    }else{
                        //Si le jeu est lancé
                        if(this.vue.jeuLance){
                            //Gestion des clicks
                            this.vue.clicksActuels +=1;
                            String clicksTxt = this.vue.clicks.getText();
                            //Si le contenu du textfield est bien un nombre sans 0 au début
                            if(clicksTxt.matches("-?(0|[1-9]\\d*)")) {
                                //Limite de clics atteinte ?
                                if(this.vue.clicksActuels>Integer.parseInt(clicksTxt)){
                                    //Perdu
                                    //Affichage de la popup
                                    showPopup("You Lost ! Restarting the game");

                                    //Remise à 0 du jeu
                                    resetGame(mouseEvent);

                                    break; //sortie pour éviter le clic
                                }
                            }


                            modele.setGrid(x, y);

                            modele.setGrid(x + 1, y);
                            modele.setGrid(x - 1, y);
                            modele.setGrid(x, y + 1);
                            modele.setGrid(x, y - 1);

                            //Est-ce que le joueur a gagné
                            if(this.modele.checkWinStatus()){
                                //Gagné
                                //Affichage de la popup
                                showPopup("You won ! Restarting the game");

                                //Remise à 0 du jeu
                                resetGame(mouseEvent);
                            }

                        }
                    }
                }
            }
        }
        vue.update();
    }
}
