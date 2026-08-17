package org.example.paqueteria.paquete.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "recargos")
public class Recargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Double montoRecargo ;
    private String zona ;

    //Costructor vacio para que lo pueda leer Spring
    public Recargo(){}
    //Cosntructor
    public Recargo(Double montoRecargo, String zona){
        this.montoRecargo= montoRecargo;
        this.zona=zona;
    }

    //Metodos getter y setter
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Double getMontoRecargo(){
        return  montoRecargo;
    }
    public void setMontoRecargo(Double montoRecargo){
        this.montoRecargo=montoRecargo;
    }

    public String getZona(){
        return zona;
    }
    public void setZona(String zona){
        this.zona= zona;

    }

}
