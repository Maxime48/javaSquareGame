package com.example.tp_lumiere.vue;

import com.example.tp_lumiere.controleur.*;
import com.example.tp_lumiere.modele.Grille;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueGraphique extends Pane {

    //Variables graphiques
    private Grille modele;
    public int vueX;
    public int vueY;
    public static final int TAILLE_CASE = 100;
    public Rectangle[][] grid;

    //Variables de jeu
    public boolean configOn;
    public boolean jeuLance;
    public TextField clicks;
    public int clicksActuels;


    public VueGraphique(Grille grille, int dimX, int dimY){
        this.vueX = dimX;
        this.vueY = dimY;
        this.modele = grille;
        this.configOn = false;
        this.jeuLance = false;
        this.clicksActuels = 0;
        this.initGrille();
    }

    public void initGrille(){
         int nbCasesX = this.vueX / VueGraphique.TAILLE_CASE;
         int nbCasesY = this.vueY / VueGraphique.TAILLE_CASE;
         this.grid = new Rectangle[nbCasesY][nbCasesX];

         Color interieur, exterieur;

         interieur = Color.LIMEGREEN;
         exterieur = Color.BLACK;

         //Création des cases
         for(int y = 0; y < nbCasesY; y++){
             for(int x = 0; x < nbCasesX; x++){
                 Rectangle carre = new Rectangle(VueGraphique.TAILLE_CASE, VueGraphique.TAILLE_CASE);
                 carre.setFill(interieur);
                 carre.setStroke(exterieur);

                 carre.setX(x * VueGraphique.TAILLE_CASE);
                 carre.setY(y * VueGraphique.TAILLE_CASE);

                 carre.setOnMousePressed(new ControleurSouris(modele,this));
                 grid[y][x] = carre;
                 this.getChildren().add(carre);
             }
         }

         //Création de la barre des bouttons
         HBox barre = new HBox();
            barre.setLayoutY(nbCasesY * TAILLE_CASE + 5);
            barre.setLayoutX((nbCasesX * TAILLE_CASE)/2);

            //Ajouts des éléments
            Button play = new Button("Play");
                play.setOnMousePressed(new ControleurBoutton_Play(modele,this));

            this.clicks = new TextField();

            Button configure = new Button("Configure");
                configure.setOnMousePressed(new ControleurBoutton_Configure(modele, this));

            Button random = new Button("Random");
                random.setOnMousePressed(new ControleurBoutton_Aleatoire(modele, this));

            Button quit = new Button("Quit");
                quit.setOnMousePressed(new ControleurBoutton_Quit(modele, this));

            barre.getChildren().addAll(play,clicks,configure,random,quit);


        //Ajout de la barre
        this.getChildren().add(barre);

        //Comme un pane a été utilisé au début (non-optimal) pour centrer dynamiquement j'utilise cette méthode
        //On utilise "runLater" car getWidth() revoie 0 tant que la scène n'est pas générée et les boutons affichés
        Platform.runLater(() -> {
            double x = 0; //Somme de la moitié de la largeur des éléments de la barre
            for (Node child : barre.getChildren()) {
                x -= child.getBoundsInParent().getWidth()/2;
            }
            //Centrage de la barre
            barre.setTranslateX(x);
        });
    }

    //Mise à jour de la vue selon le modèle
    public void update(){
        int nbCasesX = this.vueX / VueGraphique.TAILLE_CASE;
        int nbCasesY = this.vueY / VueGraphique.TAILLE_CASE;
        for(int y = 0; y < nbCasesY; y++) {
            for (int x = 0; x < nbCasesX; x++) {
                this.grid[y][x].setFill(
                        (
                                modele.getGrid(x,y)
                        ) ? Color.GREEN : Color.LIMEGREEN
                );
            }
        }
    }

}
