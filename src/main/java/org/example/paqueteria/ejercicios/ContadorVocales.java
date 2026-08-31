package org.example.paqueteria.ejercicios;
import java.util.Scanner;
public class ContadorVocales {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int totalVocales = 0;
        System.out.println("Escribe una frase: ");
        String texto = scanner.nextLine();
        texto = texto.toLowerCase();

        for(int i = 0; i< texto.length(); i++){
            char letra= texto.charAt(i);
            if(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' ){
                totalVocales++;
            }
        }
        System.out.println("El total de vocales en la frase es: " + totalVocales);
    }
}
