package src.database.classesdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

import src.database.connection.ConnectionDb;
import src.model.Table;

public class ApsDAO {
    
    private final ConnectionDb conn;

    private static ApsDAO instance;

    private static Table table = Table.getInstance();

    private String sqlQuery = "";

    private String apsName;
    private int    apsAge;
    private String apsCpf;
    private String apsCountry;

    private byte[] apsImageCountry;
    
    private ResultSet rs;
    private PreparedStatement ps;

    private ArrayList<String> nameArr         = new ArrayList<String>();
    private ArrayList<Integer> ageArr         = new ArrayList<Integer>();
    private ArrayList<String> cpfArr          = new ArrayList<String>();
    private ArrayList<String> countryArr      = new ArrayList<String>();
    private ArrayList<byte[]> imageCountryArr = new ArrayList<byte[]>();


    public ApsDAO() {

        this.conn = new ConnectionDb();

    }

    public void searchValues(int type) throws SQLDataException {

        //=> Montando a minha Query/Consulta

        nameArr.clear();
        ageArr.clear();
        cpfArr.clear();
        countryArr.clear();
        imageCountryArr.clear();
        
        if (type == 1) {

            sqlQuery = "SELECT * FROM APS ";

        } else if (type == 2) {

            ArrayList<String> lineArr = table.getSelectedRecord();

            sqlQuery =  " SELECT * FROM APS ";
            sqlQuery += " WHERE APS_NOME  = '" + lineArr.get(0) + "'";
            sqlQuery += " AND APS_IDADE   = "  + lineArr.get(1) + "";
            sqlQuery += " AND APS_CPF     = '" + lineArr.get(2) + "'";
            sqlQuery += " AND APS_PAIS    = '" + lineArr.get(3) + "'";

        }

        try {
            
            //=> Preparando a minha Query/Consulta
            ps = conn.openConnection().prepareStatement(sqlQuery);

            //=> Executando a minha Query/Consulta e armazenando o resultset
            rs = ps.executeQuery();

            //=> Enquando tiver uma linha no resultado da Query/Consulta continua fazendos os processos abaixo
            while (rs.next()) {

                //=> Pegando os dados das colunas minha consulta
                apsName          = rs.getString("APS_NOME");
                apsAge           = rs.getInt("APS_IDADE");
                apsCpf           = rs.getString("APS_CPF");
                apsCountry       = rs.getString("APS_PAIS");
                apsImageCountry  = rs.getBytes("APS_IMAGEMPAIS");

                //=> Adicionando os dados aos meus arrays
                nameArr.add(apsName);
                ageArr.add(apsAge);
                cpfArr.add(apsCpf);
                countryArr.add(apsCountry);
                imageCountryArr.add(apsImageCountry);

            }

            //=> Fechando as conexoes se as mesmas estiverem abertas
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        sqlQuery = "";

    }

    public void modifyData(int type, String nome, Integer idade, String cpf, String pais, byte[] image) throws SQLDataException{

        try {
            //=> Montando a minha Query/Consulta
            if (type == 1) {

                sqlQuery = "INSERT INTO APS (APS_NOME, APS_IDADE, APS_CPF, APS_PAIS, APS_IMAGEMPAIS) VALUES (?, ?, ?, ?, ?)";
                ps = conn.openConnection().prepareStatement(sqlQuery);
                ps.setString(1, nome);
                ps.setInt(2, idade);
                ps.setString(3, cpf);
                ps.setString(4, pais);
                ps.setBytes(5, image);

            } else if (type == 2) {

                ArrayList<String> lineArr = table.getSelectedRecord();

                sqlQuery = "UPDATE APS SET APS_NOME = ?, APS_IDADE = ?, APS_CPF = ?, APS_PAIS = ?, APS_IMAGEMPAIS = ? WHERE APS_NOME = ? AND APS_IDADE = ? AND APS_CPF = ? AND APS_PAIS = ?";
                ps = conn.openConnection().prepareStatement(sqlQuery);
                ps.setString(1, nome);
                ps.setInt(2, idade);
                ps.setString(3, cpf);
                ps.setString(4, pais);
                ps.setBytes(5, image);
                ps.setString(6, lineArr.get(0));
                ps.setString(7, lineArr.get(1));
                ps.setString(8, lineArr.get(2));
                ps.setString(9, lineArr.get(3));

            } else if (type == 3) {

                sqlQuery = "DELETE FROM APS WHERE APS_NOME = ? AND APS_IDADE = ? AND APS_CPF = ? AND APS_PAIS = ?";
                ps = conn.openConnection().prepareStatement(sqlQuery);
                ps.setString(1, nome);
                ps.setInt(2, idade);
                ps.setString(3, cpf);
                ps.setString(4, pais);

            }

            //=> Executando a minha Query/Consulta
            ps.executeUpdate();

            //=> Fechando as conexoes se as mesmas estiverem abertas
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sqlQuery = "";

    }

    //=> Metodo responsavel por retornar o meu array contendo os nomes
    public ArrayList<String> getName(){

        return nameArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo as idades
    public ArrayList<Integer> getAge(){

        return ageArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo os cpfs
    public ArrayList<String> getCpf(){

        return cpfArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo os paises
    public ArrayList<String> getCountry(){

        return countryArr;

    }

    //=> Metodo responsavel por retornar o meu array contendo as imagens
    public ArrayList<byte[]> getImageCountry(){

        return imageCountryArr;

    }

    //=> Metodo responsavel por limpar o meu ArrayList
    public ArrayList<String> clearArraysList(ArrayList<String> array){

        array.clear();
        
        return array;

    }

    //=> Metodo responsavel por retornar a instancia da minha classe
    public static ApsDAO getInstance() {
        if (instance == null) {
            instance = new ApsDAO();
        }
        return instance;
    }

    //=> Metodo responsavel por fechar a minha conexao com o DB
    public void closeConnection() throws SQLException {

        conn.closeConnection();

    }

}
