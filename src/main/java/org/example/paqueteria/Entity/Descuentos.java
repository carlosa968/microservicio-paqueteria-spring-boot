package org.example.paqueteria.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "descuentos")
public class Descuentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean esClienteFrecuente;
    private Double descuento;

    public Descuentos(){}
    public Descuentos(boolean esClienteFrecuente, Double descuento){
        this.esClienteFrecuente=esClienteFrecuente;
        this.descuento=descuento;
    }


    //Metodos getter y setter
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public boolean getEsClienteFrecuente(){
        return esClienteFrecuente;
    }
    public void setEsClienteFrecuente(boolean esClienteFrecuente){
        this.esClienteFrecuente=esClienteFrecuente;
    }

    public Double getDescuento(){
        return descuento;
    }
    public void setDescuento(Double descuento){
        this.descuento=descuento;
    }

}
