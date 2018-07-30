package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;

public interface ISurveyService {

	// 查找所有
	List<Survey> findAllSurvey() throws Exception;

	// 级联查看所有课调信信息
	List<SurveyVM> findAllSuerveyVM() throws Exception;

	// 根据ID查询
	Survey findById(long id) throws Exception;

	List<SurveyVM> selectByClazzIdAndCheckPass(long id) throws Exception;
	
	// 通过Id级联查询课调信息
	SurveyVM findSurveyVMById(Long id) throws Exception;

	// 根据关键字查询
	List<Survey> query(String keywords) throws Exception;

	// 保存或更新
	void saveOrUpdate(Survey survey) throws Exception;

	// 级联保存或修改
	void saveOrUpdateSurveyVM(Survey survey) throws Exception;

	// 根据ID删除
	int deleteById(long id) throws Exception;

	// 批量删除
	void batchDelete(Long[] ids) throws Exception;

}
