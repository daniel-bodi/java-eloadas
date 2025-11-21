package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeCloseResponse;
import com.oanda.v20.position.Position;
import com.oanda.v20.trade.TradeSpecifier;
import hu.nje.gfekak.java.eloadas.forex.config.OdanaConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author danielbodi
 */
@Controller
public class ForexClosePositionController {

    private final OdanaConfiguration odanaConfiguration;

    public ForexClosePositionController(OdanaConfiguration odanaConfiguration) {
        this.odanaConfiguration = odanaConfiguration;
    }

    @GetMapping("/forex-close")
    public String forexCloseForm(Model model) throws Exception {
        Context ctx = new Context(odanaConfiguration.getApiUrl(), odanaConfiguration.getToken());
        List<Position> positions = ctx.position.list(new AccountID(odanaConfiguration.getAccountId())).getPositions();

        model.addAttribute("positions", positions);
        model.addAttribute("tradeId", null);
        model.addAttribute("result", null);

        return "forex-close";
    }

    @PostMapping("/forex-close")
    public String forexCloseSubmit(@RequestParam("tradeId") long tradeId, Model model) throws Exception {
        Context ctx = new Context(odanaConfiguration.getApiUrl(), odanaConfiguration.getToken());

        TradeCloseRequest closeRequest = new TradeCloseRequest(new AccountID(odanaConfiguration.getAccountId()), new TradeSpecifier(String.valueOf(tradeId)));
        TradeCloseResponse closeResponse = ctx.trade.close(closeRequest);

        String resultMsg = "Trade " + tradeId + " closed successfully at price " + closeResponse.getOrderFillTransaction().getPrice();

        List<Position> positions = ctx.position.list(new AccountID(odanaConfiguration.getAccountId())).getPositions();

        model.addAttribute("positions", positions);
        model.addAttribute("tradeId", tradeId);
        model.addAttribute("result", resultMsg);

        return "forex-close";
    }
}
