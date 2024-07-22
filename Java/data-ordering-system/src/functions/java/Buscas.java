package src.functions.java;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Buscas {
    
    private String found;

    private int index;

    public void buscaLinear(ArrayList<?> arr, String value, JFrame frame){

        for (int i = 0; i < arr.size(); i++) {
            
            if(arr.get(i).toString().equals(value)){
                found = arr.get(i).toString();
                index = i;
                break;
            }
            
        }

        if(found != null){
            JOptionPane.showMessageDialog(frame, "Encontrado: " + found + " na posição: " + index);
        }else{
            JOptionPane.showMessageDialog(frame, "Valor: " + value + " não encontrado");
        }
    }   

    public void buscaBinaria(ArrayList<?> arr, String value, JFrame frame){

        int inicio = 0, fim = arr.size()-1;
        
        int meio;
        
        while (inicio <= fim) {
        
            meio = (inicio + fim) / 2;
        
            if (arr.get(meio).toString().equals(value)){
                found = arr.get(meio).toString();
                index = meio;
            }
        
            if (arr.get(meio).toString().compareToIgnoreCase(value) > 0){
            
                fim = meio - 1;

            } else {
        
                inicio = meio + 1;
            }

        }
        
        if(found != null){
            JOptionPane.showMessageDialog(frame, "Encontrado: " + found + " na posição: " + index);
        }else{
            JOptionPane.showMessageDialog(frame, "Valor: "+ value + " não encontrado!\nVerifique se a lista está ordenada, pela colula selecionada!");
        }
    }

}
