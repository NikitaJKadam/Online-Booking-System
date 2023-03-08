package com.stackroute.userservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.userservice.ConfigRabbitmq.Producer;
import com.stackroute.userservice.ConfigRabbitmq.Userdto;
import com.stackroute.userservice.excepistion.NotFoundException;
import com.stackroute.userservice.excepistion.ServiceCenterAlredyException;
import com.stackroute.userservice.excepistion.ServiceCenterNotFoundException;
import com.stackroute.userservice.excepistion.UserAlredyExcit;
import com.stackroute.userservice.model.ReviewAndRating;
import com.stackroute.userservice.model.ServiceCenter;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.GdRepo;
import com.stackroute.userservice.repository.GsRepo;
import com.stackroute.userservice.service.ServiceCenterLayer;
import com.stackroute.userservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, ServiceCenterLayer {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private GdRepo gd;

	@Autowired
	private GsRepo gs;
	
	@Autowired
	Producer producer;

	@Override
	public User save(User user)  throws UserAlredyExcit {
		if(gd.findById(user.getUserEmailId()).isPresent()||gs.findById(user.getUserEmailId()).isPresent()) {
			throw new UserAlredyExcit();
		}
		else {
			gd.save(user);
			System.out.println("saved user in mongo");
			Userdto userdto=new Userdto();
			userdto.setEmail(user.getUserEmailId());
			userdto.setPassword(user.getUserPassword());
			userdto.setUserRole(user.getUserRole());
			producer.sendMessageToRabbitMq(userdto);
		}
		return gd.save(user);
	}

	@Override
	public User updateUser(User user) throws NotFoundException {
		User user1 = gd.findById(user.getUserEmailId()).get();
		if(user1==null) {
			throw new NotFoundException();	
		}
		user1.setUserName(user.getUserName());
		user1.setUserContactNo(user.getUserContactNo());
		user1.setUserAddress(user.getUserAddress());
		gd.save(user1);
		
		return user1;
	}

	@Override
	public User updatePassword(User password) throws NotFoundException {
		User user1 = gd.findById(password.getUserEmailId()).get();
		if(user1==null) {
			throw new NotFoundException();
			
		}
		user1.setUserPassword(password.getUserPassword());
		gd.save(user1);
		Userdto userdto=new Userdto();
		userdto.setEmail(password.getUserEmailId());
		userdto.setPassword(password.getUserPassword());
		producer.sendMessageToRabbit(userdto);
     return user1;
	}

	@Override
	public User EmailId(String emailId) throws NotFoundException {
		User user1 = gd.findById(emailId).get();
		if(user1==null) {
			throw new NotFoundException();
			
		}	
		return user1;
	}

	@Override
	public ServiceCenter serviceCentergetId(String emailId) throws ServiceCenterNotFoundException{
		if(gs.findById(emailId).isEmpty()) {
			throw new ServiceCenterNotFoundException("services center is not present");
		}
		ServiceCenter serviceCenter = gs.findById(emailId).get();

		return serviceCenter;
	}

	@Override
	public List<ServiceCenter> getallServiceCenters() {
		List<ServiceCenter> list = gs.findAll();
		
		return list;

	}

	@Override
	public ServiceCenter add(ServiceCenter serviceCenter) throws ServiceCenterAlredyException{
		if (gs.findById(serviceCenter.getCenterEmailId()).isPresent()||gd.findById(serviceCenter.getCenterEmailId()).isPresent()) {
			throw new ServiceCenterAlredyException();
		}
		ServiceCenter sc = gs.save(serviceCenter);
		Userdto userdto=new Userdto();
		userdto.setEmail(serviceCenter.getCenterEmailId());
		userdto.setPassword(serviceCenter.getPassword());
		userdto.setUserRole(serviceCenter.getUserRole());
		producer.sendMessageToRabbitMq(userdto);
		return sc;
}

	@Override
	public ServiceCenter updatePassword(ServiceCenter password) throws ServiceCenterNotFoundException{
		if(gs.findById(password.getCenterEmailId()).isEmpty()) {
			throw new ServiceCenterNotFoundException("services center is not present");
		}
		ServiceCenter serviceCenter = gs.findById(password.getCenterEmailId()).get();
		serviceCenter.setPassword(password.getPassword());
		gs.save(serviceCenter);
		Userdto userdto=new Userdto();
		userdto.setEmail(password.getCenterEmailId());
		userdto.setPassword(password.getPassword());
		producer.sendMessageToRabbit(userdto);
		return serviceCenter;
	}

	@Override
	public List<ReviewAndRating> reviewAndRating(String emailId) throws ServiceCenterNotFoundException{
		if(gs.findById(emailId).isEmpty()) {
			throw new ServiceCenterNotFoundException("services center is not present");
		}
		ServiceCenter sc = gs.findById(emailId).get();
		List<ReviewAndRating> ratings = sc.getReviewAndRating();
		return ratings;
	}

	@Override
	public ServiceCenter updateServicecenter(ServiceCenter serviceCenter) throws ServiceCenterNotFoundException{
		ServiceCenter ServiceCenter1 = gs.findById(serviceCenter.getCenterEmailId()).get();
		if(ServiceCenter1==null){
			throw new ServiceCenterNotFoundException("services center is not present");
		}
		ServiceCenter1.setCenterName(serviceCenter.getCenterName());
		ServiceCenter1.setCenterContactNo(serviceCenter.getCenterContactNo());
		ServiceCenter1.setScBranch(serviceCenter.getScBranch());
		ServiceCenter1.setScAddress(serviceCenter.getScAddress());
		ServiceCenter1.setProductCategory(serviceCenter.getProductCategory());
		gs.save(serviceCenter);
		return serviceCenter;
	}

	@Override
	public ServiceCenter addReviewAndRating(String emailId, ReviewAndRating reviewAndRating)throws ServiceCenterNotFoundException {
		ServiceCenter serviceCenter=gs.findById(emailId).get();
		if (serviceCenter==null){
			throw new ServiceCenterNotFoundException();
		}
		else {
			List<ReviewAndRating> ratinglist = serviceCenter.getReviewAndRating();
			if (ratinglist == null) {
				serviceCenter.setAvgRating(reviewAndRating.getUserRating());
				List<ReviewAndRating> newlist = new ArrayList<>();
				newlist.add(reviewAndRating);
				serviceCenter.setReviewAndRating(newlist);
				return gs.save(serviceCenter);
			} else {
				ratinglist.add(reviewAndRating);
				float sum = 0;
				for (ReviewAndRating r : ratinglist) {
					sum = sum + r.getUserRating();
				}
				float avg = sum / ratinglist.size();
				serviceCenter.setAvgRating(avg);
				return gs.save(serviceCenter);


			}
		}

	}

	@Override
	public List<ServiceCenter> FindServiceCenetByStateAndCity(String city, String state) {
	List<ServiceCenter>	serviceCentelist= gs.findAll();
		List<ServiceCenter> newlist=new ArrayList<>();
		List<ServiceCenter> resultList=new ArrayList<>();
		for(ServiceCenter s:serviceCentelist) {
			if(s.getScAddress()!=null){
				newlist.add(s);
			}
		}

	for(ServiceCenter s:newlist) {
		if (s.getScAddress().getState().equalsIgnoreCase(state)&&s.getScAddress().getCity().equalsIgnoreCase(city)){
			System.out.println(s.getScAddress().getCity());
			resultList.add(s);
		}
	}
		return resultList;
	}

}
