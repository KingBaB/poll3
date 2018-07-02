package com.briup.apps.poll.web.controller;

import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="课调相关接口")
@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@Autowired
	private ISurveyService iSurveyService;
	
	
	//查找所有课调
	@ApiOperation(value="查询全部")
	@GetMapping("findAllSurvey")
	@ResponseBody
	public MsgResponse findAllSurvey(){
		List<Survey> list;
		try {
			list = iSurveyService.findAllSurvey();
			return MsgResponse.success("查找成功",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}
	
	//级联查找所有课调
		@ApiOperation(value="级联查询所有课调")
		@GetMapping("findAllSurveyVM")
		@ResponseBody
		public MsgResponse findAllSurveyVM(){
			try {
				List<SurveyVM> list = iSurveyService.findAllSuerveyVM();
				return MsgResponse.success("查找成功",list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
			}
			
		}
	
		//根据Id查找
		@ApiOperation(value="通过id查询")
		@GetMapping("findSurveyById")
		@ResponseBody
		public MsgResponse findSurveyById(@RequestParam long id){
			Survey survey=new Survey();
			try {
				//通过id查询出课调
				survey=iSurveyService.findById(id);
				//修改课调的状态/课调编号
				if(survey.getStatus().equals(Survey.STATUS_INIT)){
					//将课调的状态设置为开启
					survey.setStatus(Survey.STATUS_BEGIN);
					//将课调的code设置为当前课调的ID，后期通过Code找survey
					survey.setCode(survey.getCode().toString());
					//执行保存或者更新操作
					iSurveyService.saveOrUpdate(survey);
					return MsgResponse.success("开启成功",null);
				}
				return MsgResponse.error("当前课调状态必须为未开启状态");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
			}
			
		}
	//根据Id查找
	@ApiOperation(value="通过id查询")
	@GetMapping("findById")
	@ResponseBody
	public MsgResponse findById(@RequestParam long id){
		Survey survey=new Survey();
		try {
			survey=iSurveyService.findById(id);
			return MsgResponse.success("查找成功", survey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}
	
	//根据关键字查找
	@ApiOperation(value="通过关键字查询")
	@PostMapping("findByKeywords")
	@ResponseBody
	public MsgResponse findByKewords(@RequestParam String keywords){
		List<Survey> list;
		try {
			list=iSurveyService.query(keywords);
			return MsgResponse.success("查找成功", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
	//保存或更新
	@ApiOperation(value="保存或更新")
	@PostMapping("saveOrUpdate")
	@ResponseBody
	public MsgResponse saveOrUpdate(Survey survey){
		try {
			iSurveyService.saveOrUpdate(survey);
			return MsgResponse.success("添加成功", survey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	//根据ID删除
	@ApiOperation(value="通过id删除")
	@GetMapping("deleteById")
	@ResponseBody
	public MsgResponse deleteById(@RequestParam long id){
		try {
			int count=iSurveyService.deleteById(id);
			if(count>0)
			return MsgResponse.justSuccess("删除成功");
			else return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
	//批量删除
	@ApiOperation(value="批量删除")
	@PostMapping("batchDelete")
	@ResponseBody
	public MsgResponse batchDelete(Long[] ids){
		try {
			iSurveyService.batchDelete(ids);
			return MsgResponse.justSuccess("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
}
