package com.bus.javabean;

/**
 * 用户建议表
 * id:建议表的ID
 * account：发表用户
 * type：类型
 * content：发表内容
 * phone：手机号
 * imagePath:图片路径
 */
public class CynAdviceBean
{
	private int id;
	private String account;
	private String type;
	private String content;
	private String phone;
	private String imagePath;

	public CynAdviceBean()
	{
	}

	public CynAdviceBean(int id, String account, String type, String content, String phone, String imagePath)
	{
		this.id = id;
		this.account = account;
		this.type = type;
		this.content = content;
		this.phone = phone;
		this.imagePath = imagePath;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}
}
