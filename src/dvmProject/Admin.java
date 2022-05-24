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
		int count = 10;				//임의의 변경할 개수
		int editDrink = dvm.getCurrentSellDrink().get(editDrinkCode).getStock();
		dvm.getCurrentSellDrink().get(editDrinkCode).setStock(editDrink+count);
		System.out.println(dvm.getCurrentSellDrink().get(editDrinkCode).getName()+"의 재고는 이제 "+
		(editDrink+count)+"개 입니다!");
	}

	public void systemStart() 
	{
		System.out.println("flag_systemstart()");
		Controller controller = new Controller(dvm);
	}

}