package com.bus.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CnmSimulationService
{




//	List<User> selectUsers();


	boolean batchImport(String fileName, MultipartFile file) throws Exception;
}
