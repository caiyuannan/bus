package com.bus.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bus.javabean.DyfAliPayBean;
import com.bus.javabean.DyfHtmlUserBean;

import com.bus.javabean.Pay;
import com.bus.service.dyfBusTwoService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 40651
 * 支付宝充值控制层
 */
@Controller
public class DyfAliPayController
{
	@Resource
	private dyfBusTwoService dbs;
	private final String APP_ID = "2016102100731631";
	private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC85QOTY++9td7acOQvAyePk/yM9UWoKl4WP3OuIM0/9smZjxqdlBFhQyElxFJYpEusmNoRh1HzCF5cvLkc9+7WRD6eiJA4eEIBeZgJHdkjPRIueHkDBzjZfg55fMb/vUsRyorUVdvq81JrDSjZVKkUr2eWys3SmiXJqgwF3bjv3CxSpRAZRtX16EpWkV/DyIzKEh+ISw1YcULyqSgCN+ccCYwoNyL2okfkcMSKu+TgX5q7c17Ujn7iz5B2lROITB6Y/NsHO/rgEDnkui3UFOyigefD+G3N/ZpEE2MjwKIkD77sTlcQoMlY6J0csL82NVVd4FJmRVAQQhch6y/GRSqvAgMBAAECggEAcq9g4mX0suvvKBUOlXSG2mdxGGv6JMXEZiGULI6Bz2oYsIDwGehCPRky0OlHTcZCo8IR9D6ChNn54Lg8c9gcTD8W/c3QMljhSl/27OIQaZK0rT47ypjh7TrZsu0NLUxSfbTJYW3Xneipi5Fr0bfTCFoOvMDZAhrRAoWZ0x3xIL8wqkIMOjKFx0Qd3jfzkpIvfoYRDvm3jIU9RH31USFs919LU/xmJ7DGrwUoGlH94pyxF7g24y9jaw6luZLB3ZeeYRSlTS1CFg/e8A8uDAJk/DkH3cMd3KgcEYqok2ECQiqinaSDZ6tJlKY5hgtgUKg9EtSV6AVgm6k+k8FTu1lwUQKBgQD/c7jAc40n1qTeghyTeq3XLAmQY4AO6eFocvn4f0lWWcaGIxCHVHebl1oBXXYqPUtLzyz6da7iPRQ+s0NLb0Zz31Z+k5AXPfyqwxLEekWiu4B1UtJp4T8IH6E+XgIqlJbvpMtqiSo2CHfq5AIS1mIcMcWHyHeO92pzJoObSdxkPQKBgQC9TL46solc1tHYSdKGz24ntZjEvkWrddpOecvEAkb9nPuapR874qpuvnqU6JJnzgB0n0r4EXkuqmBhHM9pQvlDQ/t6QRXiTJxB8H/HXcu7GCJFh3Ra/PUtFTS9KaMz+PRR/8EyN/nRA7mg3IGYkijOoooxqzQV/ZBnfDjppuc9WwKBgBFMbthpeseV23hN6015Y0hM7SDf/A4GfIMsNg1D4V19mtn+ieIAMFFTAH9TKutVV0H895jGW19qr3JHhWkYUfcKyrqrl3hbdD7EEwNHHGwWsm4/e4w7+p68NZkZmJ/1i/sSd8vxGfCqxiQb6KAJ8H7kR/IX+zCtRIIju4nKiKgpAoGBALq28YNwCnEaKo1N82upOhvM0MxuaMXATv3MwKGrJV6N8tYMPIyS/EyTcNFjvNTKqkRdwlKA2aifwMTI6AQJXVdtvKRPgkheDFo137ND3unB2jv+aVQ94Y8ANa7HXQSscEBZJbRk5fG13Oi/1swojjuSswBev9dY7ZBFRNWiHL+1AoGAE8SgHOI8vieStasxJGz8JFqVcqArkNX/aYMzh3Hu5vUClWpdZ/Ic4votnCCa34o421idmhlkXMjyp/M9fK4gfulIJvJ9ErGHGj0WOX5Ts4KF1bahM96Ofcdym988h7530yvo6S3quSPft4jyfUXtzJZgvdQEwAPFAqLoQPu3EXQ=";
	private final String CHARSET = "UTF-8";
	private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo5sB8J3zDLaUyS/75EkzPeY8jGf2iizHQJoy2spMeAunTxWFulOzt7SgO7tdGN6pa+Wx+l2QuZ6frNftIVI7RVMb0UiBfBbvlhKrssRNR3r51ERXbTpuBdQ2gXn1W4E1E+iws/hbc0FEDjQgn5PzRzxjrcV8CQcO+EH35NT61GX+hblZy5oJgzMEU6EhycbCLdmYVnxCGFp5sVO2eocJpz4Ppamt9LAWm4e52pXl4c0nZSkk28Q+325WIYif8YzAXDcetm2CqZy52j8KifS1ihq2iv7MYhW0GVwACcS/me2mb18y8rUdPVnOM6WG6tCYYaZgnrraWU3lHwtp+ym1oQIDAQAB";
	//这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
	private final String FORMAT = "JSON";
	//签名方式
	private final String SIGN_TYPE = "RSA2";
	//支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	private final String NOTIFY_URL = "http://公网地址/notifyUrl";
	//支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	private final String RETURN_URL = "http://localhost:8080/bus/returnUrl";
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	//用户钱包信息
	@RequestMapping("/myMoney")
	protected String aliPay( HttpServletRequest request)
	{
		String userPhone = request.getSession().getAttribute("userPhone").toString();
		DyfHtmlUserBean dyfHtmlUserBean = dbs.userSelectAllRepeatTwo(userPhone);
		request.setAttribute("userBean",dyfHtmlUserBean);
		return "/front/DyfMyMoney";
	}


	@RequestMapping("/aliPay")
	@ResponseBody
	protected void aliPay(Integer orderId, HttpServletResponse httpResponse,HttpServletRequest request1,String orderMoney,String orderName) throws IOException
	{
		String userName =request1.getParameter("userName") ;
		String userPointMoney = request1.getParameter("userPointMoney");
		String userType = request1.getParameter("userType");
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		List<DyfAliPayBean> selectAliPay = dbs.selectAliPay();
		//在公共参数中设置回跳和通知地址
		request.setReturnUrl(RETURN_URL);
		request.setNotifyUrl(NOTIFY_URL);
		//根据订单编号,查询订单相关信息
		//商户订单号，商户网站订单系统中唯一订单号，必填
		DyfAliPayBean dyfAliPayBean=null;
		if (selectAliPay.size()>0){
			dyfAliPayBean = selectAliPay.get(0);
		}
		String out_trade_no = String.valueOf(dyfAliPayBean.getOrderId()+1);
		//付款金额，必填
		String total_amount = userPointMoney;
		//订单名称，必填
		String subject = userType;
		String body = "";
		request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		String form = "";
		try {
			form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	//订单成功界面
	@RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
	public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AlipayApiException
	{
		// 获取支付宝GET过来反馈信息
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
		//验证签名通过
		if (signVerified)
		{
			Pay pay=new Pay();
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			DyfHtmlUserBean dyfHtmlUserBean = dbs.userSelectAllRepeatTwo(request.getSession().getAttribute("userPhone").toString());
			// 支付宝交易号
//			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			System.out.println("商户订单号=" + out_trade_no);
//			System.out.println("支付宝交易号=" + trade_no);
			System.out.println("付款金额=" + total_amount);
			pay.setOut_trade_no(Integer.valueOf(out_trade_no));
			pay.setTotal_amount(total_amount);
//			pay.setTrade_no(Long.parseLong(trade_no));
			String money[] = total_amount.split(".");
			BigDecimal bd  = new BigDecimal(total_amount);
			String phoneNum = request.getSession().getAttribute("userPhone").toString();
			int num = dbs.insertAliPay(new DyfAliPayBean(bd.intValue(),1,phoneNum,sdf.format(new Date()))) ;
			Integer userMoney =bd.intValue()+Integer.valueOf(dyfHtmlUserBean.getUserBalance());
			int num1 = dbs.updateUserMoney(String.valueOf(userMoney),phoneNum);
			//支付成功，修复支付状态
			//			payService.updateById(Integer.valueOf(out_trade_no));
			ModelAndView mv = new ModelAndView();
			//			String index="alipaySuccess";
			mv.addObject("out_trade_no", out_trade_no);
//			mv.addObject("trade_no", trade_no);
			mv.addObject("total_amount", total_amount);
			mv.addObject("flag", "success");
			mv.setViewName("/front/alipaySuccess");
			return mv;
		} else
		{
			ModelAndView mv = new ModelAndView("front/dyfMyMoney");
			return mv;//跳转付款失败页面
		}
	}

	//用户成功付款成功后跳转的界面
	@RequestMapping("/successApy")
	protected String successApy(HttpServletRequest request){
		String userPhone = request.getSession().getAttribute("userPhone").toString();
		DyfHtmlUserBean htmlUserBean = dbs.userSelectAllRepeatTwo(userPhone);
		request.setAttribute("userBean",htmlUserBean);
		request.setAttribute("success","success");
		return "/front/account";
	}
}
