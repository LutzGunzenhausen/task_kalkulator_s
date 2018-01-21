package de.lutz.task.exchange;

/**
 * General Exception to indicate, that something went wrong while accessing the
 * current exchange-rates.
 *
 * @author Christian-PC
 * 2018
 */
public class ReadExchangeRateException extends Exception {

	private static final long serialVersionUID = 2423990353034570244L;
	
	public ReadExchangeRateException(Exception cause) {
		super(cause);
	}
}
