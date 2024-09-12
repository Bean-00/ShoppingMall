package com.model2.mvc.web.purchase;

import com.model2.mvc.common.PageMaker;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.constant.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class PurchaseController {
    @Autowired
    @Qualifier("purchaseServiceImpl")
    PurchaseService purchaseService;

    @Autowired
    @Qualifier("productServiceImpl")
    ProductService productService;

    @Value("#{commonProperties['displayCount']}")
    //@Value("#{commonProperties['pageUnit'] ?: 3}")
    int displayCount;

    @Value("#{commonProperties['pageNumSize']}")
    //@Value("#{commonProperties['pageSize'] ?: 2}")
    int pageNumSize;

    @RequestMapping("/addPurchaseView.do")
    public ModelAndView addPurchaseView(@ModelAttribute("prodNo") String prodNo, HttpServletRequest request, Model model) {
        System.out.println("/addPurchaseView.do");

        Product product = productService.getProductByProdNo(Integer.parseInt(prodNo));
        User user = (User)request.getSession().getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("product", product);

        return new ModelAndView("forward:/purchase/addPurchaseView.jsp");
    }

    @RequestMapping("/addPurchase.do")
    public ModelAndView addPurchase(@ModelAttribute Purchase purchase, @ModelAttribute("prodNo") String prodNo, HttpServletRequest request, Model model) {
        System.out.println("/addPurchase.do");

        purchase.setDlvyDate(Date.valueOf(request.getParameter("receiverDate")));
        purchase.setOrderDate(Date.valueOf(LocalDate.now()));
        purchase.setPurchaseProd(productService.getProductByProdNo(Integer.parseInt(prodNo)));
        purchase.setStatus(PurchaseStatus.PURCHASED.getCode());

        purchaseService.addPurchase(purchase);

        model.addAttribute("purchase", purchase);

        return new ModelAndView("forward:/purchase/addPurchase.jsp");
    }

    @RequestMapping
    public ModelAndView listPurchase(@ModelAttribute("search") Search search, @ModelAttribute("buyerId") String buyerId, Model model) {
        System.out.println("/listPurchase.do");

        int currentPage = search.getCurrentPage();
        currentPage = Objects.nonNull(currentPage) && currentPage > 0? search.getCurrentPage() : 1;

        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        int totalCount = purchaseService.getAllPurchaseCount(buyerId);

        List<PurchaseBuyer> purchaseBuyerList = purchaseService.getPurchaseList(search);

        model.addAttribute("pageInfo", new PageMaker(currentPage, totalCount, pageNumSize, displayCount));
        model.addAttribute("list", purchaseBuyerList);

        return new ModelAndView("redirect:/purchase/listPurchase.jsp");

    }


}
