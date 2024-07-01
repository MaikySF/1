package com.proyecto.GrupoToday.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_deta_boleta")
@Getter
@Setter
public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Min(value = 1)
    private int cantidad;

    @DecimalMin(value = "0.01", message = "Precio positivo")
    private Double pre_total;

    @ManyToOne
    @JoinColumn(name = "boleta_id")
    private Boleta boleta;

    @ManyToOne
    @JoinColumn(name = "zapatilla_id")
    private Zapatilla1 zapatilla;
}
