package com.ruoyi.project.tool.email.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.config.EmailConfig;
import com.ruoyi.project.tool.email.domain.Email;
import com.ruoyi.project.tool.email.mapper.EmailMapper;
import com.ruoyi.project.tool.email.util.EmailUtil;

/**
 * 系统邮件 服务层处理
 * 站内信，只在本系统中接收；站外信，可发送外部邮件
 */
@Service
public class EmailServiceImpl implements IEmailService {
	
	@Autowired
	private EmailMapper emailMapper;
	
	@Autowired
	private EmailConfig emailConfig;
	
	/**
	 * 邮件列表
	 * @param email
	 * @return
	 */
	@Override
	public List<Email> selectEmailList(Email email) {
		return emailMapper.selectEmailList(email);
	}
	
	/**
	 * 邮件详情
	 * @param emailId
	 * @return
	 */
	@Override
	public Email selectEmailById(Long emailId) {
		return emailMapper.selectEmailById(emailId);
	}
	
	/**
	 * 发送内部邮件
	 * @param email
	 * @return
	 */
	@Override
	public int sendWithInside(Email email) {
		int count = 0;
		String [] toUserIds = email.getToUserIds().split(",");
		for(String toUserId : toUserIds){
			email.setFormUser(ShiroUtils.getUser().getUserId());
			email.setToUser(Long.valueOf(toUserId));
			email.setCreateBy(ShiroUtils.getUser().getUserName());
			email.setSendStatus(0);
			count += emailMapper.insertEmail(email);
		}
		return count;
	}

	/**
	 * 发送外部邮件
	 * @param email
	 * @return
	 */
	@Override
	public int sendWithOutside(Email email) {
		int count = 0;
		String [] toUserEmails = email.getToUserEmails().split(",");
		//发送邮件
		boolean isSend = EmailUtil.sendHtmlEmail(email.getEmailSubject(),toUserEmails,email.getEmailContent(),emailConfig);
		for(String toUserEmail : toUserEmails){
			email.setFormUser(ShiroUtils.getUser().getUserId());
			email.setToUserEmail(toUserEmail);
			email.setCreateBy(ShiroUtils.getUser().getUserName());
			email.setSendStatus(isSend?0:1);
			count += emailMapper.insertEmail(email);
		}
		return count;
	}

	/**
	 * 移入回收站
	 * @param email
	 * @return
	 */
	@Override
	public int moveToRecoveryBin(Email email) {
		return emailMapper.moveToRecoveryBin(email);
	}
	
	/**
	 * 保存至草稿
	 * @param email
	 * @return
	 */
	@Override
	public int saveToRough(Email email) {
		email.setFormUser(ShiroUtils.getUser().getUserId());
		email.setCreateBy(ShiroUtils.getUser().getUserName());
		email.setSendStatus(1);
		return emailMapper.insertEmail(email);
	}

	/**
	 * 恢复到收件箱
	 * @param email
	 * @return
	 */
	@Override
	public int moveToInBox(Email email) {
		return emailMapper.moveToInBox(email);
	}
	
	/**
	 * 批量逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	public int deleteEmailByIds(String ids) {
		Long[] emailIds = Convert.toLongArray(ids);
		return emailMapper.deleteEmailByIds(emailIds);
	}
	
	/**
	 * 批量物理删除
	 * @param ids
	 * @return
	 */
	@Override
	public int truncateEmailByIds(String ids) {
		Long[] emailIds = Convert.toLongArray(ids);
		return emailMapper.truncateEmailByIds(emailIds);
	}
}
