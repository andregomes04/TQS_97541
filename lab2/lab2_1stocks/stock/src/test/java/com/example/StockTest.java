package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class StockTest {
    
    IStockmarketService market = mock(IStockmarketService.class);
    StockPortfolio portfolio = new StockPortfolio(market);

    /*  alt way to do what's above:

     * @Mock
     * IStockmarketService mockmarket
     * 
     * @InjectMocks
     * StocksPortfolio portfolio 
     
     */
    @Test
    public void totalValueTest(){

        portfolio.addStock(new Stock("WDC", 10));
        portfolio.addStock(new Stock("BTC", 5));
        portfolio.addStock(new Stock("BBC", 1));

        when(market.lookUpPrice("WDC")).thenReturn(5.0);
        when(market.lookUpPrice("BTC")).thenReturn(4.0);
        when(market.lookUpPrice("BBC")).thenReturn(30.0);

        double res = portfolio.totalValue();

        assertEquals(100, res);
        //verify( market, times(2)).lookUpPrice(anyString()); // pergunta ao mock se o lookUpPrice foi chamado 2 vezes

    }


}
    