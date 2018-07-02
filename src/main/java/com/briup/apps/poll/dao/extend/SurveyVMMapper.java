package com.briup.apps.poll.dao.extend;
/**
*          @author
*          @version
*          @describe
*/

import java.util.List;

import com.briup.apps.poll.bean.extend.SurveyVM;

public interface SurveyVMMapper {
	List<SurveyVM> selectAll();
	
	SurveyVM selectSurveyVMById();

}
