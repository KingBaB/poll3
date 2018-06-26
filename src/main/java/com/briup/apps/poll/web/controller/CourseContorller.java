package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.service.ICourseService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;

@Api(description="课程相关接口")
@RestController
@RequestMapping("/course")
public class CourseContorller {

	@Autowired
	private ICourseService courseService;
	
	@GetMapping("findAllCourse")
	public MsgResponse findAllCourse(){
		try {
			List<Course> list = courseService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			//返回错误信息
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@GetMapping("findById")
	 public MsgResponse findById(long id){
		 try{
			 Course list = courseService.findById(id);
				//返回成功信息
				return MsgResponse.success("success", list); 
		 }catch (Exception e) {
				//返回错误信息
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
		 }
	 }
	@GetMapping("deleteById")
	public MsgResponse deleteById(long id){
		 try{
			 courseService.deleteById(id);
				//返回成功信息
				return MsgResponse.success("success", null); 
		 }catch (Exception e) {
				//返回错误信息
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
		 }
	 }
 @GetMapping("batchDelete")
	public MsgResponse batchDelete(List<Long>ids){
	 try{
		 courseService.batchDelete(ids);
			//返回成功信息
			return MsgResponse.success("success", null); 
	 }catch (Exception e) {
			//返回错误信息
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
	 }
 }
 @GetMapping("saveOrUpdate")
 public MsgResponse saveOrUpdate(Course course){
	 try{
		 courseService.saveOrUpdate(course);
			//返回成功信息
			return MsgResponse.success("success", null); 
	 }catch (Exception e) {
			//返回错误信息
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
	 }
 }
 @GetMapping("queryCourse")
 public MsgResponse queryCourse(String keywords){
	 try{
		 List<Course> list = courseService.query(keywords);
			//返回成功信息
			return MsgResponse.success("success", list); 
	 }catch (Exception e) {
			//返回错误信息
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
	 }
 }
	
}