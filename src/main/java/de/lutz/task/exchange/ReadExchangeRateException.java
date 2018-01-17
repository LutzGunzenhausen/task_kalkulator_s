package de.lutz.task.exchange;

public class ReadExchangeRateException extends Exception {

	private static final long serialVersionUID = 2423990353034570244L;
	
	public ReadExchangeRateException(Exception cause) {
		super(cause);
	}
}
