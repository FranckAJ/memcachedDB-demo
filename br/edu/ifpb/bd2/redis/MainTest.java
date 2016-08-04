package edu.ifpb.bd2.redis;

import redis.clients.jedis.Jedis;

public class MainTest {

	public static void main(String[] args) {

		Jedis jedis = new Jedis("192.168.56.102", 6379);
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		
		System.out.println(value);
		

	}

}
