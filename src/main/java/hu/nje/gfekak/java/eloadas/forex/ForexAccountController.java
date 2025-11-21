package hu.nje.gfekak.java.eloadas.forex;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.account.AccountSummaryResponse;
import hu.nje.gfekak.java.eloadas.forex.config.OdanaConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author danielbodi
 */
@Controller
public class ForexAccountController {

    private final OdanaConfiguration odanaConfiguration;

    public ForexAccountController(OdanaConfiguration odanaConfiguration) {
        this.odanaConfiguration = odanaConfiguration;
    }

    @GetMapping("/forex-account")
    public String accountInfo(Model model) throws ExecuteException, RequestException {
        Context ctx = new Context(odanaConfiguration.getApiUrl(), odanaConfiguration.getToken());
        AccountSummaryResponse response = ctx.account.summary(new AccountID(odanaConfiguration.getAccountId()));
        AccountSummary account = response.getAccount();

        model.addAttribute("account", account);

        return "forex-account";
    }
}
