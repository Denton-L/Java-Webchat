package webchat.networking;

import webchat.ui.ClientUI;

/**
 * A generic class that can perform a periodic retrieval of an online resource.
 *
 * @author Denton Liu
 * @version 2015-05-31
 */
public abstract class GenericRetriever implements Runnable {
	
	/** The time in milliseconds between successive message fetches. */
	private final long period;
	/** Indicates whether the thread is running. */
	private volatile boolean threadRunning;
	/** The UI to be updated. */
	protected ClientUI ui;
	
	/**
	 *
	 * @param period
	 *            The length of time before sucessive refreshes.
	 * @param ui
	 *            The UI to be updated.
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
		while (this.threadRunning) {
			try {
				retrieve();
				Thread.sleep(this.period);
			} catch (final InterruptedException e) {
				Thread.interrupted();
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
