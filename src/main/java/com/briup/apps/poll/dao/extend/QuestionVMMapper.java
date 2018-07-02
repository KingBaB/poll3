package com.briup.apps.poll.dao.extend;
/**
*          @author
*          @version
*          @describe
*/

import java.util.List;

import com.briup.apps.poll.bean.extend.QuestionVM;

public interface QuestionVMMapper {
	List<QuestionVM> selectAll();
	
	List<QuestionVM> selectQuestionnaireById(long id);
	
	void savaOrUpdateQuestionVM(QuestionVM questionVM);
	
	

}
