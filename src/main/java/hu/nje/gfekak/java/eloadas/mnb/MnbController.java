package hu.nje.gfekak.java.eloadas.mnb;

import hu.nje.gfekak.java.eloadas.mnb.generated.MNBArfolyamServiceSoap;
import hu.nje.gfekak.java.eloadas.mnb.generated.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import hu.nje.gfekak.java.eloadas.mnb.generated.MNBArfolyamServiceSoapImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author danielbodi
 */
@Controller
public class MnbController {

    @GetMapping("/mnb-rates")
    public String soap1(Model model) {
        model.addAttribute("param", new MnbFormParam());
        return "mnb-form";
    }

    @PostMapping("/mnb-rates")
    public String soap2(@ModelAttribute("param") MnbFormParam param, Model model)
            throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage,
            ParserConfigurationException, IOException, SAXException {

        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();

        String response = service.getExchangeRates(param.getStartDate(), param.getEndDate(), param.getCurrency());

        List<String> dates = new ArrayList<>();
        List<String> rates = new ArrayList<>();

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8)));

        NodeList dayList = doc.getElementsByTagName("Day");
        for (int i = 0; i < dayList.getLength(); i++) {
            Element day = (Element) dayList.item(i);
            String date = day.getAttribute("date");
            Element rateElem = (Element) day.getElementsByTagName("Rate").item(0);
            String rate = rateElem.getTextContent();

            dates.add(date);
            rates.add(rate);
        }

        model.addAttribute("dates", dates);
        model.addAttribute("rates", rates);
        model.addAttribute("currency", param.getCurrency());

        return "mnb-result";
    }
}
