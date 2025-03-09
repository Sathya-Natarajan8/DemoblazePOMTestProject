package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports getReport() {
		String path ="C:\\Users\\sathy\\eclipse-workspace\\demoblazeProject\\reports\\DemoblazePOMProjectTestResults.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("DemoblazePOMProjectTestResults");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
}
