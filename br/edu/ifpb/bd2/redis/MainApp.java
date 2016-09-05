package edu.ifpb.bd2.redis;

import java.io.IOException;

import edu.ifpb.bd2.redis.dao.PhotoDAO;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.util.Util;

public class MainApp {
	
	public static void main(String[] args) throws IOException {

		Photo p = new Photo();
		p.setNome("vingadores.jpg");
		p.setImage(Util.imageToByte(p.getNome()));

		Photo p2 = new Photo();
		p2.setNome("greatest-superhero-gif-ever.gif");
		p2.setImage(Util.imageToByte(p2.getNome()));

		PhotoDAO dao = new PhotoDAO();

		//dao.removePhoto(p.getNome());
		dao.savePhoto(p);
		dao.savePhoto(p2);
		
		System.out.println(dao.findByPhoto(p.getNome()));
		
		Util.bytesToImage(dao.findByPhoto(p.getNome()), p.getNome());
		Util.bytesToImage(dao.findByPhoto(p2.getNome()), p2.getNome());


	}

}
