package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa{
    @Id
    @Column(name="id_pessoa")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="id_tarefa")
    private long id_tarefa;
    
    @Column(name="nome")
    private String nome;
    
    @Column(name="funcao")
    private int funcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(long id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int quantidade) {
        this.funcao = quantidade;
    }
    
    
}
