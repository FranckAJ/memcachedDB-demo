package edu.ifpb.bd2.redis.factory;

import redis.clients.jedis.Jedis;

/**
 * Classe responsável por fornecer uma conexão ao banco redis
 *
 */
public class ConnectionFactoryJedis {

	private ConnectionFactoryJedis() {

	}

	private static JedisConnectionPool jedisConnectionPool;
	private static Jedis jedis;

	public static Jedis createConnectionJedis() {
		if (jedis == null) {

			if (jedisConnectionPool == null) {
				jedisConnectionPool = new JedisConnectionPool();
			}

			jedis = jedisConnectionPool.getJedisPool().getResource();
		}

		return jedis;
	}

	public static void closeConnectionJedis(Jedis jedis) {
		if (jedis != null) {
			if (jedisConnectionPool != null) {
				jedisConnectionPool.detroy(jedisConnectionPool.getJedisPool());

			}
			jedis = null;
		}
	}
}
