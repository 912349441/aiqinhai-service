package com.tor.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tor.generator.core.Result;
import com.tor.generator.core.ResultGenerator;
import com.tor.project.entity.TczActivity;
import com.tor.project.service.TczActivityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
* Created by Tzx on 2019/04/06.
*/
@RestController
public class TczActivityController {
    @Resource
    private TczActivityService tczActivityService;

    public interface MyInterface{
        List<TczActivity> getListByParams(List<TczActivity> list , Predicate<TczActivity> stringPredicate);
    }

    @ApiOperation(value = "列表查询", notes = "TczActivity列表")
    @ApiImplicitParams({
          @ApiImplicitParam(name="pageSize",value="每页数据量",paramType="query",dataType="String"),
          @ApiImplicitParam(name="pageNo",value="页码",paramType="query",dataType="String")
       })
    @GetMapping(value = "/v1/tcz/activity/list")
    public Result list(
        @RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
        @RequestParam(value = "pageSize",defaultValue = "0") Integer pageSize) {
        Condition condition = new Condition(TczActivity.class,false,false);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("", "");
        PageHelper.startPage(pageNo, pageSize);
        List<TczActivity> list = tczActivityService.findByCondition(condition);
        MyInterface myInterface = (listOld , stringPredicate) -> {
            List<TczActivity> listNew = new ArrayList<>();
            List<TczActivity> tczActivityStream = listOld.stream().filter( activity -> activity.getActivityType() != null && activity.getActivitySum() != null ).collect(Collectors.toList());
            for (TczActivity tczActivity : listOld){
                if(stringPredicate.test(tczActivity)){
                    listNew.add(tczActivity);
                }
            }
            return listNew;
        };
        List<TczActivity> a = myInterface.getListByParams(list, activityType ->activityType.getActivityType()!=null && activityType.getActivitySum()!=null &&  activityType.getActivityType().equals("A") && activityType.getActivitySum().equals("111"));
        PageInfo pageInfo = new PageInfo(a);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation( value = "根据ID查找实例",notes = "通过id查找详情" )
    @ApiImplicitParam( name="id" , value="id" , paramType = "path",dataType = "String",required = true)
    @GetMapping(value = "/v1/tcz/activity/{id}")
    public Result detail(
       @PathVariable @NotNull(message = "id不能为空") String id) {
       TczActivity tczActivity = tczActivityService.findById(id);
       return ResultGenerator.genSuccessResult(tczActivity);
    }

    @ApiOperation( value = "添加一个实例",notes = "添加tczActivity" )
    @PostMapping(value = "/v1/tcz/activity/save")
    public Result save(@RequestBody TczActivity tczActivity) {
        tczActivityService.save(tczActivity);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation( value = "根据ID删除",notes = "通过id删除tczActivity" )
    @ApiImplicitParam( name="id" , value="id" , paramType = "path",dataType = "String",required = true)
    @DeleteMapping("/v1/tcz/activity/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "id不能为空") String id) {
        tczActivityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation( value = "修改",notes = "修改tczActivity" )
    @PutMapping("/v1/tcz/activity/update")
    public Result update(@RequestBody TczActivity tczActivity) {
        tczActivityService.update(tczActivity);
        return ResultGenerator.genSuccessResult();
    }


}
