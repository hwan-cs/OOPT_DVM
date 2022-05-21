public class Msg {

	private String src_id;
	private String dst_id;
	private String msgType;
	private String msgDesc;
	private String drinkCode;

	// MsgDesc는 msgType마다 다르게 구성됨
	// 재고 확인 요청 : 음료코드_음료개수 => BroadCast
	// 재고 확인 응답 : 음료코드_음료개수_dstId_dst좌표 => dst > src
	// 선결제 확인 : 음료코드_음료개수_인증코드 => src > dst
	// 음료 판매하는지 확인 : 음료코드_음료개수 => BroadCast
	// 음료 판매하는지 응답 : 음료코드_dstId_dst좌표 => dst > src

	public Msg(String src_id, String dst_id, String msgType, String msgDesc) {
		this.src_id = src_id;
		this.dst_id = dst_id;
		this.msgType = msgType;
		this.msgDesc = msgDesc;
	}

	public String getSrcID() {
		return this.src_id;
	}

	public void setSrcID(String src_id) {
		this.src_id = src_id;
	}

	public String getDstID() {
		return this.dst_id;
	}

	public void setDstID(String dst_id) {
		this.dst_id = dst_id;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getDrinkCode() {
		return this.drinkCode;
	}
	public void setDrinkCode(String drinkCode) {
		this.drinkCode = drinkCode;
	}

	public String getMsgDesc() {
		return this.msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

}