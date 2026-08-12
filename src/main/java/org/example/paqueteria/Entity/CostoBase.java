package org.example.paqueteria.Entity;
import jakarta.persistence.*;
@Entity
@Table(name="costosbase")

public class CostoBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Double limiteKilos;
    private Double costoBase;
    private Double costoExtra;

    public CostoBase(){}
    public CostoBase(Double limiteKilos,Double costoBase,Double costoExtra){
        this.limiteKilos=limiteKilos;
        this.costoBase=costoBase;
        this.costoExtra=costoExtra;
    }


    //Metodos getter y setter
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Double getLimiteKilos(){
        return limiteKilos;
    }
    public void setLimiteKilos(Double limiteKilos){
        this.limiteKilos=limiteKilos;
    }
    public Double getCostoBase(){
        return costoBase;
    }
    public void setCostoBase(Double costoBase){
        this.costoBase=costoBase;
    }
    public Double getCostoExtra(){
        return costoExtra;
    }
    public void setCostoExtra(Double costoExtra){
        this.costoExtra=costoExtra;
    }

}
