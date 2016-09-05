package edu.ifpb.bd2.redis;


import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.factory.ConnectionFactoryJedis;
import redis.clients.jedis.Jedis;

public class PhotoDAO {

	public void savePhoto(Photo photo) {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		jedis.set(photo.getNome().getBytes(), photo.getImage());
		ConnectionFactoryJedis.closeConnectionJedis(jedis);
	}

	public byte[] findByPhoto(String keyPhoto) {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		byte[] img = jedis.get(keyPhoto.getBytes());
		ConnectionFactoryJedis.closeConnectionJedis(jedis);
		return img;
	}

	public void removePhoto(String keyPhoto){	
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		jedis.del(keyPhoto.getBytes());
		ConnectionFactoryJedis.closeConnectionJedis(jedis);
	}

	

}
