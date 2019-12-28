package com.bus.javabean;
/**
 * 人脸识别类(所有职员通用)
 * by连晨诚
 */
public class Face
{

	private int id;
	private byte[] face;

	public Face()
	{
	}

	public Face(int id, byte[] face)
	{
		this.id = id;
		this.face = face;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public byte[] getFace()
	{
		return face;
	}

	public void setFace(byte[] face)
	{
		this.face = face;
	}
}
