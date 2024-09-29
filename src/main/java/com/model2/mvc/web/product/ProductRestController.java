package com.model2.mvc.web.product;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @Value("#{commonProperties['displayCount']}")
    int displayCount;

    @Value("#{commonProperties['pageNumSize']}")
    int pageNumSize;

    @PostMapping({ "", "/" })
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {

        productService.addProduct(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{prodNo}")
    public ResponseEntity<Product> getProduct(@PathVariable int prodNo) {

        Product product = productService.getProductByProdNo(prodNo);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping({ "", "/" })
    public ResponseEntity<Map<String, Object>> listProduct(@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage,
                                                            @RequestParam(name= "searchKeyword", required = false) String searchKeyword,
                                                            @RequestParam(name= "searchCondition", required = false) String searchCondition) {

        Search search = new Search();
        search.setSearchCondition(searchCondition);
        search.setSearchKeyword(searchKeyword);
        search.setCurrentPage(currentPage);
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        List<ProductStatus> productStatusList = productService.getProductWithStatusList(search);
        int totalCount = productService.getAllProductCount(search);

        Map<String, Object> response = new HashMap<>();

        response.put("list", productStatusList);
        response.put("totalCount", totalCount);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> listProductName(@RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        //ToDo: js 호출 시 encodeURIComponent 사용
        List<String> productNameList = productService.getProductNameList(searchKeyword);

        return ResponseEntity.ok(productNameList);
    }
}
