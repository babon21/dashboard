package ru.eltex.app.dashboard.currency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
class CBRCurrencyServiceTest {
    @Mock
    org.apache.log4j.Logger logger;
    @InjectMocks
    ru.eltex.app.dashboard.currency.CBRCurrencyService cBRCurrencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCurrency(){
        ru.eltex.app.dashboard.currency.Currency result = cBRCurrencyService.getCurrency();
        Assertions.assertEquals(new ru.eltex.app.dashboard.currency.Currency(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme