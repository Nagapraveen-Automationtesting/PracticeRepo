package testCases;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class NewTest {
	
	@Parameters({"emulator"})
	public void sample(@Optional("androidOnly")String emulato) {
		System.out.println(emulato);
	}
	
  @Test
  public void f() {
	  if(System.getProperty("env") != null) {
		  System.out.println("************************************");
	  }else {
		  System.out.println("############################");
	  }
	 System.out.println(System.getenv());
  }
}
