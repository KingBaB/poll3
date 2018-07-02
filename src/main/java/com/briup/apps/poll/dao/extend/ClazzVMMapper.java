package com.briup.apps.poll.dao.extend;
/**
*          @author
*          @version
*          @describe
*/

import java.util.List;

import com.briup.apps.poll.bean.extend.ClazzVM;

public interface ClazzVMMapper {
	List<ClazzVM> selectAll();
	
	ClazzVM selectClazzById(long id);

}
