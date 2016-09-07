package edu.ifpb.bd2.redis;

import java.io.IOException;

import edu.ifpb.bd2.redis.dao.PhotoDao;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.util.Util;

public class MainSaveApp {
	public static void main(String[] args) throws IOException {

		// Cria as pastas se elas não existirem.
		Util.createFolders();

		PhotoDao photoDao = new PhotoDao();

		// salva no key comics fotos com field para busca considerando o nome.

		photoDao.save(new Photo("batman.jpg", Util.imageToByte("batman.jpg")));
		System.out.println("SALVA IMG 1");

		photoDao.save(new Photo("h.gif", Util.imageToByte("h.gif")));
		System.out.println("SALVA IMG 2");

		photoDao.save(new Photo("lg1.jpg", Util.imageToByte("lg1.jpg")));
		System.out.println("SALVA IMG 3");

		photoDao.save(new Photo("vig.jpg", Util.imageToByte("vig.jpg")));

		System.out.println("SALVA IMG 4");

		photoDao.save(new Photo("v1.jpg", Util.imageToByte("v1.jpg")));

		System.out.println("SALVA IMG 5");

		System.out.println(photoDao.listaAllKeys());

	}
}
