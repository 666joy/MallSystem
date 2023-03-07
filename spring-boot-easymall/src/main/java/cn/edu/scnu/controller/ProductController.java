package cn.edu.scnu.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.scnu.pojo.Echarts;
import cn.edu.scnu.pojo.Product;
import cn.edu.scnu.service.ProductService;
import cn.edu.scnu.utils.UploadUtil;
import cn.edu.scnu.vo.EasyUIResult;
import cn.edu.scnu.vo.PicUploadResult;
import cn.edu.scnu.vo.SysResult;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping("/products/pageManage")
	public EasyUIResult productPageQuery() {
		EasyUIResult result = productService.allProductQuery();
		return result;
	}

	@RequestMapping("/products/item/{productId}")
	public Product productInfo(@PathVariable String productId) {
		return productService.selectProdById(productId);
	}
	
	@RequestMapping("/products/pageManage2")
	public EasyUIResult productPageQuery2() {
		EasyUIResult result = productService.allProductQuery2();
		return result;
	}
	
	@RequestMapping("/products/pageManage3")
	public EasyUIResult productPageQuery3() {
		EasyUIResult result = productService.allProductQuery3();
		return result;
	}

	@RequestMapping("/products/save")
	public SysResult productSave(Product product) {
		try {
			product.setProductStatus("已上架");
			productService.productSave(product);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "添加失败", null);
		}
	}

	// 声明单元方法，处理上传请求
	/**
	 * MultipartFile 该形参用来接收DispatcherServlet解析request对象中存储了文件二进制的对象
	 * 形参名字必须是上传数据formData的键值名
	 */
	@RequestMapping("/products/uploadImg")
	public PicUploadResult uploadImg(@RequestParam(value = "pic", required = false) MultipartFile pic,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			// 1.确定文件的存储路径
			// 使用ServletContext动态获取upload的路径
			//String path = request.getServletContext().getRealPath("/upload");
			//System.out.println(path);
			String path ="D:/java/nginx-1.9.9/easymall_image/upload";
			// 2.确定文件的存储名字
			// 获取文件原始名
			String oldName = pic.getOriginalFilename();
			// 获取文件存储的后缀名
			String suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
			// 创建文件新的名字+后缀
			String newName = UUID.randomUUID() + "." + suffix;
			// 3.完成存储
			// 创建file对象的存储路径
			String uploadPath = UploadUtil.getUploadPath(newName, "");
			File f = new File(path+uploadPath);
			if (!f.exists()) {
				f.mkdirs();// 创建存储路径
			}
			// 输出存储
			File img=new File(f, newName);
			pic.transferTo(img);
			PicUploadResult picUploadResult = new PicUploadResult();
			picUploadResult.setUrl("http://image.easymall.com/upload"+uploadPath+"/"+newName);
			return picUploadResult;
		} catch (Exception e) {
			e.printStackTrace();
			PicUploadResult picUploadResult = new PicUploadResult();
			picUploadResult.setError(1);
			return picUploadResult;
		}
	}
	
	@RequestMapping("/products/update")
	public SysResult productUpdate(Product product) {
		try {
			productService.productUpdate(product);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "更新失败", null);
		}
	}
	@RequestMapping("/products/delete")
	public SysResult productDelete(String[] ids) {
		try {
			productService.productDelete(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "删除失败", null);
		}
	}
	@RequestMapping("/products/instock")
	public SysResult instockProduct(String[] ids) {
		try {
			String status = "已下架";    //表示下架
		    productService.updateStatus(ids,status);
		    return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "下架失败", null);
		}
	}

	@RequestMapping("/products/reshelf")
	public SysResult reshelfProduct(String[] ids) {
		try {
			String status = "已上架";    //表示上架
		    productService.updateStatus(ids,status);
		    return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "上架失败", null);
		}
	}
	
	/**
     *导出excel格式数据
     * @param ids 审计id数组
	 * @return 
     * @return
     */
    @RequestMapping("/products/export")
    public void exportExcel(String[] ids, HttpServletResponse response){
        try {
        	productService.exportExcel(ids,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     *导出销售榜单excel格式数据
     * @param ids 审计id数组
	 * @return 
     * @return
     */
    @RequestMapping("/products/export1")
    public void exportExcel1(String[] ids, HttpServletResponse response){
        try {
        	productService.exportExcel1(ids,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 // ld写，商品搜索
 		@RequestMapping(path="/searchs/query",params= {"query"})
 		public List<Product> searchsQuery(String query) {
 			return productService.searchsQuery(query);
 		}
 	// ld写，商品分类查找
 	
 	  @RequestMapping(path="/products/pageManage2",params= {"category"}) 
 	  public EasyUIResult productPageQuery2(String category) { 
 		  EasyUIResult result = productService.categoryProductQuery(category); 
 		  return result; 
 	  }
 	  
 	 @Description("获取Echarts数据")
     @RequestMapping("/products/EchartsShow")
     @ResponseBody
     public List<Echarts> echartsShow() {
     	try {
     		return productService.echartsShow();
 		} catch (Exception e) {
 			e.printStackTrace();
 			return null;
 		}
 }

}