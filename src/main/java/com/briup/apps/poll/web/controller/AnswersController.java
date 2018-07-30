package com.briup.apps.poll.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.answers;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author
 * @version
 * @describe
 */
@Api(description="课调结果相关接口")
@RestController
@RequestMapping("/answers")
public class AnswersController {
	@Autowired
	private IAnswersService answersService;

	@GetMapping("findAllAnswers")
	@ApiOperation(value="查看所有有课调结果")
	public MsgResponse findAllAnswers() {
		try {
			List<answers> list = answersService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}

	}

	@GetMapping("deleteAnswersById")
	@ApiOperation(value="通过Id删除课调结果")
	public String deleteAnswersById(@RequestParam long id) {
		try {
			answersService.deleteById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error" + e.getMessage();
		}

	}
	
	@ApiOperation(value="删除答卷主观题",
			notes="单选题答案和多选题答案不收影响")
	@GetMapping("deleteAnswerContent")
	public MsgResponse deleteAnswerContent(long id){
		try {
			// 通过id找到答卷
			answers answer = answersService.findById(id);
			// 设置答卷内容更为空
			answer.setContent("");
			answersService.saveOrUpdate(answer);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="修改答卷主观题",
			notes="")
	@GetMapping("updateAnswerContent")
	public MsgResponse updateAnswerContent(long id,String content){
		try {
			// 通过id找到答卷
			answers answer = answersService.findById(id);
			// 设置答卷内容为content
			answer.setContent(content);
			answersService.saveOrUpdate(answer);
			return MsgResponse.success("修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}

	}
	
	@GetMapping("findAnswersById")
	@ApiOperation(value="通过Id查找课调结果")
	public MsgResponse findAnswersById(@RequestParam long id){
		try {
			answers answers=answersService.findById(id);
			return MsgResponse.success("success", answers);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@GetMapping("findAnswersByKeywords")
	@ApiOperation(value="通过关键字查找课调结果")
	public MsgResponse findAnswersByKeywords(@RequestParam String keywords){
		try {
			
			List<answers> list=answersService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}
	
	@GetMapping("batchDeleteAnswers")
	@ApiOperation(value="批量删除课调结果")
	public String batchDeleteAnswers(Long[] ids){
		try {
			answersService.batchDelete(ids);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error"+e.getMessage();
		}
	}
	
	@GetMapping("saveOrUpdateAnswers")
	@ApiOperation(value="保存或更新课调结果")
	public String saveOrUpdateAnswers(answers answers){
		try {
			answersService.saveOrUpdate(answers);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error"+e.getMessage();
		}
	}

}
