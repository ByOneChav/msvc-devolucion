package com.example.perfulandia;


import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.perfulandia.model.Devolucion;
import com.example.perfulandia.service.DevolucionService;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    private final Faker faker = new Faker(new Locale("es", "cl"));
    private final Random random = new Random();

    @Autowired
    private DevolucionService devolucionService;

    @Override
    public void run(String... args) throws Exception{
        for(int i=0; i<15; i++){
            Devolucion devolucionFicticio = new Devolucion();
            devolucionFicticio.setIdDevolucion(generarIdDevolucion());
            devolucionFicticio.setUsuario(faker.name().firstName());
            devolucionFicticio.setMotivo(generarMotivoDevolucion());
            devolucionFicticio.setFechaDevolucion(LocalDate.now().minusDays(random.nextInt(60)));
            devolucionFicticio.setProducto(faker.commerce().productName());
            devolucionFicticio.setEstado(generarEstadoDevolucion());
            devolucionFicticio.setMontoReembolso(generarMontoReembolso());

            devolucionService.guardar(devolucionFicticio);
            System.out.println("Devolucion Registrado: "+devolucionFicticio.getIdDevolucion());
        }


    }

    private static long idDevolucionActual = 1000; // valor inicial

    private Long generarIdDevolucion() {
        idDevolucionActual += 10;
        return idDevolucionActual;
    }

    // public String generarMetodoPago() {
    //     String[] metodos = { "VISA", "MasterCard", "Débito", "Crédito", "Transferencia", "PayPal" };
    //     return faker.options().option(metodos);
    // }

    public String generarEstadoPago() {
        String[] estados = { "Exitoso", "En proceso", "Fallido", "Rechazado", "Pendiente" };
        return faker.options().option(estados);
    }

    private Integer generarMontoReembolso() {
        int[] montos = { 4990, 5990, 7990, 9990, 11990, 14990, 19990, 24990, 29990, 34990 };
        return montos[new Random().nextInt(montos.length)];
    }

    private String generarEstadoDevolucion() {
        String[] estados = { "Pendiente", "Aprobada", "Rechazada", "En proceso", "Finalizada" };
        return estados[new Random().nextInt(estados.length)];
    }

    private String generarMotivoDevolucion() {
        String[] motivos = {
                "Producto defectuoso",
                "No era lo que esperaba",
                "Error en el pedido",
                "El aroma no me gustó",
                "Recibí el producto equivocado",
                "Tamaño incorrecto",
                "Dañado en el transporte",
                "Producto vencido",
                "No corresponde con la descripción",
                "El empaque llegó abierto",
                "Ya no lo necesito",
                "Retraso en la entrega",
                "No cumplió con la calidad esperada",
                "Problemas con la aplicación del producto",
                "Comprado por error"
        };
        return motivos[new Random().nextInt(motivos.length)];
    }

}
