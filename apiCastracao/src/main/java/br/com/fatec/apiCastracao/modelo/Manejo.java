package br.com.fatec.apiCastracao.modelo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Manejo")
public class Manejo implements Serializable {
    private static final long serialVersion =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    //falta a lista de insumos como atributo

    //relacionamento de Manejo e ItensManejo
    @OneToMany(mappedBy = "manejo", cascade = CascadeType.ALL)
    private List<ItensManejo> itensManejos;

    //relacionamento entre Manejo e InsumosManejo
    @OneToMany(mappedBy = "manejo", cascade = CascadeType.ALL)
    private List<InsumosManejo> insumosManejos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItensManejo> getItensManejos() {
        return itensManejos;
    }

    public void setItensManejos(List<ItensManejo> itensManejos) {
        this.itensManejos = itensManejos;
    }

    public List<InsumosManejo> getInsumosManejos() {
        return insumosManejos;
    }

    public void setInsumosManejos(List<InsumosManejo> insumosManejos) {
        this.insumosManejos = insumosManejos;
    }

    //equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Manejo manejo = (Manejo) o;
        return Objects.equals(id, manejo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
