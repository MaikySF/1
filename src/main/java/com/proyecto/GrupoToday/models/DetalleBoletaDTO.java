package com.proyecto.GrupoToday.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleBoletaDTO {

    private Long id;
    private  Long boletaId;

    private Long zapatillaId;
    private int cantidad;
    private Double preTotal;

}
