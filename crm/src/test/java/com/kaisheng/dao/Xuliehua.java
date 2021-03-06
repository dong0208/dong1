package com.kaisheng.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.kaisheng.entity.Person;
import com.kaisheng.entity.Person.Gender;

public class Xuliehua {
	
	@Test
	public void test()throws Exception{
		
		File file = new File("person.out");  
		
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));  
		Person person = new Person("John", 101, Gender.MALE);  
		oout.writeObject(person);  
		oout.close();  
		
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  
		Object newPerson = oin.readObject(); // 没有强制转换到Person类型  
		oin.close();  
		System.out.println(newPerson);  
	}
 
} 

