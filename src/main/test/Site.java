//Site(站点类)

import java.util.ArrayList;
import java.util.List;
public class Site
{
	private Integer id; // 给每个站点分配一个ID
	private String name; // 站点的名字
	private List<Route> list; // 经过该站点的线路
	public Site()
	{
		super();
	}
	public Site(Integer id, String name)
	{
		super();
		this.id = id;
		this.name = name;
		this.list = new ArrayList<Route>();
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public List<Route> getList()
	{
		return list;
	}
	public void setList(List<Route> list)
	{
		this.list = list;
	}
	// 添加线路
	public boolean addRoute(Route route)
	{
		return this.getList().add(route);
	}
	// 删除线路
	public boolean removeRoute(Route route)
	{
		return this.getList().remove(route);
	}
	@Override
	public String toString()
	{
		return name;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Site)
		{
			Site s = (Site) obj;
			return this.id == s.getId() && this.name.equals(s.getName());
		}
		return false;
	}
}