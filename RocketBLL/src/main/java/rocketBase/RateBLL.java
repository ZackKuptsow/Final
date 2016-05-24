package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		ArrayList<RateDomainModel> alRates = new ArrayList<RateDomainModel>(RateDAL.getAllRates());		
		RateDomainModel firstRate = new RateDomainModel();
		double Rate = 0;
		for(RateDomainModel rate: alRates){
			
			if(GivenCreditScore >= rate.getiMinCreditScore()){
		
				firstRate = rate;
				Rate = rate.getdInterestRate();
				
			}
			
		}
		if(Rate == 0){
			
			throw new RateException(firstRate);
			
		}
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return Rate;
		
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(int CreditScore, double n, double p, double f, boolean t) throws RateException
	{		
		
		double InterestRate = getRate((int) CreditScore);
		double Future = 0;
		return FinanceLib.pmt((CreditScore / 1200), (n * (-12)), p, Future, t);
	}
}
