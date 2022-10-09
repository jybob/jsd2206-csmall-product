package cn.tedu.csmall.product;

import cn.tedu.csmall.product.mapper.AlbumMapper;
import cn.tedu.csmall.product.mapper.AttributeTemplateMapper;
import cn.tedu.csmall.product.pojo.entity.Album;
import cn.tedu.csmall.product.pojo.entity.AttributeTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class CsmallProductApplicationTests {
    @Autowired
    AlbumMapper mapper;


    @Test
    void testInsert() {
        Album album = new Album();
        album.setName("测试相册");
        album.setDescription("测试简介001");
        album.setSort(99);
        int rows = mapper.insert(album);
        System.out.println("插入数据完成,受影响的行数为:" + rows);
    }




}
