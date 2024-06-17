package com.student.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.system.entity.EmailDetails;
import com.student.system.entity.ForgotPasswordUser;
import com.student.system.entity.Student;
import com.student.system.entity.User;
import com.student.system.service.EmailService;
import com.student.system.service.UserServiceImpl;

@Controller
@RequestMapping("/students")
public class StudentManagementSystemController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
    private EmailService emailService;

    // This will store OTP temporarily, replace this with your preferred storage mechanism
    private String storedOTP;
	
	@GetMapping("/login")
	public String loginToSystem(Model theModel)
	{
		//create a user model to bind the data
		User user=new User();
		theModel.addAttribute("user",user);
		return "students/login-page";
		
	}
	
	@PostMapping("/access")
	public String loginToSystemCheck(@ModelAttribute("user") User theUser, Model model) {
	    Boolean result = userService.verifyUser(theUser);
	    if (result == false) {
	        // Add error message to the model
	        model.addAttribute("error", "Invalid username or password. Please try again.");
	        // Return login page with error message
	        return "students/login-page";
	    } else {
	        return "redirect:/students/home";
	    }
	}
	
	@GetMapping("/list")
	public String listOfStudents(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="5")int size,Model theModel)
	{
		Page<Student> studentPage=userService.findPaginated(page, size);
		theModel.addAttribute("studentPage", studentPage);
		return "students/list-students";
	}

	//Controller logic for logout
	@GetMapping("/logout")
	public String logOutFunctionality(RedirectAttributes redirectAttributes) {
	    redirectAttributes.addFlashAttribute("loggedout", "Successfully logged out!");
	    return "redirect:/students/login";
	}

	//Controller logic to show sign up form
	@GetMapping("/showFormToSignUp")
	public String showFormToSignUp(Model theModel)
	{
		User toAddNewUser=new User();
		theModel.addAttribute("toAddUser", toAddNewUser);
		return "students/signup-form";
	}
	
	@PostMapping("/signup")
	public String signUpNewUser(@ModelAttribute("toAddUser")User toAddNewUser,RedirectAttributes redirectAttributes,Model theModel)
	{
		Boolean result;
		try {
			result = userService.addNewUser(toAddNewUser);
			if(result==true)
			{
				//Adding the sign up message to be displayed in the front end 
				redirectAttributes.addFlashAttribute("signup", "Successfully Signed Up! Welcome to Student Management System");
				return "redirect:/students/login";
			}
			else
			{
				return "students/login-page.html";
			}
		} catch (Exception e) {
			theModel.addAttribute("cannotsignup", "The user already exists!");
			return "students/signup-form.html";
		}
		
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId")Integer studentId,Model theModel)
	{
		Student foundStudent=userService.findStudentById(studentId);
		theModel.addAttribute("student", foundStudent);
		return "students/student-update-form.html";
	}
	
	@PostMapping("/update")
	public String updateStudent(@ModelAttribute("student")Student student,RedirectAttributes redirectAttributes,Model theModel)
	{
		try
		{
			Student saved=userService.saveStudent(student);
			redirectAttributes.addFlashAttribute("updated", "Student Updated Successfully");
			return "redirect:/students/list";
		}
		catch(Exception e)
		{
			theModel.addAttribute("errorUpdate", e.getMessage());
			return "students/student-update-form.html";
		}
		
	}
	
	@GetMapping("/add")
	public String addNewStudentForm(Model theModel)
	{
		Student toAdd=new Student();
		theModel.addAttribute("studentToAdd", toAdd);
		return "students/student-add-form.html";
	}
	
	@PostMapping("/addNewStudent")
	public String addNewStudentToDb(@ModelAttribute("studentToAdd")Student student,RedirectAttributes redirectAttributes)
	{
		try
		{
			Student saved=userService.saveStudent(student);
			redirectAttributes.addFlashAttribute("added", "New Student Added Successfully");
			return "redirect:/students/list";
		}
		catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/students/add";
		}
		
	}
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("studentId")Integer studentId,RedirectAttributes redirectAttributes)
	{
		Boolean status=userService.deleteStudent(studentId);
		if(status!=true)
		{
			redirectAttributes.addFlashAttribute("deleteStatus", "Unable To Delete Student!");
			return "redirect:/students/list";
		}
		else
		{
			redirectAttributes.addFlashAttribute("deleteStatus", "Student Deleted Successfully!");
			return "redirect:/students/list";
		}
	}
	
	@GetMapping("/search")
	public String searchStudentsByFirstName(@RequestParam("firstName")String firstName,Model theModel,RedirectAttributes redirectAttributes)
	{
		List<Student> studentsByFirstName=new ArrayList<>();
		studentsByFirstName=userService.findStudentByFirstName(firstName);
		if(studentsByFirstName==null || studentsByFirstName.isEmpty())
		{
			redirectAttributes.addFlashAttribute("errorFirstName", "No Students available by the name of: "+firstName);
			return "redirect:/students/list";
		}
		theModel.addAttribute("students", studentsByFirstName);
		return "students/student-results";
	}
	
	@GetMapping("/home")
	public String homePage()
	{
		return "students/student-manage-sections";
	}
	
	@GetMapping("/listOfUsers")
	public String listOfUsers(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="5")int size,Model theModel)
	{
		Page<User> userPage=userService.findPaginatedUsers(page, size);
		theModel.addAttribute("userPage", userPage);
		return "students/list-users";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId")Integer userId,RedirectAttributes redirectAttributes)
	{
		Boolean status=userService.deleteUser(userId);
		if(status!=true)
		{
			redirectAttributes.addFlashAttribute("deleteStatus", "Unable To Delete User!");
			return "redirect:/students/listOfUsers";
		}
		else
		{
			redirectAttributes.addFlashAttribute("deleteStatus", "User Deleted Successfully!");
			return "redirect:/students/listOfUsers";
		}
	}
	
}
