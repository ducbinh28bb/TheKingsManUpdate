package com.shoe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shoe.entity.Accounts;
import com.shoe.entity.Brand;
import com.shoe.entity.Size;
import com.shoe.entity.Material;
import com.shoe.entity.UserAcounts;
import com.shoe.service.BrandService;
import com.shoe.service.ProductService;
import com.shoe.service.SizeService;
import com.shoe.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@Autowired 
	HttpSession session;
	@Autowired
    UserAcounts useAcc;
	@Autowired
    SizeService sizeSV;
	@Autowired
	MaterialService materialSv;
	@Autowired
    BrandService brandService;
	
	@Autowired
    ProductService productSV;
	//Login
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		Accounts acount = useAcc.User();
		if(acount!=null) {
			model.addAttribute("message", "LOCK");
			request.getSession().setAttribute("message", "LOCK");
			session.removeAttribute("User");
			session.invalidate();
		}else {
			model.addAttribute("message", "");
			request.getSession().setAttribute("message", "");
		}
		
		return"/user/login/dangNhap";
	}
	//đăng ký
	@GetMapping("/registers")
	public String dangky(Model model) {
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		return"/user/login/dangKyTK";
	}
	
	@GetMapping("/forgotPassword")
	public String quenMatKhau(Model model) {
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		return"/user/quenMK";
	}
	
	@GetMapping("/logout")
	public String dangXuat() {
		session.removeAttribute("User");
		session.invalidate();
		return"redirect:/login";
	}
		
	@GetMapping("/kingsman/chinhSachBaoHanh")
	public String chinhSachBaoHanh(Model model) {
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		return "/user/csbh/csbh";
	}
	
	@GetMapping("/kingsman/chinhSachDoiTra")
	public String chinhSachDoiTra(Model model) {
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		return "/user/csbh/csdt";
	}
	
}
