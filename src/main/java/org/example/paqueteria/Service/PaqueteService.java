package org.example.paqueteria.Service;

import org.example.paqueteria.Entity.Paquete;
import org.example.paqueteria.Repository.CostoBaseRepository;
import org.example.paqueteria.Repository.DescuentosRepository;
import  org.example.paqueteria.Repository.PaqueteRepository;
import org.example.paqueteria.Repository.RecargoRepository;
import  org.springframework.stereotype.Service;
import java.util.List;


@Service


/*
Microreto: Lógica de cálculo de paquetería

Desarrolla la lógica de cálculo para un microservicio de paquetería que debe evaluar las siguientes reglas antes de guardar un envío:

Peso: 5 kg = $50 y cada kg adicional suma $10.|===> se acuamula
Internacional: suma $200 al costo acumulado.| ===> se acumula
y sobre los acmulado se ahce el descuetlo si es frenucete o no se hace descuento
Cliente frecuente:tiene el  15% de descuento sobre el costo acumulado.
Prioridad: más de 500 km = "Alta(Envío Express)"; 500 o menos = "Normal".
Persistencia: al final asignas costoEnvio y prioridad y haces save().

 */

public class PaqueteService {

    //INYECCION
    private final PaqueteRepository paqueteRepository;
    private  final CostoBaseRepository costoBaseRepository;
    private final RecargoRepository recargoRepository;
    private final DescuentosRepository descuentosRepository;

    public PaqueteService(PaqueteRepository paqueteRepository, CostoBaseRepository costoBaseRepository, RecargoRepository recargoRepository, DescuentosRepository descuentosRepository){

        this.paqueteRepository =paqueteRepository;
        this.costoBaseRepository= costoBaseRepository;
        this.recargoRepository= recargoRepository;
        this.descuentosRepository=descuentosRepository;
    }
    //costo


    //metodos
    public List<Paquete> obtenerTodos() {
        return paqueteRepository.findAll();
    }

    public Paquete obtenerPorId(Long id){
        return  paqueteRepository.findById(id).orElse(null);
    }


    public void borrar (Long id ){
        paqueteRepository.deleteById(id);
    }









        ///////////////////////////////////////////////////////////////////////
    // Método guardar con toda la lógica integrada
    public Paquete guardar(Paquete paquete) {

    // 1. Traemos las reglas de Costo Base de la BD
    var costoBaseList = costoBaseRepository.findAll(); //consultmaos los costos en DB con findAll() y la guarda en la lista osea es una lista
    double costoBase = 0.0;
    double limiteKilos = 0.0;  //SONS VARIABELS TEMPROALES PARA HACER CALCULOS
    double costoExtra = 0.0;
        //SIRVE PAR QUE VERIIFEUQE QUE NO ESTE VACIA LALISTA Y NO TRUENE
    if (!costoBaseList.isEmpty()) {
            //TE REGRESA UNA LISTA LOS VA EXTRAÑENDO
        costoBase = costoBaseList.get(0).getCostoBase();
        limiteKilos = costoBaseList.get(0).getLimiteKilos();
        costoExtra = costoBaseList.get(0).getCostoExtra();
    }

    // 2. Calculamos la base según el peso del paquete
    double costoTotal = costoBase; // SE CREA LA CUENTA ACUMULADA DEL ENVIO
    //Arrancamos diciendo que el costo total de cajón es el costo base que acabamos de traer de la BD
        if (paquete.getPesoKg() > limiteKilos) { // CONDICION
        double kilosExtras = paquete.getPesoKg() - limiteKilos;
        costoTotal += kilosExtras * costoExtra;
    }

    // 3. Buscamos el recargo según la zona destino
    var recargoList = recargoRepository.findAll();//OTRA LISTA
    double montoRecargo = 0.0; // VARIABELS TEMPRALES PARA ALMACENAR
    for (var r : recargoList) {
        /*
        * Como es una lista con varias opciones, la computadora tiene que revisar cada
        * renglón de la tabla uno por uno, como si estuviera leyendo una lista de precios impresa.
        *  A cada renglón temporal le llamamos r*/
        if (r.getZona() != null && r.getZona().equalsIgnoreCase(paquete.getZonaDestino())) {
            montoRecargo = r.getMontoRecargo(); // o el campo de recargo que tengas en tu entidad
            break;
        }
    }
    costoTotal += montoRecargo;

    // 4. Aplicamos descuento si es cliente frecuente
    var descuentoList = descuentosRepository.findAll(); //OTRA LISTA
    double porcentajeDescuento = 0.0; // VARIABLE
        //Comparas si el estatus de cliente frecuente coincide
    for (var d : descuentoList) {
        if (d.getEsClienteFrecuente() == paquete.getEsClienteFrecuente()) {
            porcentajeDescuento = d.getDescuento();
            break;
        }
    }
    if (porcentajeDescuento > 0) {
        costoTotal -= (costoTotal * porcentajeDescuento);
    }

    // 5. Asignamos la prioridad según la distancia
    if (paquete.getDistanciaKm() > 500) {
        paquete.setPrioridad("Alta");
    } else {
        paquete.setPrioridad("Normal");
    }

    // 6. Guardamos el costo final calculado en el objeto paquete
    paquete.setCostoEnvio(costoTotal);

    // 7. Guardamos todo en la base de datos de manera definitiva
    return paqueteRepository.save(paquete);
    }
}
