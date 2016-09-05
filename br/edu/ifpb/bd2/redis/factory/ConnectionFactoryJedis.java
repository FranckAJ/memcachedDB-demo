package edu.ifpb.bd2.redis.factory;

import redis.clients.jedis.Jedis;

/**
 * Classe responsável por fornecer uma conexão ao banco redis 
 *
 */
public class ConnectionFactoryJedis {
	
	// endereço do banco e porta sendo padrão do REDIS: 6379, //ip máquina virtual.
	private static final String redisHost = "192.168.56.102";
	private static final Integer redisPort = 6379;
	
	private ConnectionFactoryJedis() {
	}
	
	private static Jedis jedis;
	
	public static Jedis createConnectionJedis(){
		if(jedis == null){
			jedis = new Jedis(redisHost, redisPort);
		}
		
		return jedis;
	}
	
	public static void closeConnectionJedis(Jedis jedis){ 
		if(jedis != null){
			jedis.close();
			jedis = null;
		}
	}
}
