package com.smart.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import javax.swing.text.ChangedCharSetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@EnableMethodSecurity
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ContactRepository contactRepository;
	
	
//	Common data like title and user name which is now logged in
	
	@ModelAttribute
	public void commonData(Model model,Principal principal) {
		String name = principal.getName();
		System.out.println("Username "+name);
		
		User user = userRepository.getUserByName(name);
		
		System.out.println("User" + user);
		model.addAttribute("user",user);
		
		System.out.println("inside user role trying to access view index");
	}
	
	
//	for returning index view page
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String dashboard(Model model,Principal principal) {
		
		model.addAttribute("contact", new Contact());
			
			return "normal/user_dashboard";
	}
	
	
	
	
//	for returning addcontact view pages
	@GetMapping("addContact")
	public String adddContact(Model model, HttpSession session) {
		
		return "/normal/addContact";
	}
	
	
	
	
	
	
//	Processing the form which comes from addContact to save the contact in database
	
	@PostMapping("/process-form")
	public String processForm(@ModelAttribute Contact contact,@RequestParam("image")MultipartFile file, Principal principal,HttpSession session) {
		try {
			
			
		String name = principal.getName();
		System.out.println(name);
		User user = userRepository.getUserByName(name);
		System.out.println(user);
		
		if(file.isEmpty()) {
			System.out.println("file is empty..!!");
			contact.setImageUrl("default.png");
			
		}else {
			contact.setImageUrl(user.getUserId()+file.getOriginalFilename());
			
			File saveFile = new ClassPathResource("static/img").getFile(); 
			java.nio.file.Path path = Paths.get(saveFile.getAbsolutePath()+ File.separator +file.getOriginalFilename() );
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
		
		contact.setUser(user);
		user.getContacts().add(contact);
		
		
		userRepository.save(user);
		System.out.println(user);
		System.out.println(contact);
		session.setAttribute("message", new Message("Contact added successfully..!!", "alert-success"));
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("something went wrong...!!" + e.getMessage(), "alert-danger"));
			
		}
		
		return "/normal/addContact";
		
	}
	
	
	
//	for returning view contact page
	
	@GetMapping("/show-contacts/{page}")
	public String viewContact(@PathVariable("page") int page, Model  model,Principal principal ) {
		
		String name = principal.getName();
		User user = userRepository.getUserByName(name);
		Pageable pageable = PageRequest.of(page, 3);
		Page<Contact> contacts = contactRepository.findContactsByUserId(user.getUserId(),pageable);
		
		System.out.println(contacts);
		
		model.addAttribute("contacts",contacts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages",contacts.getTotalPages());
		
		return "/normal/viewContacts";
	}
	
	
//	Controller for showing full contact details
	@GetMapping("/{cId}/contact")
	public String seeContact(@PathVariable("cId") Integer cId, Model model,Principal principal) {
		System.out.println("json file working");
		Optional<Contact> findById = contactRepository.findById(cId);
		Contact contact = findById.get();
		String name = principal.getName();
		User user = userRepository.getUserByName(name);
		if(user.getUserId()==contact.getUser().getUserId()) {
			model.addAttribute("contact", contact);
		}
		return "/normal/contactDetails";
	}
	
	
	
//	Delete contact controller
	@GetMapping("/delete/{cId}")
	public String delete(@PathVariable("cId") Integer cId,Principal principal) {
		
		
		Optional<Contact> findById = contactRepository.findById(cId);
		Contact contact = findById.get();
//		contact.setUser(null);
//		
//		contactRepository.delete(contact);
		
		User user = userRepository.getUserByName(principal.getName());
		user.getContacts().remove(contact);
		userRepository.save(user);
		System.out.println("contact deleted successfully");
		return"redirect:/user/show-contacts/0";
	}
	
	
//	Update pageUpdate controller
	@GetMapping("/update/{cId}")
	public String update(@PathVariable("cId") Integer cId, Model model) {
		
		
		Contact contact = contactRepository.findById(cId).get();
		model.addAttribute("contact", contact);
		
		
		return "/normal/update";
	}
	
	@PostMapping("/process-update/{cId}")
	public String updateProcess(@PathVariable("cId") Integer cId,@ModelAttribute Contact contact,@RequestParam("image") MultipartFile file, Model model,HttpSession session) throws IOException {
		
		Contact contactold = contactRepository.findById(cId).get();
		
		contactold.setName(contact.getName());
		contactold.setEmail(contact.getEmail());
		contactold.setSecondName(contact.getSecondName());
		contactold.setPhone(contact.getPhone());
		contactold.setWork(contact.getWork());
		contactold.setConDesc(contact.getConDesc());
		
		
		
		if(file.isEmpty()) {
			
			
			if(contactold.getImageUrl()==null) {
			contactold.setImageUrl("default.png");
			}
		}else {
		contactold.setImageUrl(file.getOriginalFilename());
		File file2 = new ClassPathResource("/static/img").getFile();
		java.nio.file.Path path = Paths.get(file2.getAbsolutePath()+ File.separator + file.getOriginalFilename());
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
		
		
		
		contactRepository.save(contactold);
		session.setAttribute("message", new Message("contact successfully updated..!!", "alert-success"));
		return"redirect:/user/show-contacts/0";
	}
	
	
//	Change user profile picture
	@PostMapping("/updateProfile")
	public String changeProfile(@ModelAttribute("user") User user,@RequestParam("image") MultipartFile file) throws IOException {
		
		
//		Deleting old file
		
		
		if(file.isEmpty()) {
			user.setUserImage("default.png");
		}
		else {
			File file1 = new ClassPathResource("static/img/" + file.getOriginalFilename()).getFile();
			File file2 = new File(file1, user.getUserImage());
			file2.delete();
			user.setUserImage(file.getOriginalFilename());
			Path path = Paths.get(file1.getAbsolutePath());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
		
		userRepository.save(user);
		
		
		return"/normal/user_dashboard";
	}
	
	

}
