package edu.ifpb.bd2.redis.factory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionPool {

	// endereço do banco e porta sendo padrão do REDIS: 6379, //ip máquina virtual.
	private static final String REDIS_HOST = "192.168.56.102";
	private static final Integer REDIS_PORT = 6379;

	private JedisPool jedisPool;

	
	public JedisPool getJedisPool() {
		jedisPool = new JedisPool(new JedisPoolConfig(), REDIS_HOST, REDIS_PORT);
		return jedisPool;
	}

	public void detroy(JedisPool jedisPool) {
		jedisPool.destroy();
	}
}


