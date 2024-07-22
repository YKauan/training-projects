package src.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLDataException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.database.classesdao.ApsDAO;
import src.screen.RegistrationScreen;

public class Table extends JTable {

    private static Table instance;

    private static DefaultTableModel model = new DefaultTableModel();
    
    private static ApsDAO apsDAO = ApsDAO.getInstance();
    
    private JScrollPane js;
    

    public Table(){

        setModel(model);                            
        
        //=> Definindo as colunas da tabela
        model.addColumn("Nome");
        model.addColumn("Idade");
        model.addColumn("CPF");
        model.addColumn("País");
        model.addColumn("Imagem do País");

        //=> Definindo o tamanho das minhas colunas 
        getColumnModel().getColumn(0).setPreferredWidth(15);
        getColumnModel().getColumn(1).setPreferredWidth(3);
        getColumnModel().getColumn(2).setPreferredWidth(11);
        getColumnModel().getColumn(3).setPreferredWidth(15);
        getColumnModel().getColumn(4).setPreferredWidth(10);

        //=> Adicionando mouseListener a tabela
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doubleClickTable(e);
            }
        });
        
        //=> Criando o meu JScrollPane e adicionando a tabela ao mesmo
        js = new JScrollPane(this);
        
    }

    //=> Metodo responsaver por retornar uma unica instancia da classe Table para toda a aplicacao.
    public static Table getInstance() {
        if (instance == null) {
            instance = new Table();
        }
        return instance;
    }

    //=> Metodo  responsavel por pegar e retornar o meu JScrollPane
    public JScrollPane getJScrollPane() {
        return js;
    }

    //=> Metodo responsavel por atualizar a tabela
    public void updateTableData(ArrayList<String> getName, ArrayList<Integer> getAge, ArrayList<String> getCpf, ArrayList<String> getCountry){
        model.setNumRows(0);
        searchData(model, getName, getAge, getCpf, getCountry);
        revalidate();
        repaint();
    }

    //=> Metodo responsavel por procurar e adicionar os dados que serao postos na tabela
    public static void searchData(DefaultTableModel model, ArrayList<String> getName, ArrayList<Integer> getAge, ArrayList<String> getCpf, ArrayList<String> getCountry) {
        
        //=> Adicionando os dados linha a linha, de acordo com a quantidade de intes no meu array
        for (int i = 0; i < getAge.size(); i++) {
            model.addRow(new Object[]{ getName.get(i),
                                       getAge.get(i),
                                       getCpf.get(i),
                                       getCountry.get(i),
                                       "Visualizar"});
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    //=> Metodo responsavel por verificar se o usuario selecionou uma linha da tabela
    public ArrayList<String> getSelectedRecord(){

        ArrayList<String> lineArr = new ArrayList<String>();

        if(this.getSelectedRow()!= -1 && this.getSelectedRowCount() > 1){
            
            JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Selecione uma linha", JOptionPane.INFORMATION_MESSAGE);

            return lineArr;

        } else {

            lineArr.add(getValueAt(this.getSelectedRow(), 0).toString());
            lineArr.add(getValueAt(this.getSelectedRow(), 1).toString());
            lineArr.add(getValueAt(this.getSelectedRow(), 2).toString());
            lineArr.add(getValueAt(this.getSelectedRow(), 3).toString());

            return lineArr;

        }

    }

    //=> Metodo responsavel por retornar a imagem do pais selecionado
    public ImageIcon getSelectedImage(){

        try {

            apsDAO.searchValues(2);

            byte[] image = apsDAO.getImageCountry().get(0);
            apsDAO.getImageCountry().clear();

            return new ImageIcon(image);

        } catch (SQLDataException e) {
            e.printStackTrace();
            return new ImageIcon("./src/images/MainWallpaper.png");
        }

    }

    //=> Metodo responsavel por executar o double click na tabela
    public void doubleClickTable(MouseEvent e){

        if (e.getClickCount() == 2) { 

            ArrayList<String> lineArr = getSelectedRecord();

            ImageIcon image = getSelectedImage();

            if (RegistrationScreen.isOpen == false)
                new RegistrationScreen(lineArr.get(0), lineArr.get(1), lineArr.get(2), lineArr.get(3), image);

        }
        

    }

}

