package com.briup.apps.poll.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.bean.OptionsExample;
import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.QuestionExample;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.dao.OptionsMapper;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionVMMapper;
import com.briup.apps.poll.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionVMMapper questionVMMapper;
	@Autowired
	private OptionsMapper optionsMapper;

	@Override
	public List<Question> findAll() throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example = new QuestionExample();

		return questionMapper.selectByExample(example);
	}

	@Override
	public Question findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return questionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Question> query(String keywords) throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example = new QuestionExample();
		// 添加了
		example.createCriteria().andNameLike(keywords);
		return questionMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Question question) throws Exception {
		// TODO Auto-generated method stub
		if (question.getId() != null) {
			// 更新
			questionMapper.updateByPrimaryKey(question);
		} else {
			// 插入
			questionMapper.insert(question);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		questionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(Long[] ids) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<QuestionVM> selectAll() throws Exception {
		return questionVMMapper.selectAll();
	}

	// 保存或修改
	@Override
	public void saveOrUpdateQuestionVM(QuestionVM questionVM) throws Exception {
		// 先分离questionVM，从中获取question、options
		List<Options> options = questionVM.getOptions();
		Question question = new Question();
		question.setId(questionVM.getId());
		question.setName(questionVM.getName());
		question.setQuestiontype(questionVM.getQuestionType());
		//System.out.println(""+questionVM.getId());
		// question问题对象,options所有问题的选项

		// 判断保存或修改
		if (question.getId() == null) {
			// 保存
			if (question.getQuestiontype().equals("简答题")) {
				// 保存简答题，只保存题目相关信息
				questionMapper.insert(question);
			} else {
				// 保存单选和多选的时候，先保存题目信息、
				questionMapper.insert(question);
				if (options != null) {
					long questionId = question.getId();
					for (Options option : options) {
						// 为每个options设置questionId
						option.setQuestionId(questionId);
						optionsMapper.insert(option);
					}
				}
			}
		} else {
			// 修改
			// 修改题目信息
			questionMapper.updateByPrimaryKey(question);
			// 修改选项信息(添加新选项，删除就选项，对原来的选项修改
			//删除题目原来的选项
			OptionsExample example=new OptionsExample();
			example.createCriteria().andQuestionIdEqualTo(question.getId());
			optionsMapper.deleteByExample(example);
			//重新添加选项
			long questionId=question.getId();
			for(Options option:options){
				option.setQuestionId(questionId);
				//保存选项
				optionsMapper.insert(option);
			}
		}
		// 判断是否是简答

		/**
		 * 保存 1、保存问题 2、保存选项
		 */

	}

	@Override
	public QuestionVM selectQuestionVMById(long id) throws Exception {
		return questionVMMapper.selectQuestionVMById(id);
	}

	

	/*
	 * @Override(non-Javadoc)
	 * 
	 * @see com.briup.apps.poll.service.IQuestionService#findById() public
	 * List<Question> findById() { // TODO Auto-generated method stub return
	 * null; }
	 */

}
