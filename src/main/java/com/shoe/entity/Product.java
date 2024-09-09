package com.shoe.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@Column(columnDefinition = "NVARCHAR(30)  NULL")
	private String name;
	@Column(columnDefinition = "NVARCHAR(4000) NULL")
	private String description;
	
	private Date updateDate;
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "brandId")
	private Brand brand;
	

	private int quantity;
	
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;
	
	@ManyToOne
	@JoinColumn(name = "toe_id")
	private Toe toe;
	
	@ManyToOne
	@JoinColumn(name = "design_id")
	private Design design;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "height_id")
	private Height height;
	
	@Column(columnDefinition = "NVARCHAR(255)  NULL")
	private String origin;
	
	@Column(columnDefinition = "NVARCHAR(100)  NULL")
	private String image;
	
	private boolean status;
	

	private boolean gender;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ImageProduct> images;

	
	public Product(int productId, String name, String description, Date updateDate, Date createDate, Category category,
				   Brand brand, int quantity, double price, Size size, Toe toe, Design design,
				   Material material, Height height, String origin, String image, boolean status,
				   boolean gender, List<ImageProduct> images) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.updateDate = updateDate;
		this.createDate = createDate;
		this.category = category;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
		this.toe = toe;
		this.design = design;
		this.material = material;
		this.height = height;
		this.origin = origin;
		this.image = image;
		this.status = status;
		this.gender = gender;
		this.images = images;
	}


	public List<ImageProduct> getImages() {
		return images;
	}


	public void setImages(List<ImageProduct> images) {
		this.images = images;
	}


	public boolean isGender() {
		return gender;
	}


	public void setGender(boolean gender) {
		this.gender = gender;
	}




	public Design getDesign() {
		return design;
	}




	public void setDesign(Design design) {
		this.design = design;
	}




	public int getProductId() {
		return productId;
	}




	public void setProductId(int productId) {
		this.productId = productId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Date getUpdateDate() {
		return updateDate;
	}




	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}




	




	public Date getCreateDate() {
		return createDate;
	}




	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}




	public Brand getBrand() {
		return brand;
	}




	public void setBrand(Brand brand) {
		this.brand = brand;
	}




	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public Size getSize() {
		return size;
	}




	public void setSize(Size size) {
		this.size = size;
	}




	public Toe getToe() {
		return toe;
	}




	public void setToe(Toe toe) {
		this.toe = toe;
	}




	public Material getMaterial() {
		return material;
	}




	public void setMaterial(Material material) {
		this.material = material;
	}




	public Height getHeight() {
		return height;
	}




	public void setHeight(Height height) {
		this.height = height;
	}




	public String getOrigin() {
		return origin;
	}




	public void setOrigin(String origin) {
		this.origin = origin;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public boolean isStatus() {
		return status;
	}




	public void setStatus(boolean status) {
		this.status = status;
	}


	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
