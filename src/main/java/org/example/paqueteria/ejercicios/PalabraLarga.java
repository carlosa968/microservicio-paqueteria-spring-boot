package org.example.paqueteria.ejercicios;
import java.util.Scanner;
import java.util.ArrayList;
public class PalabraLarga {
    public static  void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa una frase:  ");
        String texto= scanner.nextLine();
        String[] palabras = texto.split(" ");
        String masLarga = palabras[0];
        
        for(int i = 0; i<palabras.length;     i++){
            if (palabras[i].length() > masLarga.length()) {
                masLarga = palabras[i];
            }

        }
        System.out.println("La palabra más larga de la frase \"" + texto + "\" es: " + masLarga);


    }
}
