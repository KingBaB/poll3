package com.briup.apps.poll.bean.extend;

import java.util.List;

import io.swagger.annotations.Api;

/**
 * @author
 * @version
 * @describe
 */
@Api(value="问卷模型，问卷中包含多个问题,如果问题是单选和多选，该问题应该包含选项信息")
public class QuestionnaireVM {
	private long id;
	private String name;
	private String description;
	private List<QuestionVM> questionVMs;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<QuestionVM> getQuestionVMs() {
		return questionVMs;
	}
	public void setQuestionVMs(List<QuestionVM> questionVMs) {
		this.questionVMs = questionVMs;
	}
	
	
}
