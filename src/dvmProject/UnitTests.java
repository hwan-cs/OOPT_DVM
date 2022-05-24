package dvmProject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitTests 
{
	DVM dvm = new DVM();
	String[] calcDVMInfo = new String[]{"VM_01", "22", "37"};
	int [] address = new int[] {37, 37};
    String[] closestDVM = new String[] {"VM_01", "71", "28"};
    ArrayList listOfDVM = new ArrayList<String[]>();
	HashMap<String, Drink> currentSellDrink = new HashMap<String, Drink>(7);
    
    @BeforeEach
    public void setUp() throws Exception 
    {
    	dvm.setCalcDVMInfo(calcDVMInfo);
    	
    	dvm.setID("VM_02");
    	
    	dvm.setAddress(address);
    	//Network 
    	//UI 빠진거없나

	    String[] example1 = new String[]{"VM_04", "34", "11"};
	    String[] example2 = new String[]{"VM_05", "66", "22"};
	    String[] example3 = new String[]{"VM_02", "55", "37"};
	    String[] example4 = new String[]{"VM_01", "71", "28"};
	 
	    listOfDVM.add(example1);
	    listOfDVM.add(example2);
	    listOfDVM.add(example3);
	    listOfDVM.add(example4);
	    
	    dvm.getCard().setBalance(7200);
	    
	    dvm.getDrinkList()[0].setName("UnitTestDrink");
	    dvm.getDrinkList()[0].setPrice(9999);
	    dvm.getDrinkList()[0].setStock(99);
	    dvm.getDrinkList()[0].setDrinkCode("99");

		currentSellDrink.put("01", dvm.getDrinkList()[0]);
		currentSellDrink.put("02", dvm.getDrinkList()[1]);
		currentSellDrink.put("03", dvm.getDrinkList()[2]);
		currentSellDrink.put("04", dvm.getDrinkList()[3]);
		currentSellDrink.put("05", dvm.getDrinkList()[4]);
		currentSellDrink.put("06", dvm.getDrinkList()[5]);
		currentSellDrink.put("07", dvm.getDrinkList()[6]);
		
		dvm.setCurrentSellDrink(currentSellDrink);
		
		dvm.setDvm3X(37);
		dvm.setDvm3Y(73);
    }
    
	@Test
	void test() 
	{
		Assert.assertArrayEquals(calcDVMInfo, dvm.getCalcDVMInfo());
		Assert.assertEquals("VM_02", dvm.getID());
		Assert.assertArrayEquals(address, dvm.getAddress());
		//setDrinkList 안함
		
	    Assert.assertEquals(true, dvm.cardPayment(92800));
	    Assert.assertEquals(7200, dvm.calcPrice("06", 6));
	    //setCurrentSellDrink 안함
	    Assert.assertEquals(92800, dvm.getCard().getBalance());
	    Assert.assertEquals("UnitTestDrink", dvm.getDrinkList()[0].getName());
	    Assert.assertEquals(9999, dvm.getDrinkList()[0].getPrice());
	    Assert.assertEquals(99, dvm.getDrinkList()[0].getStock());
	    Assert.assertEquals("99", dvm.getDrinkList()[0].getDrinkCode());
	    Assert.assertEquals(currentSellDrink, dvm.getCurrentSellDrink());
	    
	    Assert.assertEquals(37, dvm.getDvm3X());
	    Assert.assertEquals(73, dvm.getDvm3Y());
	    
	    Assert.assertEquals(true, dvm.checkOurDVMStock("06", 1));
	}

}
