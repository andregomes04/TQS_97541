package com.example;

import java.util.ArrayList;
import java.util.List;

public class StockPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StockPortfolio(IStockmarketService market){
        this.stockmarket = market;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock s){
        stocks.add(s);
    }

    public double totalValue(){
        double total = 0;
        for(Stock x: stocks){
            String label = x.getLabel();
            double quantity = x.getQuantity();
            double price = stockmarket.lookUpPrice(label);
            total += (price*quantity);
        }
        return total;
    }
}
