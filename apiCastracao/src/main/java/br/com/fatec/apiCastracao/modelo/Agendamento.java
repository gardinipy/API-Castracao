package br.com.fatec.apiCastracao.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Agendamentos")
public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000)
    private String observacoes;

    @Column
    private Date dataPrevista;

    //falta inserir os relacionamentos

    //relacionamento Agendamento e Animal
    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal;

    //relacionamento Agendamento e Responsavel
    @ManyToOne
    @JoinColumn(name = "responsavel")
    private Responsavel responsavel;

    //relacionamento Agendamento e ItensManejo
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL)
    private List<ItensManejo> itensManejos;

    //métodos acessores

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public List<ItensManejo> getItensManejos() {
        return itensManejos;
    }

    public void setItensManejos(List<ItensManejo> itensManejos) {
        this.itensManejos = itensManejos;
    }

    //equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
