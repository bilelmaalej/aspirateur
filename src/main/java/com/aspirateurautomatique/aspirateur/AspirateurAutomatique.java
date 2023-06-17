package com.aspirateurautomatique.aspirateur;

public class AspirateurAutomatique {

    // Dimension de la grille en X
    private int dimensionX;

    // Dimension de la grille en Y
    private int dimensionY;

    // Position Initiale en X
    private int positionX;

    // Position Initiale en Y
    private int positionY;

    private char orientation;

    /**
     * Constructeur
     *
     * @param dimensionX
     * @param dimensionY
     * @param positionX
     * @param positionY
     * @param orientation
     */
        public AspirateurAutomatique(int dimensionX, int dimensionY, int positionX, int positionY, char orientation) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
            if (!positionValide(positionX, positionY)) {
                throw new IllegalArgumentException("Position initiale en dehors de la grille.");
            }
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

    /**
     * Switch qui permet d'exécuter les insctructions donnée en entrée en chaine de caracteres
     * @param instructions
     */
    public void executerInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'D':
                    tournerDroite();
                    break;
                case 'G':
                    tournerGauche();
                    break;
                case 'A':
                    avancer();
                    break;
                default:
                    throw new IllegalArgumentException("Instruction invalide : " + instruction);
            }
        }
    }

    /**
     * Orientation de l'aspirateur lorsqu'il tourne à droite (90 degres)
     *
     */
    void tournerDroite() {
        switch (orientation) {
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'N';
                break;
        }
    }

    /**
     * Orientation de l'aspirateur lorsqu'il tourne à gauche (90 degtes)
     *
     */
    void tournerGauche() {
        switch (orientation) {
            case 'N':
                orientation = 'W';
                break;
            case 'E':
                orientation = 'N';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'W':
                orientation = 'S';
                break;
        }
    }

    /**
     * Méthode qui affectue l'avancement
     * Lève une exception si l'aspirateur tente de sortir de la grille
     *
     */
    void avancer() {
        int positionSuivanteX = positionX;
        int ositionSuivanteY = positionY;
        switch (orientation) {
            case 'N':
                ositionSuivanteY++;
                break;
            case 'E':
                positionSuivanteX++;
                break;
            case 'S':
                ositionSuivanteY--;
                break;
            case 'W':
                positionSuivanteX--;
                break;
        }

        if (positionValide(positionSuivanteX, ositionSuivanteY)) {
            positionX = positionSuivanteX;
            positionY = ositionSuivanteY;
        } else {
            throw new IllegalStateException("L'aspirateur tente de sortir de la grille.");
        }
    }

    /**
     * Determine si la position est valide ou non
     *
     * @param x
     * @param y
     * @return
     */
    private boolean positionValide(int x, int y) {
        return x >= 0 && x <= dimensionX && y >= 0 && y <= dimensionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public char getOrientation() {
        return orientation;
    }


    public static void main(String[] args) {
        AspirateurAutomatique aspirateur = new AspirateurAutomatique(10, 10, 5, 5, 'N');
        aspirateur.executerInstructions("DADADADAA");
        System.out.println("La position finale : x = " + aspirateur.getPositionX() + " et y = " + aspirateur.getPositionY() + " l'orientation = " + aspirateur.getOrientation());
    }
}
