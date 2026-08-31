        package org.example.paqueteria.ejercicios;
        import java.util.Scanner;
        import java.util.ArrayList;


        public class PalabrasFrase {
            public static void main(String[] args){
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingresa una frase:  ");
                String texto= scanner.nextLine();
                String[] palabras = texto.split(" ");
                String fraseInvertida = "";

                for(int i = palabras.length - 1; i >= 0; i--){
                    fraseInvertida += palabras[i] + " ";
                }
                // 4. Imprimimos el resultado final
                System.out.println("Frase original: " + texto);
                System.out.println("Frase invertida: " + fraseInvertida);


            }
        }
