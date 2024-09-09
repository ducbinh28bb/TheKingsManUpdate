package com.shoe.restController;

import java.util.List;

import com.shoe.entity.Roles;
import com.shoe.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
	@Autowired
    RolesService roleService;
	
	@GetMapping()
	public List<Roles> getAll() {
		return roleService.findAll();
	}
}