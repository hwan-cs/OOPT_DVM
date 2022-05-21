package dvmProject;

import DVM_Client.DVMClient;
import GsonConverter.Serializer;
import Model.Message;

import java.util.*;
public class Network {
	ArrayList<Message> checkOtherDVMDrinkExistMsgs;
	String choiceDrinkCode;
	int choiceDrinkNum;
	Serializer serializer;

	public Network(ArrayList<Message> checkOtherDVMDrinkExistMsgs, String choiceDrinkCode, int choiceDrinkNum) {
		checkOtherDVMDrinkExistMsgs = new ArrayList<Message>();
		this.choiceDrinkCode = choiceDrinkCode;
		this.choiceDrinkNum = choiceDrinkNum;
		this.serializer = new Serializer();
		this.checkOtherDVMDrinkExistMsgs = checkOtherDVMDrinkExistMsgs;
	}

	public void sendBroadcastMsg(Message msg) {
		DVMClient dvmClient = new DVMClient("172.20.10.12", serializer.message2Json(msg));
		try {
			dvmClient.run();
			System.out.println("send");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("error send");
		}
	}

	public void checkOtherDVMDrinkExists(){	//모두에 전송
		Message msg1 = new Message();
		Message msg2 = new Message();
		Message msg4 = new Message();
		Message msg5 = new Message();
		Message msg6 = new Message();

		msgSetting(msg1, "01");
		msgSetting(msg2, "02");
		msgSetting(msg4, "04");
		msgSetting(msg5, "05");
		msgSetting(msg6, "06");

		sendBroadcastMsg(msg1);
		sendBroadcastMsg(msg2);
		sendBroadcastMsg(msg4);
		sendBroadcastMsg(msg5);
		sendBroadcastMsg(msg6);
	}

	private void msgSetting(Message msg, String dstId){
		Message.MessageDescription msgDescription = new Message.MessageDescription();
		msg.setSrcId("03");
		msg.setDstID(dstId);
		msg.setMsgType("음료 판매 확인");
		msgDescription.setItemCode(choiceDrinkCode);
		msgDescription.setItemNum(choiceDrinkNum);
		msg.setMsgDescription(msgDescription);
	}

	public void checkOtherDVMStock() {
		// TODO implement here
	}

	public void sendSoldDrinkInfo() {
		// TODO implement here
	}
}