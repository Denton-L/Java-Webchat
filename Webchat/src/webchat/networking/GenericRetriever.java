package webchat.networking;

import java.rmi.RemoteException;

import webchat.ui.ClientUI;

/**
 * A generic class that can perform a periodic retrieval of an online resource.
 * 
 * @author Denton Liu
 * @version 2015-05-31
 */
public abstract class GenericRetriever implements Runnable {
	
	/** The time in milliseconds between successive message fetches. */
	private long period;
	/** Indicates whether the thread is running. */
	private volatile boolean threadRunning;
	
	protected ClientUI ui;
	
	/**
	 * 
	 * @param remote The {@code Remote} interface that this retriever interacts with.
	 * @param period
	 */
	protected GenericRetriever(long period, ClientUI ui) {
		this.period = period;
		this.threadRunning = true;
		this.ui = ui;
	}
	
	/**
	 * The method which will contain the retrieval logic.
	 */
	protected abstract void retrieve();
	
	/**
	 * Throwing an {@code InterruptedException} will cause the thread to refresh
	 * instantaneously.
	 */
	@Override
	public void run() {
		while (threadRunning) {
			try {
				retrieve();
				Thread.sleep(period);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}
	
	/**
	 * Halts the thread on the next loop iteration.
	 */
	public synchronized void stopThread() {
		this.threadRunning = false;
	}
}
