package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricPage5;

import com.itextpdf.text.DocumentException;

public class AscentricPage5Test {
	
	ClientHolder theClient = new ClientHolder();
	AscentricPage5 thePage = new AscentricPage5();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		theClient.makeNewFirstClient();
		theClient.makeNewSecondClient();
		BankAccountDetails fbd = theClient.getFirstClient().getBankAccountDetails();
		BankAccountDetails sbd = theClient.getSecondClient().getBankAccountDetails();
		setBankAccountDetails(fbd);
		setBankAccountDetails(sbd);
		
		fbd.setNoIncomeWithdrawl(true);
		fbd.setLeaveInIncomeAccount(true);
		fbd.setRegWithdrawalAmount("3342");
		fbd.setNatIncomePayTiming("Annually");
		fbd.setRegWithdrawalPayTiming("Annually");
		
	}
	
	private void setBankAccountDetails(BankAccountDetails bd) {
		bd.setAccountHolderNames("Daniel Orthodox");
		bd.setBankAccountNumber("2949291");
		bd.setBankAddress("23 Sutton road, Dumblefor, Yorkshire");
		bd.setBankName("Natwest");
		bd.setBankPostCode("YWD 4XP");
		bd.setBranchSortCode("602847");
		bd.setLeaveInIncomeAccount(true);
		bd.setNatIncomeWrappers("Turnabout");
		bd.setNoIncomeWithdrawl(false);
		bd.setPaymentFromDeposit(999);
		bd.setNatIncomePayTiming("38439");
		bd.setRegWithdrawlWrappers("These are wrappers");
		bd.setStartDate("30092014");
		bd.setWithdrawNaturalIncome(true);
		
	}

	@Test
	public void fillPage() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}

}
