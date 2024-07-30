package com.YP.bookstore.controller;

import com.YP.bookstore.entity.Product;
import com.YP.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        try {
            List<Product> products = productService.getAllProducts();
            logger.info("Products retrieved: " + products.size());
            model.addAttribute("products", products);
            return "products";
        } catch (Exception e) {
            logger.error("Error retrieving products", e);
            model.addAttribute("errorMessage", "Error retrieving products");
            return "error";
        }
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                model.addAttribute("errorMessage", "Product not found");
                return "error";
            }
            logger.info("Product retrieved: " + product.getTitle());
            model.addAttribute("product", product);
            return "product";
        } catch (Exception e) {
            logger.error("Error retrieving product details", e);
            model.addAttribute("errorMessage", "Error retrieving product details");
            return "error";
        }
    }

    @GetMapping("/bestseller")
    public String viewBestsellerProducts(Model model) {
        try {
            List<Product> products = productService.getBestsellerProducts();
            if (products == null || products.isEmpty()) {
                model.addAttribute("errorMessage", "No bestseller products found");
                return "error";
            }
            logger.info("Bestseller products retrieved: " + products.size() + " products");
            model.addAttribute("products", products);
            return "bestseller";
        } catch (Exception e) {
            logger.error("Error retrieving bestseller products", e);
            model.addAttribute("errorMessage", "Error retrieving bestseller products");
            return "error";
        }
    }

}