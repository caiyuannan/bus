package com.bus.aoplog;

/**
 * 日志实体
 *
 * @author zx
 *
 */
public class CynSystemLog
{
	private int loginfid;
	private int documentid;
	private String actionname;
	private String actiontime;
	private String actioninf;

	public CynSystemLog()
	{
	}
	public CynSystemLog(int loginfid, int documentid, String actionname, String actiontime, String actioninf)
	{
		this.loginfid = loginfid;
		this.documentid = documentid;
		this.actionname = actionname;
		this.actiontime = actiontime;
		this.actioninf = actioninf;
	}

	@Override
	public String toString()
	{
		return "CynSystemLog{" + "loginfid=" + loginfid + ", documentid=" + documentid + ", actionname='" + actionname + '\'' + ", actiontime='" + actiontime + '\'' + ", actioninf='" + actioninf + '\'' + '}';
	}

	public int getLoginfid()
	{
		return loginfid;
	}

	public void setLoginfid(int loginfid)
	{
		this.loginfid = loginfid;
	}

	public int getDocumentid()
	{
		return documentid;
	}

	public void setDocumentid(int documentid)
	{
		this.documentid = documentid;
	}

	public String getActionname()
	{
		return actionname;
	}

	public void setActionname(String actionname)
	{
		this.actionname = actionname;
	}

	public String getActiontime()
	{
		return actiontime;
	}

	public void setActiontime(String actiontime)
	{
		this.actiontime = actiontime;
	}

	public String getActioninf()
	{
		return actioninf;
	}

	public void setActioninf(String actioninf)
	{
		this.actioninf = actioninf;
	}
}
