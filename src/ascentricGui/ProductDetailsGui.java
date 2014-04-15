package ascentricGui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;

public abstract class ProductDetailsGui extends Page {

	protected static ProductDetailsGui instance;
	protected int gridVert = 2;
	protected String appType;
	protected Set<Label> theLabels;

	@Override
	public void setUp(Stage primaryStage, Scene previousScene, ClientHolder client) {
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		this.client = client;
		
        grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
        setColumnSizes(grid, 5, 90, 80, 120 ,110 ,80 ,80 ,100 , 80);
        setRowSizes(grid, 10, 20, 20, 20, 20, 50, 20, 20, 20, 30, 20, 20);
        
		setTitleAndHeader(grid);
        set1stLayerLabels(grid);
        
        generate1stLayerFields(grid, "gia");
        generate1stLayerFields(grid, "sas");
        
        generate2ndLayerLabels(grid);
        generate2ndLayerFields(grid);
        
        //Make all text fields except acctName immutable by default 
        TextField[] theFields = textFields.values().toArray(new TextField[0]);
        for(TextField tf: theFields){
        	GridPane.setHalignment(tf, HPos.CENTER);
        	if(tf.getId().startsWith("gia") || tf.getId().startsWith("sas")){
        		tf.setDisable(true);
        	}
        }
        
        for(Label label: theLabels){
        	GridPane.setHalignment(label, HPos.CENTER);
        }
        
        gridVert++;
        
        if(appType.equals("First") && client.getSecondClient()!= null){
        	nextPage = new SecondProductDetails();
        } else {
        	nextPage = new BankDetails("first");
        }
        
        movementButtons2Columns(true);
        createMovementButtons(12, 9);
        
        thisScene = new Scene(grid, pageWidth, pageHeight);
        primaryStage.setScene(thisScene);
	}


	protected Map<String, TextField> set1stLayerLabels(GridPane grid) {
		
		gridVert = 2;
		int firstColumn = 1;
		int thirdColumn = 3;
		int fourthColumn = 4;
		int fifthColumn = 5;
		int sixthColumn = 6;
		int seventhColumn = 7;
		int eighthColumn = 8;
		int ninthColumn = 9;
		
		textFields = new HashMap<String, TextField>();
		checkBoxes = new HashMap<String, CheckBox>();
		theLabels = new HashSet<Label>();
		
		Label top = new Label("If you would like a name other than that of the account holder"
				+ " for your platform account, please enter here: ");
		top.setWrapText(true);
		grid.add(top, firstColumn, gridVert, 4, 2);
		TextField acctName = new TextField();
		acctName.setId("acctName");
		textFields.put("accountName", acctName);
		acctName.setPrefWidth(100);
		grid.add(acctName, fifthColumn, gridVert++, 2, 2);
		GridPane.setValignment(acctName, VPos.CENTER);
		gridVert++;
		
		Label cashLabel = new Label("Cash - Received with \nApplication");
		theLabels.add(cashLabel);
		GridPane.setHalignment(cashLabel, HPos.CENTER);
		grid.add(cashLabel, thirdColumn, ++gridVert, 1, 1);
		
		Label sofLabel = new Label("Source of funds \n(Cheque, BACS, Transfer)");
		theLabels.add(sofLabel);
		sofLabel.setWrapText(true);
		grid.add(sofLabel, fourthColumn, gridVert, 1, 1);
		
		Label transferRegistration = new Label ("Transfer/Re-registration");
		theLabels.add(transferRegistration);
		transferRegistration.setWrapText(true);
		GridPane.setHalignment(transferRegistration, HPos.CENTER);
		grid.add(transferRegistration, fifthColumn, gridVert-1, 2, 1);
		
		Label approxTransCash = new Label ("Approximate cash to be\ntransferred");
		theLabels.add(approxTransCash);
		approxTransCash.setWrapText(true);
		grid.add(approxTransCash, fifthColumn, gridVert, 1, 1);
		
		Label assetsRereg = new Label ("Assets to be\nre-registered");
		theLabels.add(assetsRereg);
		assetsRereg.setWrapText(true);
		grid.add(assetsRereg, sixthColumn, gridVert, 1, 1);
		
		Label reserveAcct = new Label("Reserve\nAccount");
		theLabels.add(reserveAcct);
		reserveAcct.setWrapText(true);
		grid.add(reserveAcct, seventhColumn, gridVert, 1, 1);
		
		Label wrapperType = new Label("Wrapper Type");
		theLabels.add(wrapperType);
		wrapperType.setWrapText(true);
		GridPane.setHalignment(wrapperType, HPos.CENTER);
		grid.add(wrapperType, eighthColumn, gridVert-1, 2, 1);
		
		Label advisoryWrapper = new Label("Advisory\nWrapper");
		theLabels.add(advisoryWrapper);
		advisoryWrapper.setWrapText(true);
		grid.add(advisoryWrapper, eighthColumn, gridVert, 1, 1);
		
		Label discretionaryWrapper = new Label ("Discretionary\nWrapper");
		theLabels.add(discretionaryWrapper);
		discretionaryWrapper.setWrapText(true);
		grid.add(discretionaryWrapper, ninthColumn, gridVert, 1, 1);
		
		CheckBox generalInvestmentAccount = new CheckBox("General Investment Account");
		checkBoxes.put("giaCheckBox", generalInvestmentAccount);
		generalInvestmentAccount.setId("giaCheckBox");
		enableDisableTextFields(generalInvestmentAccount);
		GridPane.setHalignment(generalInvestmentAccount, HPos.LEFT);
		grid.add(generalInvestmentAccount, 1, ++gridVert, 2, 1);
		enableDisableTextFields(generalInvestmentAccount);
		
		CheckBox stocksAndSharesISA = new CheckBox("Stocks & Shares ISA");
		checkBoxes.put("sasCheckBox", stocksAndSharesISA);
		stocksAndSharesISA.setId("sasCheckBox");
		enableDisableTextFields(stocksAndSharesISA);
		GridPane.setHalignment(stocksAndSharesISA, HPos.LEFT);
		stocksAndSharesISA.setWrapText(true);
		grid.add(stocksAndSharesISA, 1, ++gridVert, 2, 1);
		enableDisableTextFields(stocksAndSharesISA);
		
		gridVert--;
		
		return textFields;
		
	}
	
	protected void generate1stLayerFields(GridPane grid, String wrap) {
		
		int fieldWidth = 3;
		
		//generating all the textfields for the wrapper info
		for(int i = 0; i < 5; i++){
			TextField newField = new TextField();
			newField.setId(wrap + i);
			System.out.println(wrap + i);
			newField.setMaxWidth(70);
			textFields.put(wrap+i, newField);
			grid.add(newField, fieldWidth+i, gridVert);
		}
		
		fieldWidth +=5;
		
		CheckBox advWrap = new CheckBox();
		checkBoxes.put("adv" + wrap, advWrap);
		GridPane.setHalignment(advWrap, HPos.CENTER);
		grid.add(advWrap, fieldWidth++, gridVert);
		
		CheckBox discWrap = new CheckBox();
		checkBoxes.put("disc" + wrap, discWrap);
		GridPane.setHalignment(discWrap, HPos.CENTER);
		grid.add(discWrap, fieldWidth, gridVert);
		gridVert++;
		
	}
	
	public abstract void setTitleAndHeader(GridPane grid);
	
	public void fillAndSaveClientInfo() {
		
		ProductDetails pd = null;
		
		if(appType.equals("First")){
			pd = client.getFirstClient().getProductDetails();
			System.out.println("First");
		} else if(appType.equals("Second")){
			System.out.println("Second");
			pd = client.getSecondClient().getProductDetails();
		} else if(appType.equals("Joint")){
			System.out.println("Joint");
			pd = client.getJointAccount().getProductDetails();
		}
		
		pd.setPlatformAccountNameChoice(textFields.get("accountName").getText());
		
		//If the general investment account is selected then fill the details
		if(checkBoxes.get("giaCheckBox").isSelected()){
			pd.makeGeneralInvestmentAccount();
			Wrapper gia = pd.getGeneralInvestmentAccount();
			pd.getGeneralInvestmentAccount().setCash(textFields.get("gia0").getText());
			System.out.println("CashTime:" + gia.getCash());
			gia.setSourceOfFunds(textFields.get("gia1").getText());
			gia.setCashToBeTransferred(textFields.get("gia2").getText());
			gia.setAssetsToBeReregistered(textFields.get("gia3").getText());
			gia.setReserverAccount(textFields.get("gia4").getText());
			gia.setAdvisoryWrapper(checkBoxes.get("advgia").isSelected());
			gia.setDiscretionaryWrapper(checkBoxes.get("discgia").isSelected());
		}
		
		if(checkBoxes.get("sasCheckBox").isSelected()){
			pd.makeStocksAndSharesISA();
			Wrapper sas = pd.getStocksAndSharesISA();
			sas.setCash(textFields.get("sas0").getText());
			sas.setSourceOfFunds(textFields.get("sas1").getText());
			sas.setCashToBeTransferred(textFields.get("sas2").getText());
			sas.setAssetsToBeReregistered(textFields.get("sas3").getText());
			sas.setReserverAccount(textFields.get("sas4").getText());
			sas.setAdvisoryWrapper(checkBoxes.get("advsas").isSelected());
			sas.setDiscretionaryWrapper(checkBoxes.get("discsas").isSelected());
		}
		
		
	}
	
	private void generate2ndLayerLabels(GridPane grid) {
		Label thirdPartyProdAcc = new Label("Third Party Product Accounts \n (insert name of third party)");
		thirdPartyProdAcc.setWrapText(true);
		GridPane.setHalignment(thirdPartyProdAcc, HPos.CENTER);
		GridPane.setValignment(thirdPartyProdAcc, VPos.CENTER);
		grid.add(thirdPartyProdAcc, 2, ++gridVert, 2, 2);
		
		Label amountToBeReceived = new Label("Amount to be received");
		GridPane.setHalignment(amountToBeReceived, HPos.CENTER);
		GridPane.setValignment(amountToBeReceived, VPos.CENTER);
		amountToBeReceived.setWrapText(true);
		grid.add(amountToBeReceived, 4, gridVert, 1, 2);
		
		Label sourceOfFunds = new Label ("Source of Funds \n (Cheque, BACS, Transfer)");
		sourceOfFunds.setWrapText(true);
		GridPane.setHalignment(sourceOfFunds, HPos.CENTER);
		GridPane.setValignment(sourceOfFunds, VPos.CENTER);
		grid.add(sourceOfFunds, 5, gridVert, 1, 2);
		
		Label approxTransCash = new Label ("Wrapper Type");
		GridPane.setHalignment(approxTransCash, HPos.CENTER);
		approxTransCash.setWrapText(true);
		grid.add(approxTransCash, 6, gridVert, 2, 1);

		Label thirdAdvWrap = new Label ("Advisory\nWrapper");
		GridPane.setHalignment(thirdAdvWrap, HPos.CENTER);
		grid.add(thirdAdvWrap, 6, gridVert+1);
		
		Label thirdDiscWrap = new Label ("Discretionary\nWrapper");
		GridPane.setHalignment(thirdDiscWrap, HPos.CENTER);
		grid.add(thirdDiscWrap, 7, gridVert+1);
		
		gridVert+=2;

	}
	
	private void generate2ndLayerFields(GridPane grid) {
		int fieldWidth = 2;
		
		//generating all the textfields for the wrapper info
		for(int i = 0; i < 3; i++){
			TextField newField = new TextField();
			newField.setId("Third" + i);
			System.out.println("Third" + i);
			newField.setMaxWidth(70);
			textFields.put("Third"+i, newField);
			if(i == 0){
				newField.setPrefWidth(70);
				grid.add(newField,  fieldWidth+i, gridVert, 2, 1);
				fieldWidth++;
				continue;
			}
			grid.add(newField, fieldWidth+i, gridVert);
		}
		
		CheckBox advWrap = new CheckBox();
		checkBoxes.put("ThirdAdv", advWrap);
		GridPane.setHalignment(advWrap, HPos.CENTER);
		grid.add(advWrap, fieldWidth+3, gridVert);
		
		CheckBox discWrap = new CheckBox();
		checkBoxes.put("ThirdDisc", discWrap);
		GridPane.setHalignment(discWrap, HPos.CENTER);
		grid.add(discWrap, fieldWidth+4, gridVert);
		gridVert+=3;
	}
	
	/**
	 * Controls whether the wrapper specfic text fields are enabled or disabled, dependent on whether
	 * the relevant check box has been selected
	 * @param cBox The check box associated with wrapper to be enabled or disabled
	 */
	private void enableDisableTextFields(final CheckBox cBox){
		cBox.selectedProperty().addListener(new ChangeListener<Boolean>(){
			public void changed(ObservableValue<? extends Boolean> ov, 
					Boolean oldVal, Boolean newVal){
				TextField[] theFields = textFields.values().toArray(new TextField[0]);
				if(newVal){
			        for(TextField tf: theFields){
			        	if(tf.getId().startsWith(cBox.getId().substring(0,3)))tf.setDisable(false);
			        }
				}
				if(oldVal){
					for(TextField tf: theFields){
			        	if(tf.getId().startsWith(cBox.getId().substring(0,3)))tf.setDisable(true);
			        }
				}
			}
		});
	}
}
