package cn.edu.scnu.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.edu.scnu.pojo.Product;
import cn.edu.scnu.pojo.ywdCategory;
import cn.edu.scnu.vo.EasyUIResult;
import cn.edu.scnu.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;


	@Override
	public EasyUIResult allCategoryQuery() {
			// 1.创建一个返回的对象,将查询数据set进去然后返回
			EasyUIResult result = new EasyUIResult();
			// 2封装第一个属性 total 的数量
			QueryWrapper<ywdCategory> categoryQueryWrapper = new QueryWrapper<>();
			Integer total = categoryMapper.selectCount(categoryQueryWrapper);
			// 3封装第二个属性List<Product> pList
			List<ywdCategory> pList = categoryMapper.selectList(categoryQueryWrapper);
			// 4封装对象属性
			result.setTotal(total);
			result.setRows(pList);
			return result;
	}



	@Override
	public ywdCategory selectCatById(String categoryId) {
		return categoryMapper.selectById(categoryId);
	}

	@Override
	public void categoryDelete(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		categoryMapper.deleteBatchIds(idList);
	}
	
	@Override
	public void categorySave(ywdCategory category) {
		category.setCategoryId(UUID.randomUUID().toString());
		categoryMapper.insert(category);
	}
	
	@Override
	public void categoryUpdate(ywdCategory category) {
		categoryMapper.updateById(category);
	}



}