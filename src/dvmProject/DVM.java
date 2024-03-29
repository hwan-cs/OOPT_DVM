package dvmProject;

import Model.Message;

import java.lang.reflect.Array;
import java.util.*;

import javax.swing.JOptionPane;

public class DVM extends AbstractDVMClass
{
	private int dvm3X = 12, dvm3Y = 47;

	private Card user1Card = new Card(100000); // 임시로 생성한 Card 객체
	private String id;
	private int state;
	private String address; // address가 String 타입? int[] 타입이어야 하는거 아닌가?
	private Drink[] entireDrinkList = new Drink[20];	//전체 판매 리스트
	private HashMap<String, Drink> currentSellDrink = new HashMap<String, Drink>(7);
	private OtherDVMReceiveCode otherDVMReceiveCode;	// 외부 DVM으로 부터 온 verification code 하나씩 확인 한후 및 해시맵에 풋한다.
	//PMD 빨간색 -> 변수 이름 소문자로 시작해
	private HashMap<String, Message> receivedVerifyCodeMap;	//외부 DVM으로 부터 온 verification code 확인 작업
	private Network network;
	private String createdCode;
	private String[] calculatedDVMInfo = new String[3];// = new String[]{"03", String.valueOf(dvm3X), String.valueOf(dvm3Y)};	//[id, x좌표, y좌표] -> 확인된 dvm 변수에서 거리를 계산한 후 저장하는 변수
	private ArrayList<Message> confirmedDVMList;	//[[id1, x, y], [id2, x, y], [id3, x, y] ,,,,,] -> 확인된 dvm이 저장되는 변수
	private int[] finalDVMLoc; // 최종적으로 최단거리에 있는 DVM의 위치를 담고있는 변수
	private Message[] message;
	private int choiceDrinkNum = 0;
	private String choiceDrinkCode = "00";
	
	private static DVM dvm = new DVM();

	public int getDvm3X()
	{
		return dvm3X;
	}

	public void setDvm3X(int dvm3X) 
	{
		this.dvm3X = dvm3X;
	}

	public int getDvm3Y() 
	{
		return dvm3Y;
	}

	public void setDvm3Y(int dvm3Y) 
	{
		this.dvm3Y = dvm3Y;
	}

	public HashMap<String, Message> getreceivedVerifyCodeMap() 
	{
		return receivedVerifyCodeMap;
	}
	
	public void setODRCHashMap(HashMap<String, Message> ODRCHashMap) 
	{
		this.receivedVerifyCodeMap = ODRCHashMap;
	}

	public String getChoiceDrinkCode() 
	{
		return this.choiceDrinkCode;
	}

	public void setChoiceDrinkCode(String choiceDrinkCode) 
	{
		this.choiceDrinkCode = choiceDrinkCode;
	}

	public int getChoiceDrinkNum() 
	{
		return this.choiceDrinkNum;
	}

	public void setChoiceDrinkNum(int choiceDrinkNum) 
	{
		this.choiceDrinkNum = choiceDrinkNum;
	}

	public String[] getCalculatedDVMInfo() 
	{
		return this.calculatedDVMInfo;
	}

	public void setCalcDVMInfo(String[] calcDVMInfo) 
	{
		this.calculatedDVMInfo = calcDVMInfo;
	}

	public void createNetwork() 
	{
		this.network = new Network(this.choiceDrinkCode, this.choiceDrinkNum);
	}
	
	public Network getNetwork() 
	{
		return this.network;
	}

	//make the constructor private so that this class cannot be
	//instantiated
	private DVM() 
	{
		this.id = "03"; //임의로 설정
		this.address = "DVM3";  //임의로 설정
		basicSetting();
	}

	public static DVM getInstance()
	{
		return dvm;
	}
	
	public void setState(int state) 
	{
		this.state = state;
	}

	public String getID() 
	{
		return id;
	}

	public void setID(String id) 
	{ // id의 타입이 int, String? 둘 중 어떤거?
		this.id = id;
	}

	//PMD 빨간색 -> null 리턴하지 말아라
	public int[] getAddress() 
	{
		// TODO implement here
		return new int[2];
	}

	public void setAddress(int[] address) 
	{
		// TODO implement here

	}

	//PMD 빨간색 -> null 리턴하지 말아라
	public int[] getLocation() 
	{
		// TODO implement here
		return new int[2];
	}

	public Drink[] getEntireDrinkList() 
	{
		return this.entireDrinkList;
	}

	public void setentireDrinkList(Drink[] entireDrinkList) 
	{
		this.entireDrinkList = entireDrinkList;
	}

	@Override
	public void start()
	{
        //ServerThreadTest serverThreadTest = ServerThreadTest.getInstance();
//      ClientThreadTest clientThreadTest = new ClientThreadTest();
        //serverThreadTest.start();

      //	Receiver receiver = Receiver.getInstance(); //객체 생성
      	Boolean networkConnect = true;  //임시로 일단 true라고 설정했습니다.
      
        Admin admin = Admin.getInstance(networkConnect, dvm);    //admin에서 system start()
        Message msg = new Message();
        Message.MessageDescription msgDesc = new Message.MessageDescription();
        
        msgDesc.setItemCode("This is ItemCode");
        msgDesc.setItemNum(208051);
        msgDesc.setDvmXCoord(139);
        msgDesc.setDvmYCoord(202);
        msgDesc.setAuthCode("This is AuthCode");

        msg.setSrcId("dvm3");
        msg.setDstID("hwisik");
        msg.setMsgType("Check, Is it working?");
        msg.setMsgDescription(msgDesc);
	}
	
	@Override
	public void calcClosestDVMLoc() 
	{ // getConfirmedDVMList()로 얻은 return 값을 전달함.
//		calcDVMInfo = new String[3];
		// 계산 시작
		if(checkOurDVMStock(this.choiceDrinkCode, this.choiceDrinkNum)) 
		{ // true면 선택한 음료 개수보다 우리DVM의 재고가 더 많음
			calculatedDVMInfo[0] = id;
			calculatedDVMInfo[1] = String.valueOf(dvm3X);
			calculatedDVMInfo[2] = String.valueOf(dvm3Y);
			// return calcDVMInfo;
		} 
		else 
		{ // 외부 DVM
			calculatedDVMInfo[0] = "VM_06";
			calculatedDVMInfo[1] = "37";
			calculatedDVMInfo[2] = "37";
			
			int minDistance = Integer.MAX_VALUE;
			String srcId = "";
			int minX = 0, minY = 0;
			if(this.confirmedDVMList != null) 
			{
				for (Message msg : this.confirmedDVMList) 
				{
//					if (msg.getMsgDescription().getItemNum() != 0)
					if (msg.getMsgDescription().getItemNum() >= this.choiceDrinkNum) 
					{
						int x = msg.getMsgDescription().getDvmXCoord();
						int y = msg.getMsgDescription().getDvmYCoord();
						// 거리
						double d = Math.sqrt((int) Math.pow(dvm3X - x, 2) + (int) Math.pow(dvm3Y - y, 2));
						if (minDistance > d) 
						{
							srcId = msg.getSrcId();
							minX = x;
							minY = y;
						}
					}
				}
			}
			calculatedDVMInfo[0] = srcId;
			calculatedDVMInfo[1] = String.valueOf(minX);
			calculatedDVMInfo[2] = String.valueOf(minY);
		}
		// 계산 끝
		if(this.confirmedDVMList != null) 
			this.confirmedDVMList.clear();
//		return calcDVMInfo;
	}

	public ArrayList<Message> getConfirmedDVMList() 
	{
		return this.confirmedDVMList;
	}

	@Override
	public boolean checkOurDVMStock(String drinkCode, int drinkNum) 
	{ // 우리 DVM(=DVM3)의 재고 확인
		if(currentSellDrink.get(drinkCode) == null) 
			return false;
		return currentSellDrink.get(drinkCode).getStock() >= drinkNum;
	}

	public boolean recheckStock(Message msg) 
	{
		// 인자로 전달받은 msg를 해독 -> 음료코드&음료개수 얻을 수 있음 -> 얻은 정보를 바탕으로 entireDrinkList
		return false;
	}

	public void sellDrinkAtOtherDVM(String drinkCode, int drinkNum, String verificationCode) 
	{
		// TODO implement here
	}

	public boolean cardPayment(int balance) 
	{ // balance 매개변수는 구매한 (음료 개수 * 가격)임
		return user1Card.getBalance() >= balance;
	}

	public Card getCard() 
	{
		return this.user1Card;
	}

	public int calcPrice(String drinkCode, int drinkNum) 
	{
		int totalPrice = 0;
		Drink currDrink = this.currentSellDrink.get(drinkCode);
		totalPrice += (currDrink.getPrice() * drinkNum);

		return totalPrice;
	}

	public void createVerificationCode() 
	{
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		//uuid 10자리 생성
		this.createdCode = uuid;
	}
	
	public String getCreatedCode()
	{
		return this.createdCode;
	}

	public void stockRefresh(String drinkCode, int drinkNum) 
	{
		/* 잘못 생각함
		// for문으로 모든 drink를 돌아서 효율성 떨어짐.
		// 메뉴에서 음료 버튼을 누르면 그 메뉴의 음료 코드가 drinkCode 매개변수로 전달되서
		// 일일히 for문으로 drinkCode 일치하는 걸 찾지 않게 하는게 더 나아보임. -> entireDrinkList랑 currentSellDrink 헷갈림 ---> 무시!
		*/
		Drink currDrink = this.currentSellDrink.get(drinkCode);
		currDrink.setStock(drinkNum);
		// 정상적으로 stock 변경 되는것 확인함.
	}

	public boolean checkVerificationCode(String verifyCode) 
	{
		// TODO implement here
		if(this.receivedVerifyCodeMap.get(verifyCode) != null)
			return true;
		
//		if(this.createdCode == verifyCode)
//			return true;
//		if(verifyCode.equals("aaaaaaaaaa"))
//		{
//			setChoiceDrinkNum(5);
//			setChoiceDrinkCode("05");
//			return true;
//		}
		return false;
	}

	private void basicSetting()
	{
		this.entireDrinkList[0] = new Drink("콜라", 900, 7, "01");
		this.entireDrinkList[1] = new Drink("사이다", 1000, 2, "02");
		this.entireDrinkList[2] = new Drink("녹차", 800, 8, "03");
		this.entireDrinkList[3] = new Drink("홍차", 700, 7, "04");
		this.entireDrinkList[4] = new Drink("밀크티", 1100, 7, "05");
		this.entireDrinkList[5] = new Drink("탄산수", 1200, 7, "06");
		this.entireDrinkList[6] = new Drink("보리차", 1300, 7, "07");
		this.entireDrinkList[7] = new Drink("캔커피", 1400, 0, "08");
		this.entireDrinkList[8] = new Drink("물", 2300, 0, "09");
		this.entireDrinkList[9] = new Drink("에너지드링크", 1500, 0, "10");
		this.entireDrinkList[10] = new Drink("바닷물", 1200, 0, "11");
		this.entireDrinkList[11] = new Drink("식혜", 1200, 0, "12");
		this.entireDrinkList[12] = new Drink("아이스티", 1500, 0, "13");
		this.entireDrinkList[13] = new Drink("딸기주스", 1700, 0, "14");
		this.entireDrinkList[14] = new Drink("오렌지주스", 2000, 0, "15");
		this.entireDrinkList[15] = new Drink("포도주스", 2100, 0, "16");
		this.entireDrinkList[16] = new Drink("이온음료", 3100, 0, "17");
		this.entireDrinkList[17] = new Drink("아메리카노", 4500, 0, "18");
		this.entireDrinkList[18] = new Drink("핫초코", 4500, 0, "19");
		this.entireDrinkList[19] = new Drink("카페라떼", 5000, 0, "20");

		//현재 판매 목록
		currentSellDrink.put("01", this.entireDrinkList[0]);
		currentSellDrink.put("02", this.entireDrinkList[1]);
		currentSellDrink.put("03", this.entireDrinkList[2]);
		currentSellDrink.put("04", this.entireDrinkList[3]);
		currentSellDrink.put("05", this.entireDrinkList[4]);
		currentSellDrink.put("06", this.entireDrinkList[5]);
		currentSellDrink.put("07", this.entireDrinkList[6]);
	}

	public HashMap<String, Drink> getCurrentSellDrink() 
	{
		return currentSellDrink;
	}

	public void setCurrentSellDrink(HashMap<String, Drink> currentSellDrink) 
	{
		this.currentSellDrink = currentSellDrink;
	}
	
	public boolean purchaseDrink(String drinkCode, int numDrink)
	{
		if (currentSellDrink.get(drinkCode).getStock() >= numDrink)
		{
			currentSellDrink.get(drinkCode).setStock(currentSellDrink.get(drinkCode).getStock()-numDrink);
			return true;
		}
		else 
			JOptionPane.showMessageDialog(null, "재고가 부족합니다!");
		return false;
	}

}
