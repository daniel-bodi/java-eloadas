package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.transaction.OrderFillTransaction;
import hu.nje.gfekak.java.eloadas.forex.config.OdanaConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author danielbodi
 */
@Controller
public class ForexOpenPositionController {

    private final OdanaConfiguration odanaConfiguration;

    public ForexOpenPositionController(OdanaConfiguration odanaConfiguration) {
        this.odanaConfiguration = odanaConfiguration;
    }

    @GetMapping("/forex-open")
    public String forexOpenForm(Model model) {

        model.addAttribute("instruments", ForexConstants.INSTRUMENTS);
        model.addAttribute("selectedInstrument", "");
        model.addAttribute("amount", null);
        model.addAttribute("result", null);

        return "forex-open";
    }

    @PostMapping("/forex-open")
    public String forexOpenSubmit(@RequestParam("instrument") String instrument,
                                  @RequestParam("amount") BigDecimal amount,
                                  Model model) throws Exception {

        Context ctx = new Context(odanaConfiguration.getApiUrl(), odanaConfiguration.getToken());

        MarketOrderRequest marketOrder = new MarketOrderRequest()
                .setInstrument(instrument)
                .setUnits(amount);

        OrderCreateRequest request = new OrderCreateRequest(new AccountID(odanaConfiguration.getAccountId()))
                .setOrder(marketOrder);

        OrderCreateResponse response = ctx.order.create(request);
        OrderFillTransaction fill = response.getOrderFillTransaction();

        String resultMsg = "Opened position: " + fill.getInstrument() + " | Units: " + fill.getUnits() +
                " | Price: " + fill.getPrice();

        model.addAttribute("instruments", ForexConstants.INSTRUMENTS);
        model.addAttribute("selectedInstrument", instrument);
        model.addAttribute("amount", amount);
        model.addAttribute("result", resultMsg);

        return "forex-open";
    }
}
