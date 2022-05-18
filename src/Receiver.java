
public class Receiver extends Thread{

	public String receiverID;
	private DVM dvm;
	public Receiver(DVM dvm) {
		this.dvm = dvm;
	}

	public String getReceiverID() {
		// TODO implement here
		return "";
	}

	public void setReceiverID(String receiverID) {
		// TODO implement here
	}

	public int responseStockConfirmMsg(Msg msg) {
		// TODO implement here
		return 0;
	}

	public int responseSalesConfirmMsg(Msg msg) {
		// TODO implement here
		return 0;
	}

	public int soldInfo(Msg msg) {
		// TODO implement here
		return 0;
	}
	@Override
	public void run() {
		super.run();
		System.out.println("flag_run()");
	}
}