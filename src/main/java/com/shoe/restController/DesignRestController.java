package com.shoe.restController;

import java.util.List;

import com.shoe.entity.Design;
import com.shoe.service.DesignService;
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
@RequestMapping("/rest/design")
public class DesignRestController {
	@Autowired
	DesignService designService;

	@GetMapping
	public List<Design> getAll(){
	  return designService.findAll();
	}
	
	@GetMapping("/getAllStatus")
	public List<Design> getAllStatus(){
	  return designService.findByStatus(true);
	} 
	
	@GetMapping("{name}")
	public Design getName(@PathVariable("name") String name) {
		return designService.findByName(name);
	}
	
	@PostMapping
	public Design on(@RequestBody Design Design) {
		return designService.save(Design);
	}
	
	@PutMapping("{id}")
	public Design update(@PathVariable("id") Integer id, @RequestBody Design Design) {
		return designService.save(Design);
	}
	
	@PutMapping("/delete/{id}")
	public Design updatetrangthai(@PathVariable("id") Integer id, @RequestBody Design Design) {
		Design.setStatus(false);
		return designService.save(Design);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		designService.deleteById(id);
	}
	
	@GetMapping("/timKiem/{name}/{status}")
	public List<Design> timKiem(@PathVariable("name") String name, @PathVariable("status") String status){
		//System.out.println("tên= "+name + " status= "+ status);
		if (status.equals("null")) {
			System.out.println("tên= "+name);
			return designService.findByName1("%"+name+"%");
		} else {
			boolean in  = Boolean.parseBoolean(status);
			System.out.println("tên= "+name + " status="+ in);
			return designService.findByName("%"+name+"%" , in);
		}
	}
	
	@GetMapping("/timKiem/{status}")
	public List<Design> getStatus(@PathVariable("status") Boolean status){
		return designService.findByStatus(status);
	}
	
	
}