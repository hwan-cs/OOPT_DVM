package dvmProject;

import java.util.*;

public class Msg 
{

	public String srcId;
	public String dstId;
	public String msgType;
	public String msgDesc;
	public String drinkCode;

	// MsgDesc는 msgType마다 다르게 구성됨
	// 재고 확인 요청 : 음료코드_음료개수 => BroadCast
	// 재고 확인 응답 : 음료코드_음료개수_dstId_dst좌표 => dst > src
	// 선결제 확인 : 음료코드_음료개수_인증코드 => src > dst
	// 음료 판매하는지 확인 : 음료코드_음료개수 => BroadCast
	// 음료 판매하는지 응답 : 음료코드_dstId_dst좌표 => dst > src

	//PMD 빨간색
	public Msg(String srcId, String dstId, String msgType, String msgDesc) 
	{
		this.srcId = srcId;
		this.dstId = dstId;
		this.msgType = msgType;
		this.msgDesc = msgDesc;
	}

	public String getSrcID() 
	{
		return this.srcId;
	}

	public void setSrcID(String srcId) 
	{
		this.srcId = srcId;
	}

	public String getDstID() 
	{
		return this.dstId;
	}

	public void setDstID(String dstId) 
	{
		this.dstId = dstId;
	}

	public String getMsgType() 
	{
		return this.msgType;
	}

	public void setMsgType(String msgType) 
	{
		this.msgType = msgType;
	}

	public String getDrinkCode() 
	{
		return this.drinkCode;
	}
	public void setDrinkCode(String drinkCode) 
	{
		this.drinkCode = drinkCode;
	}

	public String getMsgDesc() 
	{
		return this.msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

}