package dvmProject;

import java.util.*;

public class Receiver extends Thread
{

	public String receiverID;
	private DVM dvm;
	public Receiver(DVM dvm) 
	{
		this.dvm = dvm;
	}

	public String getReceiverID() 
	{
		// TODO implement here
		return this.receiverID;
	}

	public void setReceiverID(String receiverID) 
	{
		// TODO implement here
		this.receiverID = receiverID;
	}

	public int responseStockConfirmMsg(Msg msg) 
	{
		// TODO implement here
		return 0;
	}

	public int responseSalesConfirmMsg(Msg msg) 
	{
		// TODO implement here
		return 0;
	}

	public int soldInfo(Msg msg) 
	{
		// TODO implement here
		return 0;
	}
	@Override
	public void run() 
	{
		super.run();
		System.out.println("flag_run()");
	}
}