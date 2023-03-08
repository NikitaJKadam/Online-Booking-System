package com.stackroute.userservice.controller;
import java.util.List;

import com.stackroute.userservice.excepistion.NotFoundException;
import com.stackroute.userservice.excepistion.ServiceCenterAlredyException;
import com.stackroute.userservice.excepistion.ServiceCenterNotFoundException;
import com.stackroute.userservice.excepistion.UserAlredyExcit;
import com.stackroute.userservice.model.ReviewAndRating;
import com.stackroute.userservice.model.ServiceCenter;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.serviceImpl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "UserController", description = "REST Apis related to user user !!!!")
@RequestMapping("/api/v1")
@RestController
//@CrossOrigin(origins="http://localhost:3000")

public class UserController {
	

	@Autowired
	public UserServiceImpl gs;
	
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "reg/user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

	

	@PostMapping("/reg/user")
	public ResponseEntity<?> save(@RequestBody User user) {
		try {
			return new ResponseEntity<User>(gs.save(user),HttpStatus.ACCEPTED);
		} catch (UserAlredyExcit e) {
			e.printStackTrace();
			return new ResponseEntity<String>("user alredy exicity",HttpStatus.CONFLICT);
		}
	}
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "reg/userupdate")

	@PutMapping("/reg/userupdate")
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			return new ResponseEntity<User>(gs.updateUser(user),HttpStatus.OK);
		} catch (NotFoundException e) {
						e.printStackTrace();
			return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);

		}
	}
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "reg/passwordupdate")

	
	 @PutMapping("/reg/passwordupdate") 
	public ResponseEntity<?>updatePassword(@RequestBody User password){
	 try {
		return new
		 ResponseEntity<User>(gs.updatePassword(password),HttpStatus.ACCEPTED);
	} catch (NotFoundException e) {
		e.printStackTrace();
		return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);

		
	} }
	 
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "reg/user/{emailId}")

	@GetMapping("/reg/user/{emailId}")
	public ResponseEntity<?> EmailId(@PathVariable String emailId) {
		try {
			return new ResponseEntity<User>(gs.EmailId(emailId),HttpStatus.OK);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);

		}
	}
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "reg/serviceCenter/{emailId}")

	@GetMapping("/reg/serviceCenter/{emailId}") //RequestParam, @PathVariable
	public ResponseEntity<?> ServiceEmailId(@PathVariable String emailId){
		try {
			return new ResponseEntity<ServiceCenter>(gs.serviceCentergetId(emailId),HttpStatus.OK);
		} catch (ServiceCenterNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Service not found",HttpStatus.NOT_FOUND);

		}
	}
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "/getAllServiceCenters}")

	 @GetMapping("/getAllServiceCenters") 
	 public ResponseEntity<List<ServiceCenter>> getAllServiceCenters(){ return new
	  ResponseEntity<List<ServiceCenter>>(gs.getallServiceCenters(),HttpStatus.	  ACCEPTED); 
	 }
	
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "/reg/address}")

	 @PostMapping("/reg/servicecenter")
		public ResponseEntity<?> add(@RequestBody ServiceCenter serviceCenter){
			try {
				return new ResponseEntity<ServiceCenter>(gs.add(serviceCenter),HttpStatus.ACCEPTED);
			} catch (ServiceCenterAlredyException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Service alredy exit",HttpStatus.CONFLICT);

			}
		}
	 
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags = "/reg/updatePassword")

	 @PutMapping("/reg/updatePassword") 
	 public ResponseEntity<?>
	  updatePassword(@RequestBody ServiceCenter password){ try {
		return new
		  ResponseEntity<ServiceCenter>(gs.updatePassword(password),HttpStatus.ACCEPTED);
	} catch (ServiceCenterNotFoundException e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Service not found",HttpStatus.NOT_FOUND);

	} 
	 }
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags ="/getAllRatings/{emailId}")

	 @GetMapping("/getAllRatings/{emailId}")
	 public ResponseEntity<?> getAllRatings(@PathVariable String emailId){
		 try {
			return new ResponseEntity<List<ReviewAndRating>>(gs.reviewAndRating(emailId),HttpStatus.ACCEPTED);
		} catch (ServiceCenterNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Service not found",HttpStatus.NOT_FOUND);

		}
	 }
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags ="/updateServicecenter")

	 @PutMapping("/updateServicecenter")
	 public ResponseEntity<?> updateServicecenter(@RequestBody ServiceCenter serviceCenter){
	 try {
		return new ResponseEntity<ServiceCenter>(gs.updateServicecenter(serviceCenter),HttpStatus.OK);
	} catch (ServiceCenterNotFoundException e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Service not found",HttpStatus.NOT_FOUND);

	}
 }
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags ="/ReviewAndRating/{emailId}")

	 @PutMapping("/ReviewAndRating/{emailId}")
	 public ResponseEntity<?>reviewandrating(@PathVariable String emailId,@RequestBody ReviewAndRating reviewAndRating){
		try {
			return new ResponseEntity<ServiceCenter>(gs.addReviewAndRating(emailId, reviewAndRating),HttpStatus.ACCEPTED);
		} catch (ServiceCenterNotFoundException e) {
			e.printStackTrace();	 
			return new ResponseEntity<String>("Service not found",HttpStatus.NOT_FOUND);

	}

}
	@ApiOperation(value = "This API is used to add user to database", response =User .class, tags ="/FindServiceCenetByStateAndCity/{city}/{state}")

	@GetMapping("/FindServiceCenetByStateAndCity/{state}/{city}")
	public ResponseEntity<?>FindServiceCenetByStateAndCity(@PathVariable String state,@PathVariable String city ){
	
	return new ResponseEntity<>(gs.FindServiceCenetByStateAndCity(city,state),HttpStatus.ACCEPTED);
	
		
	
		
	
	}
}
