package com.bus.javabean;

public class CnmAdvertisementBean
{
	private int advertisementId;
	private int cooperativeId ;
	private String advertisingHeadlines;
	private String advertisingType;
	private String releaseTime;
	private String deadline;
	private int stateId;
	private String stateName;
	private String cooperativeName;
	private String advertisingImgurl;

	public CnmAdvertisementBean()
	{
	}
	public CnmAdvertisementBean(int advertisementId, int cooperativeId, String advertisingHeadlines, String advertisingType, String releaseTime, String deadline, int stateId, String stateName, String cooperativeName, String advertisingImgurl)
	{
		this.advertisementId = advertisementId;
		this.cooperativeId = cooperativeId;
		this.advertisingHeadlines = advertisingHeadlines;
		this.advertisingType = advertisingType;
		this.releaseTime = releaseTime;
		this.deadline = deadline;
		this.stateId = stateId;
		this.stateName = stateName;
		this.cooperativeName = cooperativeName;
		this.advertisingImgurl = advertisingImgurl;
	}

	public String getAdvertisingImgurl()
	{
		return advertisingImgurl;
	}

	public void setAdvertisingImgurl(String advertisingImgurl)
	{
		this.advertisingImgurl = advertisingImgurl;
	}

	public int getAdvertisementId()
	{
		return advertisementId;
	}

	public void setAdvertisementId(int advertisementId)
	{
		this.advertisementId = advertisementId;
	}

	public int getCooperativeId()
	{
		return cooperativeId;
	}

	public void setCooperativeId(int cooperativeId)
	{
		this.cooperativeId = cooperativeId;
	}

	public String getAdvertisingHeadlines()
	{
		return advertisingHeadlines;
	}

	public void setAdvertisingHeadlines(String advertisingHeadlines)
	{
		this.advertisingHeadlines = advertisingHeadlines;
	}

	public String getAdvertisingType()
	{
		return advertisingType;
	}

	public void setAdvertisingType(String advertisingType)
	{
		this.advertisingType = advertisingType;
	}

	public String getReleaseTime()
	{
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime)
	{
		this.releaseTime = releaseTime;
	}

	public String getDeadline()
	{
		return deadline;
	}

	public void setDeadline(String deadline)
	{
		this.deadline = deadline;
	}

	public int getStateId()
	{
		return stateId;
	}

	public void setStateId(int stateId)
	{
		this.stateId = stateId;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getCooperativeName()
	{
		return cooperativeName;
	}

	public void setCooperativeName(String cooperativeName)
	{
		this.cooperativeName = cooperativeName;
	}
}
