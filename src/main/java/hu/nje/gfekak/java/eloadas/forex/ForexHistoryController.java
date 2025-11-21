package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickData;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.InstrumentName;
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
public class ForexHistoryController {



    private final OdanaAccountConfiguration odanaAccountConfiguration;

    public ForexHistoryController(OdanaAccountConfiguration odanaAccountConfiguration) {
        this.odanaAccountConfiguration = odanaAccountConfiguration;
    }

    @GetMapping("/forex-history")
    public String forexHistoryForm(Model model) {
        model.addAttribute("instruments", ForexConstants.INSTRUMENTS);
        model.addAttribute("granularities", ForexConstants.GRANULARITIES);
        model.addAttribute("selectedInstrument", "");
        model.addAttribute("selectedGranularity", "");
        model.addAttribute("candles", null);

        return "forex-history";
    }

    @PostMapping("/forex-history")
    public String forexHistorySubmit(@RequestParam("instrument") String instrument,
                                     @RequestParam("granularity") String granularity,
                                     Model model) throws Exception {

        Context ctx = new Context("https://api-fxpractice.oanda.com", odanaAccountConfiguration.getToken());

        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                .setCount(10L)
                .setGranularity(CandlestickGranularity.valueOf(granularity));

        InstrumentCandlesResponse response = ctx.instrument.candles(request);

        List<Candlestick> candles = response.getCandles();

        model.addAttribute("instruments", ForexConstants.INSTRUMENTS);
        model.addAttribute("granularities", ForexConstants.GRANULARITIES);
        model.addAttribute("selectedInstrument", instrument);
        model.addAttribute("selectedGranularity", granularity);
        model.addAttribute("candles", candles);

        return "forex-history";
    }
}