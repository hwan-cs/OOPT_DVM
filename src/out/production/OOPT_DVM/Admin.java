package dvmProject;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Admin extends JDialog {
	private Boolean networkConnected;
	private DVM dvm;

	public Admin(Boolean networkStatus, DVM dvm) {
		this.networkConnected = networkStatus;
		this.dvm = dvm;
		if (networkConnected){
			systemStart();
		}
	}

	public void refillDrink() {
		String editDrinkCode = "01"; //임의의 코드
		int count = 10;				//임의의 변경할 개수
		Drink editDrink = dvm.getCurrentSellDrink().get(editDrinkCode);
		editDrink.setStock(editDrink.getStock() + count);
	}

	public void systemStart() {
		System.out.println("flag_systemstart()");
		Controller controller = new Controller(dvm);
	}

}