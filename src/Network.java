import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;

import java.util.*;

public class Network {
	private ArrayList<Message> checkOtherDVMDrinkExistMsgs;
	private String choiceDrinkCode;
	private int choiceDrinkNum;
	private Serializer serializer;

	public Network(String choiceDrinkCode, int choiceDrinkNum) {
		checkOtherDVMDrinkExistMsgs = new ArrayList<Message>();
		this.choiceDrinkCode = choiceDrinkCode;
		this.choiceDrinkNum = choiceDrinkNum;
		this.serializer = new Serializer();
	}

	public void sendBroadcastMsg(Message msg) {
		DVMClient dvmClient = new DVMClient("localhost", serializer.message2Json(msg));
		try {
			dvmClient.run();
			System.out.println("send");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("error send");
		}
	}

	public void checkOtherDVMDrinkExists(){	// 모두에 전송
//		Message msg1 = new Message();
//		Message msg2 = new Message();
//		Message msg4 = new Message();
//		Message msg5 = new Message();
//		Message msg6 = new Message();
//		Message.MessageDescription msgDesc = new Message.MessageDescription();
//		msgDesc.setItemCode(this.choiceDrinkCode);
//		msgDesc.setItemNum(this.choiceDrinkNum);
//		msgSetting(msg1, "Team3", "Team1", "SalesCheckRequest", msgDesc);
//		msgSetting(msg2, "Team3", "Team2", "SalesCheckRequest", msgDesc);
//		msgSetting(msg4, "Team3", "Team4", "SalesCheckRequest", msgDesc);
//		msgSetting(msg5, "Team3", "Team5", "SalesCheckRequest", msgDesc);
//		msgSetting(msg6, "Team3", "Team6", "SalesCheckRequest", msgDesc);
//
//		sendBroadcastMsg(msg1);
//		sendBroadcastMsg(msg2);
//		sendBroadcastMsg(msg4);
//		sendBroadcastMsg(msg5);
//		sendBroadcastMsg(msg6);
	}

	private void msgSetting(Message sendToOtherMsg, String srcID, String dstID, String msgType, Message.MessageDescription msgDesc){
		sendToOtherMsg.setSrcId(srcID);
		sendToOtherMsg.setDstID(dstID);
		sendToOtherMsg.setMsgType(msgType);
		sendToOtherMsg.setMsgDescription(msgDesc);
	}

	public void checkOtherDVMStock(ArrayList<Message> confirmedList) {
		// 음료 재고 확인 메세지 설정
		for(Message msg: confirmedList) {
			Message sendToOtherMsg = new Message(); // 상대 DVM으로 보내는 메세지
			Message.MessageDescription msgDesc = new Message.MessageDescription();

			String srcID = msg.getDstID(); // 도착지 설정
			String dstID = msg.getSrcId();
			String msgType = "StockCheckRequest";
			String drinkCode = msg.getMsgDescription().getItemCode();
			int drinkNum = msg.getMsgDescription().getItemNum();

			msgDesc.setItemCode(drinkCode);
			msgDesc.setItemNum(drinkNum);

			msgSetting(sendToOtherMsg, srcID, dstID, msgType, msgDesc);

			sendBroadcastMsg(msg);
		}
	}

	public void sendSoldDrinkInfo() {
		// TODO implement here
	}
//	{"srcId":"Team3","dstID":"0","msgType":"StockCheckRequest","msgDescription":{"itemCode":"08","itemNum":3}}

}