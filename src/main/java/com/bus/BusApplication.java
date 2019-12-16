package com.bus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bus.mapper")
public class BusApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(BusApplication.class, args);
	}

}
