package com.shoe.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shoe.dao.AccountDao;
import com.shoe.dao.OrderDetailDao;
import com.shoe.dao.OrdersDao;
import com.shoe.dao.ProductDao;
import com.shoe.dao.VoucherDao;
import com.shoe.entity.Accounts;
import com.shoe.entity.Brand;
import com.shoe.entity.OrderDetail;
import com.shoe.entity.Orders;
import com.shoe.entity.Product;
import com.shoe.entity.Size;
import com.shoe.entity.Material;
import com.shoe.entity.UserAcounts;
import com.shoe.entity.Vouchers;
import com.shoe.entity.WishList;
import com.shoe.service.AccountService;
import com.shoe.service.BrandService;
import com.shoe.service.OrdersService;
import com.shoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoe.service.SizeService;
import com.shoe.service.MaterialService;
import com.shoe.service.WishListService;


@Controller
public class AccountController {
	@Autowired
    OrdersService orderService;
	@Autowired
	WishListService wishListService;
	@Autowired
    AccountService accountService;
	@Autowired 
	HttpSession session;
	@Autowired
    OrdersDao orderDao;
	@Autowired
    UserAcounts useAcc;
	@Autowired
    ProductDao productDao;
	@Autowired
    VoucherDao voucherDao;
	@Autowired
    AccountDao accountDao;
	
	@Autowired
    OrderDetailDao detailDao;
	@Autowired
	SizeService sizeSV;
	@Autowired
	MaterialService materialSv;
	@Autowired
    BrandService brandService;
	
	@Autowired
    ProductService productSV;
	// Get Cap nhat tai khoan
	@GetMapping("/kingsman/account/view")
	public String capNhatTaiKhoan(Model model, HttpServletRequest request) {
		
		if(useAcc.User()==null) {
			return "redirect:/login";
		}
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		Accounts account = useAcc.User();
		Long id = account.getAccountId();
		Accounts account1= accountDao.getById(id);
		Date ngaySinh = account1.getBirthdate();
		String dateNgaySinh="";
		
		if(ngaySinh!=null) {
			DateFormat  dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
			dateNgaySinh  = dateFormat.format(ngaySinh);
		}
		if(!"".equals(account1.getImage()) && account1.getImage() != null) {
			model.addAttribute("ckimage",true);
		}
		model.addAttribute("dateNgaySinh",dateNgaySinh);
		model.addAttribute("account",account1);
		session.setAttribute("accountSession",account1);
		return "/user/account/capNhatTK";
	}

	// Get lichSuMuahang
	@GetMapping("/kingsman/account/history")
	public String lichSuMuahang(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p) {
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		Accounts account = useAcc.User();
		if(useAcc.User()==null) {
			return "redirect:/login";
		}
		Long id = account.getAccountId();
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Orders> ord = orderService.getOrderByUserId(id, pageable);
		model.addAttribute("number",ord.getNumber());
		model.addAttribute("totalPages",ord.getTotalPages());
		model.addAttribute("totalElements",ord.getTotalElements());
		model.addAttribute("size",ord.getSize());
		
		model.addAttribute("orders", ord);
		return "/user/account/lichSuMuaHang";
	}
	
	//huy don hang
	@GetMapping("/kingsman/account/history/cancel/{orderId}")
	public String huyDon(Model model, HttpServletRequest request,@PathVariable("orderId") Integer orderId, @RequestParam("p") Optional<Integer> p) {
		try {
			List<Material> materials = materialSv.findAll();
			model.addAttribute("materials", materials);
			List<Size> sizes = sizeSV.findAll();
			model.addAttribute("sizes",sizes);
			
			List<Brand> listBrand = brandService.findAll();
			model.addAttribute("brands", listBrand);
			Accounts account = useAcc.User();
			if(useAcc.User()==null) {
				return "redirect:/login";
			}
			Long id = account.getAccountId();
//			model.addAttribute("orders", orderService.findByUserId(id));
			
			Orders order = orderService.getById(orderId);
			Pageable pageable = PageRequest.of(p.orElse(0), 7);
			Page<Orders> ord = orderService.getOrderByUserId(id, pageable);
			model.addAttribute("number",ord.getNumber());
			model.addAttribute("totalPages",ord.getTotalPages());
			model.addAttribute("totalElements",ord.getTotalElements());
			model.addAttribute("size",ord.getSize());
			
			model.addAttribute("orders", ord);
			
			if(order.getStatus() == 1) {
				order.setStatus(0);
				if(order.getTthaiThanhToan() == 1) {
					order.setTthaiThanhToan(2);
				}
				List<OrderDetail> odt = detailDao.getOdtByOd(orderId);
//				List<Product> listPro = productDao.getProductByOrders(orderId);
				
				for(OrderDetail o : odt) {
					Product pro = productDao.getProductByOrderDetail(o.getOrderDetailId());
					pro.setQuantity(pro.getQuantity() + o.getQuantity());
					productSV.save(pro);
				}
				//back lại số lượng sp
				// back lại sl voucher
				
				if(null != order.getVoucher()) {
					Vouchers voucher = voucherDao.getVoucherWithOrder(order.getVoucher().getVoucherName());
					voucher.setQuantity(voucher.getQuantity() + 1);
					voucherDao.save(voucher);
				}
			}
			
			orderService.save(order);
			System.out.println("Đã hủy đơn: "+orderId);
			model.addAttribute("message", "Hủy đơn thành công");
			return "/user/account/lichSuMuaHang";
		} catch (Exception e) {
			// TODO: handle
			e.printStackTrace();
			model.addAttribute("message", "Có lỗi xảy ra trong quá trình hủy!");
			return "/user/account/lichSuMuaHang";
		}
	}
	
	@RequestMapping("/kingsman/account/history/search")
	public String Search(Model model, @RequestParam("keyword") String kw, @RequestParam("page") Optional<Integer> p) {
		List<Material> materials = materialSv.findAll();
		model.addAttribute("materials", materials);
		List<Size> sizes = sizeSV.findAll();
		model.addAttribute("sizes",sizes);
		
		List<Brand> listBrand = brandService.findAll();
		model.addAttribute("brands", listBrand);
		try {
			session.setAttribute("keywords", kw);
			Pageable pageable = PageRequest.of(p.orElse(0), 12);
			Page<WishList> page = orderDao.findByKeywords("%" + kw + "%", pageable);
			model.addAttribute("items", page);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "product/list";
	}	
		
		@GetMapping("/kingsman/account/favorite")
		public String sanPhamYeuThich(Model model, HttpServletRequest request) {
			List<Material> materials = materialSv.findAll();
			model.addAttribute("materials", materials);
			List<Size> sizes = sizeSV.findAll();
			model.addAttribute("sizes",sizes);
			
			List<Brand> listBrand = brandService.findAll();
			model.addAttribute("brands", listBrand);
			if(useAcc.User()==null) {
				return "redirect:/login";
			}
			Accounts account = useAcc.User();
			Long id = account.getAccountId();
			model.addAttribute("wishLists", wishListService.findByUserId(id));
			return "/user/account/sanPhamYeuThich";
		}

		// Get doiMatKhau
		@GetMapping("/kingsman/account/changePassword")
		public String doiMatKhau(Model model) {
			if(useAcc.User()==null) {
				return "redirect:/login";
			}
			List<Material> materials = materialSv.findAll();
			model.addAttribute("materials", materials);
			List<Size> sizes = sizeSV.findAll();
			model.addAttribute("sizes",sizes);
			
			List<Brand> listBrand = brandService.findAll();
			model.addAttribute("brands", listBrand);
			return "/user/account/doiMatKhau";
		}
				
		@GetMapping("/kingsman/account/like/{id}")
		public String likeOrUnlike(@PathVariable("id") Integer id,Model model) {
			List<Material> materials = materialSv.findAll();
			model.addAttribute("materials", materials);
			List<Size> sizes = sizeSV.findAll();
			model.addAttribute("sizes",sizes);
			
			List<Brand> listBrand = brandService.findAll();
			model.addAttribute("brands", listBrand);
			Accounts account = useAcc.User();
			if(useAcc.User()==null) {
				return "redirect:/login";
			}
			
			Long idac = account.getAccountId();
			WishList wl = wishListService.findBy(id, idac);
			wishListService.delete(wl);
			return "redirect:/kingsman/account/favorite";
		}
		
		@PostMapping("/kingsman/account/update")
		public String capNhatTaiKhoan2(@ModelAttribute("account") Accounts account
				, @RequestParam("image1") String image1, @RequestParam("image2") String image2
				, @RequestParam("bithdate") String bithdate,Model model) throws ParseException {
			if(useAcc.User()==null) {
				return "redirect:/login";
			}
			List<Material> materials = materialSv.findAll();
			model.addAttribute("materials", materials);
			List<Size> sizes = sizeSV.findAll();
			model.addAttribute("sizes",sizes);
			
			List<Brand> listBrand = brandService.findAll();
			model.addAttribute("brands", listBrand);
			
			String amage = account.getImage();
			if(!"".equals(amage) && amage != null) {
				amage = amage.replaceAll(",", "");
			}
			 if( !"".equals(image1)) {
				account.setImage(image1);
			}else {
				account.setImage(image2);
			}
			try {
				
				Accounts account1 = useAcc.User();	
				Long id = account1.getAccountId();
				Accounts account2= accountDao.getById(id);
				account.setCreate_date(account2.getCreate_date());
				account.setStatus(account2.isStatus());
				account.setRoles(account2.getRoles());
				account.setAdminAstrator(account2.getAdminAstrator());
			
			DateFormat  dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
			Date dateNgaySinh  = dateFormat.parse(bithdate);
			account.setBirthdate(dateNgaySinh);
			
			accountService.save(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/kingsman/account/view";
		}
		
		@GetMapping("/kingsman/account/history/detail/{orderId}")
		public String chiTietDonHang(Model model, @PathVariable("orderId") Integer orderId,HttpServletRequest request) {
			List<Material> materials = materialSv.findAll();
			model.addAttribute("materials", materials);
			List<Size> sizes = sizeSV.findAll();
			model.addAttribute("sizes",sizes);
			
			List<Brand> listBrand = brandService.findAll();
			model.addAttribute("brands", listBrand);
			//Accounts account = useAcc.User();
			if(useAcc.User()==null) {
				return "redirect:/login";
			}
			
			List<OrderDetail> detail = detailDao.findOrderDetailById(orderId);
			model.addAttribute("details", detail);
			System.out.println(detail);
			return "/user/account/orderDetail";
		}

}