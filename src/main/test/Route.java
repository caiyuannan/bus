//线路类
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Route
{
	private Integer id; // 给每个线路分配一个ID
	private String name; // 线路名称
	private List<Site> list; // 线路所包含的站点
	private Date startDate; // 发班时间,从首站点算起
	private Date endDate; // 收班时间,从首站点算起
	public Route()
	{
		super();
	}
	public Route(Integer id, String name, Date startDate, Date endDate)
	{
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.list = new ArrayList<Site>();
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
	public List<Site> getList()
	{
		return list;
	}
	public void setList(List<Site> list)
	{
		this.list = list;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	public boolean isHasThisSite(Site site)
	{
		for (Site l_site : this.getList())
		{
			if (l_site.equals(site))
			{
				return true;
			}
		}
		return false;
	}
	// 添加站点,成功返回true
	public boolean addSite(Site site)
	{
		return this.getList().add(site);
	}
	// 移除站点,成功返回true
	public boolean removeSite(Site site)
	{
		return this.getList().remove(site);
	}
	@Override
	public String toString()
	{
		return name;
	}
}