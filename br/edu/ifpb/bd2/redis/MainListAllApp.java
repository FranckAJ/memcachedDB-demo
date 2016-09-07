package edu.ifpb.bd2.redis;

import edu.ifpb.bd2.redis.dao.PhotoDao;
import edu.ifpb.bd2.redis.entities.Photo;

public class MainListAllApp {
	public static void main(String[] args) {
		PhotoDao photoDao = new PhotoDao();
		for(Photo p : photoDao.list())
			System.out.println("FOTO : { Nome: "+ p.getNome() +", Bytes "+ p.getImage()+"}" );
	}
}
