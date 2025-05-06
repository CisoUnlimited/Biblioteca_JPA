package model.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria", schema = "biblioteca")
public class Categoria {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "categoria")
    private String categoria;

    public Categoria() {
    }

    public Categoria(Integer id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}