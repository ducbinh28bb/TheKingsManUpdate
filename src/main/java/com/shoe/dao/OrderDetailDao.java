package com.shoe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.dto.ThongKeDto;
import com.shoe.entity.OrderDetail;
import com.shoe.entity.ReportProduct;
import com.shoe.entity.ReportQuantityProduct;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT d FROM OrderDetail d WHERE d.order.orderId=?1")
	List<OrderDetail> findOrderDetailById(Integer id);
	
	@Query(value="select * from order_detail where order_id = ?1", nativeQuery = true)
	List<OrderDetail> getOdtByOd(Integer id);
//	@Query("SELECT name, product.price, count(order_details.quantity) from order_details join product\r\n"
//			+ "on order_details.product_id = product.product_id\r\n"
//			+ "GROUP BY name, product.price\r\n"
//			+ "ORDER BY count(order_details.quantity) ASC")
//	List<OrderDetail> report();

	@Query("SELECT new ReportProduct(o.product.productId,o.product.name,o.product.image,o.product.price,COUNT(o.product.productId) ) FROM OrderDetail o "
			+"						where o.order.sdtNn is not null and o.order.address is not null and o.order.tenNn is not null and o.order.tthaiThanhToan = 1 and o.order.status =4  "
			+ "GROUP BY o.product.productId,o.product.name,o.product.image,o.product.price,o.order.status "
			+ "ORDER BY COUNT(o.product.productId) DESC ")
	List<ReportProduct> getReportProduct();
	

	@Query("SELECT new ReportQuantityProduct(o.product.productId,o.product.name,o.product.price,SUM(o.quantity) ) FROM OrderDetail o "
			+ "GROUP BY o.product.productId,o.product.name,o.product.price " + "ORDER BY SUM(o.quantity) ASC")
	List<ReportQuantityProduct> getProductMin();
	
	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity, sum(c.total) as total)  \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+"						where b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null and b.order.tthaiThanhToan = 1 and b.order.status =4 "
			+ "						GROUP BY a.productId,a.name, d.name,e.name")
	List<ThongKeDto> getProductAll();

	@Query(value="select new ThongKeDto(a.productId as id,a.name, d.name as brand,e.name as cate, sum(b.quantity) as quantity, sum(b.price) as total) \r\n"
			+ "						from Product a join OrderDetail b on a.productId = b.product.productId\r\n"
			+ "						join Orders c on b.order.orderId = c.orderId \r\n"
			+ "						join Brand d on a.brand.brandId = d.brandId\r\n"
			+ "						join Category e on e.categoryId = a.category.categoryId\r\n"
			+ "						where b.order.status != 0 and b.order.sdtNn is not null and b.order.address is not null and b.order.tenNn is not null and b.order.tthaiThanhToan = 1 and b.order.status =4 "	
			+ "						GROUP BY a.productId,a.name, d.name,e.name"
			+ "						ORDER BY sum(b.quantity) asc")
	List<ThongKeDto> getProductBySmall();
}