package com.shoe.restController;

import java.util.List;

import com.shoe.entity.Height;
import com.shoe.service.HeightService;
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
@RequestMapping("/rest/height")
public class HeightRestController {
	@Autowired
	HeightService heightService;

	@GetMapping
	public List<Height> getAll(){
	  return heightService.findAll();
	}
	
	@GetMapping("/getAllStatus")
	public List<Height> getAllStatus(){
	  return heightService.findByStatus(true);
	}
	
	@GetMapping("{name}")
	public Height getName(@PathVariable("name") String name) {
		return heightService.findByName(name);
	}
	
	@PostMapping
	public Height on(@RequestBody Height Height) {
		return heightService.save(Height);
	}
	
	@PutMapping("{id}")
	public Height update(@PathVariable("id") Integer id, @RequestBody Height Height) {
		return heightService.save(Height);
	}
	
	@PutMapping("/delete/{id}")
	public Height updatetrangthai(@PathVariable("id") Integer id, @RequestBody Height Height) {
		Height.setStatus(false);
		return heightService.save(Height);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		heightService.deleteById(id);
	}
	
	@GetMapping("/timKiem/{name}/{status}")
	public List<Height> timKiem(@PathVariable("name") String name, @PathVariable("status") String status){
		//System.out.println("tên= "+name + " status= "+ status);
		if (status.equals("null")) {
			System.out.println("tên= "+name);
			return heightService.findByName1("%"+name+"%");
		} else {
			boolean in  = Boolean.parseBoolean(status);
			System.out.println("tên= "+name + " status="+ in);
			return heightService.findByName("%"+name+"%" , in);
		}
	}
	
	@GetMapping("/timKiem/{status}")
	public List<Height> getStatus(@PathVariable("status") Boolean status){
		return heightService.findByStatus(status);
	}
	
	
}