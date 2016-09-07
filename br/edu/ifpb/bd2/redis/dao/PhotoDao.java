package edu.ifpb.bd2.redis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.factory.ConnectionFactoryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * Classe que fornece operações de crud de photos.
 * @author rafaelfeitosa
 *
 */
public class PhotoDao {

	/**
	 * Chave para acesso as fotos.
	 */
	private static final String KEY_PHOTOS = "comics";

	
	/**
	 * Método que salva uma nova foto no agregado de fotos com key_photos específicada.
	 * @param photo
	 */
	public void save(Photo photo) {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		jedis.hset(KEY_PHOTOS.getBytes(), photo.getNome().getBytes(), photo.getImage());
		ConnectionFactoryJedis.closeConnectionJedis(jedis);

	}

	/**
	 * Método que salva um lista de fotos no agregado com key_photo específica.
	 * @param photos
	 */
	public void savePhotoList(Map<byte[], byte[]> photos) {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		jedis.hmset(KEY_PHOTOS.getBytes(), photos);
		ConnectionFactoryJedis.closeConnectionJedis(jedis);
	}

	/**
	 * Método que busca uma foto passando seu nome, no agregado da chave key_photos
	 * @param photoName
	 * @return
	 */
	public Photo find(String photoName) {

		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		byte[] photo = jedis.hget(KEY_PHOTOS.getBytes(), photoName.getBytes());

		ConnectionFactoryJedis.closeConnectionJedis(jedis);

		return new Photo(photoName, photo);
	}

	/**
	 * Método que remove um field (nome da foto) do agregado com key_photos
	 * @param photoName
	 * @return
	 */
	public boolean remove(String photoName) {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		Pipeline pipeline = jedis.pipelined();
		Response<Long> value = pipeline.hdel(KEY_PHOTOS, photoName);
		pipeline.sync();
		ConnectionFactoryJedis.closeConnectionJedis(jedis);

		return value.get() > 0;

	}


	/**
	 * Método que busca todas as fotos do agregado de key_photos.
	 * @return
	 */
	public List<Photo> list() {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		
		List<Photo> photos = new ArrayList<>();
		
		Map<String, String> retrieveMap = jedis.hgetAll(KEY_PHOTOS);
		Map<byte[], byte[]> listPhotos = jedis.hgetAll(KEY_PHOTOS.getBytes());

		
		for (String keyMap : retrieveMap.keySet()) {
			photos.add(new Photo(keyMap , listPhotos.get(keyMap.getBytes())));
		}
		ConnectionFactoryJedis.closeConnectionJedis(jedis);

		return photos;
	}

}
