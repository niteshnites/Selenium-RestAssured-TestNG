package selenium_practice.main.extends_reports;

public class ExtentReport {

    public void getReportObject(){
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter = new ExtentSparkReporter();
    }

}