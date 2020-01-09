package com.bus.service.Impl;


import com.bus.common.MyException;
import com.bus.dao.CnmSimulationMapper;
import com.bus.javabean.CnmSimulationBean;
import com.bus.service.CnmSimulationService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements CnmSimulationService
{


//	@Autowired
//	private UserMapper userMapper;

	@Resource
	private CnmSimulationMapper cnmSimulationMapper;

//	@Override
//	public List<User> selectUsers() {
//		return userMapper.selectUsers();
//	}


	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		boolean notNull = false;
		List<CnmSimulationBean> CnmSimulationBeanList = new ArrayList<>();
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new MyException("上传文件格式不正确");
		}
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
			wb = new HSSFWorkbook(is);
		} else {
			wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);
		if(sheet!=null){
			notNull = true;
		}
		CnmSimulationBean cnmSimulationBean;
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
			Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
			if (row == null){
				continue;
			}

			//sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException

			cnmSimulationBean = new CnmSimulationBean();

//			if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
//				throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
//			}
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			String stationId = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值

			if(stationId == null || stationId.isEmpty()){//判断是否为空
				throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
			}

			String stationName = row.getCell(1).getStringCellValue();//得到每一行第一个单元格的值

			if(stationName == null || stationName.isEmpty()){//判断是否为空
				throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
			}

			row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			String simulationSum = row.getCell(2).getStringCellValue();//得到每一行第一个单元格的值

			if(simulationSum == null || simulationSum.isEmpty()){//判断是否为空
				throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
			}

			row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
			String simulationTime = row.getCell(3).getStringCellValue();//得到每一行第一个单元格的值

			if(simulationTime == null ){//判断是否为空
				throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
			}





//			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
//			String password = row.getCell(1).getStringCellValue();
//
//
//			if(password==null || password.isEmpty()){
//				throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
//			}


			//完整的循环一次 就组成了一个对象
//			user.setUsername(username);
//			user.setPassword(password);
			cnmSimulationBean.setStationId(Integer.valueOf(stationId));
			cnmSimulationBean.setStationName(stationName);
			cnmSimulationBean.setSimulationSum(Integer.valueOf(simulationSum));
			cnmSimulationBean.setSimulationTime(simulationTime);


			CnmSimulationBeanList.add(cnmSimulationBean);
		}
		for (CnmSimulationBean simulationResord : CnmSimulationBeanList) {
//			String name = simulationResord.getUsername();
//			int cnt = userMapper.selectByName(name);
//			if (cnt == 0) {
			cnmSimulationMapper.addSimulationTable(simulationResord);
				System.out.println(" 插入 "+simulationResord);
//			} else {
//				userMapper.updateUserByName(simulationResord);
//				System.out.println(" 更新 "+simulationResord);
//			}
		}
		return notNull;
	}

}
