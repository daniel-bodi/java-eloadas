package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.position.Position;
import hu.nje.gfekak.java.eloadas.forex.config.OdanaConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author danielbodi
 */
@Controller
public class ForexPositionsController {

    private final OdanaConfiguration odanaConfiguration;

    public ForexPositionsController(OdanaConfiguration odanaConfiguration) {
        this.odanaConfiguration = odanaConfiguration;
    }

    @GetMapping("/forex-positions")
    public String forexPositions(Model model) throws Exception {
        Context ctx = new Context(odanaConfiguration.getApiUrl(), odanaConfiguration.getToken());

        List<Position> positions = ctx.position.list(new AccountID(odanaConfiguration.getAccountId())).getPositions();

        model.addAttribute("positions", positions);

        return "forex-positions";
    }
}