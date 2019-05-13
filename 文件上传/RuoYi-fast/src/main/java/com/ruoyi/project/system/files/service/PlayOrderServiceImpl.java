package com.ruoyi.project.system.files.service;

import java.util.List;

import com.ruoyi.project.system.files.mapper.PlayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.files.domain.PlayOrder;
import com.ruoyi.common.support.Convert;

/**
 * 播放顺序 服务层实现
 * 
 * @author yc
 * @date 2018-12-25
 */
@Service
public class PlayOrderServiceImpl implements IPlayOrderService
{
	@Autowired
	private PlayOrderMapper orderMapper;

	/**
     * 查询播放顺序信息
     * 
     * @param id 播放顺序ID
     * @return 播放顺序信息
     */
    @Override
	public PlayOrder selectOrderById(Long id)
	{
	    return orderMapper.selectOrderById(id);
	}
	
	/**
     * 查询播放顺序列表
     * 
     * @param order 播放顺序信息
     * @return 播放顺序集合
     */
	@Override
	public List<PlayOrder> selectOrderList(PlayOrder order)
	{
	    return orderMapper.selectOrderList(order);
	}
	
    /**
     * 新增播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	@Override
	public int insertOrder(PlayOrder order)
	{
	    return orderMapper.insertOrder(order);
	}
	
	/**
     * 修改播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	@Override
	public int updateOrder(PlayOrder order)
	{
	    return orderMapper.updateOrder(order);
	}

	/**
     * 删除播放顺序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderByIds(String ids)
	{
		return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
	}
	
}
