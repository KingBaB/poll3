package com.briup.apps.poll.service;

import java.util.List;

//import java.util.List;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.extend.ClazzVM;

public interface IClazzService {
	/*
	 * 查询所有*/
	List<Clazz> findAll() throws Exception;
	
	List<ClazzVM> selectAll() throws Exception;
	
	/*
	 *通过Id查询 */
	Clazz findById(long id) throws Exception;
	
	/*
	 * 通过关键字查询*/
	List<Clazz> query(String keywords) throws Exception;
	
	/*
	 * 保存或者更新*/
	void saveOrUpdate(Clazz clazz) throws Exception;
	
	/*
	 * 通过ID删除*/
	void deleteById(long id) throws Exception;
	
	/*
	 * 批量删除*/

	void batchDeleteById(Long[] ids) throws Exception;

	

	
	
	
	

}
