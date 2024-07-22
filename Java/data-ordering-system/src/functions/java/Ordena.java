package src.functions.java;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ordena {
    
    //=> Metodo responsavel por ordenar os arrays com ordenacao bolha
    public ArrayList<Object> bubbleSort(ArrayList<String> arrName, ArrayList<Integer> arrAge, ArrayList<String> arrCpf, ArrayList<String> arrCountry, ArrayList<byte[]> arrImageCountry, int opc){

        int n = arrAge.size();

        ArrayList<Object> auxArray = new ArrayList<Object>();

        ArrayList<Object> arr = new ArrayList<Object>();

        if(opc == 0){

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    
                    if (arrName.get(j).compareToIgnoreCase(arrName.get(j + 1)) > 0) {

                        auxArray.add(arrAge.get(j));
                        arrAge.set(j, arrAge.get(j + 1));
                        arrAge.set(j + 1, (Integer) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrName.get(j));
                        arrName.set(j, arrName.get(j + 1));
                        arrName.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCpf.get(j));
                        arrCpf.set(j, arrCpf.get(j + 1));
                        arrCpf.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCountry.get(j));
                        arrCountry.set(j, arrCountry.get(j + 1));
                        arrCountry.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrImageCountry.get(j));
                        arrImageCountry.set(j, arrImageCountry.get(j + 1));
                        arrImageCountry.set(j + 1, (byte[]) auxArray.get(0));

                        auxArray.clear();

                    }
                }
            }

        } else if(opc == 1){

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
        
                    if (arrAge.get(j) > arrAge.get(j + 1)) {

                        auxArray.add(arrAge.get(j));
                        arrAge.set(j, arrAge.get(j + 1));
                        arrAge.set(j + 1, (Integer) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrName.get(j));
                        arrName.set(j, arrName.get(j + 1));
                        arrName.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCpf.get(j));
                        arrCpf.set(j, arrCpf.get(j + 1));
                        arrCpf.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCountry.get(j));
                        arrCountry.set(j, arrCountry.get(j + 1));
                        arrCountry.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrImageCountry.get(j));
                        arrImageCountry.set(j, arrImageCountry.get(j + 1));
                        arrImageCountry.set(j + 1, (byte[]) auxArray.get(0));

                        auxArray.clear();

                    }
                }
            }
        } else if(opc == 2){
            
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    
                    if (arrCpf.get(j).compareToIgnoreCase(arrCpf.get(j + 1)) > 0) {

                        auxArray.add(arrAge.get(j));
                        arrAge.set(j, arrAge.get(j + 1));
                        arrAge.set(j + 1, (Integer) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrName.get(j));
                        arrName.set(j, arrName.get(j + 1));
                        arrName.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCpf.get(j));
                        arrCpf.set(j, arrCpf.get(j + 1));
                        arrCpf.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCountry.get(j));
                        arrCountry.set(j, arrCountry.get(j + 1));
                        arrCountry.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrImageCountry.get(j));
                        arrImageCountry.set(j, arrImageCountry.get(j + 1));
                        arrImageCountry.set(j + 1, (byte[]) auxArray.get(0));

                        auxArray.clear();

                    }
                }
            }

        } else if(opc == 3){

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    
                    if (arrCountry.get(j).compareToIgnoreCase(arrCountry.get(j + 1)) > 0) {

                        auxArray.add(arrAge.get(j));
                        arrAge.set(j, arrAge.get(j + 1));
                        arrAge.set(j + 1, (Integer) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrName.get(j));
                        arrName.set(j, arrName.get(j + 1));
                        arrName.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCpf.get(j));
                        arrCpf.set(j, arrCpf.get(j + 1));
                        arrCpf.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCountry.get(j));
                        arrCountry.set(j, arrCountry.get(j + 1));
                        arrCountry.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrImageCountry.get(j));
                        arrImageCountry.set(j, arrImageCountry.get(j + 1));
                        arrImageCountry.set(j + 1, (byte[]) auxArray.get(0));

                        auxArray.clear();

                    }
                }
            }

        } else if(opc == 4){

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    
                    if (arrImageCountry.get(j).length > arrImageCountry.get(j + 1).length) {

                        auxArray.add(arrAge.get(j));
                        arrAge.set(j, arrAge.get(j + 1));
                        arrAge.set(j + 1, (Integer) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrName.get(j));
                        arrName.set(j, arrName.get(j + 1));
                        arrName.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCpf.get(j));
                        arrCpf.set(j, arrCpf.get(j + 1));
                        arrCpf.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrCountry.get(j));
                        arrCountry.set(j, arrCountry.get(j + 1));
                        arrCountry.set(j + 1, (String) auxArray.get(0));

                        auxArray.clear();

                        auxArray.add(arrImageCountry.get(j));
                        arrImageCountry.set(j, arrImageCountry.get(j + 1));
                        arrImageCountry.set(j + 1, (byte[]) auxArray.get(0));

                        auxArray.clear();

                    }
                }
            }

        }

        arr.add(arrName);
        arr.add(arrAge);
        arr.add(arrCpf);
        arr.add(arrCountry);
        arr.add(arrImageCountry);
        
        return arr;

    }

    //=> Metodo responsavel por ordenar os arrays com ordenacao QuickSort
    public ArrayList<Object> quickSort(ArrayList<String> arrName, ArrayList<Integer> arrAge, ArrayList<String> arrCpf, ArrayList<String> arrCountry, ArrayList<byte[]> arrImageCountry, int nopc, int left, int right){
        
        ArrayList<Object> arr = new ArrayList<Object>();

        int intAux = 0;
        int intPivot = 0;

        int lft = left;
        int rgt = right;

        String strPivot = arrName.get((lft + rgt) / 2);
        String strAux = "";
        
        
        if( nopc == 0) {
            
            strPivot = arrName.get((lft + rgt) / 2);
            
            while( lft <= rgt ) {

                while(arrName.get(lft).compareToIgnoreCase(strPivot) < 0){
                    lft++;
                }
                while(arrName.get(rgt).compareToIgnoreCase(strPivot) > 0){
                    rgt--;
                }

                if(lft <= rgt){
                    strAux = arrName.get(lft);
                    arrName.set(lft, arrName.get(rgt));
                    arrName.set(rgt, strAux);

                    intAux = arrAge.get(lft);
                    arrAge.set(lft, arrAge.get(rgt));
                    arrAge.set(rgt, intAux);

                    strAux = arrCpf.get(lft);
                    arrCpf.set(lft, arrCpf.get(rgt));
                    arrCpf.set(rgt, strAux);

                    strAux = arrCountry.get(lft);
                    arrCountry.set(lft, arrCountry.get(rgt));
                    arrCountry.set(rgt, strAux);

                    byte[] byteAux = arrImageCountry.get(lft);
                    arrImageCountry.set(lft, arrImageCountry.get(rgt));
                    arrImageCountry.set(rgt, byteAux);

                    lft++;
                    rgt--;
                }
            }

            if(rgt > left){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, left, rgt);
            }

            if(lft < right){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, lft, right);
            }

        } else if( nopc ==1 ) {

            intPivot = arrAge.get((lft + rgt) / 2);

            while( lft <= rgt ) {

                while(arrAge.get(lft) < intPivot){
                    lft++;
                }
                while(arrAge.get(rgt) > intPivot){
                    rgt--;
                }

                if(lft <= rgt){
                    strAux = arrName.get(lft);
                    arrName.set(lft, arrName.get(rgt));
                    arrName.set(rgt, strAux);

                    intAux = arrAge.get(lft);
                    arrAge.set(lft, arrAge.get(rgt));
                    arrAge.set(rgt, intAux);

                    strAux = arrCpf.get(lft);
                    arrCpf.set(lft, arrCpf.get(rgt));
                    arrCpf.set(rgt, strAux);

                    strAux = arrCountry.get(lft);
                    arrCountry.set(lft, arrCountry.get(rgt));
                    arrCountry.set(rgt, strAux);

                    byte[] byteAux = arrImageCountry.get(lft);
                    arrImageCountry.set(lft, arrImageCountry.get(rgt));
                    arrImageCountry.set(rgt, byteAux);

                    lft++;
                    rgt--;
                }

            }

            if(rgt > left){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, left, rgt);
            }

            if(lft < right){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, lft, right);
            }

        } else if ( nopc == 2 ) {

            strPivot = arrCpf.get((lft + rgt) / 2);

            while ( lft <= rgt ) {

                while(arrCpf.get(lft).compareToIgnoreCase(strPivot) < 0){
                    lft++;
                }
                while(arrCpf.get(rgt).compareToIgnoreCase(strPivot) > 0){
                    rgt--;
                }

                if(lft <= rgt){
                    strAux = arrName.get(lft);
                    arrName.set(lft, arrName.get(rgt));
                    arrName.set(rgt, strAux);

                    intAux = arrAge.get(lft);
                    arrAge.set(lft, arrAge.get(rgt));
                    arrAge.set(rgt, intAux);

                    strAux = arrCpf.get(lft);
                    arrCpf.set(lft, arrCpf.get(rgt));
                    arrCpf.set(rgt, strAux);

                    strAux = arrCountry.get(lft);
                    arrCountry.set(lft, arrCountry.get(rgt));
                    arrCountry.set(rgt, strAux);

                    byte[] byteAux = arrImageCountry.get(lft);
                    arrImageCountry.set(lft, arrImageCountry.get(rgt));
                    arrImageCountry.set(rgt, byteAux);

                    lft++;
                    rgt--;
                }

            }

            if(rgt > left){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, left, rgt);
            }

            if(lft < right){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, lft, right);
            }

        } else if ( nopc == 3 ) {

            strPivot = arrCountry.get((lft + rgt) / 2);

            while ( lft <= rgt ) {

                while( arrCountry.get(lft).compareToIgnoreCase(strPivot) < 0 ){
                    lft++;
                }
                while( arrCountry.get(rgt).compareToIgnoreCase(strPivot) > 0 ){
                    rgt--;
                }

                if(lft <= rgt){
                    strAux = arrName.get(lft);
                    arrName.set(lft, arrName.get(rgt));
                    arrName.set(rgt, strAux);

                    intAux = arrAge.get(lft);
                    arrAge.set(lft, arrAge.get(rgt));
                    arrAge.set(rgt, intAux);

                    strAux = arrCpf.get(lft);
                    arrCpf.set(lft, arrCpf.get(rgt));
                    arrCpf.set(rgt, strAux);

                    strAux = arrCountry.get(lft);
                    arrCountry.set(lft, arrCountry.get(rgt));
                    arrCountry.set(rgt, strAux);

                    byte[] byteAux = arrImageCountry.get(lft);
                    arrImageCountry.set(lft, arrImageCountry.get(rgt));
                    arrImageCountry.set(rgt, byteAux);

                    lft++;
                    rgt--;
                }

            }

            if(rgt > left){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, left, rgt);
            }

            if(lft < right){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, lft, right);
            }

        } else if ( nopc == 4 ) {

            intPivot = arrImageCountry.get((lft + rgt) / 2).length;

            while ( lft <= rgt ) {

                while( arrImageCountry.get(lft).length < intPivot ){
                    lft++;
                }
                while( arrImageCountry.get(rgt).length > intPivot ){
                    rgt--;
                }

                if(lft <= rgt){
                    strAux = arrName.get(lft);
                    arrName.set(lft, arrName.get(rgt));
                    arrName.set(rgt, strAux);

                    intAux = arrAge.get(lft);
                    arrAge.set(lft, arrAge.get(rgt));
                    arrAge.set(rgt, intAux);

                    strAux = arrCpf.get(lft);
                    arrCpf.set(lft, arrCpf.get(rgt));
                    arrCpf.set(rgt, strAux);

                    strAux = arrCountry.get(lft);
                    arrCountry.set(lft, arrCountry.get(rgt));
                    arrCountry.set(rgt, strAux);

                    byte[] byteAux = arrImageCountry.get(lft);
                    arrImageCountry.set(lft, arrImageCountry.get(rgt));
                    arrImageCountry.set(rgt, byteAux);

                    lft++;
                    rgt--;
                }

            }

            if(rgt > left){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, left, rgt);
            }

            if(lft < right){
                quickSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, nopc, lft, right);
            }

        }
        
        

        arr.add(arrName);
        arr.add(arrAge);
        arr.add(arrCpf);
        arr.add(arrCountry);
        arr.add(arrImageCountry);
        
        return arr;
    }
    
    //=> Metodo responsavel por ordenar os arrays em arvore
    public ArrayList<Object> tree(ArrayList<String> arrName, ArrayList<Integer> arrAge, ArrayList<String> arrCpf, ArrayList<String> arrCountry, ArrayList<byte[]> arrImageCountry, int opc){

        TreeSort bin = TreeSort.getInstance();

        bin.treeSort(arrName, arrAge, arrCpf, arrCountry, arrImageCountry, opc);

        ArrayList<Object> arr = bin.treeOrdered();

        return arr;

    }

    //=> Metodo responsavel por buscar um elemento na arvore
    public void searchInTree(String search, int opc, JFrame frame){

        TreeSort bin = TreeSort.getInstance();

        try{

            bin.search(TreeSort.nodeList.get(0), search, opc);

        }catch(NullPointerException e){
        
            JOptionPane.showMessageDialog(frame, search + " não encontrado");
        
        }
        
    }

}

//=> Classe responsavel por criar a arvore binaria
class TreeNode {

    String name;
    Integer age;
    String cpf;
    String country;
    byte[] imageCountry;
    TreeNode lft;
    TreeNode rgt;

    public TreeNode(String name, Integer age, String cpf, String country, byte[] imageCountry) {
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.country = country;
        this.imageCountry = imageCountry;
        this.lft = null;
        this.rgt = null;
    }
}

//=> Classe responsavel por ordenar os arrays em arvore
class TreeSort {

    private static TreeSort instance;

    public static ArrayList<TreeNode> nodeList = new ArrayList<>();

    public void treeSort(ArrayList<String> arrName, ArrayList<Integer> arrAge, ArrayList<String> arrCpf, ArrayList<String> arrCountry, ArrayList<byte[]> arrImageCountry, int opc) {

        nodeList.clear();

        //=> Inserindo os elementos na arvore
        for (int i = 0; i < arrName.size(); i++) {
            TreeNode node = new TreeNode(arrName.get(i), arrAge.get(i), arrCpf.get(i), arrCountry.get(i), arrImageCountry.get(i));
            insert(nodeList, node, opc);
        }
        
    }

    //=> Metodo responsavel por retornar os arrays ordenados
    public ArrayList<Object> treeOrdered(){

        ArrayList<Object> arr = new ArrayList<>();
        ArrayList<String> arrName = new ArrayList<>();
        ArrayList<Integer> arrAge = new ArrayList<>();
        ArrayList<String> arrCpf = new ArrayList<>();
        ArrayList<String> arrCountry = new ArrayList<>();
        ArrayList<byte[]> arrImageCountry = new ArrayList<>();
        ArrayList<TreeNode> sortedList = new ArrayList<>();

        //=> Percorrendo a arvore em ordem e adicionando os elementos em uma lista
        inorderTraversal(nodeList.get(0), sortedList);

        //=> Adicionando os elementos da lista ordenada nos arrays
        for (int i = 0; i < sortedList.size(); i++) {
            arrName.add(sortedList.get(i).name);
            arrAge.add(sortedList.get(i).age);
            arrCpf.add(sortedList.get(i).cpf);
            arrCountry.add(sortedList.get(i).country);
            arrImageCountry.add(sortedList.get(i).imageCountry);
        }

        arr.add(arrName);
        arr.add(arrAge);
        arr.add(arrCpf);
        arr.add(arrCountry);
        arr.add(arrImageCountry);

        return arr;

    }

    //=> Metodo responsavel por inserir os elementos na arvore
    public static void insert(ArrayList<TreeNode> nodeList, TreeNode newNode, int opc) {
        
        //=> Se a lista estiver vazia, insiro o novo no na lista
        if (nodeList.isEmpty()) {
            nodeList.add(newNode);
            return;
        }

        //=> Pegando o primeiro elemento da lista
        TreeNode current = nodeList.get(0);

        while (true) {

            if(opc == 0){

                if (newNode.name.compareToIgnoreCase(current.name) < 0) {
                    if (current.lft == null) {
                        current.lft = newNode;
                        break;
                    }
                    current = current.lft;
                } else {
                    if (current.rgt == null) {
                        current.rgt = newNode;
                        break;
                    }
                    current = current.rgt;
                }
            } else if(opc == 1){

                if (newNode.age < current.age) {
                    if (current.lft == null) {
                        current.lft = newNode;
                        break;
                    }
                    current = current.lft;
                } else {
                    if (current.rgt == null) {
                        current.rgt = newNode;
                        break;
                    }
                    current = current.rgt;
                }

            } else if(opc == 2){

                if (newNode.cpf.compareToIgnoreCase(current.cpf) < 0) {
                    if (current.lft == null) {
                        current.lft = newNode;
                        break;
                    }
                    current = current.lft;
                } else {
                    if (current.rgt == null) {
                        current.rgt = newNode;
                        break;
                    }
                    current = current.rgt;
                }

            } else if(opc == 3){

                if (newNode.country.compareToIgnoreCase(current.country) < 0) {
                    if (current.lft == null) {
                        current.lft = newNode;
                        break;
                    }
                    current = current.lft;
                } else {
                    if (current.rgt == null) {
                        current.rgt = newNode;
                        break;
                    }
                    current = current.rgt;
                }

            } else if(opc == 4){

                if (newNode.imageCountry.length < current.imageCountry.length) {
                    if (current.lft == null) {
                        current.lft = newNode;
                        break;
                    }
                    current = current.lft;
                } else {
                    if (current.rgt == null) {
                        current.rgt = newNode;
                        break;
                    }
                    current = current.rgt;
                }

            }
            
        }
    }

    //=> Metodo responsavel por percorrer a arvore em ordem
    public static void inorderTraversal(TreeNode root, ArrayList<TreeNode> sortedList) {

        if (root != null) {
            inorderTraversal(root.lft, sortedList);
            sortedList.add(root);
            inorderTraversal(root.rgt, sortedList);
        }
    }

    //=> Metodo responsavel por buscar um elemento na arvore
    public TreeNode search(TreeNode root, String key, int opc) {

        if(opc == 0){

            if (root == null || root.name.equals(key)) {
                JOptionPane.showMessageDialog(null, "Encontrado: " + root.name);
                return root;
            }

            if (key.compareToIgnoreCase(root.name) < 0) {
                return search(root.lft, key, 0);
            }

            return search(root.rgt, key, 0);

        }else if(opc == 1){

            if (root == null || root.age == Integer.parseInt(key)) {
                JOptionPane.showMessageDialog(null, "Encontrado: " + root.age);
             return root;
            }

            if (Integer.parseInt(key) < root.age) {
                return search(root.lft, key, 1);
            }

            return search(root.rgt, key, 1);

        }else if(opc == 2){

            if (root == null || root.cpf.equals(key)) {
                JOptionPane.showMessageDialog(null, "Encontrado: " + root.cpf);
             return root;
            }

            if (key.compareToIgnoreCase(root.cpf) < 0) {
                return search(root.lft, key, 2);
            }

            return search(root.rgt, key, 2);

        }else if(opc == 3){

            if (root == null || root.country.equals(key)) {
                JOptionPane.showMessageDialog(null, "Encontrado: " + root.country);
             return root;
            }

            if (key.compareToIgnoreCase(root.country) < 0) {
                return search(root.lft, key, 3);
            }

            return search(root.rgt, key, 3);

        }else if(opc == 4){

            if (root == null || root.imageCountry.equals(key)) {
                JOptionPane.showMessageDialog(null, "Encontrado: " + root.imageCountry);
             return root;
            }

            if (key.compareToIgnoreCase(root.imageCountry.toString()) < 0) {
                return search(root.lft, key, 4);
            }

            return search(root.rgt, key, 4);

        }

        return root;

        
    }

    //=> Metodo responsavel por retornar uma unica instancia da classe TreeSort
    public static TreeSort getInstance() {
        if (instance == null) {
            instance = new TreeSort();
        }
        return instance;
    }

}
