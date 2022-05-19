package dvmProject;

import java.util.*;

public class otherDVMReceiveCode 
{

	public String code;
	public List<String> msgDesc;

	public otherDVMReceiveCode() 
	{
		this.code = "";
		this.msgDesc = null;
	}

	public String getCode() 
	{
		// TODO implement here
		return this.code;
	}

	public void setCode(String code) 
	{
		// TODO implement here
		this.code = code;
	}

	public void setMsgDesc(List<String> msgDesc) 
	{
		// TODO implement here
		this.msgDesc = msgDesc;
	}

	public List<String> getMsgDesc() 
	{
		// TODO implement here
		return this.msgDesc;
	}
}