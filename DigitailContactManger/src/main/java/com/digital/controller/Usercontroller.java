package com.digital.controller;

import java.io.File;
import java.lang.StackWalker.Option;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

import com.digital.dao.ContactRepository;
import com.digital.dao.UserRepository;
import com.digital.entitys.Contact;
import com.digital.entitys.User;
import com.digital.message.Message;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@Controller
@RequestMapping("/user")
public class Usercontroller {

	 @Autowired
	 private UserRepository userRepository;
     
	 @Autowired
	 private ContactRepository contactRepository;
	 
	 // METHOD FOR ADDING  COMMON DATA IN DASH BOARD
	 
	 @ModelAttribute
	 public void addCommonData(Model model, Principal principal) {
		 
		 String username= principal.getName();
			System.out.println("User name :" +username);
			
			// get user using by username(Email)
			
			User  user=userRepository.getUserByUserName(username);
			System.out.println("User Details"+ user);
			
			model.addAttribute("user",user);
		 
	 }
	 
	 
	 // USER DASHBOARD HOME HANDLER
	
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) 
	{
		model.addAttribute("title","User- Dashboard");
			return "normal/user_dashboard";
	}
	
	
	
	// open add form handler 
	
	@GetMapping("/add-contact")
	public  String openAddcontactForm(Model model){
		
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		
		
		return"normal/add_contact_form";
	}
	
	//PROCESSING ADD CONTACT FORM
	
	@PostMapping("/process-contact")
	public String processContact(
			@ModelAttribute Contact contact,
			@RequestParam("profileImage") MultipartFile file ,
			Principal principal,HttpSession session) {
	
		try {
		
	 String name  =	principal.getName();
	  User user = this.userRepository.getUserByUserName(name);
	  
	  // PROCESSING AND UPLOADIG IMAGE FILE....
	  if (file.isEmpty()) {
		
		 System.out.println("File is Empity");
		 contact.setImage("contact.png");
	} else
	{
        contact.setImage(file.getOriginalFilename());
        
		File saveFile = new ClassPathResource("static/img").getFile();
		
	Path path =	Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
		
		Files.copy(file.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println("your file is uploding");
		
	}
	  
	  
	  
	  
		 contact.setUser(user); 
	
	  user.getContacts().add(contact);
	  
	  
	  
	  this.userRepository.save(user);
	  
		System.out.println("data :" +contact);
		
		System.out.println("Added to data bases");
		
		// MESSAGE SUCCSESS 
		session.setAttribute("message",new Message("your contact is add!!", "success"));
		
		}catch(Exception e) {
			System.out.println("Error"+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message",new Message("something went to wrong !!", "danger"));

			// ERROR MESSAGE
		}
		
		return "normal/add_contact_form";
	
	}
	
	
	
	//SHOW CONTACTES  HANDLER
	
	//PER PAGE 10 CONTACTS
	
	@GetMapping("/view-contacts/{page}")
	public String showContacts(@PathVariable("page")Integer page ,Model model,Principal principal) {
		
		model.addAttribute("title","View Contacts");
		
		// CONTACT LIST ON VIEW PAGE 
	String userName=	principal.getName();
	
	User user= 	this.userRepository.getUserByUserName(userName);
	
	 Pageable pageable   =PageRequest.of(page, 10);
	
	Page<Contact> contacts=	this.contactRepository.findContactsByUser(user.getId(),pageable);
	
	model.addAttribute("contacts",contacts);
	model.addAttribute("current page",page);
	
	model.addAttribute("totalpages",contacts.getTotalPages());
		
		
		return "normal/viewcontacts";
	}
	
	
	//SHOWING HANDLER FOR PERTICULAR CONTACT DETAILS
	
	@RequestMapping("/{cid}/contact")
	public String showContactDetail(@PathVariable("cid")Integer cid,Model model,Principal principal)
	{
	 System.out.println("CID"+cid);
	 
	 
	 Optional<Contact> contactOptional  = this.contactRepository.findById(cid);
	 Contact contact   = contactOptional.get();
	 
	   String userName= principal.getName();
	   User user= this.userRepository.getUserByUserName(userName);
	   
	   if(user.getId()==contact.getUser().getId()) 
	   {
		   model.addAttribute("contact",contact);
		   model.addAttribute("title",contact.getName());
	   }
	
	 
		return "normal/contact_detail";
	}
	
	
	
	// DELETE CONTACT HANDLER
	
	
	@GetMapping("/delete/{cid}")
	@Transactional
	public String deleteContact(@PathVariable("cid")Integer cid,Model model,HttpSession session,
			Principal principal) 
	{
		Contact contact   = this.contactRepository.findById(cid).get();
		
		User user= this.userRepository.getUserByUserName(principal.getName());
		user.getContacts().remove(contact);
	
		
		this.userRepository.save(user);
		
		System.out.println("Deleted");
		 
		 session.setAttribute("message", new Message("Contact delete Successfull", "success"));
		
		 return"redirect:/user/view-contacts/0";
	}
	
	
	// OPEN UPDATE FORM HANDLER
	
	@PostMapping("update-contact/{cid}")
	public String updateForm( @PathVariable("cid") Integer cid, Model  model) {
		
		model.addAttribute("title","Update contact");
		
		Contact contact=	this.contactRepository.findById(cid).get();
		model.addAttribute("contact",contact);
		return "normal/Update_form";
	}
	
	// SAVE UPDATE CONTACT HANDLER
	
	
	  @PostMapping("/process-update") 
	  public String updateHandler(@ModelAttribute Contact contact,@RequestParam("profileImage")MultipartFile file
			 ,Model model ,HttpSession session,Principal principal) {
	  
		  
		  try {
			  
			  // old contact details
			Contact oldcontactdetails=  this.contactRepository.findById(contact.getCid()).get();
			  
			  
			  if (!file.isEmpty()) 
			{
				  
				  // delete old photo
                  
				  
				  File deletefile = new ClassPathResource("static/img").getFile();
				   File file1 =new File (deletefile ,oldcontactdetails.getImage());
				   file1.delete();
				  
				  // update new photo
				
				  File saveFile = new ClassPathResource("static/img").getFile();
					
				  Path path =	Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
						
				  Files.copy(file.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
				  
				  contact.setImage(file.getOriginalFilename());
				  
				  
			}
			  else {
				
				contact.setImage(oldcontactdetails.getImage());
				
			}
		
		User user= this.userRepository.getUserByUserName(principal.getName());
		
		contact.setUser(user);
		
		this.contactRepository.save(contact);
		
		session.setAttribute("message", new Message("your contact is updated", "success"));
			
		}
		  catch (Exception e) {
			
			e.printStackTrace();
		}
	  System.out.println("contact name"+contact.getName());
	  
	  System.out.println("contact id "+ contact.getCid());
	  
	  
	  
	  return"redirect:/user/"+contact.getCid()+"/contact"; 
	  }
	 
	
	// your user profile handler
	  
	  @GetMapping("/profile")
	  public String userProfile(Model model) {
		  
		  
		  
		  model.addAttribute("title", "profile");
		  return"normal/profile";
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
