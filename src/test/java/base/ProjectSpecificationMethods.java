package base;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import utils.UtilsClass;

public class ProjectSpecificationMethods extends UtilsClass {

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void lauchBrowser(String browser, String url) {

		browserLaunch(browser, url);

	}

	@DataProvider
	public String[][] excelRead() throws IOException {

		String[][] data = readExcel(sheetname);
		return data;

	}

	@AfterMethod
	public void close() {

		closeBrowser();
	}

}