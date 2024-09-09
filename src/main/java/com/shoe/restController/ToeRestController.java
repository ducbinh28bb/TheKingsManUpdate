package com.shoe.restController;

import java.util.List;

import com.shoe.entity.Toe;
import com.shoe.service.ToeService;
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
@RequestMapping("/rest/toe")
public class ToeRestController {
	@Autowired
	ToeService toeService;

	@GetMapping
	public List<Toe> getAll(){
	  return toeService.findAll();
	}
	@GetMapping("/getAllStatus")
	public List<Toe> getAllStatus(){
	  return toeService.findByStatus(true);
	}
	@GetMapping("{name}")
	public Toe getName(@PathVariable("name") String name) {
		return toeService.findByName(name);
	}
	
	@PostMapping
	public Toe on(@RequestBody Toe Toe) {
		return toeService.save(Toe);
	}
	
	@PutMapping("{id}")
	public Toe update(@PathVariable("id") Integer id, @RequestBody Toe Toe) {
		return toeService.save(Toe);
	}
	
	@PutMapping("/delete/{id}")
	public Toe updatetrangthai(@PathVariable("id") Integer id, @RequestBody Toe Toe) {
		Toe.setStatus(false);
		return toeService.save(Toe);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		toeService.deleteById(id);
	}
	
	@GetMapping("/timKiem/{name}/{status}")
	public List<Toe> timKiem(@PathVariable("name") String name, @PathVariable("status") String status){
		//System.out.println("tên= "+name + " status= "+ status);
		if (status.equals("null")) {
			System.out.println("tên= "+name);
			return toeService.findByName1("%"+name+"%");
		} else {
			boolean in  = Boolean.parseBoolean(status);
			System.out.println("tên= "+name + " status="+ in);
			return toeService.findByName("%"+name+"%" , in);
		}
	}
	
	@GetMapping("/timKiem/{status}")
	public List<Toe> getStatus(@PathVariable("status") Boolean status){
		return toeService.findByStatus(status);
	}
	
	
}