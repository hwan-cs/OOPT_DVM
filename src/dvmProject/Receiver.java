package dvmProject;

import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;

import java.sql.SQLOutput;
import java.util.*;

public class Receiver extends Thread
{ // 상대 DVM에서 발신한 MSG 수신하는 파트

	private String receiverID;
	private DVMServer server;
	private DVM dvm;
	private Message msg;
	private Serializer serializer;

	public Receiver(DVM dvm) 
	{
		this.dvm = dvm;
		this.server = new DVMServer();
		this.serializer = new Serializer();
	}

	public String getReceiverID() 
	{
		// TODO implement here
		return "";
	}

	public void setReceiverID(String receiverID) 
	{
		// TODO implement here
	}

	public int responseStockConfirmMsg(Message msg) 
	{
		dvm.getConfirmedDVMList().add(msg);
		return 0;
	}

	public int responseSalesConfirmMsg(Message msg) 
	{
		// 상대 DVM 에서 음료 판매한다는 메세지를 받음 -> List에 받은 메세지 추가
		dvm.getConfirmedDVMList().add(msg);
		return 0;
	}

	public void handlePrepaymentMsg(Message msg) 
	{ // 선결제 메세지 받아서 핸들

		// 재고 차감
		String drinkCode = msg.getMsgDescription().getItemCode();
		int drinkNum = msg.getMsgDescription().getItemNum();
		Drink tempDrink = dvm.getCurrentSellDrink().get(drinkCode);
		tempDrink.setStock(tempDrink.getStock() - drinkNum);
		dvm.getCurrentSellDrink().put(drinkCode, tempDrink); // 작동 완료 확인
		/**/
		// 인증코드 포함된 메세지 넣음
		String keyCode = msg.getMsgDescription().getAuthCode();
		dvm.getODRCHashMap().put(keyCode, msg);
	}

	public void handleStockCheckRequestAndSend(Message msg) 
	{
		String srcId = msg.getSrcId(); // 상대 DVM
		String dstId = msg.getDstID(); // 우리 DVM
		int myX = dvm.getDvm3X();
		int myY = dvm.getDvm3Y();
		String drinkCode = msg.getMsgDescription().getItemCode();
		int drinkNum = msg.getMsgDescription().getItemNum();
		boolean flag = dvm.checkOurDVMStock(drinkCode, drinkNum);
		Message sendToMsg = new Message();
		Message.MessageDescription sendToMsgDesc = new Message.MessageDescription();
		if(flag) 
		{ // 재고 있을 때만 보냄
			sendToMsgDesc.setItemCode(drinkCode);
			sendToMsgDesc.setItemNum(drinkNum);
			sendToMsgDesc.setDvmXCoord(myX);
			sendToMsgDesc.setDvmYCoord(myY);
			// msgDesc 클래스에 setDstID 없음.. 호출 불가

			msgSetting(sendToMsg, dstId, srcId, "StockCheckResponse", sendToMsgDesc);

			// 메세지를 json 타입으로 변환
			String msgToJson = serializer.message2Json(sendToMsg);

			DVMClient client = new DVMClient("localhost", msgToJson); // 메세지 보내기위해 클라이언트 선언

			// 클라이언트에 메세지 실어서 보낸다.
			try 
			{
				client.run();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Message send Failed..");
			}
		}
	}
	public void handleSaleCheckRequestAndSend(Message msg) 
	{
		String srcId = msg.getSrcId(); // 상대 DVM
		String dstId = msg.getDstID(); // 우리 DVM
		int myX = dvm.getDvm3X();
		int myY = dvm.getDvm3Y();
		String drinkCode = msg.getMsgDescription().getItemCode();
		boolean flag = (dvm.getCurrentSellDrink().get(drinkCode) != null);
		Message sendToMsg = new Message();
		Message.MessageDescription sendToMsgDesc = new Message.MessageDescription();
		if(flag) 
		{ // 판매하면 보냄
			// 판매하지 않으면 안보냄?
			sendToMsgDesc.setItemCode(drinkCode);
			sendToMsgDesc.setDvmXCoord(myX);
			sendToMsgDesc.setDvmYCoord(myY);
			// msgDesc 클래스에 setDstID 없음.. 호출 불가

			msgSetting(sendToMsg, dstId, srcId, "SalesCheckResponse", sendToMsgDesc);

			// 메세지를 json 타입으로 변환
			String msgToJson = serializer.message2Json(sendToMsg);

			DVMClient client = new DVMClient("localhost", msgToJson); // 메세지 보내기위해 클라이언트 선언

			// 클라이언트에 메세지 실어서 보낸다.
			try 
			{
				client.run();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Message send Failed..");
			}
		}
	}

	private void msgSetting(Message sendToOtherMsg, String srcID, String dstID, String msgType, Message.MessageDescription msgDesc)
	{
		sendToOtherMsg.setSrcId(srcID);
		sendToOtherMsg.setDstID(dstID);
		sendToOtherMsg.setMsgType(msgType);
		sendToOtherMsg.setMsgDescription(msgDesc);
	}

	@Override
	public void run() 
	{
		super.run();
		try 
		{
			while(true) 
			{
				getMSG();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void getMSG() 
	{
		/* 여기서 제한시간 걸기 ? 약 1.5초 */
		if (server.msgList.size() > 0) 
		{
			Message msg = server.msgList.get(server.msgList.size() - 1);
			switch (msg.getMsgType()){
				case "StockCheckRequest" : // 우리 DVM에서 응답 필수
					/* 상대 DVM 에서 보낸 메세지 수신하는 파트 */
					System.out.println("RECEIVED");
					handleStockCheckRequestAndSend(msg);
					break;
				case "StockCheckResponse" :
					responseStockConfirmMsg(msg);
					break;
				case "PrepaymentCheck":
					/* 우리 DVM에서 상대 쪽에서 받은 MSG를 기반으로 음료 코드에 맞는 음료의 개수를 UPDATE */
					// 재고 먼저 차감
					handlePrepaymentMsg(msg);
					// dvm의 hashmap에 인증코드를 key로, msg를 value로 넣는다
					// 음료랑 개수를 팝업형태로 마지막에 출력해줘야함.
					break;
				case "SalesCheckRequest": // 우리 DVM에서 응답 필수
					/* 상대 DVM 에서 보낸 메세지 수신하는 파트 */
					System.out.println("RECEIVED");
					break;
				case "SalesCheckResponse" :
					responseSalesConfirmMsg(msg);
					break;
				default:
					System.out.println("error!");
					break;
			}
			server.msgList.remove(server.msgList.size() - 1); // add한 메세지 제거
		}
	}
}