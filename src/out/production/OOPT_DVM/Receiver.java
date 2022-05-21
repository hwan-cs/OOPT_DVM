package dvmProject;

import DVM_Server.DVMServer;
import GsonConverter.Deserializer;
import GsonConverter.Serializer;
import Model.Message;

import java.util.*;

public class Receiver extends Thread{

	DVMServer server;
	DVM dvm;
	Message msg;


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
		dvm.getConfirmedDVMList().add(msg);
		return 0;
	}

	public int soldInfo() {
		// TODO implement here
		return 0;
	}

	@Override
	public void run() {
		super.run();
		server = new DVMServer();
		try {
			while(true) {
				getMSG();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getMSG() {
		if (server.msgList.size() > 0) {
			Message msg = server.msgList.get(server.msgList.size() - 1);
			switch (msg.getMsgType()){
				case "재고 확인 요청":

					break;
				case "음료 판매 응답":
					responseSalesConfirmMsg(msg);
					break;

				case "선 결제 확인":
					break;

				default:
					System.out.println("error!");
					break;
			}
			server.msgList.remove(server.msgList.size() - 1);
		}

	}
}