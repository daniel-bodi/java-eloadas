package hu.nje.gfekak.java.eloadas.mnb;

import hu.nje.gfekak.java.eloadas.mnb.generated.MNBArfolyamServiceSoap;
import hu.nje.gfekak.java.eloadas.mnb.generated.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import hu.nje.gfekak.java.eloadas.mnb.generated.MNBArfolyamServiceSoapImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author danielbodi
 */
@Controller
public class MnbThymeleafController {

    @GetMapping("/exercise")
    public String soap1(Model model) {
        model.addAttribute("param", new MessagePrice());
        return "form";
    }

    @PostMapping("/exercise")
    public String soap2(@ModelAttribute MessagePrice messagePrice, Model model) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        String strOut = "Currency:" + messagePrice.getCurrency() + ";" + "Start date:" + messagePrice.getStartDate() + ";" + "End date:" + messagePrice.getEndDate() + ";";
        strOut += service.getExchangeRates(messagePrice.getStartDate(), messagePrice.getEndDate(), messagePrice.getCurrency());
        model.addAttribute("sendOut", strOut);
        return "result";
    }
}
