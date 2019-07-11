package com.tor.project.controller;

import com.tor.generator.core.Result;
import com.tor.generator.core.ResultGenerator;
import com.tor.project.entity.Role;
import com.tor.project.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import javax.annotation.Resource;
import java.util.List;

/**
* Created by Tzx on 2019/07/11.
*/
@RestController
public class RoleController {
    @Resource
    private RoleService roleService;

    @ApiOperation(value = "列表查询", notes = "Role列表")
    @ApiImplicitParams({
          @ApiImplicitParam(name="pageSize",value="每页数据量",paramType="query",dataType="String"),
          @ApiImplicitParam(name="pageNo",value="页码",paramType="query",dataType="String")
       })
    @GetMapping(value = "/v1/role/list")
    public Result list(
        @RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
        @RequestParam(value = "pageSize",defaultValue = "0") Integer pageSize) {
        Condition condition = new Condition(Role.class,false,false);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("", "");
        PageHelper.startPage(pageNo, pageSize);
        List<Role> list = roleService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation( value = "根据ID查找实例",notes = "通过id查找详情" )
    @ApiImplicitParam( name="id" , value="id" , paramType = "path",dataType = "String",required = true)
    @GetMapping(value = "/v1/role/{id}")
    public Result detail(
       @PathVariable @NotNull(message = "id不能为空") String id) {
       Role role = roleService.findById(id);
       return ResultGenerator.genSuccessResult(role);
    }

    @ApiOperation( value = "添加一个实例",notes = "添加role" )
    @PostMapping(value = "/v1/role/save")
    public Result save(@RequestBody Role role) {
        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation( value = "根据ID删除",notes = "通过id删除role" )
    @ApiImplicitParam( name="id" , value="id" , paramType = "path",dataType = "String",required = true)
    @DeleteMapping("/v1/role/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "id不能为空") String id) {
        roleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation( value = "修改",notes = "修改role" )
    @PutMapping("/v1/role/update")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }


}
