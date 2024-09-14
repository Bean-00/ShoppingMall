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
@RequestMapping("/purchase/*")
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

    @RequestMapping("/addPurchaseView")
    public ModelAndView addPurchaseView(@ModelAttribute("prodNo") String prodNo, HttpServletRequest request, Model model) {
        System.out.println("/addPurchaseView");

        Product product = productService.getProductByProdNo(Integer.parseInt(prodNo));
        User user = (User)request.getSession().getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("product", product);

        return new ModelAndView("forward:/purchase/addPurchaseView.jsp");
    }

    @RequestMapping("/addPurchase")
    public ModelAndView addPurchase(@ModelAttribute Purchase purchase, @ModelAttribute("prodNo") String prodNo, HttpServletRequest request, Model model) {
        System.out.println("/addPurchase");

        if (purchaseService.checkPurchaseLog(prodNo) != 0)
            return new ModelAndView("redirect:/product/listProduct");

        User user = (User) request.getSession().getAttribute("user");
        purchase.setDlvyDate(Date.valueOf(request.getParameter("dlvyDate")));
        purchase.setOrderDate(Date.valueOf(LocalDate.now()));
        purchase.setPurchaseProd(productService.getProductByProdNo(Integer.parseInt(prodNo)));
        purchase.setStatus(PurchaseStatus.PURCHASED.getCode());
        purchase.setBuyer(user);

        purchaseService.addPurchase(purchase);

        model.addAttribute("purchase", purchase);

        return new ModelAndView("forward:/purchase/addPurchase.jsp");
    }

    @RequestMapping("/listPurchase")
    public ModelAndView listPurchase(@ModelAttribute("search") Search search, @ModelAttribute("buyerId") String buyerId, Model model) {
        System.out.println("/listPurchase");


        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        int totalCount = purchaseService.getAllPurchaseCount(buyerId);

        List<PurchaseBuyer> purchaseBuyerList = purchaseService.getPurchaseList(search);

        model.addAttribute("pageInfo", new PageMaker(search.getCurrentPage(), totalCount, search.getPageNumSize(), search.getDisplayCount()));
        model.addAttribute("list", purchaseBuyerList);

        return new ModelAndView("forward:/purchase/listPurchase.jsp");

    }

    @RequestMapping("/updateTranCode")
    public ModelAndView updateTranCode(@ModelAttribute("prodNo") String prodNo, @ModelAttribute("page") String pageNum,
                                            @ModelAttribute("role") String role, HttpServletRequest request) {
        System.out.println("/updateTranCode");

        int page = Integer.parseInt(pageNum);

        purchaseService.updateTransCode(Integer.parseInt(prodNo));
        if (Objects.isNull(role)) {
            return new ModelAndView("redirect:/purchase/listProduct?menu=manage&page=" + page);
        }
        if (role.equals("Buyer")){
            StringBuilder path = new StringBuilder("redirect:/purchase/listPurchase?buyerId=");
            path.append(request.getParameter("buyerId"));
            path.append("&page=");
            path.append(page);
            return new ModelAndView(path.toString());
        }
        return new ModelAndView("redirect:/purchase/listProduct?menu=manage&page=" + page);
    }


}
