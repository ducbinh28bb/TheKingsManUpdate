package com.shoe.restController;

import java.util.List;

import com.shoe.entity.Material;
import com.shoe.service.MaterialService;
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
@RequestMapping("/rest/material")
public class MaterialRestController {
	@Autowired
	MaterialService materialService;

	@GetMapping
	public List<Material> getAll(){
	  return materialService.findAll();
	}
	@GetMapping("/getAllStatus")
	public List<Material> getAllStatus(){
	  return materialService.findByStatus(true);
	}
	@GetMapping("{name}")
	public Material getName(@PathVariable("name") String name) {
		return materialService.findByName(name);
	}
	
	@PostMapping
	public Material on(@RequestBody Material Material) {
		return materialService.save(Material);
	}
	
	@PutMapping("{id}")
	public Material update(@PathVariable("id") Integer id, @RequestBody Material Material) {
		return materialService.save(Material);
	}
	
	@PutMapping("/delete/{id}")
	public Material updatetrangthai(@PathVariable("id") Integer id, @RequestBody Material Material) {
		Material.setStatus(false);
		return materialService.save(Material);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		materialService.deleteById(id);
	}
	
	@GetMapping("/timKiem/{name}/{status}")
	public List<Material> timKiem(@PathVariable("name") String name, @PathVariable("status") String status){
		//System.out.println("tên= "+name + " status= "+ status);
		if (status.equals("null")) {
			System.out.println("tên= "+name);
			return materialService.findByName1("%"+name+"%");
		} else {
			boolean in  = Boolean.parseBoolean(status);
			System.out.println("tên= "+name + " status="+ in);
			return materialService.findByName("%"+name+"%" , in);
		}
	}
	
	@GetMapping("/timKiem/{status}")
	public List<Material> getStatus(@PathVariable("status") Boolean status){
		return materialService.findByStatus(status);
	}
	
	
}