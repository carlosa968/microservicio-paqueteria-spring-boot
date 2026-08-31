package org.example.paqueteria.ejercicios;

public class Mayorque {
    public static void main(String [] args ){
        int numero1 = 1;
        int numero2 = 1;

        if(numero1 > numero2){
                System.out.println("El numero 1 es : " + numero1 + "mayor que el numero 2: " + numero2);
        }else if (numero2 > numero1){
            System.out.println("El numero 2: " + numero2 + "mayor que el numero 1: " + numero1);


        }else{
            System.out.println("Ambos nuemros son iguales: " + numero1 + "==" + numero2);

        }
    }
}
