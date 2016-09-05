package edu.ifpb.bd2.redis.entities;

import java.util.Arrays;

public class Photo {

	private long id;
	private String nome;
	private byte[] image;

	public Photo() {
	}

	public Photo(long id, String nome, byte[] image) {
		super();
		this.id = id;
		this.nome = nome;
		this.image = image;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", nome=" + nome + ", image=" + Arrays.toString(image) + "]";
	}

	

}
