package com.briup.apps.poll.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Qq;
import com.briup.apps.poll.bean.QqExample;
import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.QuestionnaireExample;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.dao.QqMapper;
//import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.dao.QuestionnaireMapper;
import com.briup.apps.poll.dao.extend.QuestionnaireVMMapper;
import com.briup.apps.poll.service.IQuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService {

	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	@Autowired
	private QuestionnaireVMMapper questionnaireVMMapper;
	@Autowired
	private QqMapper qqMapper;

	@Override
	public List<Questionnaire> findAll() throws Exception {
		QuestionnaireExample example = new QuestionnaireExample();
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Questionnaire findById(long id) throws Exception {

		return questionnaireMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Questionnaire> query(String keywords) throws Exception {
		QuestionnaireExample example = new QuestionnaireExample();
		example.createCriteria().andNameLike(keywords);
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Questionnaire questionnaire) throws Exception {
		if (questionnaire.getId() != null) {
			questionnaireMapper.updateByPrimaryKey(questionnaire);
		} else {
			questionnaireMapper.insert(questionnaire);
		}

	}

	@Override
	public void deleteById(long id) throws Exception {
		questionnaireMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		for (Long id : ids) {
			questionnaireMapper.deleteByPrimaryKey(id);
		}

	}

	@Override
	public QuestionnaireVM findQuestionnaireVMById(long id) throws Exception {
		return questionnaireVMMapper.selectQuestionnaireById(id);
	}

	@Override
	public void saveOrUpdate(Questionnaire questionnaire, Long[] questionIds) throws Exception {
		//判断保存还是修改
		if (questionnaire.getId() == null) {
			// 1、保存
			//1.1保存问卷信息
			questionnaireMapper.insert(questionnaire);
			//1.1维护问卷和问题的关系
			long questionnaireId=questionnaire.getId();
			for(Long questionId:questionIds){
				Qq qq=new Qq();
				qq.setQuestionId(questionId);
				qq.setQuestionnaireId(questionnaireId);
				qqMapper.insert(qq);
			}
		}else{
			//2、修改
			//2.1修改问卷信息
			questionnaireMapper.updateByPrimaryKeyWithBLOBs(questionnaire);
			//2.2删除原有的问卷和问题关系
			long questionnaireId=questionnaire.getId();
			QqExample example=new QqExample();
			example.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
			qqMapper.deleteByExample(example);
			//2.3保存新的问卷和问题关系
			for(Long questionId:questionIds){
				Qq qq=new Qq();
				qq.setQuestionId(questionId);
				qq.setQuestionnaireId(questionnaireId);
				qqMapper.insert(qq);
			}
		}
	}

}
