package org.example.paqueteria.ejercicios;

public class SemaforoInteligente {
    public static  void main (String[] args){
        String luz = "JHVHV";

        switch (luz){
            case "Rojo":
                System.out.println("Debes hacer alto toal ok.");
                break;
            case "Amarillo":
                System.out.println("Debes hacer reducir la velocidad.");
                break;
            case "Verde":
                System.out.println("Puedes avanzar ok.");

                break;
            default:
                System.out.println("Error: Estado Desconocido.");
                break;

        }

    }
}
