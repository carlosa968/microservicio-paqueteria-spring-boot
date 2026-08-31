package org.example.paqueteria.ejercicios;
import java.util.ArrayList;


public class EvaluadorCalificaiones {
        public static void main (String[] args){
            int [] calificaciones= {58,86,90,90,100,67,60};
            int aprobador =0;
            int reprobados = 0;
            for(int i =0; i<calificaciones.length; i++){
                if(calificaciones[i] >= 60){
                    aprobador ++;
                } else  {
                    reprobados ++;

                    
                }

            }
            System.out.println("Total aprobados: "+aprobador);
            System.out.println("Total reprobados: "+reprobados);



        }
}
