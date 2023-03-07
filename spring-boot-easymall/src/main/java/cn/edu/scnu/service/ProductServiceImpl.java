package cn.edu.scnu.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import cn.edu.scnu.pojo.Echarts;
import cn.edu.scnu.pojo.Product;
import cn.edu.scnu.utils.ExcelUtil;
import cn.edu.scnu.vo.EasyUIResult;
import cn.edu.scnu.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	public ProductServiceImpl() {
		super();
	}
	
	@Override
	public EasyUIResult allProductQuery() {
		// 1.创建一个返回的对象,将查询数据set进去然后返回
		EasyUIResult result = new EasyUIResult();
		// 2封装第一个属性 total 的数量
		QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
		productQueryWrapper.eq("product_status", "已上架");
		Integer total = productMapper.selectCount(productQueryWrapper);
		// 3封装第二个属性List<Product> pList
		List<Product> pList = productMapper.selectList(productQueryWrapper);
		// 4封装对象属性
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}

	@Override
	public Product selectProdById(String productId) {
		return productMapper.selectById(productId);
	}

	@Override
	public void productSave(Product product) {
		product.setProductId(UUID.randomUUID().toString());
		productMapper.insert(product);
	}

	@Override
	public void productUpdate(Product product) {
		productMapper.updateById(product);
	}

	@Override
	public void productDelete(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		productMapper.deleteBatchIds(idList);
	}

	@Override
	public void updateStatus(String[] ids, String status) {
		Product product = new Product();
		product.setProductStatus(status);
		UpdateWrapper<Product> updateWrapper = new UpdateWrapper<Product>();
		List<String> idList = Arrays.asList(ids);
		updateWrapper.in("product_id", idList);
		productMapper.update(product, updateWrapper);
	}

	@Override
	public EasyUIResult allProductQuery2() {
		// 1.创建一个返回的对象,将查询数据set进去然后返回
		EasyUIResult result = new EasyUIResult();
		// 2封装第一个属性 total 的数量
		QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
		Integer total = productMapper.selectCount(productQueryWrapper);
		// 3封装第二个属性List<Product> pList
		List<Product> pList = productMapper.selectList(productQueryWrapper);
		// 4封装对象属性
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}
	
	@Override
	public EasyUIResult allProductQuery3() {//根据销售数量从大到小排序
		// 1.创建一个返回的对象,将查询数据set进去然后返回
		EasyUIResult result = new EasyUIResult();
		// 2封装第一个属性 total 的数量
		QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
		productQueryWrapper.orderByDesc("product_sellnum");
		
		Integer total = productMapper.selectCount(productQueryWrapper);
		// 3封装第二个属性List<Product> pList
		List<Product> pList = productMapper.selectList(productQueryWrapper);
		// 4封装对象属性
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}
	
	@Override
	public void exportExcel(String[] ids, HttpServletResponse response) {

		// 表头数
		String[] header = { "商品ID", "商品名称", "商品类目", "商品价格", "商品描述", "商品状态","商品销量" };
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 文件名
		String fileName = sdf.format(new Date()).toString() + ".xls";
		// 封装的方法需要的key 后期excel对应表头
		String keys[] = { "productId", "productName", "productCategory", "productPrice", "productDescription",
				"productStatus" ,"productSellnum"};// map中的key
		List<Product> productList = productMapper.selectBatchIds(Arrays.asList(ids));
		List<Map<String, Object>> list = createExcelRecord(productList);
		// 通用代码
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, header).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName).getBytes(), "iso-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private List<Map<String, Object>> createExcelRecord(List<Product> list) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "商品信息表");
		listmap.add(map);
		Product product = null;

		for (int i = 0; i < list.size(); i++) {
			product = list.get(i);

			Map<String, Object> mapValue = new HashMap<String, Object>();

			mapValue.put("productId", product.getProductId());
			mapValue.put("productName", product.getProductName());
			mapValue.put("productCategory", product.getProductCategory());
			mapValue.put("productPrice", product.getProductPrice());
			mapValue.put("productDescription", product.getProductDescription());
			mapValue.put("productStatus", product.getProductStatus());
			mapValue.put("productSellnum", product.getProductSellnum());

			listmap.add(mapValue);

		}
		return listmap;
	}
	
	@Override
	public void exportExcel1(String[] ids, HttpServletResponse response) {

		// 表头数
		String[] header = { "商品ID", "商品名称", "商品销量" };
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 文件名
		String fileName = sdf.format(new Date()).toString() + ".xls";
		// 封装的方法需要的key 后期excel对应表头
		String keys[] = { "productId", "productName", "productSellnum"};// map中的key
		List<Product> productList = productMapper.selectBatchIds(Arrays.asList(ids));
		List<Map<String, Object>> list = createExcelRecord1(productList);
		// 通用代码
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, header).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName).getBytes(), "iso-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	private List<Map<String, Object>> createExcelRecord1(List<Product> list) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "商品信息表");
		listmap.add(map);
		Product product = null;

		for (int i = 0; i < list.size(); i++) {
			product = list.get(i);

			Map<String, Object> mapValue = new HashMap<String, Object>();

			mapValue.put("productId", product.getProductId());
			mapValue.put("productName", product.getProductName());
			mapValue.put("productSellnum", product.getProductSellnum());

			listmap.add(mapValue);

		}
		return listmap;
	}
	// ld写，商品查找
	@Override
	public List<Product> searchsQuery(String query) {
		
		QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
		productQueryWrapper.like("product_name", query);
		List<Product> productList = productMapper.selectList(productQueryWrapper);
		return productList;
	}
	// ld写，商品分类查找
	@Override
	public EasyUIResult categoryProductQuery(String category) {
		// 1.创建一个返回的对象,将查询数据set进去然后返回
				EasyUIResult result = new EasyUIResult();
				// 2封装第一个属性 total 的数量
				QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
				productQueryWrapper.eq("product_category", category);
				Integer total = productMapper.selectCount(productQueryWrapper);
				// 3封装第二个属性List<Product> pList
				List<Product> pList = productMapper.selectList(productQueryWrapper);
				// 4封装对象属性
				result.setTotal(total);
				result.setRows(pList);
				return result;
	}
	@Override
	public List<Echarts> echartsShow() {
		List<Echarts> list = new ArrayList<Echarts>();
		QueryWrapper<Product> productQueryWrapper = new QueryWrapper<Product>();
		List<Product> pList = productMapper.selectList(productQueryWrapper);
		for(Product product:pList){
			list.add(new Echarts(product.getProductName(),product.getProductSellnum()));
		}
		return list;
	}

}