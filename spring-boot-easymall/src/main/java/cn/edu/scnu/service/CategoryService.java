package cn.edu.scnu.service;

import cn.edu.scnu.pojo.ywdCategory;
import cn.edu.scnu.vo.EasyUIResult;

public interface CategoryService {

//	public EasyUIResult allProductQuery();
	
	public EasyUIResult allCategoryQuery();
	public ywdCategory selectCatById(String categoryId);
	public void categoryDelete(String[] ids);
//	//ywd
	//ywd
//	public int ywdselectallcategory();
	public void categorySave(ywdCategory category);
	public void categoryUpdate(ywdCategory category);
	

}
