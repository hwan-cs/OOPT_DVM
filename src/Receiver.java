import DVM_Server.DVMServer;
import Model.Message;
import java.util.*;

public class Receiver extends Thread{ // 상대 DVM에서 발신한 MSG 수신하는 파트

	private String receiverID;
	private DVMServer server;
	private DVM dvm;
	private Message msg;

	public Receiver(DVM dvm) {
		this.dvm = dvm;
		this.server = new DVMServer();
	}

	public String getReceiverID() {
		// TODO implement here
		return "";
	}

	public void setReceiverID(String receiverID) {
		// TODO implement here
	}

	public int responseStockConfirmMsg(Message msg) {
		dvm.getConfirmedDVMList().add(msg);
		return 0;
	}

	public int responseSalesConfirmMsg(Message msg) {
		// 상대 DVM 에서 음료 판매한다는 메세지를 받음 -> List에 받은 메세지 추가
		dvm.getConfirmedDVMList().add(msg);
		return 0;
	}

	public int soldInfo(Msg msg) {
		// TODO implement here
		return 0;
	}
	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				getMSG();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getMSG() {
		/* 여기서 제한시간 걸기 ? 약 1.5초 */
		if (server.msgList.size() > 0) {
			Message msg = server.msgList.get(server.msgList.size() - 1);
			switch (msg.getMsgType()){
				case "StockCheckRequest" : // 우리 DVM에서 응답 필수
					/* 상대 DVM 에서 보낸 메세지 수신하는 파트 */
					break;
				case "StockCheckResponse" :
					responseStockConfirmMsg(msg);
					break;
				case "PrepaymentCheck":
					/* 우리 DVM에서 상대 쪽에서 받은 MSG를 기반으로 음료 코드에 맞는 음료의 개수를 UPDATE */
					break;
				case "SalesCheckRequest": // 우리 DVM에서 응답 필수
					/* 상대 DVM 에서 보낸 메세지 수신하는 파트 */
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