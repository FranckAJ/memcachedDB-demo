package edu.ifpb.bd2.redis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.ifpb.bd2.redis.entities.Keys;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.factory.ConnectionFactoryJedis;
import edu.ifpb.bd2.redis.util.Tool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class PhotoDao {

	public Photo savePhoto(Photo user){
		
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		long photoId = jedis.incr(Keys.PHOTO_IDS.key());
		user.setId(photoId);
		
		//Getting the Pipeline
		Pipeline pipeline = jedis.pipelined();
		//add to photos list
		pipeline.lpush(Keys.PHOTO_ALL.key(), String.valueOf(photoId));
		//add to the hash
		
		pipeline.hmset(Keys.PHOTO_DATA.formated(String.valueOf(photoId)), Tool.toMap(user));
		
		pipeline.sync();
		
		return user;
	}
	
	/**
	 * 
	 * @param photoId
	 * @return
	 */
	public Photo getPhoto(long photoId){
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		String userInfoKey = Keys.PHOTO_DATA.formated(String.valueOf(photoId));
		Map<String, String> properties = jedis.hgetAll(userInfoKey);
		return Tool.populate(properties, new Photo());
	}
	
	/**
	 * 
	 * @param photoId
	 * @return
	 */
	public boolean remove(long photoId){
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		String userInfoKey = Keys.PHOTO_DATA.formated(String.valueOf(photoId));
		Pipeline pipeline = jedis.pipelined();
		Response<Long> responseDel = pipeline.del(userInfoKey);
		Response<Long> responseLrem = pipeline.lrem(Keys.PHOTO_ALL.key(), 0, String.valueOf(photoId));
		pipeline.sync();
		return responseDel.get() > 0 && responseLrem.get() > 0;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Photo update(Photo photo){
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		String userInfoKey = Keys.PHOTO_ALL.formated(String.valueOf(photo.getId()));
		jedis.hmset(userInfoKey,Tool.toMap(photo));
		return photo;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Photo> list(){
		Jedis jedis = ConnectionFactoryJedis.createConnectionJedis();

		List<Photo> photos = new ArrayList<Photo>();
		//Get all user ids from the redis list using LRANGE
		List<String> allphotoIds = jedis.lrange(Keys.PHOTO_ALL.key(), 0, -1);
		if(allphotoIds != null && !allphotoIds.isEmpty()){
			List<Response<Map<String,String>>> responseList = new ArrayList<Response<Map<String,String>>>();
			
			Pipeline pipeline = jedis.pipelined();
			for(String photoId : allphotoIds){
				//call HGETALL for each user id
				responseList.add(pipeline.hgetAll(Keys.PHOTO_DATA.formated(photoId)));
			}
			pipeline.sync();
			//iterate over the pipelined results
			for(Response<Map<String, String>> properties : responseList){
				photos.add(Tool.populate(properties.get(), new Photo()));
			}
		}
		return photos;
}
}
