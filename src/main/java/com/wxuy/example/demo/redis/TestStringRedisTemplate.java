package com.wxuy.example.demo.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wxuy.example.entity.TypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestStringRedisTemplate {
	@Autowired
	StringRedisTemplate redisTemplate;

	public void TestStringRedisTemplate() {
		// 初始化 redis 组件
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		// 存储数据
		ops.set("悟空", "悟空聊架构_" + UUID.randomUUID().toString());
		// 查询数据
		String wukong = ops.get("悟空");
		System.out.println(wukong);
	}
	/**
	 * 从数据库中查询到的数据先要序列化成 JSON 字符串后再存入到 Redis 中，
	 * 从 Redis 中查询数据时，也需要将 JSON 字符串反序列化为对象实例。
	 */
/*	public List<TypeEntity> getTypeEntityList() {
		// 1.初始化 redis 组件
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		// 2.从缓存中查询数据
		String typeEntityListCache = ops.get("typeEntityList");
		// 3.如果缓存中没有数据
		if (StringUtils.isEmpty(typeEntityListCache)) {
			System.out.println("The cache is empty");
			// 4.从数据库中查询数据
			List<TypeEntity> typeEntityListFromDb = this.list();
			// 5.将从数据库中查询出的数据序列化 JSON 字符串
			typeEntityListCache = JSON.toJSONString(typeEntityListFromDb);
			// 6.将序列化后的数据存入缓存中
			ops.set("typeEntityList", typeEntityListCache);
			return typeEntityListFromDb;
		}
		// 7.如果缓存中有数据，则从缓存中拿出来，并反序列化为实例对象
		List<TypeEntity> typeEntityList = JSON.parseObject(typeEntityListCache, new TypeReference<List<TypeEntity>>(){});
		return typeEntityList;
	}*/

	/**
	 * 我们需要用 synchronized 来进行加锁。当然这是本地锁的方式
	 * 对空结果进行缓存，用来解决缓存穿透问题。
	 * 设置过期时间，且加上随机值进行过期偏移，用来解决缓存雪崩问题。
	 * 加锁，解决缓存击穿问题。另外需要注意，加锁对性能会带来影响。
	 * (最终结局方案为分布式锁)
	 */
/*	public List<TypeEntity> getTypeEntityListByLock() {
		synchronized (this) {
			// 1.从缓存中查询数据
			String typeEntityListCache = stringRedisTemplate.opsForValue().get("typeEntityList");
			if (!StringUtils.isEmpty(typeEntityListCache)) {
				// 2.如果缓存中有数据，则从缓存中拿出来，并反序列化为实例对象，并返回结果
				List<TypeEntity> typeEntityList = JSON.parseObject(typeEntityListCache, new TypeReference<List<TypeEntity>>(){});
				return typeEntityList;
			}
			// 3.如果缓存中没有数据，从数据库中查询数据
			System.out.println("The cache is empty");
			List<TypeEntity> typeEntityListFromDb = this.list();
			// 4.将从数据库中查询出的数据序列化 JSON 字符串
			typeEntityListCache = JSON.toJSONString(typeEntityListFromDb);
			// 5.将序列化后的数据存入缓存中，并返回数据库查询结果
			stringRedisTemplate.opsForValue().set("typeEntityList", typeEntityListCache, 1, TimeUnit.DAYS);
			return typeEntityListFromDb;
		}
	}*/
	public List<TypeEntity> getTypeEntityListByRedisDistributedLock() throws InterruptedException {
		// 1.生成唯一 id
		String uuid = UUID.randomUUID().toString();
		// 2. 抢占锁
		Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 10, TimeUnit.SECONDS);
		if(lock) {
			System.out.println("抢占成功：" + uuid);
			// 3.抢占成功，执行业务
			List<TypeEntity> typeEntityListFromDb = getDataFromDB();

			// 4.Lua 脚本解锁 (Redis 脚本是由 Redis 内嵌的 Lua 环境执行的，所以又称作 Lua 脚本)
			String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
			redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("lock"), uuid);

			System.out.println("解锁成功：" + uuid);

			return typeEntityListFromDb;
		} else {
			System.out.println("抢占失败，等待锁释放");
			// 4.休眠一段时间
			sleep(100);
			// 5.抢占失败，等待锁释放
			return getTypeEntityListByRedisDistributedLock();
		}
	}

	private List<TypeEntity> getDataFromDB() {
		String typeEntityListCache;
		// 4.从缓存中查询数据
		typeEntityListCache = redisTemplate.opsForValue().get("typeEntityList");
		if (!StringUtils.isEmpty(typeEntityListCache)) {
			// 5.如果缓存中有数据，则从缓存中拿出来，并反序列化为实例对象，并返回结果
			List<TypeEntity> typeEntityList = JSON.parseObject(typeEntityListCache, new TypeReference<List<TypeEntity>>(){});
			return typeEntityList;
		}
		// 6.如果缓存中没有数据，从数据库中查询数据
		System.out.println("The cache is empty");
		//List<TypeEntity> typeEntityListFromDb = this.list();
		List<TypeEntity> typeEntityListFromDb = new ArrayList<TypeEntity>();
		// 7.将从数据库中查询出的数据序列化 JSON 字符串
		typeEntityListCache = JSON.toJSONString(typeEntityListFromDb);
		// 8.将序列化后的数据存入缓存中
		redisTemplate.opsForValue().set("typeEntityList", typeEntityListCache, 1, TimeUnit.DAYS);
		return typeEntityListFromDb;
	}
}
