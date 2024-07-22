package src.screen;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Screen extends JFrame{

    public Screen(int type){

        //=> Uso o SwingUtilities para que minha GUI na seja criada na thread de eventos (EDT)
        SwingUtilities.invokeLater(() -> {

            if (type == 1) {
                
                this.setBounds(0, 0, 1250, 600); //=> Defino o tamanho como 1250 x 600 pixels
               
            } else {

                this.setBounds(0, 0, 850, 450); //=> Defino o tamanho como 1250 x 600 pixels

            }

            //=> Definindo as propiedades padrao para as minhas telas
            this.setUndecorated(true); //=> Retiro a barra de titulo
            this.setLocationRelativeTo(null); //=> Centralizo o JFrame no centro da tela
            this.setResizable(false); //=> Retiro o redimensionamento da tela
            this.setVisible(true); //=> Defino a tela como visivel

        });
    }

}