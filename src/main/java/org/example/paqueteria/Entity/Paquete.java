package org.example.paqueteria.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //UITLAZAR WRAPPERS PARA BUENAS PRACTICAS
    private Long id;
    private Double pesoKg ;
    private String zonaDestino = "";
    private Boolean esClienteFrecuente = true;
    private Integer distanciaKm = 0;
    private Double costoEnvio ;
    private String prioridad = "";

    public Paquete(){}
    public Paquete(Double pesoKg, String zonaDestino, Boolean esClienteFrecuente,Integer distanciaKm, Double costoEnvio, String prioridad){
        this.pesoKg = pesoKg;
        this.zonaDestino = zonaDestino;
        this.esClienteFrecuente = esClienteFrecuente;
        this.distanciaKm = distanciaKm;
        this.costoEnvio = costoEnvio;
        this.prioridad = prioridad;
    }

    //getter y setter
    //
     public Long getId() {
        return id;
     }
     public void setId(Long id){
        this.id = id;
     }

     //
     public Double getPesoKg(){
        return pesoKg;
     }
     public void setPesoKg(Double pesoKg) {
        this.pesoKg= pesoKg;
     }

     //
     public  String getZonaDestino(){
        return zonaDestino;
     }
     public void setZonaDestino(String zonaDestino){
        this.zonaDestino= zonaDestino;
     }

     //
    public Boolean getEsClienteFrecuente(){
        return esClienteFrecuente;
    }
    public void setEsClienteFrecuente(Boolean esClienteFrecuente){
        this.esClienteFrecuente= esClienteFrecuente;
    }

    //
    public Integer getDistanciaKm(){
        return distanciaKm;
    }
    public void setDistanciaKm(Integer distanciaKm){
        this.distanciaKm= distanciaKm;
    }

    //
    public Double getCostoEnvio(){
        return  costoEnvio;
    }
    public void setCostoEnvio(Double costoEnvio){
        this.costoEnvio= costoEnvio;
    }

    //
    public String getPrioridad(){
        return prioridad;
    }
    public void setPrioridad(String prioridad){
        this.prioridad= prioridad;
    }

}
