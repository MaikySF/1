package com.proyecto.GrupoToday.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20, message = "Teléfono máximo 20")
    private String tel_cli;

   // private int depa_id;

    @ManyToOne
    @JoinColumn(name = "depa_id", updatable = false, insertable = false)
    private Departamento objDepartamento;


}
