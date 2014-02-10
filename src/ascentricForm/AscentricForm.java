package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.ClientFactory;

import com.itextpdf.text.DocumentException;

/*
 * This Class is used to collate the necessary information on each client to successfully fill in an ascentric form
 * the information collected from SQL is then used by each of the pages in turn to from 
 */
public class AscentricForm{
	
	private AscentricPage page;
	
	/*
	 * Fills the page chosen by the calling class
	 * @return String address of the form with corresponding page filled
	 */
	public void fillIt(ClientFactory theClient) throws IOException, DocumentException {
		
		page = new AscentricPage1();
		//This is an absurdly shitty lazy workaround, I'm going to have to change this some time soon
		//Like, literally unusably bad, a total joke I'll change it on monday morning when, god-willing, I have some more energy
		AscentricPage1 d = new AscentricPage1();
		d.tickBox(theClient);
		page.fillPage(theClient.getFirstClient());
		
		//If a second Client exists then the relevant information is filled in.
		if(theClient.getSecondClient()!=null){
			page = new AscentricPage2();
			page.fillPage(theClient.getSecondClient());
		}
		
		page = new AscentricPage3();
		page.fillPage(theClient.getFirstClient());//Filling in the separate access rights applying to each of the applicants if
		if(!theClient.getSecondClient().equals(null)){//they exist
			page.fillPage(theClient.getSecondClient());
		}
		if(!theClient.getJointAccount().equals(null)){
			page.fillPage(theClient.getJointAccount());
		}
		
		page = new AscentricPage4();
		page.fillPage(theClient);
		page = new AscentricPage5();
		page.fillPage(theClient);
		page = new AscentricPage6();
		page.fillPage(theClient);
		page = new AscentricPage7();
		page.fillPage(theClient);
		page = new AscentricPage8();
		page.fillPage(theClient);
	}

}