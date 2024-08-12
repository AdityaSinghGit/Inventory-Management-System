package com.inventory.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// import org.apache.el.stream.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.inventory.inventory.Entity.BuyNowEntity;
import com.inventory.inventory.Entity.CategoryEntity;
import com.inventory.inventory.Entity.ProductEntity;
import com.inventory.inventory.Entity.PurchaseEntity;
import com.inventory.inventory.Entity.StockEntity;
import com.inventory.inventory.Repository.BuynowRepository;
import com.inventory.inventory.Repository.CategoryRepository;
import com.inventory.inventory.Repository.ProductRepository;
import com.inventory.inventory.Repository.PurchaseReposistory;
import com.inventory.inventory.Repository.StockRepository;
import com.inventory.inventory.model.BuyNow;
import com.inventory.inventory.model.Category;
import com.inventory.inventory.model.Product;
import com.inventory.inventory.model.Purchase;
import com.inventory.inventory.model.Stock;

// import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final PurchaseReposistory purchaseReposistory;

    private final StockRepository stockRepository;

    private final BuynowRepository buynowRepository;

    public InventoryServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            PurchaseReposistory purchaseReposistory, StockRepository stockRepository, BuynowRepository buynowRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.purchaseReposistory = purchaseReposistory;
        this.stockRepository = stockRepository;
        this.buynowRepository = buynowRepository;
    }

    List<Product> prods = new ArrayList<>();

    @Override
    public String createProduct(Product Prod) {
        boolean cateoryExist = categoryRepository.existsById(Prod.getCategory());
        if (!cateoryExist) {
            return "Category Not Found";
        }
        ProductEntity prodEnt = new ProductEntity();
        BeanUtils.copyProperties(Prod, prodEnt);
        productRepository.save(prodEnt);
        // prods.add(Prod);
        return "save";

    }

    @Override
    public List<Product> productList() {
        List<ProductEntity> prodList = productRepository.findAll();
        List<Product> prodLists = new ArrayList<>();
        for (ProductEntity productEntity : prodList) {
            Product prods = new Product();
            prods.setId(productEntity.getId());
            prods.setProductName(productEntity.getProductName());
            prods.setCategory(productEntity.getCategory());
            prods.setMrp(productEntity.getMrp());
            prods.setProductVarient(productEntity.getProductVarient());
            prods.setSellingPrice(productEntity.getSellingPrice());
            prods.setDescription(prods.getDescription());
            prodLists.add(prods);
        }
        return prodLists;
    }

    @Override
    public boolean deleteProduct(String id) {
        ProductEntity prod = productRepository.findById(id).get();
        productRepository.delete(prod);
        return true;

    }

    @Override
    public String createCategory(Category cat) {
        CategoryEntity catEnt = new CategoryEntity();
        BeanUtils.copyProperties(cat, catEnt);
        categoryRepository.save(catEnt);
        return "Category Save Successfully";
    }

    @Override
    public List<Category> categoryList() {
        List<CategoryEntity> catEnt = categoryRepository.findAll();

        List<Category> catList = new ArrayList<>();
        for (CategoryEntity categoryEntity : catEnt) {
            Category cat = new Category();
            cat.setId(categoryEntity.getId());
            cat.setCatName(categoryEntity.getCatName());
            cat.setUpdatedOn(categoryEntity.getUpdatedOn());
            catList.add(cat);
        }
        return catList;
    }

    @Override
    public boolean deleteCategory(String Id) {
        CategoryEntity catEnt = categoryRepository.findById(Id).get();
        categoryRepository.delete(catEnt);
        return true;
    }

    @Override
    public String purchaseProduct(Purchase pur) {
        boolean productExist = productRepository.existsById(pur.getProductId());

        if (!productExist) {
            return "not";
        }
        PurchaseEntity purEnt = new PurchaseEntity();
        BeanUtils.copyProperties(pur, purEnt);
        purchaseReposistory.save(purEnt);

        StockEntity stck = new StockEntity();
        stck.setProductId(purEnt.getProductId());
        stck.setQuantity(purEnt.getStock());
        stockRepository.save(stck);

        return "save";
    }

    @Override
    public List<Purchase> purchaseList() {
        List<PurchaseEntity> purEnt = purchaseReposistory.findAll();

        List<Purchase> purList = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : purEnt) {
            Purchase pur = new Purchase();
            pur.setId(purchaseEntity.getId());
            ProductEntity prod = productRepository.findById(purchaseEntity.getProductId()).get();
            pur.setProductId(prod.getProductName());
            pur.setMrp(purchaseEntity.getMrp());
            pur.setRate(purchaseEntity.getRate());
            pur.setStock(purchaseEntity.getStock());
            pur.setSupplierName(purchaseEntity.getSupplierName());
            pur.setSupplierMobile(purchaseEntity.getSupplierMobile());
            pur.setVarient(purchaseEntity.getVarient());
            pur.setUpdatedOn(purchaseEntity.getUpdatedOn());

            purList.add(pur);
        }
        return purList;
    }

    @Override
    public String stockEntry(Stock stk) {
        boolean productExist = productRepository.existsById(stk.getProductId());
        if (!productExist) {
            return "not";
        }

        Optional<StockEntity> existingStockOpt = stockRepository.findByProductId(stk.getProductId());

        StockEntity stkEnt;
        if (existingStockOpt.isPresent()) {
            stkEnt = existingStockOpt.get();
            int currentstock =  Integer.parseInt(stkEnt.getQuantity()) + Integer.parseInt(stk.getQuantity()); 
            stk.setQuantity(Integer.toString(currentstock));

            BeanUtils.copyProperties(stk, stkEnt, "id"); 
        } else {
            stkEnt = new StockEntity();
            BeanUtils.copyProperties(stk, stkEnt);
        }
        stockRepository.save(stkEnt);
        return "save";
    }

    @Override
    public List<Stock> stockList() {
        List<StockEntity> stkEnt = stockRepository.findAll();

        List<Stock> stkList = new ArrayList<>();
        for (StockEntity stockEntity : stkEnt) {
            Stock stk = new Stock();
            stk.setId(stockEntity.getId());
            stk.setQuantity(stockEntity.getQuantity());
            ProductEntity prod = productRepository.findById(stockEntity.getProductId()).get();
            stk.setProductId(prod.getProductName());
            stk.setUpdatedon(stockEntity.getUpdatedOn());

            stkList.add(stk);
        }
        return stkList;
    }

    @Override
    public String buyNow(BuyNow buy) {
        Optional<StockEntity> existingStockOpt = stockRepository.findByProductId(buy.getProductId());

        StockEntity stkEnt;
        if(existingStockOpt.isEmpty()){
            return "Product Not Found In Stock";
        }
        else{
            stkEnt = existingStockOpt.get();
            int stockquant = Integer.parseInt(stkEnt.getQuantity());
            if(stockquant<=0){
                return "Insufficient Stock";
            }
            int aftersell = stockquant- Integer.parseInt(buy.getQuant());
            if(aftersell<0){
                return "you have only "+(aftersell + Integer.parseInt(buy.getQuant()))+" quantity left";
            }
            stkEnt.setQuantity(Integer.toString(aftersell));
            stockRepository.save(stkEnt);

            BuyNowEntity buyEnt = new BuyNowEntity();
            BeanUtils.copyProperties(buy, buyEnt);
            buynowRepository.save(buyEnt);

            return "Product Buy Successfull";

        }
    }

    @Override
    public List<BuyNow> buynowList() {
        List<BuyNowEntity> buyEnt = buynowRepository.findAll();

        List<BuyNow> buyList = new ArrayList<>();
        for (BuyNowEntity buyNowEntity : buyEnt) {
            BuyNow buy = new BuyNow();
            buy.setId(buyNowEntity.getId());
            buy.setQuant(buyNowEntity.getQuant());
            ProductEntity prod = productRepository.findById(buyNowEntity.getProductId()).get();
            buy.setProductId(prod.getProductName());
            buy.setCustomerName(buyNowEntity.getCustomerName());
            buy.setCustomerMobile(buyNowEntity.getCustomerMobile());
            buy.setUpdatedOn(buyNowEntity.getUpdatedOn());

            buyList.add(buy);
        }
        return buyList;
    }

    

}
