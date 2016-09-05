package edu.ifpb.bd2.redis.entities;

public enum Keys {

	PHOTO_ALL("photo:all"), PHOTO_DATA("photo:%s:data"), PHOTO_IDS("photo:ids");

	private String key;

	Keys(String key) {
		this.key = key;
	}

	public String key() {
		return key;
	}

	public String formated(String... value) {
		return String.format(key, value);
	}

}
