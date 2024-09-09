package com.shoe.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import com.shoe.dao.BrandDao;
import com.shoe.dao.NewsDao;
import com.shoe.dao.ProductDao;
import com.shoe.entity.Brand;
import com.shoe.entity.News;
import com.shoe.entity.Product;
import com.shoe.entity.Size;
import com.shoe.entity.Material;
import com.shoe.service.BrandService;
import com.shoe.service.NewsService;
import com.shoe.service.ProductService;
import com.shoe.service.SizeService;
import com.shoe.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired
    ProductService productService;
	@Autowired
    ProductDao pdao;

	@Autowired
    NewsDao ndao;
	@Autowired
    NewsService newsService;

	@Autowired
    BrandDao bdao;
	@Autowired
    BrandService brandService;
	
	@Autowired
	MaterialService materialSv;
	
	@Autowired
    SizeService sizeSV;
	
	@Autowired 
    HttpSession session;
	
	//home người admin
	@GetMapping({"/","/admin","/admin/kingsman"})
	public String homeAmin(Model model) {
		return "redirect:/assets/admin/main/homeAdmin.html";
	}
	
	//Home người dùng
	@GetMapping({"/kingsman","/kingsman/home"})
	public String homeClient(Model model) {
		List<Product> list = productService.findTop6Img();
		model.addAttribute("items", list);
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		
		
		List<Brand> list2 = brandService.findTop4Img();
		model.addAttribute("items2", list2);

		List<News> list3 = newsService.findAll();
		model.addAttribute("items3", list3);

		List<News> list1= newsService.findAll();
		model.addAttribute("news", list1);

		return "/layout/homeClient";	
	}
}
