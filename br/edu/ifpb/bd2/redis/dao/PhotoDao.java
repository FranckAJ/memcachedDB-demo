package edu.ifpb.bd2.redis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.ifpb.bd2.redis.PersistenceException;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.factory.ConnectionFactoryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * Classe que fornece operações de crud de photos.
 * 
 * @author rafaelfeitosa
 *
 */
public class PhotoDao {

	/**
	 * Chave para acesso as fotos.
	 */
	private static final String KEY_PHOTOS = "comics";

	/**
	 * Método que salva uma nova foto no agregado de fotos com key_photos
	 * específicada.
	 * 
	 * @param photo
	 */
	public void save(Photo photo) {
		// cria conexão pool
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		// assiste a transação para a chave passada.
		Pipeline pipeline = jedis.pipelined();
		pipeline.watch(KEY_PHOTOS);
		pipeline.multi();
		pipeline.hset(KEY_PHOTOS.getBytes(), photo.getNome().getBytes(), photo.getImage());

		pipeline.sync();
		pipeline.exec();

		ConnectionFactoryJedis.closeConnectionJedis(jedis);

	}

	/**
	 * Método que busca uma foto passando seu nome, no agregado da chave
	 * key_photos
	 * 
	 * @param photoName
	 * @return
	 * @throws PersistenceException 
	 */
	public Photo find(String photoName) throws PersistenceException {
		
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		Pipeline pipeline = jedis.pipelined();

		Response<byte[]> photo = pipeline.hget(KEY_PHOTOS.getBytes(), photoName.getBytes());

		pipeline.sync();
		
		ConnectionFactoryJedis.closeConnectionJedis(jedis);
		
		if (photo == null) {
			throw new PersistenceException("Não existe nenhuma chave com valor informado.");
		}

		return new Photo(photoName, photo.get());
	}

	/**
	 * Método que remove um field (nome da foto) do agregado com key_photos
	 * 
	 * @param photoName
	 * @return
	 * @throws IOException
	 */
	public boolean remove(String photoName) throws IOException {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		// Pipelining é principalmente uma otimização da rede. É essencialmente
		// significa que o cliente buffers acima de um grupo de comandos e as
		// envia para o servidor de uma só vez . Os comandos não são garantidos
		// para ser executado em uma transação. A vantagem aqui é poupar rede em
		// tempo de ida e volta para cada comando .
		Pipeline pipeline = jedis.pipelined();

		pipeline.watch(KEY_PHOTOS);
		pipeline.multi();

		Response<Long> value = pipeline.hdel(KEY_PHOTOS, photoName);
		pipeline.sync();

		ConnectionFactoryJedis.closeConnectionJedis(jedis);

		pipeline.exec();
		pipeline.close();
		return value.get() > 0;

	}

	public void removeAll() {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		jedis.del(KEY_PHOTOS);
	}

	/**
	 * Método que busca todas as fotos do agregado de key_photos.
	 * 
	 * @return
	 */
	public List<Photo> list() {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		List<Photo> photos = new ArrayList<>();

		Map<String, String> retrieveMap = jedis.hgetAll(KEY_PHOTOS);
		Map<byte[], byte[]> listPhotos = jedis.hgetAll(KEY_PHOTOS.getBytes());

		for (String keyMap : retrieveMap.keySet()) {
			photos.add(new Photo(keyMap, listPhotos.get(keyMap.getBytes())));
		}
		ConnectionFactoryJedis.closeConnectionJedis(jedis);

		return photos;
	}

	/***
	 * Buscas todos a chaves existentes em comics
	 * @return
	 * @throws IOException
	 */
	public Set<String> listaAllKeys() throws IOException {
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();
		Pipeline pipeline = jedis.pipelined();

		pipeline.watch(KEY_PHOTOS);
		pipeline.multi();

		Response<Set<String>> value = pipeline.hkeys(KEY_PHOTOS);
		pipeline.sync();

		ConnectionFactoryJedis.closeConnectionJedis(jedis);

		pipeline.exec();
		pipeline.close();	
		
		return value.get();
		
	}
}
