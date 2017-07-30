package P1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Coperator {
	WebDriver driver;
	FileWriter fw1,fw2,fw3;
	int u1=0,u2=0,u3=0;
	@BeforeMethod
	public void M2() throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
		driver=new ChromeDriver();
		//driver=new FirefoxDriver();
		String k[]=Data(5);
		driver.get(k[3]);
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUserName")).sendKeys(k[4]);
		driver.findElement(By.id("txtPwd")).sendKeys(k[5]);
		Thread.sleep(10000);
		driver.findElement(By.id("btnLogin")).click();
	}
	@Test
	public void T1() throws  IOException, InterruptedException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		String pp[]=Data(1);
		String p1=pp[0];
		 fw1=new FileWriter(new File("code1.txt"));
		 u1=1;
		int startd=Integer.parseInt(pp[1]);
		int diff=Integer.parseInt(pp[2]);
		M1(p1,startd,diff,u1);
	}
	//@Test 
	public void T2() throws IOException, InterruptedException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		String pp[]=Data(2);
		String p1=pp[0];
		 fw1=new FileWriter(new File("code2.txt"));
		 u2=2;
		int startd=Integer.parseInt(pp[1]);
		int diff=Integer.parseInt(pp[2]);
		M1(p1,startd,diff,u2);
	}
	//@Test
	public void T3() throws IOException, InterruptedException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		String pp[]=Data(3);
		String p1=pp[0];
		 fw1=new FileWriter(new File("code3.txt"));
		 u3=3;
		int startd=Integer.parseInt(pp[1]);
		int diff=Integer.parseInt(pp[2]);
		M1(p1,startd,diff,u3);
	}
	public void M1(String ppp,int startd,int diff,int coun) throws InterruptedException, IOException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException{
		BookDB2 db=new BookDB2();
		Thread.sleep(3000);
		try{
		driver.navigate().refresh();
		String tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
		System.out.println("tr=="+tr);
		for(;;){
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			if(tr.equals(ppp)){
				System.out.println("1");
				Thread.sleep(2000);
				break;
			}
			else{
				Thread.sleep(500);
				WebElement next=driver.findElement(By.xpath(".//a[@title='Go to the next month']"));
				Actions c=new Actions(driver);
				c.moveToElement(next).doubleClick().build().perform();
				Thread.sleep(500);
				continue;
			}
		}

		
		int p=startd,l=0,count=diff,hh=0,kk=0;
		SimpleDateFormat dateF = new SimpleDateFormat("dd");
		Date date = new Date();
		int dat4=Integer.parseInt(dateF.format(date));
		System.out.println(dat4);
		List<WebElement> dat=driver.findElements(By.xpath("//table[@id='calDeptDate']/tbody/tr/td/a"));
		
		int s6=dat.size();
		for(int i=p;i<p+s6-1;i++){
			int tt=dat4+i;
			System.out.println("tt......"+i);
			int dat1=Integer.parseInt(driver.findElement(By.xpath(".//a[contains(text(),'"+i+"')]")).getText());
			System.out.println("dat1===="+dat1+"p======"+p);
			if(dat1==p){
				hh++;
				kk=i;;
				break;
			}
		}
		
		while(l<count){
		 s6=dat.size();
		for(int w=p;w<p+s6;w++){
			int tt=p+w;
			if(hh>0&&l<count){
				System.out.println("df------"+w);
		driver.findElement(By.xpath(".//a[contains(text(),'"+w+"')]")).click();
		System.out.println("date----------------"+driver.findElement(By.xpath(".//a[contains(text(),'"+w+"')]")).getText());
		Thread.sleep(2000);
		l++;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Select s=new Select(driver.findElement(By.id("ddlFrmPlace")));
		List<WebElement> sou=s.getOptions();
		int ff=sou.size();
		for(int i=0;i<ff;i++){
			Thread.sleep(2000);
			 s=new Select(driver.findElement(By.id("ddlFrmPlace")));
			 sou=s.getOptions();
			 String Source=sou.get(i).getText();
			System.out.println(sou.get(i).getText());
		s.selectByIndex(i);
		/*
		if(coun==1&&i==0){
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			fw1.write("Jounary date starting=="+w+" "+tr);
			
		}
		else if(coun==1&&i==ff-1)
		{
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			fw1.write("Jounary date starting=="+w+" "+tr);
		
		}
		if(coun==2&&i==0){
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			fw1.write("Jounary date starting=="+w+" "+tr);
			
		}
		else if(coun==2&&i==ff-1)
		{
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			fw1.write("Jounary date starting=="+w+" "+tr);
		
		}
		if(coun==3&&i==0){
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			fw1.write("Jounary date starting=="+w+" "+tr);
			
		}
		else if(coun==3&&i==ff-1)
		{
			tr=driver.findElement(By.xpath(".//*[@id='calDeptDate']/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText();
			fw1.write("Jounary date starting=="+w+" "+tr);
		
		}
		*/
		Thread.sleep(2000);
		Select subp=new Select(driver.findElement(By.id("ddlFrmSubPlace")));
		List<WebElement> sp=subp.getOptions();
		System.out.println(sp.size());
							for(int j=0;j<sp.size();j++){
								subp=new Select(driver.findElement(By.id("ddlFrmSubPlace")));
								sp=subp.getOptions();
								System.out.println(sp.get(j).getText());
								
								subp.selectByIndex(j);
								Thread.sleep(3000);
								List<WebElement> dest=driver.findElements(By.xpath("//div[@id='pnlToPlace']/input"));
								
								int s2=dest.size();
								
								
								for(int y=1;y<=s2;y++){
									System.out.println("5");
									Thread.sleep(5000);
									String destination =driver.findElement(By.xpath("//div[@id='pnlToPlace']/input["+y+"]")).getAttribute("title");
									driver.findElement(By.xpath("//div[@id='pnlToPlace']/input["+y+"]")).click();
									List<WebElement> subdest=driver.findElements(By.xpath(".//div[@id='pnlToSubPlace']/input"));
									int s3=subdest.size();
									for(int z=1;z<=s3;z++){
										Thread.sleep(2000);
										driver.findElement(By.xpath(".//div[@id='pnlToSubPlace']/input["+z+"]")).click();
										List<WebElement> subtime=driver.findElements(By.xpath(".//div[@id='pnlDeptTime']/input"));
										int s4=subtime.size();
										for(int x=1;x<=s4;x++){
											Thread.sleep(2000);
											driver.findElement(By.xpath(".//div[@id='pnlDeptTime']/input["+x+"]")).click();
											Thread.sleep(2000);
											String coachid=driver.findElement(By.id("lblCoachCode")).getText();
											String pickup=driver.findElement(By.id("lblPickup")).getText();
											String droping=driver.findElement(By.id("lblDropOff")).getText();
											double cost=Double.parseDouble(driver.findElement(By.id("lblTicketCost")).getText());
											String operator=driver.findElement(By.id("lblCompanyName")).getText();
											int available=Integer.parseInt(driver.findElement(By.id("lblAvailability")).getText());
											String depature=driver.findElement(By.id("lblDeptTime")).getText();
											String currency=driver.findElement(By.id("lblCurrency")).getText();
											
										    DateFormat readFormat = new SimpleDateFormat( "dd MMM yyyy hh:mm aaa");

										    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
										    Date date1 = null;
										    try {
										       date1 = readFormat.parse( depature );
										    } catch ( ParseException e ) {
										        e.printStackTrace();
										    }

										    String formattedDate = "";
										    if( date1 != null ) {
										    formattedDate = writeFormat.format( date1 );
										    }
										    //hash key related code
										    String m=pickup+droping+depature+coachid+cost;
										    MessageDigest md = MessageDigest.getInstance("MD5");
									        byte[] messageDigest = md.digest(m.getBytes());
									        BigInteger number = new BigInteger(1, messageDigest);
									        String hashtext = number.toString(16);
									        db.insertBookingDetails(operator, pickup, droping, hashtext, coachid,cost,formattedDate,available,currency,Source,destination);
											System.out.println("operator==="+operator+"coachid==="+coachid+"pickup===="+pickup+"droping==="+droping+"cost===="+cost+"availableseat===="+available+"deparature=="+ formattedDate+"hash key ---"+hashtext+"currency--"+currency+"source--"+Source+"destination--"+destination);
											
									}
						
								}
							}
		}
		
	}
			}
		}

		p=0;
		kk=2;
		driver.findElement(By.xpath(".//a[@title='Go to the next month']")).click();
		Thread.sleep(2000);
		dat=driver.findElements(By.xpath("//table[@id='calDeptDate']/tbody/tr/td/a"));
		System.out.println("dat-==--"+dat.size());
		}
		}
		finally{
			if(coun==1){
				fw1.close();
			}
			if(coun==2){
				fw2.close();
			}
			if(coun==3){
				fw3.close();
			}
		}
	}

	public String[] Data(int rr) throws IOException{

		String[] s=new String[6];
		File f=new File("C:\\Users\\CTBCS\\Desktop\\6.xlsx");
		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheetAt(0);
		 for(int i=1;i<ws.getLastRowNum();i=i+4){
		Row r=ws.getRow(i);
		 Cell cpp=r.getCell(2);
		// System.out.println("ffffffff"+cpp.getStringCellValue());
		 Cell cp=r.getCell(3);
		 String ty=cp.getStringCellValue();
		 System.out.println("ty=="+ty);
		 if(ty.equalsIgnoreCase("Y")){
			 Cell cp1=r.getCell(0);
			 String ty1=cp1.getStringCellValue();
			 Cell cp2=r.getCell(1);
			 String ty2=cp2.getStringCellValue();
			 Cell cp3=r.getCell(2);
			 String ty3=" ";
			 try{
			  ty3=cp3.getStringCellValue();
			 }
			 catch(Exception e){
				 int password=(int) cp3.getNumericCellValue();
				 
				 ty3=String.valueOf(password);
			 }
			 System.out.println("ty1=="+ty1+"ty2==="+ty2+"ty3=="+ty3);
			 s[3]=ty1;
			 s[4]=ty2;
			 s[5]=ty3;
			 if(rr!=5){
				 int tt=rr+i;
				 Row r1=ws.getRow(tt);
				 Cell c=r1.getCell(0);
					System.out.println("c====="+c.getStringCellValue());
					
					Cell c1=r1.getCell(1);
					System.out.println("c1====="+c1.getDateCellValue());
					SimpleDateFormat dt=new SimpleDateFormat("dd");
					String DDD=dt.format(c1.getDateCellValue());
					System.out.println("DDD---------"+DDD);
					Cell c2=r1.getCell(2);
					System.out.println("c2====="+c2.getDateCellValue());
					//Cell c3=r.getCell(3);
					//System.out.println("c3====="+c3.getStringCellValue());
					Cell c4=r1.getCell(3);
					System.out.println("c4====="+c4.getNumericCellValue());
					int y=(int)c4.getNumericCellValue();
					int y1=y+1;
					s[0]=c.getStringCellValue();
					s[1]=DDD;
					s[2]=String.valueOf(y1);
			 }
			 else
			 {
				 s[0]=" ";
				 s[1]=" ";
				 s[2]=" ";
			 }
		 }
		 
		
		 }
		return s;
		
	}
	@AfterMethod()
	public void T4(){
		driver.close();
	}

}
