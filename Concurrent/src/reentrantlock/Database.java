package reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

class Database {

	final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	final ReadLock readLock = lock.readLock();

	final WriteLock writeLock = lock.writeLock();

	private String result = "Inicial";

	public String getObject() {
		readLock.lock();

		try {

			System.out.println(Thread.currentThread().getName() + " reading..." + lock.getReadHoldCount());
			Thread.sleep(500);

			return result;
		} catch (final InterruptedException error) {
			// NA
			error.printStackTrace();
			return "";
		} finally {
			readLock.unlock();
		}

	}

	public String getObjectTryLock() {

		if (readLock.tryLock()) {

			try {

				System.out.println(Thread.currentThread().getName() + " reading..." + lock.getReadHoldCount());
				Thread.sleep(500);

				return result;
			} catch (final InterruptedException error) {
				// NA
				error.printStackTrace();
				return "";
			} finally {
				readLock.unlock();
			}

		} else {
			System.out.println(Thread.currentThread().getName() + " cant read..." + lock.getReadHoldCount());

			return null;
		}

	}

	public void setObject(final String value) {
		writeLock.lock();

		try {
			System.out.println(Thread.currentThread().getName() + " writing..." + lock.getQueueLength());
			Thread.sleep(50);

			result = value;
		} catch (final InterruptedException error) {
			/// NA
		} finally {
			writeLock.unlock();
		}

	}

}