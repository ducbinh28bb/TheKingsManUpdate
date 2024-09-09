package com.shoe.controller;

import java.util.List;

import com.shoe.dao.OrderDetailDao;
import com.shoe.dao.OrdersDao;
import com.shoe.entity.Accounts;
import com.shoe.entity.Brand;
import com.shoe.entity.Product;
import com.shoe.entity.Size;
import com.shoe.entity.Material;
import com.shoe.entity.UserAcounts;
import com.shoe.entity.Vouchers;
import com.shoe.service.BrandService;
import com.shoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shoe.service.SizeService;
import com.shoe.service.MaterialService;
import com.shoe.service.VoucherService;

@Controller
public class CartItemController {
	@Autowired
	public VoucherService vser;
	@Autowired
    ProductService productService;
	@Autowired
    OrdersDao ordersDao;
	@Autowired
    OrderDetailDao ordersDetailService;
	 @Autowired
     UserAcounts useAcc;
		@Autowired
        BrandService brandService;
		
		@Autowired
		MaterialService materialSv;
		
		@Autowired
		SizeService sizeSV;
	//giỏ hàng
			@GetMapping("/kingsman/cartItem")
			public String gioHang(Model model) {
				Accounts account = useAcc.User();
				if(useAcc.User()==null) {
					return "redirect:/login";
				}
//				if(account.getAccountId() != null) {
//				Orders or = ordersDao.getGanNhat1(account.getAccountId());
//						if(or !=null) {
//								ordersDetailService.deleteAll(or.getOrderDetails());
//								ordersDao.delete(or);
//								System.out.println("Xóa Ok Order");
//						}
//				}
				List<Product> list = productService.findTop6Img();
				model.addAttribute("items", list);
				List<Material> materials = materialSv.findAll();
				model.addAttribute("materials", materials);
				List<Size> sizes = sizeSV.findAll();
				model.addAttribute("sizes",sizes);
				List<Brand> listBrand = brandService.findAll();
				model.addAttribute("brands", listBrand);
			List<Vouchers> voucher=vser.findAllByDate();
			List<Product> top10 = productService.top10a();
			model.addAttribute("account", account);
		    model.addAttribute("top10", top10);
			model.addAttribute("cates", voucher);
			return"/user/GioHang";
	
			}
}