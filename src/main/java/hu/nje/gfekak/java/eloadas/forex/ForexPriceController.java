package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import hu.nje.gfekak.java.eloadas.forex.config.OdanaAccountConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author danielbodi
 */
@Controller
public class ForexPriceController {

    public static final List<String> INSTRUMENTS = List.of("EUR_USD", "GBP_USD", "USD_JPY", "EUR_GBP");
    private final OdanaAccountConfiguration odanaAccountConfiguration;

    public ForexPriceController(OdanaAccountConfiguration odanaAccountConfiguration) {
        this.odanaAccountConfiguration = odanaAccountConfiguration;
    }

    // GET: instrumentum kiválasztás oldala
    @GetMapping("/forex-price")
    public String forexPriceForm(Model model) {
        model.addAttribute("instruments", INSTRUMENTS);
        model.addAttribute("selectedInstrument", "");
        model.addAttribute("currentPrice", null);
        return "forex-price";
    }

    // POST: kiválasztott instrumentum ár lekérése
    @PostMapping("/forex-price")
    public String forexPriceSubmit(@RequestParam("instrument") String instrument, Model model) throws Exception {
        Context ctx = new Context("https://api-fxpractice.oanda.com", odanaAccountConfiguration.getToken());

        PricingGetRequest request = new PricingGetRequest(new AccountID(odanaAccountConfiguration.getAccountId()), List.of(instrument));
        PricingGetResponse response = ctx.pricing.get(request);
        List<ClientPrice> prices = response.getPrices();

        model.addAttribute("instruments", INSTRUMENTS);
        model.addAttribute("selectedInstrument", instrument);
        model.addAttribute("prices", prices);

        return "forex-price";
    }
}
