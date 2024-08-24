package com.kiddle.um.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiddle.um.entity.Employee;
import com.kiddle.um.mapper.EmployeeMapper;
import com.kiddle.um.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {
}
