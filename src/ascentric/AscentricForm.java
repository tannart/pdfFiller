package ascentric;

import java.io.IOException;

import pdfFiller.AscentricPage;

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
	public void fillIt(String client) throws IOException, DocumentException {
		
		page = new AscentricPage1();
		page.fillPage();
		page = new AscentricPage2();
		page.fillPage();
		page = new AscentricPage3();
		page.fillPage();
		
	}

}