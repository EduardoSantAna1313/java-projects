package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class LockedObject {

	final ReentrantLock lock = new ReentrantLock(true);

	public void doSOmethingWithTryLock() {

		if (lock.tryLock()) {

			try {
				System.out.println("Thread: " + Thread.currentThread().getName() + " acessou!");
				Thread.sleep(1000);
			} catch (final InterruptedException error) {
				error.printStackTrace();
			} finally {
				lock.unlock();
			}

		} else {
			System.out.println("Thread: " + Thread.currentThread().getName() + " fora :( " + lock.getQueueLength()
					+ " threads esperando!");
		}

	}

	public void doSOmethingWithLock() {
		lock.lock();

		try {
			System.out.println("Thread: " + Thread.currentThread().getName() + " acessou!");
			Thread.sleep(1000);
		} catch (final InterruptedException error) {
			error.printStackTrace();
		} finally {
			System.out.println("	" + lock.getQueueLength() + " threads esperando!");
			lock.unlock();
		}

	}

}