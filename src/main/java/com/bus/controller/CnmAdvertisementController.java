package com.bus.controller;

import com.bus.javabean.CnmAdvertisementBean;
import com.bus.javabean.CnmCooperativeBean;
import com.bus.javabean.CnmTablePageInf;
import com.bus.service.CnmAdvertisementService;
import net.minidev.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CnmAdvertisementController
{
	@Resource
	CnmAdvertisementService cnmAdvertisementService;
	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();

	@RequestMapping("/advertisement")
	public ModelAndView treeUrl(){

		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("roleSelect",cnmAdvertisementService.getAdvertisementType());
//		modelAndView.addObject("coopNameSelect",cnmAdvertisementService.getfindCooperativeInf());
		modelAndView.setViewName("backjsp/advertisement");
		return modelAndView;
	}


	@RequestMapping("/showAdvertisementTable")
	@ResponseBody
	public CnmTablePageInf getUserTable(HttpServletRequest request, String page, String limit){

		String cooperativeName=request.getParameter("uername");
		String SelectType=request.getParameter("SelectType");
		String roleSelect=request.getParameter("roleSelect");

		System.out.println(cooperativeName+SelectType+roleSelect+"------------");
		cnmTablePageInf.setCode(0);
		cnmTablePageInf.setMsg("");
		cnmTablePageInf.setData(cnmAdvertisementService.gettable(cooperativeName,SelectType,roleSelect,Integer.valueOf(page),Integer.valueOf(limit)));
		cnmTablePageInf.setCount(cnmAdvertisementService.getTableCount());

		List<CnmAdvertisementBean> tableList=cnmAdvertisementService.getAdvertisementType();

		return cnmTablePageInf;


	}


	@RequestMapping("/deleteAdvertisementTable")
	@ResponseBody
	public String deleteAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean){
		int num=cnmAdvertisementService.getDeleteAdvertisementTable(cnmAdvertisementBean);
		if (num>0){
			return "删除成功";
		}
		return "删除失败";
	}

	@RequestMapping("/addAdvertisementTable")
	@ResponseBody
	public JSONObject addAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean,MultipartFile file){
		JSONObject json=new JSONObject();
		CnmCooperativeBean cnmCooperativeBean=cnmAdvertisementService.getfindCooperativeInf(cnmAdvertisementBean.getCooperativeName());
//		System.out.println("filename="+System.currentTimeMillis()+file.getOriginalFilename());
		long s=System.currentTimeMillis();
		cnmAdvertisementBean.setCooperativeId(cnmCooperativeBean.getCooperativeId());
		cnmAdvertisementBean.setAdvertisingImgurl("/img/"+s+file.getOriginalFilename());
		if (null!=cnmCooperativeBean)
		{
			if(cnmCooperativeBean.getStateId()==2){
				 json.put("msg","改合作商被禁用");
			}else
			{
//				System.out.println("filename="+System.currentTimeMillis()+file.getOriginalFilename());
//				cnmAdvertisementBean.setCooperativeId(cnmCooperativeBean.getCooperativeId());
//				cnmAdvertisementBean.setAdvertisingImgurl(System.currentTimeMillis()+file.getOriginalFilename());
				CnmAdvertisementBean cnmAdvertisementBean2=cnmAdvertisementService.getfindAdvertisementInf(cnmAdvertisementBean);

				if (null==cnmAdvertisementBean2)
                {
                   //正式添加
				   int num = cnmAdvertisementService.getaddAdvertisementTable(cnmAdvertisementBean);
				   if (num > 0)
				  {
					  try
					  {
						  file.transferTo(new File("C:\\Users\\Administrator\\Desktop\\12\\bus\\src\\main\\resources\\static\\img\\"+s+file.getOriginalFilename()));
					  }catch (Exception e){
						  e.printStackTrace();
					  }
					  json.put("msg","添加成功");
				  } else
				  {
				 json.put("msg","添加失败");
				  }
                }else{
					json.put("msg","有记录了");
                }
			}
		}else{
			json.put("msg","无合作商");
		}


		return json;
	}


	@RequestMapping("/updateAdvertisementTable")
	@ResponseBody
	public String updateAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean){

		int num = cnmAdvertisementService.getupdateAdvertisementTable(cnmAdvertisementBean);
		if (num>0){
			return "修改成功";
		}
		return "修改失败";
	}

	@RequestMapping("/updateAdvertisementTableState")
	@ResponseBody
	public String updateAdvertisementTableState(HttpServletRequest request){

		String stateName=request.getParameter("stateName");
		String advertisementId=request.getParameter("advertisementId");
//		System.out.println(stateName+advertisementId+"updateAdvertisementTableState-----------");
		int stateID=cnmAdvertisementService.getfindAdvertisementState(stateName);

		CnmAdvertisementBean cnmAdvertisementBean=new CnmAdvertisementBean();
		cnmAdvertisementBean.setAdvertisementId(Integer.valueOf(advertisementId));
		cnmAdvertisementBean.setStateId(stateID);

		int num = cnmAdvertisementService.getUpdateAdvertisementState(cnmAdvertisementBean);
		if (num>0){
			return "修改成功1";
		}
		return "修改失败1";
	}
//	@RequestMapping("/uploadfile")
//	@ResponseBody
//	public JSONObject upload(MultipartFile file){
//		try
//		{
//			System.out.println("filename="+System.currentTimeMillis()+file.getOriginalFilename());
//			file.transferTo(new File("C:\\Users\\Administrator\\Desktop\\" +file.getOriginalFilename()));
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		JSONObject json=new JSONObject();
//		json.put("msg","success");
//		return json;
//	}



}
