package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tarefa")
public class Tarefa{
    @Id
    @Column(name="id_tarefa")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="nome_projeto")
    private String nome_projeto;
    
    @Column(name="nome_tarefa")
    private String nome_tarefa;
    
    @Column(name="semanaInicio")
    private int semanaInicio;
    
    @Column(name="duracao")
    private int duracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nome_projeto;
    }

    public void setNomeProjeto(String nome) {
        this.nome_projeto = nome;
    }
    
    public String getNome() {
        return nome_tarefa;
    }

    public void setNome(String nome) {
        this.nome_tarefa = nome;
    }
    

    public int getSemanaInicio() {
        return semanaInicio;
    }

    public void setSemanaInicio(int semanaInicio) {
        this.semanaInicio = semanaInicio;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
}
