package server.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String texto;
	private Date data;

	public Mensagem() {
	}

	public Mensagem(String nome, String texto) {
		this(null, nome, texto, Calendar.getInstance().getTime());
	}

	public Mensagem(Long id, String nome, String texto, Date data) {
		this.id = id;
		this.nome = nome;
		this.texto = texto;
		this.data = data;
	}

	public void print() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("(" + sdf.format(getData()) + ")");
		System.out.println(getNome() + ": " + getTexto());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
