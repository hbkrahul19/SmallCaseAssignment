package com.scripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Comparison {
	
	Scenario1 fp = new Scenario1();
//	Scenario2 pc = new Scenario2();
	
	@Test
	public void getPrices() {
		String FPrice = fp.FProductPrice.replace("₹", "").replace(".00", "").replaceAll(",", "");
	//	String APrice = pc.APrice.replace("₹", "").replace(".00", "").replaceAll(",", "");
		Reporter.log(FPrice);
    //	Reporter.log(APrice);
	}
}
