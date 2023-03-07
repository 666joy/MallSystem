package cn.edu.scnu.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.edu.scnu.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}