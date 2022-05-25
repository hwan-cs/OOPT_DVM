import Model.Message;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class DVM {

	private int dvm3X = 12, dvm3Y = 47;
	private Card user1Card = new Card(100000); // 임시로 생성한 Card 객체
	private String id;
	private Drink[] drinkList = new Drink[20];	//전체 판매 리스트
	private HashMap<String, Drink> currentSellDrink;
	private HashMap<String, Message> ODRCHashMap; //외부 DVM으로 부터 온 verification code 확인 작업
	private Network network;
	private String createdCode;
	private String[] calcDVMInfo = new String[3]; // = new String[]{"03", String.valueOf(dvm3X), String.valueOf(dvm3Y)};	//[id, x좌표, y좌표] -> 확인된 dvm 변수에서 거리를 계산한 후 저장하는 변수
	private ArrayList<Message> confirmedDVMList; //[[id1, x, y], [id2, x, y], [id3, x, y] ,,,,,] -> 확인된 dvm이 저장되는 변수
	private int choiceDrinkNum = 0;
	private String choiceDrinkCode = "00";

	public int getDvm3X() {
		return dvm3X;
	}

	public int getDvm3Y() {
		return dvm3Y;
	}

	public HashMap<String, Message> getODRCHashMap() {
		return ODRCHashMap;
	}

	public String getChoiceDrinkCode() {
		return this.choiceDrinkCode;
	}

	public void setChoiceDrinkCode(String choiceDrinkCode) {
		this.choiceDrinkCode = choiceDrinkCode;
	}

	public int getChoiceDrinkNum() {
		return this.choiceDrinkNum;
	}

	public void setChoiceDrinkNum(int choiceDrinkNum) {
		this.choiceDrinkNum = choiceDrinkNum;
	}

	public String[] getCalcDVMInfo() {
		return this.calcDVMInfo;
	}

	public void createNetwork() {
		this.network = new Network(this.choiceDrinkCode, this.choiceDrinkNum);
	}
	public Network getNetwork() {
		return this.network;
	}

	public DVM() {
		this.id = "Team3"; //임의로 설정
		this.confirmedDVMList = new ArrayList<>();
		this.currentSellDrink = new HashMap<String, Drink>(7);
		this.ODRCHashMap = new HashMap<>();
		basicSetting();
	}

	public Drink[] getDrinkList() {
		return this.drinkList;
	}

	public void calcClosestDVMLoc() { // getConfirmedDVMList()로 얻은 return 값을 전달함.
//		calcDVMInfo = new String[3];
		// 계산 시작
		if(checkOurDVMStock(this.choiceDrinkCode, this.choiceDrinkNum)) { // true면 선택한 음료 개수보다 우리DVM의 재고가 더 많음
			calcDVMInfo[0] = id;
			calcDVMInfo[1] = String.valueOf(dvm3X);
			calcDVMInfo[2] = String.valueOf(dvm3Y);
			// return calcDVMInfo;
		} else { // 외부 DVM
			int min = Integer.MAX_VALUE;
			String srcId = "";
			int minX = 0, minY = 0;
			if(this.confirmedDVMList != null) {
				for (Message msg : this.confirmedDVMList) {
//					if (msg.getMsgDescription().getItemNum() != 0)
					if (msg.getMsgDescription().getItemNum() >= this.choiceDrinkNum) {
						int x = msg.getMsgDescription().getDvmXCoord();
						int y = msg.getMsgDescription().getDvmYCoord();
						// 거리
						double d = Math.sqrt((int) Math.pow(dvm3X - x, 2) + (int) Math.pow(dvm3Y - y, 2));
						if (min > d) {
							srcId = msg.getSrcId();
							minX = x;
							minY = y;
						}
					}
				}
			}
			calcDVMInfo[0] = srcId;
			calcDVMInfo[1] = String.valueOf(minX);
			calcDVMInfo[2] = String.valueOf(minY);
		}
		// 계산 끝
		if(this.confirmedDVMList != null) {
			this.confirmedDVMList.clear();
		}
	}

	public ArrayList<Message> getConfirmedDVMList() {
		return this.confirmedDVMList;
	}

	public boolean checkOurDVMStock(String drinkCode, int drinkNum) { // 우리 DVM(=DVM3)의 재고 확인
		if(currentSellDrink.get(drinkCode) == null) {
			return false;
		}
		return currentSellDrink.get(drinkCode).getStock() >= drinkNum;
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

	public Card getCard() {
		return this.user1Card;
	}


	public void createVerificationCode() {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		//uuid 10자리 생성
		this.createdCode = uuid;
	}

	public String getVerificationCode() {
		createVerificationCode();
		return this.createdCode;
	}

	public boolean checkVerificationCode(String verifyCode) {
		// this.createdCode는 인증코드 동작하는지 보기 위해 선언한 변수.
		// 실제로 인증코드 확인작업을 하려면 ODRCHashmap에서 가져온 값을 비교해야 함.
		// TODO implement here
		if(this.ODRCHashMap.get(verifyCode) != null)
			return true;

		/* 우리 시스템에서 정상 작동하는지 만든 임시 코드 */
		if(verifyCode.equals("aaaaaaaaaa"))
		{
			setChoiceDrinkNum(5);
			setChoiceDrinkCode("05");
			return true;
		}
		return false;
		/* */
//		return this.createdCode == verifyCode; // 다시 주석 없애야 됨 ??
	}

	private void basicSetting(){
		this.drinkList[0] = new Drink("콜라", 900, 999, "01");
		this.drinkList[1] = new Drink("사이다", 1000, 2, "02");
		this.drinkList[2] = new Drink("녹차", 800, 8, "03");
		this.drinkList[3] = new Drink("홍차", 700, 7, "04");
		this.drinkList[4] = new Drink("밀크티", 1100, 7, "05");
		this.drinkList[5] = new Drink("탄산수", 1200, 7, "06");
		this.drinkList[6] = new Drink("보리차", 1300, 7, "07");
		this.drinkList[7] = new Drink("캔커피", 1400, 0, "08");
		this.drinkList[8] = new Drink("물", 2300, 0, "09");
		this.drinkList[9] = new Drink("에너지드링크", 1500, 0, "10");
		this.drinkList[10] = new Drink("바닷물", 1200, 0, "11");
		this.drinkList[11] = new Drink("식혜", 1200, 0, "12");
		this.drinkList[12] = new Drink("아이스티", 1500, 0, "13");
		this.drinkList[13] = new Drink("딸기주스", 1700, 0, "14");
		this.drinkList[14] = new Drink("오렌지주스", 2000, 0, "15");
		this.drinkList[15] = new Drink("포도주스", 2100, 0, "16");
		this.drinkList[16] = new Drink("이온음료", 3100, 0, "17");
		this.drinkList[17] = new Drink("아메리카노", 4500, 0, "18");
		this.drinkList[18] = new Drink("핫초코", 4500, 0, "19");
		this.drinkList[19] = new Drink("카페라떼", 5000, 0, "20");

		//현재 판매 목록
		this.currentSellDrink.put("01", this.drinkList[0]);
		this.currentSellDrink.put("02", this.drinkList[1]);
		this.currentSellDrink.put("03", this.drinkList[2]);
		this.currentSellDrink.put("04", this.drinkList[3]);
		this.currentSellDrink.put("05", this.drinkList[4]);
		this.currentSellDrink.put("06", this.drinkList[5]);
		this.currentSellDrink.put("07", this.drinkList[6]);
	}
	public HashMap<String, Drink> getCurrentSellDrink() {
		return currentSellDrink;
	}
}
