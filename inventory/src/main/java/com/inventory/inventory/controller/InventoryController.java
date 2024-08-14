package com.inventory.inventory.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.inventory.model.BuyNow;
import com.inventory.inventory.model.Category;
import com.inventory.inventory.model.Product;
import com.inventory.inventory.model.Purchase;
import com.inventory.inventory.model.Stock;
import com.inventory.inventory.service.InventoryService;
// import com.inventory.inventory.service.InventoryServiceImpl;
// import com.inventory.inventory.service.InventoryServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class InventoryController {
    // calls service layer (where service is interface)

    // @Autowired
    // InventoryService inventory = new InventoryServiceImpl();

    // Dependancy Injection
    @Autowired
    InventoryService inventory;

    @GetMapping("listproduct")
    public List<Product> getMethodName(@RequestParam(defaultValue = "defaultValue") String param) {
        return inventory.productList();
    }

    @PostMapping("saveproduct")
    public String saveProduct(@RequestBody Product entity) {
        String prodEnt = inventory.createProduct(entity);
        if (prodEnt.equals("save")) {
            return "Product Save Successfull";

        } else {
            return "Category Not Found";
        }
    }

    @DeleteMapping("deleteproduct/{id}")
    public String deleteProduct(@PathVariable String id) {
        if (inventory.deleteProduct(id))
            return "Delete Successfull";
        return "Not Found";
    }

    @PostMapping("savecategory")
    public String saveCategory(@RequestBody Category category) {
        inventory.createCategory(category);
        return "Category Save Successfull";
    }

    @GetMapping("listcategory")
    public List<Category> listCategory(@RequestParam(defaultValue = "defaultvalue") String param) {
        return inventory.categoryList();
    }

    @DeleteMapping("deletecategory/{id}")
    public String deleteCategory(@PathVariable String id) {
        if (inventory.deleteCategory(id))
            return "Delete Successfull";
        return "Not Found";
    }

    @PostMapping("purchaseproduct")
    public String purchaseProduct(@RequestBody Purchase pur) {
        String purProd = inventory.purchaseProduct(pur);

        if (purProd.equals("save")) {
            return "Purchase Product Successfull";
        } else {
            return "Product Not Found";
        }
    }

    @GetMapping("purchaselist")
    public List<Purchase> purchaseList(@RequestParam(defaultValue = "defaultvalue") String param) {
        return inventory.purchaseList();
    }

    @PostMapping("stockentry")
    public String stockEntry(@RequestBody Stock stk) {
        String stockEntry = inventory.stockEntry(stk);
        if (stockEntry.equals("save")) {
            return "Stock Entry Successfull";
        }
        return "Product Not Found";
    }

    @GetMapping("stockList")
    public List<Stock> stockList(@RequestParam(defaultValue = "defaultvalue") String param) {
        return inventory.stockList();
    }

    @PostMapping("buynow")
    public String buyNow(@RequestBody BuyNow buy) {
        return inventory.buyNow(buy);
    }

    @GetMapping("buynowlist")
    public List<BuyNow> buyNowList(@RequestParam(defaultValue = "defaultvalue") String param) {
        return inventory.buynowList();
    }
}
