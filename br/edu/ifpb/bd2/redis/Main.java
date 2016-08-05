package edu.ifpb.bd2.redis;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {

		Photo p = new Photo();
		p.setNome("vingadores.jpg");
		p.setImage(Util.imageToByte(p.getNome()));

		PhotoDAO dao = new PhotoDAO();

		dao.removePhoto(p.getNome());
		;

		System.out.println(dao.findByPhoto(p.getNome()));

	}

}
