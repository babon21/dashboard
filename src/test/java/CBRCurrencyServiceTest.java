import org.apache.log4j.Logger;
import org.junit.Test;
import ru.eltex.app.dashboard.currency.CBRCurrencyService;
import ru.eltex.app.dashboard.currency.Currency;
import ru.eltex.app.dashboard.exception.ApiException;

import java.io.IOException;

import static org.junit.Assert.*;

public class CBRCurrencyServiceTest {
    Logger logger = Logger.getLogger(CBRCurrencyServiceTest.class);

    @Test
    public void getCurrency() {
        CBRCurrencyService service = new CBRCurrencyService();
        Currency currency = null;
        try {
            currency = service.getCurrency();
        } catch (ApiException | IOException e) {
            e.printStackTrace();
            logger.error("Failed!", e);
        }
        assertNotNull(currency);
    }
}