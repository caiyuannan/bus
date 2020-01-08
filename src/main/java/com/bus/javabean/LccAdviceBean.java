package com.bus.javabean;

/**
 * 用户投诉建议表
 * imagePath:图片路径
 * by连晨诚
 */
public class LccAdviceBean
{
	private int adviceId;
	private String adviceName;
	private String adviceSth;
	private String advicePhone;
	private String imagePath;
	private String processState;

	public LccAdviceBean()
	{
	}

	public LccAdviceBean(int adviceId, String adviceName, String adviceSth, String advicePhone, String imagePath, String processState)
	{
		this.adviceId = adviceId;
		this.adviceName = adviceName;
		this.adviceSth = adviceSth;
		this.advicePhone = advicePhone;
		this.imagePath = imagePath;
		this.processState = processState;
	}

	public int getAdviceId()
	{
		return adviceId;
	}

	public void setAdviceId(int adviceId)
	{
		this.adviceId = adviceId;
	}

	public String getAdviceName()
	{
		return adviceName;
	}

	public void setAdviceName(String adviceName)
	{
		this.adviceName = adviceName;
	}

	public String getAdviceSth()
	{
		return adviceSth;
	}

	public void setAdviceSth(String adviceSth)
	{
		this.adviceSth = adviceSth;
	}

	public String getAdvicePhone()
	{
		return advicePhone;
	}

	public void setAdvicePhone(String advicePhone)
	{
		this.advicePhone = advicePhone;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public String getProcessState()
	{
		return processState;
	}

	public void setProcessState(String processState)
	{
		this.processState = processState;
	}

	@Override
	public String toString()
	{
		return "LccAdviceBean{" + "adviceId=" + adviceId + ", adviceName='" + adviceName + '\'' + ", adviceSth='" + adviceSth + '\'' + ", advicePhone='" + advicePhone + '\'' + ", imagePath='" + imagePath + '\'' + ", processState='" + processState + '\'' + '}';
	}
}

