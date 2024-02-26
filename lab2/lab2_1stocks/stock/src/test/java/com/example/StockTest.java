package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;


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

    @Test
    void testGetTotalValueHamcrest(){
        Stock stock1 = new Stock("KTC", 1);
        Stock stock2 = new Stock("WDW", 2);

        portfolio.addStock(stock1);
        portfolio.addStock(stock2);
        
        when(market.lookUpPrice("KTC")).thenReturn(4.0);
        when(market.lookUpPrice("WDW")).thenReturn(8.0);

        double result = portfolio.totalValue();

        assertThat(result, is(20.0));
        verify(market, times(2)).lookUpPrice(anyString());
    }


}
    