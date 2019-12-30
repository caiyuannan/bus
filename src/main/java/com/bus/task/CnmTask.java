package com.bus.task;

import com.bus.javabean.CnmAdvertisementBean;
import com.bus.service.CnmAdvertisementService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CnmTask
{
	@Resource
	CnmAdvertisementService cnmAdvertisementService;
//	@Resource
//	CnmAdvertisementBean cnmAdvertisementBean;

	@Scheduled(cron="0 48 22 * * ?")
	public void dd(){
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(dateFormat.format(date)+"_____________________------------------");
		String endDate=dateFormat.format(date);
		CnmAdvertisementBean cnmAdvertisementBean=new CnmAdvertisementBean();
		cnmAdvertisementBean.setDeadline(endDate);
		List<CnmAdvertisementBean> tableList=cnmAdvertisementService.getfindAdvertisement(cnmAdvertisementBean);
		for (int i = 0; i < tableList.size(); i++)
		{
			tableList.get(i).setStateId(2);
			cnmAdvertisementService.getUpdateAdvertisementState(tableList.get(i));
		}

//		System.out.println(tableList.size());
//		System.out.println("1111111111111111111111");

	}
}
