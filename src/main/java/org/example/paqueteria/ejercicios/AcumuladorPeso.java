package org.example.paqueteria.ejercicios;
import java.util.ArrayList;

public class AcumuladorPeso {
    public  static  void main (String[] args) {
        int [] pesos = {23,453,5346,346,235,532};
            int totalPeso=0;
             for(int i =0; i < pesos.length; i++){
                 totalPeso += pesos[i];

             }

             System.out.print("Total de pesos: "+ totalPeso);



    }

    }
