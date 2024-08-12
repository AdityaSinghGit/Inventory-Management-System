package com.inventory.inventory.service;

import java.util.List;

import com.inventory.inventory.model.BuyNow;
import com.inventory.inventory.model.Category;
import com.inventory.inventory.model.Product;
import com.inventory.inventory.model.Purchase;
import com.inventory.inventory.model.Stock;

public interface InventoryService {
    String createProduct(Product Prod);
    List<Product> productList();
    boolean deleteProduct(String id);


    String createCategory(Category cat);
    List<Category> categoryList();
    boolean deleteCategory(String Id);

    String purchaseProduct(Purchase pur);
    List<Purchase> purchaseList();

    String stockEntry(Stock stk);
    List<Stock> stockList();

    String buyNow(BuyNow buy);
    List<BuyNow> buynowList();
}