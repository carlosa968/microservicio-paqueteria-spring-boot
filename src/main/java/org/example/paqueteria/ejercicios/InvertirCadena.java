package org.example.paqueteria.ejercicios;
import java.util.Scanner;

public class InvertirCadena {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un texto ");
        String texto= scanner.nextLine();
        String alRevez = "";

        for(int i = texto.length() - 1; i >= 0; i--){
            char letra = texto.charAt(i);
            alRevez += letra;

        }
        System.out.println(alRevez);





    }
}
