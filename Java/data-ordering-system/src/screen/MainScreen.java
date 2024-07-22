package src.screen;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import src.database.classesdao.ApsDAO;
import src.functions.java.Buscas;
import src.functions.java.Ordena;
import src.model.Table;
import src.styles.Styles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLDataException;
import java.util.ArrayList;

public class MainScreen {

    private static MainScreen instance;

    private static ApsDAO apsDAO = ApsDAO.getInstance();

    private static Table table   = Table.getInstance();

    private static Boolean isBinaryTree = false;

    public Screen screen;

    private Ordena ordenaJava;
    private Buscas search;
    
    private JPanel menuPanel;
    private JPanel tablePanel;

    private JLabel lTitle;
    private JLabel lOrdenadores;
    private JLabel lProgrammerLanguage;
    private JLabel lTypeOrder;
    private JLabel lTotalTime;
    private JLabel lSearchType;
    private JLabel lJava;
    private JLabel lJava2;
    private JLabel lTextOrd1;
    private JLabel lTextOrd2;
    private JLabel lTextOrd3;
    private JLabel lTextBusc;
    private JLabel lTextBusc2;
    private JLabel lTextBusc3;
    private JLabel lResultOrd1Java;
    private JLabel lResultOrd2Java;
    private JLabel lResultOrd3Java;
    private JLabel lResultBusc1Java;
    private JLabel lResultBusc2Java;
    private JLabel lResultBusc3Java;
    
    private JButton bOrder;
    private JButton bExit;
    private JButton bLoadData;
    private JButton bSearch;

    private JRadioButton rBubbleSort;
    private JRadioButton rTree;
    private JRadioButton rQuickSort;
    private JRadioButton rNameField;
    private JRadioButton rAgeField;
    private JRadioButton rCpfField;
    private JRadioButton rCountryField;
    private JRadioButton rImageCountryField;
    private JRadioButton rJava;
    private JRadioButton rBinarySearch;
    private JRadioButton rLinearSearch;
    private JRadioButton rSearchInTree;

    private ButtonGroup groupOrd;
    private ButtonGroup groupLang;
    private ButtonGroup groupField;
    private ButtonGroup groupSearch;

    private ArrayList<String> arrName;
    private ArrayList<Integer> arrAge;
    private ArrayList<String> arrCpf;
    private ArrayList<String> arrCountry;
    private ArrayList<byte[]> arrImageCountry;

    private Styles styles;

    private SpringLayout layout;

    private JScrollPane js;

    private int nopc;

    private long startTime;
    private long endTime;

    private Double totalTimeJava;

    private JTextField tfSearch;

    private Color purple = Color.decode("#9400D3");
    private Color red = Color.decode("#FF0000");

    public MainScreen(){

        //=> Instanciando a classe responsavel por definir as propiedades da Frame
        screen = new Screen(1);

        //=> Instanciando os paineis
        menuPanel = new JPanel();
        tablePanel = new JPanel();

        //=> Instanciando a classe responsavel por definir os estilos dos componentes
        styles = new Styles();

        //=> Instanciando os componentes de label
        lTitle = new JLabel("Sistema de Ordenação de dados");
        lTitle.setForeground(Color.WHITE);

        lOrdenadores = new JLabel("Escolha um dos Algoritmos de ordenação abaixo");
        lOrdenadores.setForeground(purple);

        lProgrammerLanguage = new JLabel("Selecione a linguagem");
        lProgrammerLanguage.setForeground(purple);

        lTypeOrder = new JLabel("Selecione o tipo de ordenação / coluna de pesquisa");
        lTypeOrder.setForeground(purple);

        lSearchType = new JLabel("Selecione o tipo de busca");
        lSearchType.setForeground(purple);

        lTotalTime = new JLabel("Tempo total de Busca e Ordenação");
        lTotalTime.setForeground(purple);

        lJava = new JLabel("Java -> ");
        lJava.setForeground(red);

        lTextOrd1 = new JLabel("Bolha");
        lTextOrd1.setForeground(purple);

        lTextOrd2 = new JLabel("Arvore Binaria");
        lTextOrd2.setForeground(purple);

        lTextOrd3 = new JLabel("Quick Sort");
        lTextOrd3.setForeground(purple);

        lResultOrd1Java = new JLabel("0.0");  
        lResultOrd1Java.setForeground(Color.WHITE);

        lResultOrd2Java = new JLabel("0.0");
        lResultOrd2Java.setForeground(Color.WHITE);

        lResultOrd3Java = new JLabel("0.0");
        lResultOrd3Java.setForeground(Color.WHITE);

        lJava2 = new JLabel("Java -> ");
        lJava2.setForeground(red);

        lTextBusc = new JLabel("Busca Binaria");
        lTextBusc.setForeground(purple);

        lTextBusc2 = new JLabel("Busca Linear");
        lTextBusc2.setForeground(purple);

        lTextBusc3 = new JLabel("Busca na Arvore");
        lTextBusc3.setForeground(purple);

        lResultBusc1Java = new JLabel("0.0");
        lResultBusc1Java.setForeground(Color.WHITE);

        lResultBusc2Java = new JLabel("0.0");
        lResultBusc2Java.setForeground(Color.WHITE);

        lResultBusc3Java = new JLabel("0.0");
        lResultBusc3Java.setForeground(Color.WHITE);

        //=> Instanciando os componentes de botao   

        bOrder = new JButton("Ordenar");
        bOrder.addActionListener(e -> {orderButtonAction(); validateColorsByTime();});
        bOrder.setEnabled(false);

        bExit = new JButton();
        bExit.addActionListener(e -> exitButtonAction());

        bLoadData = new JButton("Carregar Dados");
        bLoadData.addActionListener(e -> loadDataButtonAction());

        bSearch = new JButton();
        bSearch.addActionListener(e -> searchButtonAction());
        bSearch.setEnabled(false);

        styles.styleJButtonsSearchButton(bSearch);
        styles.styleJButtonsExitButton(bExit);
        styles.styleJButtons(bOrder);
        styles.styleJButtons(bLoadData);

        //=> Instanciando os componentes de radio

        rBubbleSort = new JRadioButton("Bolha");
        rTree = new JRadioButton("Arvore Binaria");
        rQuickSort = new JRadioButton("Quick Sort");
        rBinarySearch = new JRadioButton("Busca Binaria");
        rLinearSearch = new JRadioButton("Busca Linear");
        rSearchInTree = new JRadioButton("Busca na Arvore");
        rJava = new JRadioButton("Java");
        rNameField = new JRadioButton("Nome");
        rAgeField = new JRadioButton("Idade");
        rCpfField = new JRadioButton("CPF");
        rCountryField = new JRadioButton("País");
        rImageCountryField = new JRadioButton("Tamanho da Imagem");

        rBubbleSort.setSelected(true);
        rLinearSearch.setSelected(true);
        rJava.setSelected(true);
        rNameField.setSelected(true);

        styles.styleRadButton(rBubbleSort);
        styles.styleRadButton(rTree);
        styles.styleRadButton(rQuickSort);
        styles.styleRadButton(rBinarySearch);
        styles.styleRadButton(rLinearSearch);
        styles.styleRadButton(rSearchInTree);
        styles.styleRadButton(rJava);
        styles.styleRadButton(rNameField);
        styles.styleRadButton(rAgeField);
        styles.styleRadButton(rCpfField);
        styles.styleRadButton(rCountryField);
        styles.styleRadButton(rImageCountryField);

        rTree.addActionListener(e -> validateRadiosButton());
        rBubbleSort.addActionListener(e -> validateRadiosButton());
        rQuickSort.addActionListener(e -> validateRadiosButton());

        rSearchInTree.setEnabled(false);

        //=> Instanciando o grupo de botoes
        groupOrd = new ButtonGroup();
        groupLang = new ButtonGroup();
        groupField = new ButtonGroup();
        groupSearch = new ButtonGroup();

        //=> Adicionando os botoes ao grupo
        groupOrd.add(rBubbleSort);
        groupOrd.add(rTree);
        groupOrd.add(rQuickSort);

        groupLang.add(rJava);

        groupField.add(rNameField);
        groupField.add(rAgeField);
        groupField.add(rCpfField);
        groupField.add(rCountryField);
        groupField.add(rImageCountryField);

        groupSearch.add(rBinarySearch);
        groupSearch.add(rLinearSearch);
        groupSearch.add(rSearchInTree);

        //=> Instanciando o campo de texto
        tfSearch = new JTextField(15);

        //=> Instanciando o layout
        layout = new SpringLayout();

        //=> Definindo as cores dos paineis
        menuPanel.setBackground(Color.BLACK);
        tablePanel.setBackground(Color.BLACK);

        //=> Definindo as dimensoes dos paineis
        menuPanel.setPreferredSize(new Dimension(750, screen.getHeight()));
        tablePanel.setPreferredSize(new Dimension(500, screen.getHeight()));

        //=> Definindo os Layouts
        screen.setLayout(new BorderLayout());
        menuPanel.setLayout(layout);
        tablePanel.setLayout(new BorderLayout());

        //=> Adicionando os componentes aos paineis
        menuPanel.add(lTitle);
        menuPanel.add(lOrdenadores);
        menuPanel.add(lProgrammerLanguage);
        menuPanel.add(lTypeOrder);
        menuPanel.add(lSearchType);
        menuPanel.add(lTotalTime);
        menuPanel.add(lJava);
        menuPanel.add(lTextOrd1);
        menuPanel.add(lTextOrd2);
        menuPanel.add(lTextOrd3);
        menuPanel.add(lTextBusc);
        menuPanel.add(lTextBusc2);
        menuPanel.add(lTextBusc3);
        menuPanel.add(lResultOrd1Java);
        menuPanel.add(lResultOrd2Java);
        menuPanel.add(lResultOrd3Java);
        menuPanel.add(lJava2);
        menuPanel.add(lResultBusc1Java);
        menuPanel.add(lResultBusc2Java);
        menuPanel.add(lResultBusc3Java);
        menuPanel.add(bExit);
        menuPanel.add(bOrder);
        menuPanel.add(bLoadData);
        menuPanel.add(bSearch);
        menuPanel.add(rBubbleSort);
        menuPanel.add(rTree);
        menuPanel.add(rQuickSort);
        menuPanel.add(rBinarySearch);
        menuPanel.add(rLinearSearch);
        menuPanel.add(rSearchInTree);
        menuPanel.add(rJava);
        menuPanel.add(rNameField);
        menuPanel.add(rAgeField);
        menuPanel.add(rCpfField);
        menuPanel.add(rCountryField);
        menuPanel.add(rImageCountryField);
        menuPanel.add(tfSearch);

        //=> Posicionando so componentes
        layout.putConstraint(SpringLayout.WEST  , lTitle              , 010 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTitle              , 015 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lOrdenadores        , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lOrdenadores        , 075 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lProgrammerLanguage , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lProgrammerLanguage , 295 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTypeOrder          , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTypeOrder          , 215 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lSearchType         , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lSearchType         , 135 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTotalTime          , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTotalTime          , 375 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lJava               , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lJava               , 450 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTextOrd1           , 100 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTextOrd1           , 425 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTextOrd2           , 280 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTextOrd2           , 425 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTextOrd3           , 510 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTextOrd3           , 425 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTextBusc           , 100 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTextBusc           , 510 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTextBusc2          , 280 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTextBusc2          , 510 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lTextBusc3          , 510 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lTextBusc3          , 510 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lResultOrd1Java     , 100 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lResultOrd1Java     , 450 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lResultOrd2Java     , 280 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lResultOrd2Java     , 450 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lResultOrd3Java     , 510 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lResultOrd3Java     , 450 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lJava2              , 015 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lJava2              , 535 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lResultBusc1Java    , 100 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lResultBusc1Java    , 535 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lResultBusc2Java    , 280 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lResultBusc2Java    , 535 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , lResultBusc3Java    , 510 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , lResultBusc3Java    , 535 , SpringLayout.NORTH , menuPanel);

        layout.putConstraint(SpringLayout.WEST  , bExit               , 700 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , bExit               , 010 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , bOrder              , 650 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , bOrder              ,  80 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , bLoadData           , 500 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , bLoadData           ,  80 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , bSearch             , 700 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , bSearch             , 120 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , tfSearch            , 500 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , tfSearch            , 125 , SpringLayout.NORTH , menuPanel);

        layout.putConstraint(SpringLayout.WEST  , rBubbleSort         , 030 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rBubbleSort         , 100 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rTree               , 100 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rTree               , 100 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rQuickSort          , 237 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rQuickSort          , 100 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rLinearSearch       , 030 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rLinearSearch       , 170 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rSearchInTree       , 280 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rSearchInTree       , 170 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rBinarySearch       , 150 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rBinarySearch       , 170 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rJava               , 030 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rJava               , 335 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rNameField          , 030 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rNameField          , 255 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rAgeField           , 100 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rAgeField           , 255 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rCpfField           , 180 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rCpfField           , 255 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rCountryField       , 249 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rCountryField       , 255 , SpringLayout.NORTH , menuPanel);
        layout.putConstraint(SpringLayout.WEST  , rImageCountryField  , 320 , SpringLayout.WEST  , menuPanel);
        layout.putConstraint(SpringLayout.NORTH , rImageCountryField  , 255 , SpringLayout.NORTH , menuPanel);

        screen.add(menuPanel, BorderLayout.WEST);
        screen.add(tablePanel, BorderLayout.EAST);

    }

    //=> Metodo rsponsavel por fechar a tela
    public void exitButtonAction() {
        screen.dispose();
    }

    //=> Metodo responsavel por ordenar os dados
    public void orderButtonAction() {

        ArrayList<?> newArr = new ArrayList<Object>();

        ordenaJava = new Ordena();

        if(rBubbleSort.isSelected()){

            validateOpc();

            startTime = System.nanoTime();
            newArr =  ordenaJava.bubbleSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc);
            endTime = System.nanoTime();                

            totalTimeJava = (endTime - startTime) / 1e9;

            lResultOrd1Java.setText(String.valueOf(totalTimeJava));

            isBinaryTree = false;

        } else if(rTree.isSelected()){

            validateOpc();

            startTime = System.nanoTime();
            newArr = ordenaJava.tree(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc);
            endTime = System.nanoTime();

            totalTimeJava = (endTime - startTime) / 1e9;

            lResultOrd2Java.setText(String.valueOf(totalTimeJava));

            isBinaryTree = true;

        } else if(rQuickSort.isSelected()){

            validateOpc();

            startTime = System.nanoTime();
            newArr =  ordenaJava.quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, 0, arrName.size() - 1);
            endTime = System.nanoTime();                

            totalTimeJava = (endTime - startTime) / 1e9;

            lResultOrd3Java.setText(String.valueOf(totalTimeJava));

            isBinaryTree = false;

        }

        if (newArr != null){
                
            @SuppressWarnings ("unchecked")
            ArrayList<String> nameArr = new ArrayList<String>((ArrayList<String>) newArr.get(0));
            @SuppressWarnings ("unchecked")
            ArrayList<Integer> ageArr = new ArrayList<Integer>((ArrayList<Integer>) newArr.get(1));
            @SuppressWarnings ("unchecked")
            ArrayList<String> cpfArr = new ArrayList<String>((ArrayList<String>) newArr.get(2));
            @SuppressWarnings ("unchecked")
            ArrayList<String> countryArr = new ArrayList<String>((ArrayList<String>) newArr.get(3));

            table.updateTableData(nameArr, ageArr, cpfArr, countryArr);

            clearArraysLists(0);

            bOrder.setEnabled(false);

        }

        bSearch.setEnabled(true);

    }

    //=> Metodo responsavel por buscar os dados
    public void searchButtonAction() {

        search = new Buscas();

        if(rBinarySearch.isSelected()){

            startTime = System.nanoTime();
            search.buscaBinaria(validadeArrSelectedField(), tfSearch.getText(), screen);
            endTime = System.nanoTime();

        } else if(rLinearSearch.isSelected()){

            startTime = System.nanoTime();
            search.buscaLinear(validadeArrSelectedField(), tfSearch.getText(), screen);
            endTime = System.nanoTime();

        }else if(rTree.isSelected()){

            if(!isBinaryTree){
                JOptionPane.showMessageDialog(null, "É necessário ordenar por Arvore Binaria antes de realizar a busca na árvore!", "Busca na Árvore", JOptionPane.INFORMATION_MESSAGE);
            }else{

                validateOpc();

                startTime = System.nanoTime();
                ordenaJava.searchInTree(tfSearch.getText(), nopc, screen);
                endTime = System.nanoTime();

            }

        }

        totalTimeJava = (endTime - startTime) / 1e9;

        if(rBinarySearch.isSelected()){
            lResultBusc1Java.setText(String.valueOf(totalTimeJava));
        }else if(rLinearSearch.isSelected()){
            lResultBusc2Java.setText(String.valueOf(totalTimeJava));
        }else if(rSearchInTree.isSelected()){
            lResultBusc3Java.setText(String.valueOf(totalTimeJava));
        }

    }

    //=> Metodo responsavel por carregar os dados
    public void loadDataButtonAction() { 

        try{
            
            apsDAO.searchValues(1);

        } catch (SQLDataException e) {
            System.out.println("Erro ao buscar os dados: " + e.getMessage());
        }

        arrName         = new ArrayList<>(apsDAO.getName());
        arrAge          = new ArrayList<>(apsDAO.getAge());
        arrCpf          = new ArrayList<>(apsDAO.getCpf());
        arrCountry      = new ArrayList<>(apsDAO.getCountry());
        arrImageCountry = new ArrayList<>(apsDAO.getImageCountry());

        table.updateTableData(arrName, arrAge, arrCpf, arrCountry);

        clearArraysLists(1);

        tablePanel.setLayout(new BorderLayout());

        js = table.getJScrollPane();

        tablePanel.add(BorderLayout.CENTER, js);

        tablePanel.revalidate();

        //=> Desabilitando os JButtons
        bOrder.setEnabled(true);
        bSearch.setEnabled(false);

    }

    //=> Metodo responsavel por limpar os arrays
    public void clearArraysLists(int type) {

        if(type == 1){
            apsDAO.getName().clear();
            apsDAO.getAge().clear();
            apsDAO.getCpf().clear();
            apsDAO.getCountry().clear();
            apsDAO.getImageCountry().clear();
        }else if(type == 2){
            arrName.clear();
            arrAge.clear();
            arrCpf.clear();
            arrCountry.clear();
            arrImageCountry.clear();
        }

    }
    
    //=> Metodo responsavel por validar as cores dos labels
    public void validateColorsByTime(){

        Double result1Java =       Double.parseDouble(lResultOrd1Java.getText());
        Double result2Java =       Double.parseDouble(lResultOrd2Java.getText());
        Double result3Java =       Double.parseDouble(lResultOrd3Java.getText());
        
        Double result1BuscJava =   Double.parseDouble(lResultBusc1Java.getText());
        Double result2BuscJava =   Double.parseDouble(lResultBusc2Java.getText());
        Double result3BuscJava =   Double.parseDouble(lResultBusc3Java.getText());

        if(result1Java < result2Java && result1Java < result3Java){

            lResultOrd1Java.setForeground(Color.GREEN);

            if(result2Java < result3Java){
                lResultOrd2Java.setForeground(Color.YELLOW);
                lResultOrd3Java.setForeground(Color.RED);
            } else {
                lResultOrd2Java.setForeground(Color.RED);
                lResultOrd3Java.setForeground(Color.YELLOW);
            }

        } else if (result2Java < result1Java && result2Java < result3Java){

            lResultOrd2Java.setForeground(Color.GREEN);

            if(result1Java < result3Java){
                lResultOrd1Java.setForeground(Color.YELLOW);
                lResultOrd3Java.setForeground(Color.RED);
            } else {
                lResultOrd1Java.setForeground(Color.RED);
                lResultOrd3Java.setForeground(Color.YELLOW);
            }

        } else {

            lResultOrd3Java.setForeground(Color.GREEN);

            if(result1Java < result2Java){
                lResultOrd1Java.setForeground(Color.YELLOW);
                lResultOrd2Java.setForeground(Color.RED);
            } else {
                lResultOrd1Java.setForeground(Color.RED);
                lResultOrd2Java.setForeground(Color.YELLOW);
            }

        }

        if(result1BuscJava < result2BuscJava && result1BuscJava < result3BuscJava){

            lResultBusc1Java.setForeground(Color.GREEN);

            if(result2BuscJava < result3BuscJava){
                lResultBusc2Java.setForeground(Color.YELLOW);
                lResultBusc3Java.setForeground(Color.RED);
            } else {
                lResultBusc2Java.setForeground(Color.RED);
                lResultBusc3Java.setForeground(Color.YELLOW);
            }

        } else if (result2BuscJava < result1BuscJava && result2BuscJava < result3BuscJava){

            lResultBusc2Java.setForeground(Color.GREEN);

            if(result1BuscJava < result3BuscJava){
                lResultBusc1Java.setForeground(Color.YELLOW);
                lResultBusc3Java.setForeground(Color.RED);
            } else {
                lResultBusc1Java.setForeground(Color.RED);
                lResultBusc3Java.setForeground(Color.YELLOW);
            }

        } else {
            
            lResultBusc3Java.setForeground(Color.GREEN);

            if(result1BuscJava < result2BuscJava){
                lResultBusc1Java.setForeground(Color.YELLOW);
                lResultBusc2Java.setForeground(Color.RED);
            } else {
                lResultBusc1Java.setForeground(Color.RED);
                lResultBusc2Java.setForeground(Color.YELLOW);
            }

        }
        
    }
    
    //=> Metodo responsavel por validar os radios buttons
    public void validateRadiosButton(){

        if(rTree.isSelected()){

            rLinearSearch.setEnabled(false);
            rBinarySearch.setEnabled(false);
            rSearchInTree.setEnabled(true);
            rSearchInTree.setSelected(true);

        }else {

            rLinearSearch.setEnabled(true);
            rBinarySearch.setEnabled(true);
            rSearchInTree.setEnabled(false);
            rLinearSearch.setSelected(true);

        }

    }
    
    //=> Metodo responsavel por validar a opcao selecionada
    public void validateOpc(){

        nopc = 0;

        if(rNameField.isSelected()){

            nopc = 0;

        }else if(rAgeField.isSelected()){
        
            nopc = 1;

        }else if(rCpfField.isSelected()){

            nopc = 2;

        }else if(rCountryField.isSelected()){

            nopc = 3;

        }else if(rImageCountryField.isSelected()){

            nopc = 4;


        }

    }
    
    //=> Metodo responsavel por validar o campo selecionado
    public ArrayList<?> validadeArrSelectedField(){

        ArrayList<?> arr = new ArrayList<>();

        if(rNameField.isSelected()){

            arr = new ArrayList<>(arrName);
            
        }else if(rAgeField.isSelected()){

            arr = new ArrayList<>(arrAge);

        }else if(rCpfField.isSelected()){

            arr = new ArrayList<>(arrCpf);

        }else if(rCountryField.isSelected()){

            arr = new ArrayList<>(arrCountry);

        }else if(rImageCountryField.isSelected()){

            arr = new ArrayList<>(arrImageCountry);
        }

        return arr;

    }

    //=> Metodo responsavel por retornar a instancia da minha classe
    public static MainScreen getInstance() {
        if (instance == null) {
            instance = new MainScreen();
        }
        return instance;
    }

}
