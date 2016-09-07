package edu.ifpb.bd2.redis;
import java.io.IOException;

import edu.ifpb.bd2.redis.dao.PhotoDao;

public class MainDeleteApp {
	public static void main(String[] args) throws IOException {
		
		PhotoDao photoDao = new PhotoDao();
		
		//remove dois itens com filds nome da foto setado;
		
		photoDao.remove("batman.jpg");
		photoDao.remove("h.gif");
		
		System.out.println("FOTOS REMOVIDAS");
	}
}
