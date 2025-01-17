package com.shoe.restController;

import java.util.List;

import com.shoe.dao.AccountDao;
import com.shoe.dao.OrderDetailDao;
import com.shoe.dao.OrdersDao;
import com.shoe.dto.ThongKeDto;
import com.shoe.entity.ReportAccount;
import com.shoe.entity.ReportProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController()
public class DashboardRestController {
	@Autowired
    OrderDetailDao orderDetailDao;
	@Autowired
    OrdersDao ordersDao;
	@Autowired
    AccountDao accountDao;
	//1 double
	@GetMapping("/rest/dashboard/total")
	public double getTotal() {
		double a = ordersDao.getReportTotal();
		return a;
		
	}
	//count account 2 long
	@GetMapping("/rest/dashboard/totalAccount")
	public Long getTotalAccount() {
		Long a = accountDao.getTotalAccount();
		return a;
	}
	//count order 3 Long
	@GetMapping("/rest/dashboard/totalOrder")
	public Long getTotalOrder() {
		Long a = ordersDao.getTotalOrder();
		return a;
	}
	//top product
	@GetMapping("/rest/dashboard/totalProduct")
	public List<ReportProduct> getTotalProduct() {
		List<ReportProduct> a = orderDetailDao.getReportProduct();
		return a;
	}
	//top account
	@GetMapping("/rest/dashboard/totalAccountTop")
	public List<ReportAccount> getTotalAccountTop() {
		List<ReportAccount> a = ordersDao.getReportAccount();
		return a;
	}
	
	@GetMapping("/rest/dashboard/totalProdctASC")
	public List<ThongKeDto> getTotalOrderTop() {
		List<ThongKeDto> a = orderDetailDao.getProductAll();
		return a;
	}
	
	@GetMapping("/rest/dashboard/totalProductSmall")
	public List<ThongKeDto> getTotalItNhat() {
		List<ThongKeDto> a = orderDetailDao.getProductBySmall();
		return a;
	}
	
	@GetMapping("/rest/dashboard/searchDthu/{namTke}/{categoySelect}/{brandSelect}")
	public List<ThongKeDto> getDashBoard(@PathVariable("namTke") String namTke1, @PathVariable("categoySelect") String categoySelect1
			,@PathVariable("brandSelect") String brandSelect1) {
		
		List<ThongKeDto> a = null;
		String thang="";
		String nam="";
		if(!"null".equals(namTke1) && !"".equals(namTke1)) {
			String[] abc =   namTke1.split("-");
					 thang = abc[0];
					 nam = abc[1];
			}
		if(!"null".equals(namTke1) && !"null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer categoySelect = Integer.parseInt(categoySelect1);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getProductSearch(thangtke,namTke,categoySelect,brandSelect);
		}else if("null".equals(namTke1) && !"null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer categoySelect = Integer.parseInt(categoySelect1);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getProductSearch1(categoySelect,brandSelect);
		}else if(!"null".equals(namTke1) && "null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getProductSearch2(thangtke,namTke,brandSelect);
		}else if(!"null".equals(namTke1) && !"null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer categoySelect = Integer.parseInt(categoySelect1);
			a = ordersDao.getProductSearch3(thangtke,namTke,categoySelect);
		}else if("null".equals(namTke1) && "null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getProductSearch4(brandSelect);
		}else if("null".equals(namTke1) && !"null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer categoySelect = Integer.parseInt(categoySelect1);
			a = ordersDao.getProductSearch5(categoySelect);
		}else if(!"null".equals(namTke1) && "null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			a = ordersDao.getProductSearch6(thangtke,namTke);
		}else if("null".equals(namTke1) && "null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			a = orderDetailDao.getProductAll();
		}
		return a;
	}

	@GetMapping("/rest/dashboard/searchDthu/hoaDon/{namTke}/{categoySelect}/{brandSelect}")
	public Long getDashBoard1(@PathVariable("namTke") String namTke1, @PathVariable("categoySelect") String categoySelect1
			,@PathVariable("brandSelect") String brandSelect1) {
		Long a = null;
		String thang="";
		String nam="";
		if(!"null".equals(namTke1) && !"".equals(namTke1)) {
			String[] abc =   namTke1.split("-");
					 thang = abc[0];
					 nam = abc[1];
			}
		if(!"null".equals(namTke1) && !"null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer categoySelect = Integer.parseInt(categoySelect1);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCount(thangtke,namTke,categoySelect,brandSelect);
		}else if("null".equals(namTke1) && !"null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer categoySelect = Integer.parseInt(categoySelect1);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCount1(categoySelect,brandSelect);
		}else if(!"null".equals(namTke1) && "null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCount2(thangtke,namTke,brandSelect);
		}else if(!"null".equals(namTke1) && !"null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer categoySelect = Integer.parseInt(categoySelect1);
			a = ordersDao.getCount3(thangtke,namTke,categoySelect);
		}else if("null".equals(namTke1) && "null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCount4(brandSelect);
		}else if("null".equals(namTke1) && !"null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer categoySelect = Integer.parseInt(categoySelect1);
			a = ordersDao.getCount5(categoySelect);
		}else if(!"null".equals(namTke1) && "null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			a = ordersDao.getCount6(thangtke,namTke);
		}else if("null".equals(namTke1) && "null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			a = ordersDao.getTotalOrder();
		}
		return a;
	}
	
	@GetMapping("/rest/dashboard/searchDthu/account/{namTke}/{categoySelect}/{brandSelect}")
	public Long getDashBoard2(@PathVariable("namTke") String namTke1, @PathVariable("categoySelect") String categoySelect1
			,@PathVariable("brandSelect") String brandSelect1) {
		Long a = null;
		String thang="";
		String nam="";
		if(!"null".equals(namTke1) && !"".equals(namTke1)) {
			String[] abc =   namTke1.split("-");
					 thang = abc[0];
					 nam = abc[1];
			}
		if(!"null".equals(namTke1) && !"null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer categoySelect = Integer.parseInt(categoySelect1);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCountAcc(thangtke,namTke,categoySelect,brandSelect);
		}else if("null".equals(namTke1) && !"null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer categoySelect = Integer.parseInt(categoySelect1);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCountAcc1(categoySelect,brandSelect);
		}else if(!"null".equals(namTke1) && "null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCountAcc2(thangtke,namTke,brandSelect);
		}else if(!"null".equals(namTke1) && !"null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			Integer categoySelect = Integer.parseInt(categoySelect1);
			a = ordersDao.getCountAcc3(thangtke,namTke,categoySelect);
		}else if("null".equals(namTke1) && "null".equals(categoySelect1) && !"null".equals(brandSelect1)) {
			Integer brandSelect = Integer.parseInt(brandSelect1);
			a = ordersDao.getCountAcc4(brandSelect);
		}else if("null".equals(namTke1) && !"null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer categoySelect = Integer.parseInt(categoySelect1);
			a = ordersDao.getCountAcc5(categoySelect);
		}else if(!"null".equals(namTke1) && "null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			Integer thangtke = Integer.parseInt(thang);
			Integer namTke = Integer.parseInt(nam);
			a = ordersDao.getCountAcc6(thangtke,namTke);
		}else if("null".equals(namTke1) && "null".equals(categoySelect1) && "null".equals(brandSelect1)) {
			a = accountDao.getTotalAccount();
		}
		return a;
	}
}