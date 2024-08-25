package com.kiddle.um.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kiddle.um.common.R;
import com.kiddle.um.dto.EmployeeQuery;
import com.kiddle.um.entity.Employee;
import com.kiddle.um.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping("/save")
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param emp
     * @return
     */
    @PostMapping("/page")
    public R<Page> page(int page, int pageSize, @RequestBody Employee emp){
        String username = emp.getUsername();
        String sex = emp.getSex();
        String status = emp.getStatus();

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(username),Employee::getUsername,username);
        queryWrapper.eq(StringUtils.isNotEmpty(sex),Employee::getSex,sex);
        queryWrapper.eq(StringUtils.isNotEmpty(status),Employee::getStatus,status);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee){
        log.info(employee.toString());
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);

        return R.success("信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);

        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }

    /**
     * 删除員工
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);

        employeeService.removeByIds(ids);

        return R.success("删除成功");
    }
}
