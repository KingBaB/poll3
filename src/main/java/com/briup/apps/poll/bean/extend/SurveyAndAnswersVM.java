package com.briup.apps.poll.bean.extend;

import java.util.List;
import com.briup.apps.poll.bean.answers;

/**
*          @author
*          @version
*          @describe
*/
public class SurveyAndAnswersVM {
	private SurveyVM surveyVM;
	private List<answers> answers;
	
	public SurveyVM getSurveyVM() {
		return surveyVM;
	}
	public void setSurveyVM(SurveyVM surveyVM) {
		this.surveyVM = surveyVM;
	}
	public List<answers> getAnswers() {
		return answers;
	}
	public void setAnswers(List<answers> answers) {
		this.answers = answers;
	}
	
}