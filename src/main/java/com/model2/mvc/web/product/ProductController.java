package com.model2.mvc.web.product;

import com.model2.mvc.common.PageMaker;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.SessionUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Controller
@RequestMapping("/product/*")
public class ProductController {
    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @Value("#{commonProperties['displayCount']}")
    //@Value("#{commonProperties['pageUnit'] ?: 3}")
    int displayCount;

    @Value("#{commonProperties['pageNumSize']}")
    //@Value("#{commonProperties['pageSize'] ?: 2}")
    int pageNumSize;

    @RequestMapping("/addProductView")
    public String addProductView() {
        System.out.println("/addProductView");

        return "redirect:/product/addProductView.jsp";
    }

    @RequestMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        System.out.println("/addProduct");

        productService.addProduct(product);

        return "redirect:/product/listProduct?menu=manage";
    }

    @RequestMapping("/getProduct")
    public String getProduct(@ModelAttribute("prodNo") String prodNo,  Model model, HttpServletRequest request, HttpServletResponse response) {

        String cookieValue = SessionUtil.getCookieValue(request.getCookies(), SessionUtil.HISTORY_NAME)
                .map(str -> {
                    StringJoiner sj = new StringJoiner("|");
                    sj.add(str);
                    sj.add(prodNo);
                    return sj.toString();
                })
                .orElse(prodNo);

        Cookie cookie = SessionUtil.createCookie(SessionUtil.HISTORY_NAME, cookieValue);

        Product product = productService.getProductByProdNo(Integer.parseInt(prodNo));

        response.addCookie(cookie);

        User user = (User) request.getSession().getAttribute("user");

        model.addAttribute("product", product);
        model.addAttribute("user", user);

        return "forward:/product/getProduct.jsp";
    }

    @RequestMapping("/listProduct")
    public String listProduct(@ModelAttribute("search") Search search,Model model, HttpServletRequest request) {
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        User user = (User) request.getSession().getAttribute("user");

        List<ProductStatus> productStatusList = productService.getProductWithStatusList(search);
        int totalCount = productService.getAllProductCount(search);

        PageMaker pageInfo = new PageMaker(search.getCurrentPage(), totalCount, search.getPageNumSize(), search.getDisplayCount());

        model.addAttribute("list", productStatusList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("search", search);
        model.addAttribute("user", user);

        return "forward:/product/listProduct.jsp";

    }
}
