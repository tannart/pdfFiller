package ascentricGui;

import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.ClientHolder;

public class BankDetails extends Page{

	private String type;
	
	public BankDetails(String type){
		this.type = type;
	}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
		
		System.out.println(type);
		this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Client Bank Account Details");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
		if(type == "first"){
			primaryStage.setTitle("First Applicant Bank Account Details");
			Text sceneTitle = new Text("First Applicant Bank Account Details");
	        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
	        grid.add(sceneTitle, 1, 1, 2, 2);
		} else {
			primaryStage.setTitle("Second Applicant Bank Account Details");
			Text sceneTitle = new Text("Second Applicant Bank Account Details");
	        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
	        grid.add(sceneTitle, 1, 1, 2, 2);
		}
        
        thisScene = new Scene(grid, pageWidth, pageHeight);       
        
        createFields(grid);
        
        createMovementButtons();

//        grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);
		
	}

	private void createFields(GridPane grid) {
		
		textFields = new HashMap<String, TextField>();
		
		if(type.equals("first")){
			Label warning = new Label("Failure to provide bank details at the time of application\n "
					+ "may result in delays when making future payments");
			warning.setFont(Font.font(null, FontWeight.BOLD, 12));
			grid.add(warning, 2, 3, 2, 2);
		} else {
			CheckBox same = new CheckBox("Tick if bank account details are same as those of first client");
			grid.add(same, 2, 3);
		}
		
		Label accountHolderName = new Label("Names of account holder(s)");
		grid.add(accountHolderName, 2, 5);
		TextField names1 = new TextField();
		names1.setId("names1");
		textFields.put("names1", names1);
		grid.add(names1, 2, 6, 2, 1);
		TextField names2 = new TextField();
		names2.setId("names2");
		textFields.put("names2", names2);
		grid.add(names2, 2, 7, 2, 1);
		
		Label accountNumber = new Label("Bank/Building Society account number");
		grid.add(accountNumber, 2, 8);
		TextField acctNumber = new TextField();
		acctNumber.setId("accountNumber");
		textFields.put("accountNumber", acctNumber);
		grid.add(acctNumber, 2, 9);
		
		Label srtcde = new Label("Branch sort code");
		grid.add(srtcde, 2, 10);
		TextField sortCode = new TextField();
		sortCode.setId("sortCode");
		textFields.put("sortCode", sortCode);
		grid.add(sortCode, 2, 11);
		
		Label addLabel = new Label("Full Name and Postal address of your\nBank or Building Society");
		addLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		grid.add(addLabel, 4, 4, 2, 2);
		
		Label bankName = new Label("Name");
		grid.add(bankName, 4, 6);
		TextField bankNameField = new TextField();
		bankNameField.setId("bankName");
		textFields.put("bankName", bankNameField);
		grid.add(bankNameField, 5, 6);
		
		Label bankAddress = new Label("Address");
		grid.add(bankAddress, 4, 7);
		TextField bankAddressField1 = new TextField();
		bankAddressField1.setId("bankAddress1");
		textFields.put("bankAddress1", bankAddressField1);
		grid.add(bankAddressField1, 5, 7);
		TextField bankAddressField2 = new TextField();
		bankAddressField2.setId("bankAddress2");
		textFields.put("bankAddress2", bankAddressField2);
		grid.add(bankAddressField2, 5, 8);
		TextField bankAddressField3 = new TextField();
		bankAddressField3.setId("bankAddress3");
		textFields.put("bankAddress3", bankAddressField3);
		grid.add(bankAddressField3, 5, 9);
		
		Label bankPostCode = new Label("Postcode");
		grid.add(bankPostCode, 4, 10);
		TextField bankPostCodeField = new TextField();
		bankPostCodeField.setId("postcode");
		textFields.put("postcode", bankPostCodeField);
		grid.add(bankPostCodeField, 5, 10);
		
	}
	
	protected void fillAndSaveClientInfo() {
		
		BankAccountDetails bd = null;
		
		if(type.equals("first")){
			bd = client.getFirstClient().getBankAccountDetails();
			System.out.println("first");
		} else if(type.equals("second")){
			System.out.println("second");
			bd = client.getSecondClient().getBankAccountDetails();
		}
		
		bd.setAccountHolderNames(textFields.get("names1").getText() + textFields.get("names2").getText());
		bd.setBankAccountNumber(textFields.get("accountNumber").getText());
		bd.setBranchSortCode(textFields.get("sortCode").getText());
		bd.setBankName(textFields.get("bankName").getText());
		bd.setBankAddress(textFields.get("bankAddress1").getText()
				+ textFields.get("bankAddress2").getText()
				+ textFields.get("bankAddress3").getText());
		bd.setBankPostCode(textFields.get("postcode").getText());
		
		System.out.println("More CashTime: "+client.getFirstClient().getProductDetails().getGeneralInvestmentAccount().getCash());
	}

	
}
