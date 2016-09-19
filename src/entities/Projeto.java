package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="projeto")
public class Projeto {
	
	@Id
	@Column(name="nome")
	private String nome;
	
	@Column(name="duracao")
	private long duracao;
	
	
	public Projeto() {
		// TODO Auto-generated constructor stub
	}
	
	public Projeto(String nome, long dur){
		this.nome = nome;
		this.duracao = dur;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getDuracao() {
		return duracao;
	}
	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}
	
}
