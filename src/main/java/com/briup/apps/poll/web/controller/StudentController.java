package com.briup.apps.poll.web.controller;
/**
*          @author
*          @version
*          @describe
*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.answers;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="学生权限接口")
@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private IAnswersService answersService;
	@ApiOperation("登录课调")
	@GetMapping("loginSurvey")
	public MsgResponse loginSurvey(long id){
		try{
			//通过id查找课调
			SurveyVM surveyVM=surveyService.findSurveyVMById(id);
			/*身份验证，判断用户ip，如果该ip下的用户已经提交问卷后，不允许二次提交*/
			//如果课调存在，并且课调状态为“开启”，返回课调信息，否则返回错误信息
			if(surveyVM!=null && surveyVM.getStatus().equals(Survey.STATUS_BEGIN)){
				return MsgResponse.success("success", surveyVM);
			}else{
				return MsgResponse.error("课调状态不合法");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="提交答卷")
	@PostMapping("submitAnswers")
	public MsgResponse submitAnswers(answers answers){
		try {
			answersService.saveOrUpdate(answers);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}

}
