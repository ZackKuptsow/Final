package rocket.app.view;

import java.text.DecimalFormat;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	@FXML
	TextField txtCreditScore;
	@FXML
	TextField txtHouseCost;
	@FXML
	TextField txtIncome;
	@FXML
	TextField txtExpenses;
	@FXML
	ComboBox<String> cmbTerm;
	
	@FXML
	Label lblMortgagePayment;
	
	
	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initiailize(){
		ObservableList<String> cmbBox = FXCollections.observableArrayList("15 years", "30 years");
		cmbTerm.setItems(cmbBox);
	}
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		lblMortgagePayment.setText("");
		
		//Credit Score text input
		int CreditScore = (int)Double.parseDouble(txtCreditScore.getText());
		lq.setIncome(CreditScore);
		System.out.println(CreditScore);
		
		//House Cost text input
		double HouseCost = Double.parseDouble(txtHouseCost.getText());
		lq.setIncome(HouseCost);
		System.out.println(HouseCost);
		
		//Income text input
		double Income = Double.parseDouble(txtIncome.getText());
		lq.setIncome(Income);
		System.out.println(Income);
		
		//Expenses text input
		double Expenses = Double.parseDouble(txtExpenses.getText());
		lq.setIncome(Expenses);
		System.out.println(Expenses);
		
		//Term select
		int Term = 0;
		if(cmbTerm.getValue() == "15 years"){
			Term = 15;
		}else{
			Term = 30;
		}
		lq.setiTerm(Term);
		System.out.println(Term);
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double payment;
		payment = lRequest.getdPayment();
		double elm1 = (.28 * lRequest.getIncome() / 12);
		double elm2 = (.36 * lRequest.getIncome() / 12) - lRequest.getExpenses();
		if(payment >= elm1 & payment >= elm2){
			DecimalFormat decForm = new DecimalFormat("#, ###.##");
			String monthlyPay = "Monthly Payment: $" + decForm.format(payment);
			System.out.println(monthlyPay);
			lblMortgagePayment.setText(monthlyPay);
		}else
			lblMortgagePayment.setText("You can't afford this house.");
		
	}
}
