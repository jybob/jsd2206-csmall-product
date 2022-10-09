package cn.tedu.csmall.product.service;

import cn.tedu.csmall.product.service.IAlbumService;
import cn.tedu.csmall.product.service.impl.AlbumServiceImpl;
import cn.tedu.csmall.product.ex.ServiceException;
import cn.tedu.csmall.product.pojo.dto.AlbumAddNewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlbumServiceTests {
    @Autowired
    IAlbumService service;

    @Test
    void contextLoads(){
        System.out.println(service);
    }
    @Test
    void testAddNew() {
        AlbumAddNewDTO albumAddNewDTO = new AlbumAddNewDTO();
        albumAddNewDTO.setName("测试相册名称002");
        albumAddNewDTO.setDescription("测试相册简介002");
        albumAddNewDTO.setSort(88);

        try {
            service.addNew(albumAddNewDTO);
            System.out.println("添加相册成功！");
        } catch (RuntimeException e) {
            System.out.println("添加相册失败，相册名称已经被占用！");
        }
    }
    @Test
    void testDelete() {
        Long id = 18L;

        try {
            service.delete(id);
            System.out.println("删除相册成功！");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
