package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.Test;

import rocketDomain.RateDomainModel;
import util.HibernateUtil;

public class Rate_Test {

	
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		ArrayList<RateDomainModel> alRates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + alRates.size());
		assertTrue(alRates.size() > 0);
		
		
	}

}