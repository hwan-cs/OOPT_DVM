import java.util.*;

public class DVM {

	public int id;
	public String address;
	public Drink[] drinkList = new Drink[20];	//전체 판매 리스트
	public HashMap<String, Drink> currentSellDrink = new HashMap<String, Drink>(7);
	public OtherDVMReceiveCode otherDVMReceiveCode;	//외부 DVM으로 부터 온 vericode 하나씩 확인 한후 및 해시맵에 풋한다.
	public HashMap<String, List<OtherDVMReceiveCode>> ODRCHashMap;	//외부 DVM으로 부터 온 verification code 확인 작업
	public Network network;
	public String createdCode;
	private String[] calcDVMInfo;	//[id, x좌표, y좌표]
	public List<String[]> confirmedDVMList;	//[[id1, x, y], [id2, x, y], [id3, x, y] ,,,,,]
	public int[] finalDVMLoc;
	public Msg[] message;


	public String[] getCalcDVMInfo() {
		calcClosestDVMLoc(confirmedDVMList);	//원래 여기 있으면 안됨 다시 생각해서 집어넣다
		//지금은 tmp임!!!!!

		return calcDVMInfo;
	}

	public void setCalcDVMInfo(String[] calcDVMInfo) {
		this.calcDVMInfo = calcDVMInfo;
	}

	public DVM() {
		this.id = 3; //임의로 설정
		this.address = "DVM3";  //임의로 설정
		basicSetting();
	}

	public void setState() {
		// TODO implement here
	}

	public int getID() {
		// TODO implement here
		return 0;
	}

	public void setID() {
		// TODO implement here
	}

	public int[] getAddress() {
		// TODO implement here
		return null;
	}

	public void setAddress() {
		// TODO implement here
	}

	public int[] getLocation() {
		// TODO implement here
		return null;
	}

	public Drink[] getDrinkList() {
		return this.drinkList;
	}

	public void setDrinkList() {
		// TODO implement here
	}

	public void makeSaleConfirmMsgList() {
		// TODO implement here
	}

	public void makeStockConfirmMsgList() {
		// TODO implement here
	}

	public int[] calcClosestDVMLoc(List<String[]> confirmedDVMList) {
		// TODO implement here
		//다 계산되어 최종적으로 좌표 리턴해야함.
		//계산하는 알고리즘 작성 요망
		calcDVMInfo = new String[3];	//tmp
		calcDVMInfo[0] = "03";			//tmp
		calcDVMInfo[1] = "11";			//tmp
		calcDVMInfo[2] = "22";			//tmp
		//지금은 임시임 !
		setCalcDVMInfo(calcDVMInfo);
		int[] xy = new int[2];
		//int x = Integer.parseInt(calcDVMInfo[1]) 최소 거리 dvm x좌표
		//int y = Integer.parseInt(calcDVMInfo[2]) 최소 거리 dvm y좌표

		xy[0] = Integer.parseInt(calcDVMInfo[1]);
		xy[1] = Integer.parseInt(calcDVMInfo[2]);

		return xy;
	}

	public boolean checkOurDVMStock() {
		// TODO implement here
		return false;
	}

	public boolean recheckStock() {
		// TODO implement here
		return false;
	}

	public void sellDrinkAtOtherDVM() {
		// TODO implement here
	}

	public boolean cardPayment() {
		// TODO implement here
		return false;
	}

	public int calcPrice() {
		// TODO implement here
		return 0;
	}

	public void createVerificationCode() {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		//uuid 10자리 생성
		this.createdCode = uuid;
	}


	public void stockRefresh() {
		// TODO implement here
	}

	public boolean checkVerificationCode() {
		// TODO implement here
		return false;
	}

	private void basicSetting(){
		this.drinkList[0] = new Drink("콜라", 900, 0, "01");
		this.drinkList[1] = new Drink("사이다", 1000, 0, "02");
		this.drinkList[2] = new Drink("녹차", 800, 0, "03");
		this.drinkList[3] = new Drink("홍차", 700, 0, "04");
		this.drinkList[4] = new Drink("밀크티", 1100, 0, "05");
		this.drinkList[5] = new Drink("탄산수", 1200, 0, "06");
		this.drinkList[6] = new Drink("보리차", 1300, 0, "07");
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
		currentSellDrink.put("01", this.drinkList[0]);
		currentSellDrink.put("02", this.drinkList[1]);
		currentSellDrink.put("03", this.drinkList[2]);
		currentSellDrink.put("04", this.drinkList[3]);
		currentSellDrink.put("05", this.drinkList[4]);
		currentSellDrink.put("06", this.drinkList[5]);
		currentSellDrink.put("07", this.drinkList[6]);
	}

	public HashMap<String, Drink> getCurrentSellDrink() {
		return currentSellDrink;
	}

	public void setCurrentSellDrink(HashMap<String, Drink> currentSellDrink) {
		this.currentSellDrink = currentSellDrink;
	}
}
