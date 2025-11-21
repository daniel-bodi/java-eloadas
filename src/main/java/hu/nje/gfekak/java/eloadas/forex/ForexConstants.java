package hu.nje.gfekak.java.eloadas.forex;

import java.util.List;

/**
 * @author danielbodi
 */
public class ForexConstants {

    public static final String API_URI = "https://api-fxpractice.oanda.com";
    public static final List<String> GRANULARITIES = List.of("M1", "H1", "D", "W", "M");
    public static final List<String> INSTRUMENTS = List.of("EUR_USD", "GBP_USD", "USD_JPY", "EUR_GBP");

    private ForexConstants() {}
}
