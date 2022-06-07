package dvmProject;

import GsonConverter.Deserializer;
import GsonConverter.Serializer;
import Model.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class dvm_Main 
{
    public static void main(String[] args) 
    {
        DVM dvm = DVM.getInstance();
        dvm.start();
    }
}