package com.tritern.evozspecial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tritern.evozspecial.document.EvozDocument;
import com.tritern.evozspecial.entity.EvozEntity;
import com.tritern.evozspecial.entity.FinalEntity;
import com.tritern.evozspecial.service.EvozService;

@RestController
@RequestMapping(value = "/evoz")
public class EvozController {

	private EvozService evozservice;

	@Autowired
	public EvozController(EvozService evozservice) {
		this.evozservice = evozservice;
	}

	// MapDatabase API
	@GetMapping("/getmapped")
	public FinalEntity getMapped(@RequestParam(value = "email") String email) {
		return evozservice.mapEntity(email);
	}

	// Image API
	@RequestMapping(path = "/getimg/{email}", method = RequestMethod.GET)
	public EvozDocument getImg(@PathVariable(value = "email") String email) {
		return evozservice.getFinalData(email);
	}

	// create User API
	@PostMapping("/register")
	public String createEvozUser(@RequestBody EvozEntity evozentity) {
		System.out.println("Create User API");
		return evozservice.signUp(evozentity);
	}

	// Login User API
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginEvozUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		return evozservice.loginEvozMUser(email, password);
	}

	// List Users API
	@GetMapping("/listusers")
	public List<EvozEntity> listAllUsers(@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		return evozservice.getList(pageNo);
	}

	// Get Normal List Count
	@GetMapping("/listcount")
	public Integer getListCount() {
		return evozservice.getCount();
	}

	// Filter List User API
	@RequestMapping(value = "/search/listusers", method = RequestMethod.GET)
	public List<EvozEntity> searchListUser(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		return evozservice.getFilter(keyword, pageNo);
	}

	// Get Filter List Count
	@RequestMapping(value = "/search/listcount", method = RequestMethod.GET)
	public Integer getFilterCount(@RequestParam(value = "keyword", required = false) String keyword) {
		return evozservice.getSearchCount(keyword);
	}

	// Update User API
	@PutMapping("/update/{email}")
	public String upadateEvozUsers(@PathVariable(value = "email") String email,
			@RequestParam(value = "emailid", required = false) String emailid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "username", required = false) String username) {
		return evozservice.updateEvozUser(name, email, emailid, username);
	}

	// Delete User API
	@RequestMapping(path = "/delete/{email}", method = RequestMethod.DELETE)
	public String deleteEvozUser(@PathVariable(value = "email") String email) {
		System.out.println(email);
		return evozservice.removeUser(email);
	}

	// Insert Image API
	@PostMapping("/addimage")
	public String addImg(@RequestBody EvozDocument photo) {
		System.out.println("image api");
		System.out.println(photo);
		return evozservice.insertImage(photo);

	}

//	@GetMapping("/getffdata")
//	public List<FinalEntity> getfd(){
//		return evozservice.fddata();
//	}

}
