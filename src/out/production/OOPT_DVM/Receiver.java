package dvmProject;

import Model.Message;

import java.util.*;

public class Receiver extends Thread{

	public String receiverID;
	private DVM dvm;
	ArrayList<Message> messages;

	public Receiver(DVM dvm) {
		this.dvm = dvm;
	}

	public String getReceiverID() {
		// TODO implement here
		return "";
	}

	public void setReceiverID() {
		// TODO implement here
	}

	public int responseStockConfirmMsg() {
		// TODO implement here
		return 0;
	}

	public int responseSalesConfirmMsg(Message msg) {
		// TODO implement here
		return 0;
	}

	public int soldInfo() {
		// TODO implement here
		return 0;
	}

	@Override
	public void run() {
		super.run();
		System.out.println("flag_run()");

	}
}