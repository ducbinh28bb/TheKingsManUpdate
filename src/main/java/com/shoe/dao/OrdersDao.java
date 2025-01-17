package com.shoe.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.dto.ThongKeDto;
import com.shoe.entity.DashBoard;
import com.shoe.entity.Orders;
import com.shoe.entity.ReportAccount;
import com.shoe.entity.WishList;
@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer>{
	@Query("SELECT o FROM Orders o Where o.account.username=?1")
	List<Orders> findByUsername(String username);
	@Query("SELECT o FROM Orders o WHERE o.orderId LIKE ?1")
	Page<WishList> findByKeywords(String string, Pageable pageable);
	
	@Query(value = "select top(1) * from orders where account_id =?1  order by create_date desc",nativeQuery = true)
	Orders getGanNhat(Long maAcc);
	
//	@Query("SELECT SUM(o.total) FROM Orders o")
	@Query(value="select sum(total) from orders where status != 0 and sdt_nn is not null and ten_nn is not null and address is not null and tthai_thanh_toan = 1", nativeQuery = true)
	long getReportTotal();

	@Query(value="select count(order_id) from orders where sdt_nn is not null and ten_nn is not null and address is not null ",nativeQuery = true)
	Long getTotalOrder();

	@Query("SELECT new ReportAccount(o.account.accountId,o.account.fullname,o.account.image, COUNT(o.account.accountId) ) FROM Orders o "
			+ "WHERE o.tenNn is not null and o.address is not null and o.sdtNn is not null and o.status = 4 and o.tthaiThanhToan=1 "
			+ "GROUP BY o.account.accountId,o.account.fullname,o.account.image " + "ORDER BY COUNT(o.id) DESC")
	List<ReportAccount> getReportAccount();

	@Query("SELECT o FROM Orders o Where o.status is not null")
	List<Orders> findByStatusNotNull();

	@Query("SELECT new DashBoard( MONTH(o.createDate), sum(o.total) ) "
			+ "FROM Orders  o where YEAR(o.createDate) =?1 "
			+ "GROUP BY MONTH(o.createDate)")
	List<DashBoard> dashBoard(Integer date);
	@Query("SELECT o.account.email FROM Orders o where o.id=?1")
	String getEmail(Integer id);
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity, sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where MONTH(c.createDate) =?1 and  YEAR(c.createDate) = ?2 and e.categoryId = ?3 and d.brandId = ?4 \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "  					GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch(Integer thang,Integer namTke, Integer categoySelect, Integer brandSelect);
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity,  sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where e.categoryId = ?1 and d.brandId = ?2 \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch1(Integer categoySelect, Integer brandSelect);
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity,  sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where MONTH(c.createDate) =?1 and YEAR(c.createDate) = ?2  and d.brandId = ?3 \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch2(Integer thang,Integer namTke, Integer brandSelect);
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity,  sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where MONTH(c.createDate) =?1 and YEAR(c.createDate) = ?2 and e.categoryId = ?3 \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch3(Integer thang,Integer namTke, Integer categoySelect);
	
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity,  sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where  d.brandId = ?1 \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch4(Integer brandSelect);
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity,  sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where  e.categoryId = ?1  \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch5(Integer categoySelect);
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity,  sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where MONTH(c.createDate) =?1 and YEAR(c.createDate) = ?2  \r\n"
			+"						and b.order.status= 4 and b.order.tthaiThanhToan = 1 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductSearch6(Integer thang,Integer namTke);
	
	
	@Query(value = "select order_id,address,create_date,sdt_nn,ten_nn,tthai_thanh_toan,status,total,account_id,voucher_name from orders where account_id =?1 and status ='1' and voucher_name is not null order by create_date desc",nativeQuery = true)
	List<Orders> getByIdVoucher(Long accountId);
	@Query(value = "select top(1) * from orders where account_id =?1 and status ='1' order by create_date desc",nativeQuery = true)
	Orders getGanNhat1(Long accountId);

	@Query(value="select * from orders where orders.account_id = ?1 and orders.address is not null and orders.sdt_nn is not null and orders.ten_nn is not null order by orders.create_date desc",nativeQuery = true)
	List<Orders> findByUserId(Long id);
	
	@Query(value="select * from orders where orders.account_id = ?1 and orders.address is not null and orders.sdt_nn is not null and orders.ten_nn is not null order by orders.create_date desc",nativeQuery = true)
	Page<Orders> findByUserId2(Long id,Pageable pageable);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where o.ten_nn like %?1% and CONVERT(varchar, o.create_date, 101) = CONVERT(date, ?2) and o.status =?3 and o.address is not null and o.sdt_nn is not null and o.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch(String tenTk, String ngayTk, String tthaiTk);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where  CONVERT(varchar, o.create_date, 101) = CONVERT(date, ?1) and o.status =?2 and o.address is not null and o.sdt_nn is not null and o.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch1(String ngayTk, String tthaiTk);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where o.ten_nn like %?1%  and o.status =?2 and o.address is not null and o.sdt_nn is not null and o.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch2(String tenTk, String tthaiTk);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where o.ten_nn like %?1% and CONVERT(varchar, o.create_date, 101) = CONVERT(date, ?2) and orders.address is not null and orders.sdt_nn is not null and orders.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch3(String tenTk, String ngayTk);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where o.status =?1 and o.address is not null and o.sdt_nn is not null and o.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch4(String tthaiTk);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where  CONVERT(varchar, o.create_date, 101) = CONVERT(date, ?1) and o.address is not null and o.sdt_nn is not null and o.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch5(String ngayTk);
	
	@Query(value = "select o.* from orders o join accounts a on o.account_id = a.account_id\r\n"
			+ "	where o.ten_nn like %?1% and o.address is not null and o.sdt_nn is not null and o.ten_nn is not null",nativeQuery = true)
	List<Orders> getDonHangSearch6(String tenTk);
	
	@Query(value="select * from orders where and orders.address is not null and orders.sdt_nn is not null and orders.ten_nn is not null order by create_date desc", nativeQuery = true)
	List<Orders> getAll();
	
	@Query(value="SELECT * from orders where address is not null and sdt_nn is not null and ten_nn is not null order by create_date desc",nativeQuery = true)
	List<Orders> getRealOrder();
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2 and c.category_id = ?3 and b.brand_id = ?4",nativeQuery = true)
	long getCount(Integer thangtke, Integer namTke, Integer categoySelect, Integer brandSelect);
	
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and c.category_id = ?1 and b.brand_id = ?2",nativeQuery = true)
	long getCount1(Integer categoySelect, Integer brandSelect);
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2  and b.brand_id = ?3",nativeQuery = true)
	long getCount2(Integer thangtke, Integer namTke, Integer brandSelect);
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2 and c.category_id = ?3",nativeQuery = true)
	long getCount3(Integer thangtke, Integer namTke, Integer categoySelect);
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and b.brand_id = ?1",nativeQuery = true)
	long getCount4(Integer brandSelect);
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and c.category_id = ?1",nativeQuery = true)
	long getCount5(Integer categoySelect);
	
	@Query(value="select count(distinct(o.order_id)) from orders o\r\n"
			+ "join order_detail d on d.order_id = o.order_id\r\n"
			+ "join product p on p.product_id = d.product_id\r\n"
			+ "join brand b on p.brand_id = b.brand_id\r\n"
			+ "join category c on c.category_id = p.category_id\r\n"
			+ "where sdt_nn is not null and ten_nn is not null and address is not null and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2",nativeQuery = true)
	long getCount6(Integer thangtke, Integer namTke);
	
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2 and c.category_id = ?3 and b.brand_id = ?4",nativeQuery = true)
	Long getCountAcc(Integer thangtke, Integer namTke, Integer categoySelect, Integer brandSelect);
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and c.category_id = ?1 and b.brand_id = ?2",nativeQuery = true)
	Long getCountAcc1(Integer categoySelect, Integer brandSelect);
	
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2 and b.brand_id = ?3",nativeQuery = true)
	Long getCountAcc2(Integer thangtke, Integer namTke, Integer brandSelect);
	
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2 and c.category_id = ?3",nativeQuery = true)
	Long getCountAcc3(Integer thangtke, Integer namTke, Integer categoySelect);
	
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and  b.brand_id = ?1",nativeQuery = true)
	Long getCountAcc4(Integer brandSelect);
	
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and c.category_id = ?1 ",nativeQuery = true)
	Long getCountAcc5(Integer categoySelect);
	
	
	@Query(value="select count(distinct o.account_id) from orders o\r\n"
			+ "			join order_detail d on d.order_id = o.order_id\r\n"
			+ "			join product p on p.product_id = d.product_id\r\n"
			+ "			join brand b on p.brand_id = b.brand_id\r\n"
			+ "			join category c on c.category_id = p.category_id\r\n"
			+ "			where o.status ='4' and o.tthai_thanh_toan ='1' \r\n"
			+ "			and MONTH(o.create_date) =?1 and  YEAR(o.create_date) = ?2",nativeQuery = true)
	Long getCountAcc6(Integer thangtke, Integer namTke);
	
	
	
	
}