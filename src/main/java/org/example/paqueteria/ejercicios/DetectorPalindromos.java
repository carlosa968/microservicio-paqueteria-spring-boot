package org.example.paqueteria.ejercicios;

import java.util.Scanner;

public class DetectorPalindromos {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un texto: ");
        String texto= scanner.nextLine();
        texto = texto.toLowerCase();

        String alRevez = "";

        for(int i = texto.length() - 1; i >= 0; i--){
            char letra = texto.charAt(i);
            alRevez += letra;

        }
        if(texto.equals(alRevez)){
            System.out.println("Si es palindromo la palabra " + texto + " "+ alRevez);
        }else {
            System.out.println("No es palindromo la palabra " + texto + " "+ alRevez);


        }







    }
}
