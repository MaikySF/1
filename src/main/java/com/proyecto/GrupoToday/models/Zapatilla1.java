package com.proyecto.GrupoToday.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_zapatilla")
@Getter
@Setter
public class Zapatilla1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String descripcion;
    private Double pre_zapa;
    private int stock;
    private String img_zapa;
    private String marca_id;
    private String categoria_id;
    private String admin_id;


}
