package com.bus.aoplog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Aspect
@Component
public class CynSystemLogAspect
{

//	@Resource
//	//private MangeUserService mangeUserService;
//
//	@Pointcut("within(com.great.springboot.controller.AdminHandler)")
//	public void systemLogAspect() {
//
//	}
//	@After("systemLogAspect()")
//	public void after(JoinPoint joinPoint) throws Throwable
//	{
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest();
//
//		try
//		{
//			//得到实例对象名称
//			String targetName = joinPoint.getTarget().getClass().getName();
//			//得到方法名
//			String methodName = joinPoint.getSignature().getName();
//			//获取连接点方法运行时的入参列表
//			Object[] arguments = joinPoint.getArgs();
//
//			//通过实例对象名称，得到它的对象类
//			Class<?> clazz = Class.forName(targetName);
//			//通过对象类，获取它的所有方法
//			Method[] methods = clazz.getMethods();
//			//查找它所有方法中与所操作事务的方法名一致
//			//查找条件满足
//			//1.方法名相同 2.参数数量一致 3.参数名字是否一致
//			String operatorName = "";
//			String operatorType = "";
//			Class<?>[] paramsTypes = null;
//			boolean isParams = true;
//
//			for (Method method : methods)
//			{
//				//判断方法名相同 参数类型数量是否一致
//				if (method.getName().equals(methodName) && method.getParameterTypes().length == arguments.length)
//				{
//					//获取方法得参数类型
//					paramsTypes = method.getParameterTypes();
//					for (int i = 0; i < paramsTypes.length; i++)
//					{
//
//						if (arguments[i] instanceof HttpServletRequest && paramsTypes[i] == HttpServletRequest.class){
//							continue;
//						}
//						if (arguments[i] instanceof HttpServletResponse && paramsTypes[i] == HttpServletResponse.class){
//							continue;
//						}
//						if (paramsTypes[i].getName().equals(arguments[i].getClass().getName())){
//							continue;
//						}
//						isParams=false;
//						break;
//					}
//					if (isParams)
//					{
//						if(method.getAnnotation(CynLog.class)!=null){
//							//获取日志名字
//							operatorName = method.getAnnotation(CynLog.class).operationName();
//							//获取日志类型
//							operatorType = method.getAnnotation(CynLog.class).operationType();
//							if((!"".equals(operatorType))&&operatorType.length()>0){
//								//获取操作人
//								String actionName=((MangeUser)(request.getSession().getAttribute("user"))).getMangeusername();
//								//获取操作时间
//								String actionTime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//
//								CynSystemLog systemLog = new CynSystemLog();
//								systemLog.setDocumentid(0);
//								systemLog.setActionname(actionName);
//								systemLog.setActiontime(actionTime.split(" ")[0]);
//								systemLog.setActioninf(operatorType);
//								//添加到SQL中
////								ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationSql.xml");
////								MangeUserService loginService=(MangeUserService)context.getBean("mangeUserLoginService");
//								int res=mangeUserService.addLog(systemLog);
//								if(res>0){
//									System.out.println("添加日志成功！");
//								}else {
//									System.out.println("添加日志失败！");
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (ClassNotFoundException e1)
//		{
//			e1.printStackTrace();
//		}
//	}
}
