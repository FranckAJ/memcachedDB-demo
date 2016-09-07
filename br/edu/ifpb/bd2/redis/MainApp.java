package edu.ifpb.bd2.redis;
import java.io.IOException;

import edu.ifpb.bd2.redis.dao.PhotoDao;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.util.Util;

public class MainApp {
	public static void main(String[] args) throws IOException {
		
		//Cria as pastas se elas não existirem.
		Util.createFolders();
		
		
		PhotoDao photoDao = new PhotoDao();
		
		//photoDao.save(new Photo("batman.jpg", Util.imageToByte("batman.jpg")));
		//System.out.println(photoDao.find("batman.jpg"));
		
		Photo photo = photoDao.find("batman.jpg");

		photoDao.remove(photo.getNome());
		//Util.bytesToImage(photo.getImage(), photo.getNome());
	
	}
}
