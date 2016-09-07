package edu.ifpb.bd2.redis.entities;

import java.util.Arrays;

public class Photo {

	private String nome;
	private byte[] image;

	public Photo() {
	}

	public Photo(String nome, byte[] image) {
		super();
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

	@Override
	public String toString() {
		return "Photo [nome=" + nome + ", image=" + Arrays.toString(image) + "]";
	}

	
}
