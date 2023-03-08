package com.stackroute.userservice.service;

import java.util.List;

import com.stackroute.userservice.excepistion.ServiceCenterAlredyException;
import com.stackroute.userservice.excepistion.ServiceCenterNotFoundException;
import com.stackroute.userservice.model.ReviewAndRating;
import com.stackroute.userservice.model.ServiceCenter;

public interface ServiceCenterLayer {
    public ServiceCenter serviceCentergetId(String emailId ) throws ServiceCenterNotFoundException;
	public List<ServiceCenter> getallServiceCenters();
	public ServiceCenter add(ServiceCenter serviceCenter)throws ServiceCenterAlredyException;
    public ServiceCenter updateServicecenter(ServiceCenter serviceCenter)throws ServiceCenterNotFoundException;	
	public ServiceCenter updatePassword(ServiceCenter password)throws ServiceCenterNotFoundException;
    public List<ReviewAndRating> reviewAndRating(String emailId) throws ServiceCenterNotFoundException;
    public ServiceCenter addReviewAndRating (String emailId,ReviewAndRating reviewAndRating)throws ServiceCenterNotFoundException;
	public List<ServiceCenter> FindServiceCenetByStateAndCity (String city,String state);

}
