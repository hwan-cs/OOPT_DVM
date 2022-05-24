package dvmProject;

import java.util.*;

public class Admin  
{
	private Boolean networkConnected;
	private DVM dvm;

	public Admin(Boolean networkStatus, DVM dvm) 
	{
		this.networkConnected = networkStatus;
		this.dvm = dvm;
		if (this.networkConnected)
		{
			systemStart();
		}
	}

	public void refillDrink() 
	{
		String editDrinkCode = "01"; //임의의 코드
		for(int i = 0;i<dvm.getDrinkList().length;i++)
		{
			if(dvm.getCurrentSellDrink().get(editDrinkCode) != null)
			{
				dvm.getCurrentSellDrink().get(editDrinkCode).setStock(100);
				System.out.println(dvm.getCurrentSellDrink().get(editDrinkCode).getName()+"의 재고는 이제 100개 입니다!");
				editDrinkCode = "0"+Integer.toString(Integer.parseInt(editDrinkCode)+1);
			}
				
		}
	}

	public void systemStart() 
	{
		System.out.println("flag_systemstart()");
		Controller controller = new Controller(dvm);
	}

}