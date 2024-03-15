package com.test.scm.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.test.scm.dao.ContactRepository;
import com.test.scm.dao.UserRepository;
import com.test.scm.entity.Contact;
import com.test.scm.entity.User;
import com.test.scm.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class userController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@ModelAttribute
	public void commonHandler(Model model, Principal principal) {
		String name = principal.getName();
		User user = userRepository.getUserByUserEmail(name);
		model.addAttribute(user);
	}
	
	@GetMapping("/normal")
	public String indexPage(Model model, Principal principal) {
		
		return "normal/user_dashboard"; //normal/user_dashboard
	}
	
	@GetMapping("/addContact")
	public String addUserContact(Model model) {
		
		model.addAttribute("title","addContact");
		model.addAttribute("contact",new Contact());
		return "normal/addContact";
	}
	
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact,
			@RequestParam("cImageUr") MultipartFile file,
			Principal principal, HttpSession session) {
		
		try {
			String name = principal.getName();
			User user = userRepository.getUserByUserEmail(name);
			if(file.isEmpty()) {
				System.out.println("file is empty");
				contact.setcImageUrl("lord-rama.jpg");			}
			else {
				//set image name into db
				contact.setcImageUrl(file.getOriginalFilename());
				
				//uploading into folder
				File file2 = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(file2.getAbsolutePath(), File.separator,file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				
			}
			
			contact.setUser(user);
			user.getContacts().add(contact);
			userRepository.save(user);
			session.setAttribute("message", new Message("Contact are add successfully...","success"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("message", new Message("Some went wrong, try again!!...","danger"));
		}
		
		
		return "normal/addContact";
	}
	
	//Show contacts
	@GetMapping("/showContacts/{page}")
	public String showContacts(@PathVariable("page") Integer page, Model model, Principal principal) {
		model.addAttribute("title","show contacts");
		String name = principal.getName();
		User userByUserName = userRepository.getUserByUserEmail(name);
//		List<Contact> contacts = userByUserName.getContacts();
//		contacts.forEach(System.out::println);
//		//System.out.println(contacts);
		
		int getuId = userByUserName.getuId();
		
		Pageable pageble = PageRequest.of(page, 5);
		Page<Contact> contactByUserId = contactRepository.getContactByUserId(getuId, pageble);
		
		//Sorting contact
//		Comparator<Contact> cmpt=(x1,x2)->x1.getcName().compareToIgnoreCase(x2.getcName());
//		Collections.sort(contactByUserId,cmpt);
		List<Contact> contactss = contactByUserId.stream().sorted((x1,x2)->x1.getcName().compareToIgnoreCase(x2.getcName())).collect(Collectors.toList());
		
		
		model.addAttribute("allContact",contactss);
		model.addAttribute("currentPage",page);
		model.addAttribute("TotalPage",contactByUserId.getTotalPages());
		
		contactByUserId.forEach(System.out::println);
		return "normal/show_Contact";
	}
	
	//Showing particular contact details.
	
	@GetMapping("{cId}/contact")
	public String DetailShow(@PathVariable("cId") int cId, Model model) {
		
		Optional<Contact> findById = contactRepository.findById(cId);
		Contact contact = findById.get();
		model.addAttribute("contact",contact);
		
		return "/normal/ContactDetails";
	}
}
