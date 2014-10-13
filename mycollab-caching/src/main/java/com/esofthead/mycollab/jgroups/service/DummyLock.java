package com.esofthead.mycollab.jgroups.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class DummyLock implements Lock {

	@Override
	public void lock() {
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {

	}

	@Override
	public Condition newCondition() {
		return new Condition() {

			@Override
			public void signalAll() {
			}

			@Override
			public void signal() {
			}

			@Override
			public boolean awaitUntil(Date deadline)
					throws InterruptedException {
				return false;
			}

			@Override
			public void awaitUninterruptibly() {
			}

			@Override
			public long awaitNanos(long nanosTimeout)
					throws InterruptedException {
				return 0;
			}

			@Override
			public boolean await(long time, TimeUnit unit)
					throws InterruptedException {
				return false;
			}

			@Override
			public void await() throws InterruptedException {
			}
		};
	}

}
