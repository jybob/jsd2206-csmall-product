package cn.tedu.csmall.product.controller;

import cn.tedu.csmall.product.pojo.dto.AlbumAddNewDTO;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.service.IAlbumService;
import cn.tedu.csmall.product.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "01. 相册管理模块")
@Validated
@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    IAlbumService albumService;

    public AlbumController() {
        System.out.println("创建控制器类对象：AlbumController");
    }

    // http://localhost:8080/albums/add-new?name=TestAlbumName001&description=TestDescription001&sort=77
    @ApiOperation("添加相册")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(@Validated AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的请求，参数：{}", albumAddNewDTO);
        albumService.addNew(albumAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:8080/albums/9527/delete
    @ApiOperation("删除相册")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "相册id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除相册】的请求，参数：{}", id);
        albumService.delete(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/albums
    @ApiOperation("查询相册列表")
    @ApiOperationSupport(order = 420)
    @GetMapping("")
    public JsonResult<List<AlbumListItemVO>> list() {
        log.debug("开始处理【查询相册列表】的请求");
        List<AlbumListItemVO> list = albumService.list();
        return JsonResult.ok(list);
    }

    @Deprecated
    @ApiOperation("删除相册【测试1】")
    @ApiOperationSupport(order = 900)
    @PostMapping("/{id:[a-z]+}/delete/test")
    public String delete(@PathVariable String id) {
        log.debug("【测试】开始处理【删除相册】的请求，参数（纯字母）：{}", id);
        return "OK";
    }

    @Deprecated
    @ApiOperation("删除相册【测试2】")
    @ApiOperationSupport(order = 910)
    @ApiImplicitParam(name = "id", value = "相册id", paramType = "query")
    @PostMapping("/delete/test2")
    public String deleteTest(@Range(min = 1, message = "测试删除相册失败，id值必须是1或更大的有效整数！") Long id) {
        log.debug("【测试】开始处理【删除相册】的请求，这只是一个测试，没有实质功能！");
        return "OK";
    }

}
