package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.account.AccountSummaryResponse;
import hu.nje.gfekak.java.eloadas.forex.config.OdanaAccountConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author danielbodi
 */
@Controller
public class ForexController {

    private final OdanaAccountConfiguration odanaAccountConfiguration;

    public ForexController(OdanaAccountConfiguration odanaAccountConfiguration) {
        this.odanaAccountConfiguration = odanaAccountConfiguration;
    }

    @GetMapping("/forex-account")
    public String accountInfo(Model model) throws ExecuteException, RequestException {
        Context ctx = new Context("https://api-fxpractice.oanda.com", odanaAccountConfiguration.getToken());
        AccountSummaryResponse response = ctx.account.summary(new AccountID(odanaAccountConfiguration.getAccountId()));
        AccountSummary account = response.getAccount();

        model.addAttribute("account", account);

        return "forex-account";
    }
}
