package com.ruoyi.project.system.channel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.channel.mapper.ChannelMapper;
import com.ruoyi.project.system.channel.domain.Channel;
import com.ruoyi.common.support.Convert;

/**
 * 通道 服务层实现
 * 
 * @author yc
 * @date 2018-12-27
 */
@Service
public class ChannelServiceImpl implements IChannelService 
{
	@Autowired
	private ChannelMapper channelMapper;

	/**
     * 查询通道信息
     * 
     * @param id 通道ID
     * @return 通道信息
     */
    @Override
	public Channel selectChannelById(Long id)
	{
	    return channelMapper.selectChannelById(id);
	}
	
	/**
     * 查询通道列表
     * 
     * @param channel 通道信息
     * @return 通道集合
     */
	@Override
	public List<Channel> selectChannelList(Channel channel)
	{
	    return channelMapper.selectChannelList(channel);
	}
	
    /**
     * 新增通道
     * 
     * @param channel 通道信息
     * @return 结果
     */
	@Override
	public int insertChannel(Channel channel)
	{
	    return channelMapper.insertChannel(channel);
	}
	
	/**
     * 修改通道
     * 
     * @param channel 通道信息
     * @return 结果
     */
	@Override
	public int updateChannel(Channel channel)
	{
	    return channelMapper.updateChannel(channel);
	}

	/**
     * 删除通道对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteChannelByIds(String ids)
	{
		return channelMapper.deleteChannelByIds(Convert.toStrArray(ids));
	}
	
}
