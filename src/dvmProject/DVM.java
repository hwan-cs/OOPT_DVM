package dvmProject;

import java.util.*;

public class DVM {

	public int id;
	public int[] address;
	public List<Drink> drinkList;
	public HashMap<String, Drink> currentSellDrink;
	public OtherDVMReceiveCode otherDVMReceiveCode;
	public HashMap<String, List<String>> ODRCHashMap;
	public Network network;
	public String createdCode;
	public List<int[]> confirmedDVMList;
	public int[] finalDVMLoc;
	public List<Msg> message;

	public DVM() {
	}

	public void setState() {
		// TODO implement here
	}

	public int getID() {
		// TODO implement here
		return 0;
	}

	public void setID() {
		// TODO implement here
	}

	public int[] getAddress() {
		// TODO implement here
		return null;
	}

	public void setAddress() {
		// TODO implement here
	}

	public int[] getLocation() {
		// TODO implement here
		return null;
	}

	public List<Drink> getDrinkList() {
		// TODO implement here
		return null;
	}

	public void setDrinkList() {
		// TODO implement here
	}

	public void makeSaleConfirmMsgList() {
		// TODO implement here
	}

	public void makeStockConfirmMsgList() {
		// TODO implement here
	}

	public int[] calcClosestDVMLoc() {
		// TODO implement here
		return null;
	}

	public boolean checkOurDVMStock() {
		// TODO implement here
		return false;
	}

	public boolean recheckStock() {
		// TODO implement here
		return false;
	}

	public void sellDrinkAtOtherDVM() {
		// TODO implement here
	}

	public boolean cardPayment() {
		// TODO implement here
		return false;
	}

	public int calcPrice() {
		// TODO implement here
		return 0;
	}

	public String createVerificationCode() {
		// TODO implement here
		return "";
	}


	public void stockRefresh() {
		// TODO implement here
	}

	public boolean checkVerificationCode() {
		// TODO implement here
		return false;
	}
}