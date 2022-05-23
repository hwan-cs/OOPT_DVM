package dvmProject;

import DVM_Server.DVMServer;

public class ServerThreadTest extends Thread 
{
    DVMServer server;
    public ServerThreadTest() 
    {
    	
    }
    @Override
    public void run() 
    {
        server = new DVMServer();

        try 
        {
            System.out.println("Server running.....");
            server.run();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
    public void getMsg() 
    {
        /* 무쓸모 여기에다 선언하면 안될듯?*/
//        if(server.msgList.size() > 0) {
//            System.out.println("srcID : " + server.msgList.get(server.msgList.size() - 1).getSrcId() + "\n" +
//                    "dstID : " + server.msgList.get(server.msgList.size() - 1).getDstID() + "\n" +
//                    "msgType : " + server.msgList.get(server.msgList.size() - 1).getMsgType() + "\n" +
//                    "--------------msgDesc----------------\n" +
//                    "ItemCode : " + server.msgList.get(server.msgList.size() - 1).getMsgDescription().getItemCode() + "\n" +
//                    "ItemNum : " + server.msgList.get(server.msgList.size() - 1).getMsgDescription().getItemNum() + "\n" +
//                    "dvmXCoord : " + server.msgList.get(server.msgList.size() - 1).getMsgDescription().getDvmXCoord() + "\n" +
//                    "dvmYCoord : " + server.msgList.get(server.msgList.size() - 1).getMsgDescription().getDvmYCoord() + "\n" +
//                    "authCode : " + server.msgList.get(server.msgList.size() - 1).getMsgDescription().getAuthCode() + "\n");
//        } else {
//            System.out.println("no msg");
//        }
    }
}