package cn.edu.scnu.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.edu.scnu.pojo.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}