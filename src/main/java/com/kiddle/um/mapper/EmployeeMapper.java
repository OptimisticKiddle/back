package com.kiddle.um.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiddle.um.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
