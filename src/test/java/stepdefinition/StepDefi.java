package stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//
public class StepDefi {
    WebDriver driver;
    @Before
    public void setup(){
        System.out.println("Setup Before");
    }
    @Given("user is on home page")
    public void user_on_home_page(){
        System.out.println("User is on Home Page");
    }
    @When("open the url")
    public void openUrl(DataTable table){
        List<String> details = table.asList(String.class);
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\"+"chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(details.get(0));
    }
    @Given("This is smoke test case")
    public void smokeTestCase(){
        System.out.println("this is smoke test cases");
    }
    @Given("User logs out")
    public void user_on_logout_page(){
        System.out.println("User logout");
    }

    @Given("User is on Home Page")
    public void user_on_home_page_1(){
        System.out.println("User is on Home Page_1");
    }
    @Given("user clicks login button")
    public void user_clicks_login_button(){
        System.out.println("User clicks login button");
    }
    @After
    public void teardown(Scenario sc){
        if(sc.isFailed()==true){
            String filepath=System.getProperty("user.dir");
            System.out.println("Filepath is failed");
        }
        else{
            System.out.println("Filepath is passed");
        }
        if(driver!=null)
            driver.quit();
    }
    @AfterStep
    public void addScreenshot(Scenario sc){
        System.out.println("Sceanrio Name is"+sc.getName());
        System.out.println("Sceanrio Tag is"+sc.getStatus());
    }
    @Given("This scenario is failed")
    public void scenarioFailed(){
        System.out.println("This Sceanrio is failed");
        Assert.assertTrue(false);
    }

    @When("provide the assignment values and update the package value to the excel file")
    public void provideTheAssignmentValuesAndUpdateThePackageValueToTheExcelFile(DataTable testData) throws InterruptedException, IOException {
        Map<String,String> package_assignment_mapping=new LinkedHashMap<>();
        List<String> details = testData.asList(String.class);
        for(int i=0;i<details.size();i++){
          driver.findElement(By.name("lrno")).clear();
          driver.findElement(By.name("lrno")).sendKeys(details.get(i));
          driver.findElement(By.xpath("//input[@value='Track']")).click();
          //wait for some time for package details to load
          Thread.sleep(10000);
          String packages=driver.findElement(By.xpath("//*[text()='Packages']/..")).getText();
            packages=String.valueOf(packages.charAt(packages.length()-1));
            package_assignment_mapping.put(details.get(i),packages);
        }
         writeToExcel(package_assignment_mapping);
   }
    private void writeToExcel(Map<String,String> mapelement) throws IOException {
        try{
        FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+ "\\"+"package.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell = null;
        //Update the value of cell
        Iterator hmIterator =mapelement.entrySet().iterator();
        int i=1;
        while(hmIterator.hasNext()){
            Map.Entry mapelement1=(Map.Entry)hmIterator.next();
            XSSFRow row=sheet.createRow(i);
            XSSFCell cell1=row.createCell(0);
            XSSFCell cell2=row.createCell(1);
            cell1.setCellValue((String)mapelement1.getKey());
            cell2.setCellValue((String)mapelement1.getValue());
            i++;
           }
       file.close();
       FileOutputStream outFile =new FileOutputStream(new File(System.getProperty("user.dir")+ "\\"+"package.xlsx"));
       workbook.write(outFile);
       outFile.close();
    }
      catch(FileNotFoundException e1) {
        e1.printStackTrace();
    } catch(IOException e2) {
        e2.printStackTrace();
    }
    }
}

