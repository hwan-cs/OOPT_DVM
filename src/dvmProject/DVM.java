package dvmProject;

import java.util.*;

/**
 * 
 */
public class DVM {

	/**
	 * Default constructor
	 */
	public DVM() {
	}

	/**
	 * 
	 */
	public int id;

	/**
	 * 
	 */
	public int[] address;

	/**
	 * 
	 */
	public List<Drink> drinkList;

	/**
	 * 
	 */
	public HashMap<String, Drink> currentSellDrink;

	/**
	 * 
	 */
	public OtherDVMReceiveCode otherDVMReceiveCode;

	/**
	 * 
	 */
	public HashMap<String, List<String>> ODRCHashMap;

	/**
	 * 
	 */
	public Network network;

	/**
	 * 
	 */
	public String createdCode;

	/**
	 * 
	 */
	public List<int[]> confirmedDVMList;

	/**
	 * 
	 */
	public int[] finalDVMLoc;

	/**
	 * 
	 */
	public List<Msg> message;

	/**
	 * @return
	 */
	public void setState() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int getID() {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public void setID() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int[] getAddress() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void setAddress() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int[] getLocation() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public List<Drink> getDrinkList() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void setDrinkList() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void makeSaleConfirmMsgList() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void makeStockConfirmMsgList() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int[] calcClosestDVMLoc() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public boolean checkOurDVMStock() {
		// TODO implement here
		return false;
	}

	/**
	 * @return
	 */
	public boolean recheckStock() {
		// TODO implement here
		return false;
	}

	/**
	 * @return
	 */
	public void sellDrinkAtOtherDVM() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public boolean cardPayment() {
		// TODO implement here
		return false;
	}

	/**
	 * @return
	 */
	public int calcPrice() {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public String createVerificationCode() {
		// TODO implement here
		return "";
	}

	/**
	 * @return
	 */
	public void stockRefresh() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public boolean checkVerificationCode() {
		// TODO implement here
		return false;
	}

}