package com.shoe.restController;

import java.util.List;

import com.shoe.entity.Brand;
import com.shoe.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brand")
public class BrandRestController {
	@Autowired
    BrandService brandService;

	@GetMapping
	public List<Brand> getAll(){
	  return brandService.findAll();
	}
	
	@GetMapping("/getAllStatus")
	public List<Brand> getAllStatus(){
	  return brandService.getAllStatus();
	}
	@GetMapping("{name}")
	public Brand getName(@PathVariable("name") String name) {
		return brandService.findByName(name);
	}
	
	@PostMapping
	public Brand on(@RequestBody Brand brand) {
		return brandService.save(brand);
	}
	
	@PutMapping("{id}")
	public Brand update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
		return brandService.save(brand);
	}
	
	@PutMapping("/delete/{id}")
	public Brand updateThai(@PathVariable("id") Integer id, @RequestBody Brand brand) {
		brand.setStatus(false);
		return brandService.save(brand);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		brandService.deleteById(id);
	}
	
	@GetMapping("/timKiem/{name}/{status}")
	public List<Brand> timKiem(@PathVariable("name") String name,@PathVariable("status") String status){
		//System.out.println("tên= "+name + " status= "+ status);
		if (status.equals("null")) {
			System.out.println("tên= "+name);
			return brandService.findByName1("%"+name+"%");
		} else {
			boolean in  = Boolean.parseBoolean(status);
			System.out.println("tên= "+name + " status="+ in);
			return brandService.findByName("%"+name+"%" , in);
		}
	}
	
	@GetMapping("/timKiem/{status}")
	public List<Brand> getStatus(@PathVariable("status") Boolean status){
		return brandService.findByStatus(status);
	}
	
	
}