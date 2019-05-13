package com.ruoyi.project.system.files.service;

import com.ruoyi.project.system.files.domain.PlayOrder;
import java.util.List;

/**
 * 播放顺序 服务层
 * 
 * @author yc
 * @date 2018-12-25
 */
public interface IPlayOrderService
{
	/**
     * 查询播放顺序信息
     * 
     * @param id 播放顺序ID
     * @return 播放顺序信息
     */
	public PlayOrder selectOrderById(Long id);
	
	/**
     * 查询播放顺序列表
     * 
     * @param order 播放顺序信息
     * @return 播放顺序集合
     */
	public List<PlayOrder> selectOrderList(PlayOrder order);
	
	/**
     * 新增播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	public int insertOrder(PlayOrder order);
	
	/**
     * 修改播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	public int updateOrder(PlayOrder order);
		
	/**
     * 删除播放顺序信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String ids);
	
}
