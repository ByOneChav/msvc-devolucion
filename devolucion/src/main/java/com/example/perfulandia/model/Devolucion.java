package com.example.perfulandia.model;


import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "DEVOLUCION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa la Entidad de Devolucion")
public class Devolucion {

    @Id
    @Schema(description = "Codigo de ID de Devolucion autogenerado", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idDevolucion;


    @Column(nullable = false)
    @Schema(description = "Nombre de Cliente", example = "Loyda")
    private String Usuario;

    @Column(nullable = false)
    @Schema(description = "Motivo de la devolucion", example = "No me gusto")
    private String motivo;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Fecha en la que se realizo la devolucion", example = "2025-08-06")
    private LocalDate fechaDevolucion;


    @Column(nullable = false)
    @Schema(description = "Nombre del Producto", example = "Esika")
    private String Producto;

    @Column(nullable = false)
    @Schema(description = "Estado de la devolucion", example = "Aprobada")
    private String estado;

    @Column(nullable = false)
    @Schema(description = "Monto del Reembolso", example = "76000")
    private Integer montoReembolso;

}
