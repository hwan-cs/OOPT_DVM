
public class Main{
    public static void main(String[] args) {
        DVM dvm = new DVM();
        Receiver receiver = new Receiver(dvm); //객체 생성
        Boolean networkConnect = true;  //임시로 일단 true라고 설정했습니다.
        if (networkConnect) {
            receiver.start();   //네트워크 확인 되면 시작
        }
        Admin admin = new Admin(networkConnect, dvm);    //admin에서 system start()
    }
}
