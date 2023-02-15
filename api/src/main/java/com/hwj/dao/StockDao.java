package com.hwj.dao;

import com.hwj.pojo.Stock;

import java.util.List;

public interface StockDao {
    public List<Stock> listStock();
    public List<Stock> findStock(String num);
    public void insertStock(Stock stock);
    public void updateStock(Stock stock);
    public void deleteStock(Stock stock);
}
