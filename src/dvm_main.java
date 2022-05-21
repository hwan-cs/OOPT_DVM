import GsonConverter.Deserializer;
import GsonConverter.Serializer;
import Model.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class dvm_main {
    public static void main(String[] args) {
        ServerThreadTest serverThreadTest = new ServerThreadTest();
//        ClientThreadTest clientThreadTest = new ClientThreadTest();
        serverThreadTest.start();

        DVM dvm = new DVM();
        Receiver receiver = new Receiver(dvm); //객체 생성
        Boolean networkConnect = true;  //임시로 일단 true라고 설정했습니다.
        if (networkConnect) {
            receiver.start();   //네트워크 확인 되면 시작
        }
        Admin admin = new Admin(networkConnect, dvm);    //admin에서 system start()

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

//        clientThreadTest.start();

    }
}

//                DVMClient client = new DVMClient("localhost", serializer.message2Json(msg));
//        try {
//                client.run();
//                } catch(Exception e) {
//                e.printStackTrace();
//                }

