package com.example.tp_lumiere.modele;

public class Grille {
    private boolean[][] grid;

    public Grille(int x, int y) {
        this.grid = new boolean[x][y];
    }

    //Récupère l'élément de la grille
    public boolean getGrid(int x, int y) {
        return grid[x][y];
    }

    //Vérifie la conformitée des paramères et modifie la grille
    public void setGrid(int x, int y) {
        if(
                x<this.grid.length && x >= 0 &&
                y<this.grid[x].length && y >= 0
        )
            this.grid[x][y] = !this.grid[x][y];
    }

    //Vérifie si le jeu est gagné
    public boolean checkWinStatus(){
        boolean status = true;
        for(int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid[0].length; j++){
                status = !getGrid(i, j) && status;
            }
        }
        return status;
    }
}
