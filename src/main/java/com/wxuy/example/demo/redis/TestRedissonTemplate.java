package com.wxuy.example.demo.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestRedissonTemplate {
	@Autowired
	RedissonClient redisson;

	public String TestLock() {
		// 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
		RLock lock = redisson.getLock("WuKong-lock");

		// 2.加锁
		lock.lock();
		try {
			System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
			Thread.sleep(10000);
		} catch (Exception e) {
			log.info("context:{}",e);
		} finally {
			lock.unlock();
			// 3.解锁
			System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
		}

		return "test lock ok";
	}

	/**
	 * 五、分布式读写锁
	 * 基于 Redis 的 Redisson 分布式可重入读写锁RReadWriteLock Java对象实现了java.util.concurrent.locks.ReadWriteLock接口。其中读锁和写锁都继承了 RLock接口。
	 * 写锁是一个排他锁（互斥锁），读锁是一个共享锁。
	 * 读锁 + 读锁：相当于没加锁，可以并发读。
	 * 读锁 + 写锁：写锁需要等待读锁释放锁。
	 * 写锁 + 写锁：互斥，需要等待对方的锁释放。
	 * 写锁 + 读锁：读锁需要等待写锁释放。
	 * @return
	 */
	public void ReadWriteLock() throws InterruptedException {
		RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock");
		// 最常见的使用方法
		rwlock.readLock().lock();
		// 或
		rwlock.writeLock().lock();

		//另外Redisson还通过加锁的方法提供了leaseTime的参数来指定加锁的时间。超过这个时间后锁便自动解开了。
		// 10秒钟以后自动解锁
		// 无需调用unlock方法手动解锁
		rwlock.readLock().lock(10, TimeUnit.SECONDS);
		// 或
		rwlock.writeLock().lock(10, TimeUnit.SECONDS);

		// 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
		boolean resRead = rwlock.readLock().tryLock(100, 10, TimeUnit.SECONDS);
		// 或
		boolean resWrite = rwlock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);

		rwlock.readLock().unlock();
		rwlock.writeLock().unlock();
	}

	/**
	 * Redis 客户端添加了一个 key：“park”，值等于 3，代表信号量为 park
	 * key: park type: string value: 3
	 * @return
	 * @throws InterruptedException
	 */
	public String park() throws InterruptedException {
		// 获取信号量（停车场）
		RSemaphore park = redisson.getSemaphore("park");
		// 获取一个信号（停车位）
		park.acquire();

		return "OK";
	}
	public String leave() throws InterruptedException {
		// 获取信号量（停车场）
		RSemaphore park = redisson.getSemaphore("park");
		// 释放一个信号（停车位）
		park.release();

		return "OK";
	}
}
