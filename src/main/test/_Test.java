import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
public class _Test
{
	public static void main(String[] args) throws ParseException
	{
		TransitSystem system = new TransitSystem();
		SimpleDateFormat format = new SimpleDateFormat("hh:MM");
		Date startDate = format.parse("07:30");
		Date endDate = format.parse("22:30");
		// 向系统中添加10条线路,20个站点
		for (int i = 0; i < 20; i++)
		{
			if (i < 10)
			{
				system.addRoute(new Route(i, "线路" + i, startDate, endDate));
			}
			system.addSite(new Site(i, "站点" + i));
		}
		List<Site> siteList = system.getSiteList();
		// 为系统中的站点添加线路
		for (Route route : system.getRouteList())
		{
			// 每条线路 随机添加N个站点
			int n = new Random().nextInt(siteList.size());
			for (int i = 0; i < n; i++)
			{
				// 每个站点也是随机,如重复,则添加失败
				int x = new Random().nextInt(siteList.size());
				if (!route.isHasThisSite(siteList.get(x)))
				{
					route.addSite(siteList.get(x));
					// 同时该站点也添加该线路
					siteList.get(x).addRoute(route);
				}
			}
		}
		// 查询所有的线路所包含的站点
		for (Route route : system.getRouteList())
		{
			System.out
					.println(route.getName() + " :对应的站点集合：" + route.getList());
		}
		// 查询所有的站点对应的线路
		for (Site site : system.getSiteList())
		{
			System.out.println("站点" + site.getName() + " :对应的线路集合: "
					+ site.getList());
		}
	}
}