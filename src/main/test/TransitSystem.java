//公交系统类
import java.util.ArrayList;
import java.util.List;
public class TransitSystem
{
	/**
	 * 1）增加、修改、删除一个条公交线路信息。
	 * 2）增加、修改、删除公交站点信息。
	 * 3）按条件显示公交线路信息（条件有按线路名称、途经车站等）。
	 */
	private List<Route> routeList ;  //系统所管理的线路
	private List<Site> siteList;  //系统所管理的站点

	public TransitSystem(){
		routeList = new ArrayList<Route>();
		siteList = new ArrayList<Site>();
	}

	//增加一条线路
	public void addRoute(Route route){
		if(!routeList.add(route)){
			throw new RuntimeException("系统中已存在该线路");
		}
	}
	//修改一条线路
	public void updateRoute(Route route){
		//根据route 的id 获取已存在系统中的route
		Route l_route = getRoute(route.getId());
		if(l_route!=null){
			//注入值
			l_route.setName(route.getName());
			l_route.setList(route.getList());
			l_route.setStartDate(route.getStartDate());
			l_route.setEndDate(route.getEndDate());
		}else{
			throw new RuntimeException("线路id不匹配,无法修改");
		}
	}

	//获取该系统中的线路,根据id获取
	public Route getRoute(Integer id){
		Route route2 = null;
		for (Route route : this.getRouteList())
		{
			if(route.getId().equals(id)){
				route2 = route;
			}
		}
		return route2;
	}

	//删除一条线路,根据对象删除
	public void removeRoute(Route route){
		if(!this.getRouteList().remove(route)){
			throw new RuntimeException(route.getId()+"线路不存在");
		}
	}


	//增加一条公交站点
	public void addSite(Site site){
		if(!this.siteList.add(site)){
			throw new RuntimeException(site.getId()+"站点已存在");
		}
	}
	//删除一条公交站点
	public void removeSite(Site site){
		if(!this.getSiteList().remove(site)){
			throw new RuntimeException(site.getId()+"站点不存在");
		}
	}
	//修改一条公交站点
	public void updateSite(Site site){
		//根据route 的id 获取已存在系统中的route
		Site l_site = getSite(site.getId());
		if(l_site!=null){
			//注入值
			l_site.setName(site.getName());
			l_site.setList(site.getList());
		}else{
			throw new RuntimeException("站点id不匹配,无法修改");
		}
	}

	//获取该系统中的公交站点,根据id获取
	public Site getSite(Integer id){
		Site site2 = null;
		for (Site site : this.getSiteList())
		{
			if(site.getId().equals(id)){
				site2 = site;
			}
		}
		return site2;
	}
	//按线路名称查询公交线路信息
	public Route getRouteByName(String name){
		for (Route route : this.getRouteList())
		{
			if(route.getName().equals(name)){
				return route;
			}
		}
		return null;
	}

	//根据途经车站查询公交线路
	public List<Route> getRouteBySite(Site site){
		return site.getList();
	}
	public List<Route> getRouteList()
	{
		return routeList;
	}
	public void setRouteList(List<Route> routeList)
	{
		this.routeList = routeList;
	}
	public List<Site> getSiteList()
	{
		return siteList;
	}
	public void setSiteList(List<Site> siteList)
	{
		this.siteList = siteList;
	}
}