package src.main;

import javax.swing.SwingUtilities;

import src.screen.MainScreen;

public class Main {
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {

            MainScreen.getInstance();

        });

    }

}
