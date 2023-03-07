package cn.edu.scnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scnu.service.CartService;
import cn.edu.scnu.service.SearchsService;
import cn.edu.scnu.vo.SysResult;

@RestController
public class SearchsController {
	@Autowired
	private SearchsService searchsService;
	
	/*
	 * // ld写，商品搜索
	 * 
	 * @RequestMapping(path="/searchs/query",params= {"query","page","rows"}) public
	 * SysResult searchsQuery(String query,Integer page,Integer rows) { return
	 * SysResult.ok(); }
	 */
	
}
