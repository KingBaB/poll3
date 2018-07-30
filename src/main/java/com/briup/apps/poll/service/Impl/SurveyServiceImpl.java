/*
 * 课调Controller
 * @author:Wsiri
 * Date:2018/6/26
 */

package com.briup.apps.poll.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.SurveyExample;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.dao.SurveyMapper;
import com.briup.apps.poll.dao.extend.SurveyVMMapper;
import com.briup.apps.poll.service.ISurveyService;

@Service
public class SurveyServiceImpl implements ISurveyService {

	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private SurveyVMMapper surveyVMMapper;

	// 查询所有课调
	@Override
	public List<Survey> findAllSurvey() throws Exception {
		// TODO Auto-generated method stub
		SurveyExample example = new SurveyExample();
		return surveyMapper.selectByExample(example);
	}

	// 根据ID查询
	@Override
	public Survey findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return surveyMapper.selectByPrimaryKey(id);
	}

	// 根据关键字查询
	@Override
	public List<Survey> query(String keywords) throws Exception {
		// TODO Auto-generated method stub
		SurveyExample example = new SurveyExample();
		example.createCriteria().andStatusLike("%" + keywords + "%");
		return surveyMapper.selectByExample(example);
	}

	// 保存或更新
	@Override
	public void saveOrUpdate(Survey survey) throws Exception {

		// TODO Auto-generated method stub
		Survey count = surveyMapper.selectByPrimaryKey(survey.getId());
		if (count != null) {
			surveyMapper.updateByPrimaryKey(survey);
		} else {
			surveyMapper.insert(survey);
		}

	}

	// 根据ID删除
	@Override
	public int deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		return surveyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(Long[] ids) throws Exception {
		for (long id : ids) {
			surveyMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void saveOrUpdateSurveyVM(Survey survey) throws Exception {
		if(survey.getId()!=null){
			//修改
			surveyMapper.updateByPrimaryKey(survey);
		}else{
			//保存
			survey.setStatus(Survey.STATUS_INIT);
			survey.setCode("");
			Date now=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String surveydate=sdf.format(now);
			survey.setSurveydate(surveydate);
			surveyMapper.insert(survey);
		}
	}

	@Override
	public List<SurveyVM> findAllSuerveyVM() throws Exception {
		return surveyVMMapper.selectAll();
	}

	@Override
	public SurveyVM findSurveyVMById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return surveyVMMapper.selectSurveyVMById(id);
	}

	@Override
	public List<SurveyVM> selectByClazzIdAndCheckPass(long id) throws Exception {
		return surveyVMMapper.selectByClazzIdAndCheckPass(id);
	}

}
