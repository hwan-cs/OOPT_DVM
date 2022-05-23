package dvmProject;

import Model.Message;

import java.util.List;

public class OtherDVMReceiveCode 
{

	public String code;
	public Message msg;


	public OtherDVMReceiveCode(String code, Message msg) 
	{
		this.code = code;
		this.msg = msg;
	}

	public String getCode() 
	{
		return this.code;
	}

	public void setCode(String code) 
	{
		this.code = code;
	}

	public Message getMsg() 
	{
		return this.msg;
	}

	public void setMsg(Message msg) 
	{
		this.msg = msg;
	}


}