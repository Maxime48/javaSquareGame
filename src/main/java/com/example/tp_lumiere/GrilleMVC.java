package com.example.tp_lumiere;


import com.example.tp_lumiere.controleur.ControleurSouris;
import com.example.tp_lumiere.vue.VueGraphique;
import com.example.tp_lumiere.modele.Grille;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GrilleMVC extends Application {
    private static final int LARGEUR = 500;
    private static final int HAUTEUR = 535;

    public static Stage stg;

    @Override
    public void start(Stage stage) throws Exception {
        Grille modele = new Grille( LARGEUR / VueGraphique.TAILLE_CASE, HAUTEUR / VueGraphique.TAILLE_CASE);
        VueGraphique vue = new VueGraphique(modele, LARGEUR, HAUTEUR);
        GrilleMVC.stg = stage; //Extraction de stage pour les popups

        Scene scene = new Scene(vue, LARGEUR, HAUTEUR);
        stage.setTitle("Grille en MVC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
