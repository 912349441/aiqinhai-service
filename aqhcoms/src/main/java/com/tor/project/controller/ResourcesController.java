package com.tor.project.controller;

import com.tor.generator.core.Result;
import com.tor.generator.core.ResultGenerator;
import com.tor.project.entity.Resources;
import com.tor.project.service.ResourcesService;
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
public class ResourcesController {
    @Resource
    private ResourcesService resourcesService;

    @ApiOperation(value = "列表查询", notes = "Resources列表")
    @ApiImplicitParams({
          @ApiImplicitParam(name="pageSize",value="每页数据量",paramType="query",dataType="String"),
          @ApiImplicitParam(name="pageNo",value="页码",paramType="query",dataType="String")
       })
    @GetMapping(value = "/v1/resources/list")
    public Result list(
        @RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
        @RequestParam(value = "pageSize",defaultValue = "0") Integer pageSize) {
        Condition condition = new Condition(Resources.class,false,false);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("", "");
        PageHelper.startPage(pageNo, pageSize);
        List<Resources> list = resourcesService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation( value = "根据ID查找实例",notes = "通过id查找详情" )
    @ApiImplicitParam( name="id" , value="id" , paramType = "path",dataType = "String",required = true)
    @GetMapping(value = "/v1/resources/{id}")
    public Result detail(
       @PathVariable @NotNull(message = "id不能为空") String id) {
       Resources resources = resourcesService.findById(id);
       return ResultGenerator.genSuccessResult(resources);
    }

    @ApiOperation( value = "添加一个实例",notes = "添加resources" )
    @PostMapping(value = "/v1/resources/save")
    public Result save(@RequestBody Resources resources) {
        resourcesService.save(resources);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation( value = "根据ID删除",notes = "通过id删除resources" )
    @ApiImplicitParam( name="id" , value="id" , paramType = "path",dataType = "String",required = true)
    @DeleteMapping("/v1/resources/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "id不能为空") String id) {
        resourcesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation( value = "修改",notes = "修改resources" )
    @PutMapping("/v1/resources/update")
    public Result update(@RequestBody Resources resources) {
        resourcesService.update(resources);
        return ResultGenerator.genSuccessResult();
    }


}
