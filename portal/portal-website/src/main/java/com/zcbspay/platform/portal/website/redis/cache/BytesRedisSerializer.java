package com.zcbspay.platform.portal.website.redis.cache;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author gaoyuandong
 * @mail   466862016@qq.com
 * @date 2015年11月4日 下午2:46:03
 */
public class BytesRedisSerializer implements RedisSerializer<byte[]>{

	@Override
	public byte[] serialize(byte[] t) throws SerializationException {
		return t;
	}

	@Override
	public byte[] deserialize(byte[] bytes) throws SerializationException {
		return bytes;
	}


}
