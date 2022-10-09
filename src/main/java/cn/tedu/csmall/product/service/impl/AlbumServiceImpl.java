package cn.tedu.csmall.product.service.impl;

import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.mapper.AlbumMapper;
import cn.tedu.csmall.product.pojo.dto.AlbumAddNewDTO;
import cn.tedu.csmall.product.pojo.entity.Album;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import cn.tedu.csmall.product.service.IAlbumService;
import cn.tedu.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理相册数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Service
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    AlbumMapper albumMapper;

    public AlbumServiceImpl() {
        System.out.println("创建业务实现类对象：AlbumServiceImpl");
    }

    @Override
    public void addNew(AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的业务，参数：{}", albumAddNewDTO);
        // 从参数albumAddNewDTO中获取尝试添加的相册名称
        String name = albumAddNewDTO.getName();
        // 检查此相册名称是否已经存在：调用Mapper对象的countByName()方法，判断结果是否不为0
        int count = albumMapper.countByName(name);
        if (count != 0) {
            // 是：名称已存在，不允许创建，抛出异常
            String message = "添加相册失败，尝试添加的相册名称已经被占用！";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 创建Album对象
        Album album = new Album();
        // 将参数DTO中的数据复制到Album对象中
        BeanUtils.copyProperties(albumAddNewDTO, album);
        log.debug("即将插入相册数据：{}", album);
        // 调用Mapper对象的insert()方法，插入相册数据
        albumMapper.insert(album);
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除相册】的业务，参数：{}", id);
        // 调用Mapper对象的getDetailsById()方法执行查询
        AlbumStandardVO queryResult = albumMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：无此id对应的数据，抛出异常
            String message = "删除相册失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 调用Mapper对象的deleteById()方法执行删除
        log.debug("即将删除相册数据……");
        albumMapper.deleteById(id);
        log.debug("删除相册，完成！");
    }

    @Override
    public List<AlbumListItemVO> list() {
        log.debug("开始处理【查询相册列表】的业务");
        return albumMapper.list();
    }

}
