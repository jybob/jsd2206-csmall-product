package cn.tedu.csmall.product.mapper;

import cn.tedu.csmall.product.pojo.entity.Album;
import cn.tedu.csmall.product.pojo.vo.AlbumListItemVO;
import cn.tedu.csmall.product.pojo.vo.AlbumStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理相册数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AlbumMapper {

    /**
     * 插入相册数据
     *
     * @param album 相册数据
     * @return 受影响的行数
     */
    int insert(Album album);

    /**
     * 批量插入相册数据
     *
     * @param albumList 若干个相册数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<Album> albumList);

    /**
     * 根据id删除相册数据
     *
     * @param id 相册id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 批量删除相册
     *
     * @param ids 需要删除的若干个相册的id
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 更新相册数据
     *
     * @param album 封装了相册的id和需要更新的新数据的对象
     * @return 受影响的行数
     */
    int updateById(Album album);

    /**
     * 统计相册数据的数量
     *
     * @return 相册数据的数量
     */
    int count();

    /**
     * 根据相册名称，统计数据的数量
     *
     * @param name 相册名称
     * @return 此名称的相册数据的数量
     */
    int countByName(String name);

    /**
     * 根据id查询相册标准信息
     *
     * @param id 相册id
     * @return 匹配的相册的标准信息，如果没有匹配的数据，则返回null
     */
    AlbumStandardVO getStandardById(Long id);

    /**
     * 查询相册列表
     *
     * @return 相册列表
     */
    List<AlbumListItemVO> list();

}
