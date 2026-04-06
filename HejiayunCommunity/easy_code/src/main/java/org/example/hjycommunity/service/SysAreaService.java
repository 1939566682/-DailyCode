service;

import .entity.SysArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 区域表(SysArea)表服务接口
 *
 * @author Yang QingBo
 * @since 2026-04-06 21:27:47
 */
public interface SysAreaService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysArea queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sysArea 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysArea> queryByPage(SysArea sysArea, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    SysArea insert(SysArea sysArea);

    /**
     * 修改数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    SysArea update(SysArea sysArea);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
