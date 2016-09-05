package edu.ifpb.bd2.redis;

import java.io.IOException;

import edu.ifpb.bd2.redis.entities.Photo;

public class Main {
	
	public static void main(String[] args) throws IOException {

		Photo p = new Photo();
		p.setNome("JavaReflection.ppt");
		p.setImage(Util.imageToByte(p.getNome()));

		PhotoDAO dao = new PhotoDAO();

		//dao.removePhoto(p.getNome());
		//dao.savePhoto(p);
		
		System.out.println(dao.findByPhoto(p.getNome()));
		
		Util.bytesToImage(dao.findByPhoto(p.getNome()), p.getNome());

	}

}
