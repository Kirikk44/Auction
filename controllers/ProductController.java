package com.example.auction.controllers;
import com.example.auction.controllers.exceptions.ProductNotExistException;
import com.example.auction.controllers.models.ProductRequest;
import com.example.auction.controllers.models.ProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/products")
public class ProductController
{

    private HashMap<Integer, ProductRequest> savedProducts = new HashMap<>();

    @GetMapping("/{id}")
    ProductResponse getProduct(@PathVariable("id") Integer productId) throws ProductNotExistException
    {
        if (!savedProducts.containsKey(productId))
        {
            throw new ProductNotExistException();
        }
        ProductResponse foundProduct = convertToResponse(savedProducts.get(productId));
        return foundProduct;
    }

    @GetMapping("")
    ArrayList<ProductResponse> getProducts()
    {
        ArrayList<ProductResponse> result = new ArrayList<>();
        for (Map.Entry<Integer, ProductRequest> entry : savedProducts.entrySet()) {
            ProductResponse productResp = convertToResponse(entry.getValue());
            result.add(productResp);
        }
        return result;
    }

    @PostMapping("")
    Integer addProduct(ProductRequest newProduct)
    {
        Integer id = savedProducts.size();
        savedProducts.put(id, newProduct);
        return id;
    }

    @DeleteMapping("/{id}")
    Integer deleteProduct(@PathVariable("id") Integer productId) throws ProductNotExistException //but its strange. How did yoi get id of not existing bet.
    {
        if(!savedProducts.containsKey(productId))
        {
            throw new ProductNotExistException();
        }
        savedProducts.remove(productId);
        return productId;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      private
    ///////////////////////////////////////////////////////////////////////////
    private ProductResponse convertToResponse(ProductRequest productReq)
    {
        ProductResponse productResp = new ProductResponse();
        productResp.setProductName(productReq.getProductName());
        return productResp;
    }
}
