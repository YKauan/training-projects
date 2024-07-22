package src.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.database.classesdao.ApsDAO;
import src.styles.Styles;

public class RegistrationScreen extends JDialog{
    
    private Screen screen;

    private JPanel panel;

    private JButton bExit;
    private JButton bSave;

    private JRadioButton rbInsert;
    private JRadioButton rbAlter;
    private JRadioButton rbDelete;

    private ButtonGroup buttonsGroup;

    private JLabel lImage;
    private JLabel lCurrentName;
    private JLabel lCurrentAge;
    private JLabel lCurrentCpf;
    private JLabel lCurrentCountry;
    private JLabel lNewName;
    private JLabel lNewAge;
    private JLabel lNewCpf;
    private JLabel lNewCountry;
    private JLabel lTitle;

    private JTextField tCurrentName;
    private JTextField tCurrentAge;
    private JTextField tCurrentCpf;
    private JTextField tCurrentCountry;

    private JTextField tNewName;
    private JTextField tNewAge;
    private JTextField tNewCpf;
    private JTextField tNewCountry;

    private String currentName;
    private String currentAge;
    private String currentCpf;
    private String currentCountry;
    private ImageIcon currentImage;
    private ImageIcon bkpImage;

    private Styles styles;

    private SpringLayout layout;

    private static ApsDAO apsDAO = ApsDAO.getInstance();

    public static Boolean isOpen = false;

    public RegistrationScreen(String currentName, String currentAge, String currentCpf, String currentCountry, ImageIcon currentImage) {
        this.currentName    = currentName;
        this.currentAge     = currentAge;
        this.currentCpf     = currentCpf;
        this.currentCountry = currentCountry;
        this.currentImage   = currentImage;
        this.bkpImage       = currentImage;

        isOpen = true;

        screen = new Screen(0);

        styles = new Styles();

        layout = new SpringLayout();
       
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(layout);

        lImage = new JLabel();
        styles.styleImageLabel(lImage, currentImage);
        lImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(rbInsert.isSelected() || rbAlter.isSelected()){
                    loadImage();
                }
            }
        });
 
        bExit = new JButton();
        bExit.addActionListener(e -> exitButtonAction());

        bSave = new JButton("Salvar");
        bSave.addActionListener(e -> saveButtonAction());

        rbAlter = new JRadioButton("Alterar");
        rbInsert = new JRadioButton("Inserir");
        rbDelete = new JRadioButton("Excluir");

        rbAlter.addActionListener(e -> radioButtonAction());
        rbInsert.addActionListener(e -> radioButtonAction());
        rbDelete.addActionListener(e -> radioButtonAction());

        styles.styleRadButton(rbAlter);
        styles.styleRadButton(rbInsert);
        styles.styleRadButton(rbDelete);

        rbInsert.setSelected(true);

        buttonsGroup = new ButtonGroup();
        buttonsGroup.add(rbAlter);
        buttonsGroup.add(rbInsert);
        buttonsGroup.add(rbDelete);

        tCurrentName    = new JTextField(15);
        tCurrentAge     = new JTextField(15);
        tCurrentCpf     = new JTextField(15);
        tCurrentCountry = new JTextField(15);
 
        tCurrentName.setEditable(false);
        tCurrentAge.setEditable(false);
        tCurrentCpf.setEditable(false);
        tCurrentCountry.setEditable(false);
 
        tCurrentName.setText(currentName);
        tCurrentAge.setText(currentAge);
        tCurrentCpf.setText(currentCpf);
        tCurrentCountry.setText(currentCountry);
 
        tNewName    = new JTextField(15);
        tNewAge     = new JTextField(15);
        tNewCpf     = new JTextField(15);
        tNewCountry = new JTextField(15);

        lTitle          = new JLabel("Tela de Inserção    |    Alteração    |    Exclusão     de      Dados");
        lCurrentName    = new JLabel("Nome Atual:");
        lCurrentAge     = new JLabel("Idade Atual:");
        lCurrentCpf     = new JLabel("CPF Atual:");
        lCurrentCountry = new JLabel("País Atual:");
        lNewName        = new JLabel("Novo Nome:");
        lNewAge         = new JLabel("Nova Idade:");
        lNewCpf         = new JLabel("Novo CPF:");
        lNewCountry     = new JLabel("Novo País:");

        lTitle.setForeground(Color.WHITE);
        lCurrentName.setForeground(Color.WHITE);
        lCurrentAge.setForeground(Color.WHITE);
        lCurrentCpf.setForeground(Color.WHITE);
        lCurrentCountry.setForeground(Color.WHITE);
        lNewName.setForeground(Color.WHITE);
        lNewAge.setForeground(Color.WHITE);
        lNewCpf.setForeground(Color.WHITE);
        lNewCountry.setForeground(Color.WHITE);

        //=> Instanciando a classe responsavel por definir os estilos dos componentes
        styles = new Styles();
 
        styles.styleJButtonsExitButton(bExit);
        styles.styleJButtons(bSave);
 
        panel.add(tCurrentName);
        panel.add(tCurrentAge);
        panel.add(tCurrentCpf);
        panel.add(tCurrentCountry);
        panel.add(tNewName);
        panel.add(tNewAge);
        panel.add(tNewCpf);
        panel.add(tNewCountry);
        panel.add(lImage);
        panel.add(lTitle);
        panel.add(lCurrentName);
        panel.add(lCurrentAge);
        panel.add(lCurrentCpf);
        panel.add(lCurrentCountry);
        panel.add(lNewName);
        panel.add(lNewAge);
        panel.add(lNewCpf);
        panel.add(lNewCountry);
        panel.add(rbAlter);
        panel.add(rbInsert);
        panel.add(rbDelete);
        panel.add(bSave);
        panel.add(bExit);
 
        layout.putConstraint(SpringLayout.WEST  , bExit                , 810 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , bExit                , 010 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , bSave                , 600 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , bSave                , 400 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , rbAlter              , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , rbAlter              , 400 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , rbInsert             , 100 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , rbInsert             , 400 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , rbDelete             , 175 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , rbDelete             , 400 , SpringLayout.NORTH , panel);

        layout.putConstraint(SpringLayout.WEST  , tCurrentName        , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tCurrentName        , 125 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tCurrentAge         , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tCurrentAge         , 200 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tCurrentCpf         , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tCurrentCpf         , 275 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tCurrentCountry     , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tCurrentCountry     , 350 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tNewName            , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tNewName            , 125 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tNewAge             , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tNewAge             , 200 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tNewCpf             , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tNewCpf             , 275 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , tNewCountry         , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , tNewCountry         , 350 , SpringLayout.NORTH , panel);

        layout.putConstraint(SpringLayout.WEST  , lTitle              , 225 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lTitle              , 050 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lImage              , 550 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lImage              , 125 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lCurrentName        , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lCurrentName        , 105 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lCurrentAge         , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lCurrentAge         , 180 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lCurrentCpf         , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lCurrentCpf         , 255 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lCurrentCountry     , 025 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lCurrentCountry     , 330 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lNewName            , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lNewName            , 105 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lNewAge             , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lNewAge             , 180 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lNewCpf             , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lNewCpf             , 255 , SpringLayout.NORTH , panel);
        layout.putConstraint(SpringLayout.WEST  , lNewCountry         , 300 , SpringLayout.WEST  , panel);
        layout.putConstraint(SpringLayout.NORTH , lNewCountry         , 330 , SpringLayout.NORTH , panel);

        screen.add(panel);
        setModal(true);
    }

    //=> Metodo rsponsavel por fechar a tela
    public void exitButtonAction() {
        isOpen = false;
        screen.dispose();
    }

    //=> Metodo responsavel por validacoes do radioButton selecionado
    public void radioButtonAction(){

        if(rbInsert.isSelected()){

            tCurrentName.setEditable(false);
            tCurrentAge.setEditable(false);
            tCurrentCpf.setEditable(false);
            tCurrentCountry.setEditable(false);

            tCurrentName.setText("");
            tCurrentAge.setText("");
            tCurrentCpf.setText("");
            tCurrentCountry.setText("");

            tNewName.setEditable(true);
            tNewAge.setEditable(true);
            tNewCpf.setEditable(true);
            tNewCountry.setEditable(true);

        } else if(rbAlter.isSelected()){

            tCurrentName.setEditable(true);
            tCurrentAge.setEditable(true);
            tCurrentCpf.setEditable(true);
            tCurrentCountry.setEditable(true);

            tCurrentName.setText(currentName);
            tCurrentAge.setText(currentAge);
            tCurrentCpf.setText(currentCpf);
            tCurrentCountry.setText(currentCountry);

            styles.styleImageLabel(lImage, currentImage);
            lImage.repaint();

            tNewName.setEditable(false);
            tNewAge.setEditable(false);
            tNewCpf.setEditable(false);
            tNewCountry.setEditable(false);

        } else if(rbDelete.isSelected()){

            tCurrentName.setEditable(false);
            tCurrentAge.setEditable(false);
            tCurrentCpf.setEditable(false);
            tCurrentCountry.setEditable(false);

            tNewName.setEditable(false);
            tNewAge.setEditable(false);
            tNewCpf.setEditable(false);
            tNewCountry.setEditable(false);

            tCurrentName.setText(currentName);
            tCurrentAge.setText(currentAge);
            tCurrentCpf.setText(currentCpf);
            tCurrentCountry.setText(currentCountry);

            styles.styleImageLabel(lImage, bkpImage);
            lImage.repaint();

            tNewName.setText("");
            tNewAge.setText("");
            tNewCpf.setText("");
            tNewCountry.setText("");

        }

    }

    //=> Metodo responsavel por inserir os dados no DB
    public void insert(ImageIcon image){

        try {
            apsDAO.modifyData(1,tNewName.getText(), Integer.parseInt(tNewAge.getText()), tNewCpf.getText(), tNewCountry.getText(), convertImageToByte(image));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //=> Metodo responsavel por alterar os dados do jogador
    public void alter(ImageIcon image){
           
        try {
            apsDAO.modifyData(2,tCurrentName.getText(), Integer.parseInt(tCurrentAge.getText()), tCurrentCpf.getText(), tCurrentCountry.getText(), convertImageToByte(image));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //=> Metodo responsavel por deletar os dados do jogador
    public void delete(ImageIcon image){

        try {
            apsDAO.modifyData(3,tCurrentName.getText(), Integer.parseInt(tCurrentAge.getText()), tCurrentCpf.getText(), tCurrentCountry.getText(), convertImageToByte(image));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //=> Metodo responsavel por converter a imagem do ImageIcon em bytes
    public byte[] convertImageToByte(ImageIcon image){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Image img = image.getImage();

        if (image != null && img != null) {
            try {
                
                //=> Converte a imagem do ImageIcon em bytes e escreve no ByteArrayOutputStream
                BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = bufferedImage.createGraphics();
                bGr.drawImage(img, 0, 0, null);
                bGr.dispose();
                ImageIO.write(bufferedImage, "png", baos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return baos.toByteArray();

    }

    //=> Metodo responsavel por validar o botao salvar
    public void saveButtonAction(){

        if(rbAlter.isSelected()){
            if(tCurrentName.getText().equals("") || tCurrentAge.getText().equals("") || tCurrentCpf.getText().equals("") || tCurrentCountry.getText().equals("")){
                JOptionPane.showMessageDialog(screen, "Preencha todos os campos");
            } else {
                alter(currentImage);
                closingScreen();
            }
        }else if(rbInsert.isSelected()){
            if(tNewName.getText().equals("") || tNewAge.getText().equals("") || tNewCpf.getText().equals("") || tNewCountry.getText().equals("")){
                JOptionPane.showMessageDialog(screen, "Preencha todos os campos");
            } else {
                insert(currentImage);
                closingScreen();
            }
        }else {
            delete(currentImage);
            closingScreen();
        }

        isOpen = false;

    }

    public void closingScreen(){
        MainScreen.getInstance().loadDataButtonAction();
        screen.dispose();
    }

    //=> Metodo responsavel por carregar a nova imagem
    public ImageIcon loadImage(){

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Imagens", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(screen);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            
            String selectedFilePath = chooser.getSelectedFile().getAbsolutePath();

            if(selectedFilePath.toLowerCase().endsWith(".jpg") || selectedFilePath.toLowerCase().endsWith(".png")) {

                ImageIcon imgIcon = new ImageIcon(selectedFilePath);
                styles.styleImageLabel(lImage, imgIcon);
                lImage.repaint();
                currentImage = imgIcon;
                return imgIcon;

            } else {
                JOptionPane.showMessageDialog(screen, "Selecione um arquivo de imagem válido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;

    }

}
