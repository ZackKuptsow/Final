package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void testRate() throws RateException {
	
	try{
	assertTrue(RateBLL.getRate(650) == 650);
		
	}catch (RateException e){
		System.out.print("Throws RateException");
	}
	
	finally{
		System.out.print("Test done.");
	}
	
	}

}
