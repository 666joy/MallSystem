package cn.edu.scnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.pojo.Product;
import cn.edu.scnu.pojo.ywdCategory;
import cn.edu.scnu.service.CategoryService;
import cn.edu.scnu.vo.EasyUIResult;
import cn.edu.scnu.vo.SysResult;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/category/pageManage")//在这里到ProductService-》后端
	public EasyUIResult categoryPageQuery() {
		EasyUIResult result = categoryService.allCategoryQuery();
		return result;
	}
	
	@RequestMapping("/category/delete")
	public SysResult categoryDelete(String[] ids) {
		try {
			categoryService.categoryDelete(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "删除失败", null);
		}
	}

	@RequestMapping("/category/item/{categoryId}")
	public ywdCategory categoryInfo(@PathVariable String categoryId) {
		return categoryService.selectCatById(categoryId);
	}
	
	
	@RequestMapping("/category/save")
	public SysResult categorySave(ywdCategory category) {
		try {
			//category.setProductStatus("已上架");
			categoryService.categorySave(category);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "添加失败", null);
		}
	}
	
	@RequestMapping("/category/update")
	public SysResult categoryUpdate(ywdCategory category) {
		try {
			categoryService.categoryUpdate(category);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "更新失败", null);
		}
	}
	

	
}