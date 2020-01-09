package com.bus.until;

import java.util.*;

/**
 * Dijkstra 单源最短路径算法
 */
public class Dijkstra
{
	private Queue visited;
	int[] distance;
	private int meter;

	/**
	 * 初始化集合
	 */
	public Dijkstra(int len) {

		visited=new LinkedList();
		distance=new int[len];
	}

	/**
	 * 获取下标
	 */
	private int getIndex(Queue q,int[] dis)
	{
		int k=-1;
		int minNum=Integer.MAX_VALUE;
		for(int i=0;i<dis.length;i++)
		{
			if(!q.contains(i))
			{
				if(dis[i]<minNum)
				{
					minNum=dis[i];
					k=i;
				}
			}
		}
		return k;
	}

	/**
	 * 最短路径算法
	 */
	public void dijkstra(int[][] weight,List<String> str,int v)
	{
		List<String> list=new ArrayList<>();
		HashMap path;
		path=new HashMap();
		for(int i=0;i<str.size();i++){
			path.put(i, "");
		}

		//初始化路径长度数组distance
		for(int i=0;i<str.size();i++)
		{
			path.put(i, path.get(i)+""+str.get(v));
			if(i==v)
			{
				distance[i] = 0;
			}else if(weight[v][i]!=-1)
			{
				distance[i]=weight[v][i];
				path.put(i, path.get(i)+"-->"+str.get(i));
			}
			else
			{
				distance[i] = Integer.MAX_VALUE;
			}
		}
		visited.add(v);
		while(visited.size()<str.size())
		{
			int k=getIndex(visited,distance);//获取未访问点中距离源点最近的点
			visited.add(k);
			if(k!=-1)
			{

				for(int j=0;j<str.size();j++)
				{
					if(weight[k][j]!=-1)//判断k点能够直接到达的点
					{
						//通过遍历各点，比较是否有比当前更短的路径，有的话，则更新distance，并更新path。
						if(distance[j]>distance[k]+weight[k][j])
						{
							distance[j]=distance[k]+weight[k][j];
							path.put(j, path.get(k)+"-->"+str.get(j));
						}
					}

				}
			}
		}
		for(int h=0;h<str.size();h++)
		{
			if(v==0&&h==str.size()-1){
				System.out.println(str.get(v)+"-->"+str.get(h)+":"+distance[h]+" ");
				setMeter(distance[h]);
				if(distance[h]==Integer.MAX_VALUE)
				{
					System.out.print(str.get(v)+"-->"+str.get(h)+ "之间没有可通行路径");
				}else
				{
					System.out.print(str.get(v)+"-"+str.get(h)+ "之间有最短路径，具体路径为：" + path.get(h).toString());
				}
				System.out.println();
			}
		}
		visited.clear();
	}

	public static void main(String[] args)
	{
		int[][] weight= {
				{0,-1,10,-1,30,100},
				{-1,0,5,-1,-1,-1},
				{-1,-1,0,50,-1,-1},
				{-1,-1,-1,0,-1,10},
				{-1,-1,-1,20,0,60},
				{-1,-1,-1,-1,-1,0}};
		List<String> str=new ArrayList<>();
		str.add("V1");
		str.add("V2");
		str.add("V3");
		str.add("V4");
		str.add("V5");
		str.add("V6");

		int len=str.size();
		Dijkstra dijkstra=new Dijkstra(len);
		//依次让各点当源点，并调用dijkstra函数
		for(int i=0;i<str.size();i++)
		{
			dijkstra.dijkstra(weight, str, i);
		}
	}

	public int getMeter()
	{
		return meter;
	}

	public void setMeter(int meter)
	{
		this.meter = meter;
	}
}
