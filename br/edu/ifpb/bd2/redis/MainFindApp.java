package edu.ifpb.bd2.redis;

import edu.ifpb.bd2.redis.dao.PhotoDao;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.util.Util;

public class MainFindApp {

	public static void main(String[] args) {

		PhotoDao photoDao = new PhotoDao();

		try {
			Photo photo1 = photoDao.find("batman.jpg");
			// coloca na pasta redisfotos a image
			Util.bytesToImage(photo1.getImage(), photo1.getNome());

			Photo photo2 = photoDao.find("h.gif");
			Util.bytesToImage(photo2.getImage(), photo2.getNome());

			Photo photo3 = photoDao.find("lg1.jpg");
			Util.bytesToImage(photo3.getImage(), photo3.getNome());

			Photo photo4 = photoDao.find("v1.jpg");
			Util.bytesToImage(photo4.getImage(), photo4.getNome());

			Photo photo5 = photoDao.find("vig.jpg");
			Util.bytesToImage(photo5.getImage(), photo5.getNome());

			/*System.out.println("Photo 1: " + photo1);
			System.out.println("Photo 2: " + photo2);
			System.out.println("Photo 3: " + photo3);
			System.out.println("Photo 4: " + photo4);
			System.out.println("Photo 5: " + photo5);*/

			System.out.println("Fotos armazenadas na pasta FOTOSREDIS");
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

}
