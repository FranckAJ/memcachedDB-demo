package edu.ifpb.bd2.redis;

import edu.ifpb.bd2.redis.dao.PhotoDao;

public class MainDeleteAllApp {
	public static void main(String[] args) {
		PhotoDao photoDao = new  PhotoDao();
		photoDao.removeAll();
		System.out.println("Agregado comics apagado");
	}
}
