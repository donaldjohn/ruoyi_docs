package com.ruoyi.project.tool.email.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.project.tool.email.domain.Email;
import com.ruoyi.project.tool.email.service.IEmailService;

@Controller
@RequestMapping("/tool/email")
public class EmailController extends BaseController {

	private String prefix = "tool/email";
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IEmailService emailService;
	
	/**
	 * 默认收件箱
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("tool:email:view")
	@GetMapping("/{link}")
	public String view(ModelMap mmap, @PathVariable("link") String link, String type, String label) {
		mmap.put("emailLink", link);
		mmap.put("emailType", type);
		mmap.put("emailLabel", label);
		return prefix + "/email";
	}
	
	@PostMapping("/list/{link}")
	@ResponseBody
	public TableDataInfo list(Email email,@PathVariable("link") String link, String type, String label) {
		startPage();
		//设置文件夹列表
		if(StringUtils.equals("0", link)){
			email.setToUser(getUserId());
			email.setEmailFolder("1");
		}else{
			email.setFormUser(getUserId());
			email.setEmailFolder(link);
		}
		email.setEmailType(type);
		email.setEmailLabel(label);
		List<Email> list = emailService.selectEmailList(email);
		return getDataTable(list);
	}
	
	/**
	 * 写信页
	 * @param mmap
	 * @return
	 */
	@GetMapping("/write")
	public String write(ModelMap mmap) {
		mmap.put("users", userService.selectUserList(new User()));
		return prefix + "/write";
	}
	
	/**
	 * 读信页
	 * @param mmap
	 * @return
	 */
	@GetMapping("/read")
	public String read(ModelMap mmap,Long emailId) {
		mmap.put("email", emailService.selectEmailById(emailId));
		return prefix + "/read";
	}
	
	/**
	 * 发送内部邮件
	 * @param email
	 * @return
	 */
	@PostMapping("/sendWithInside")
	@ResponseBody
	public AjaxResult sendWithInside(Email email) {
		return toAjax(emailService.sendWithInside(email));
	}
	
	/**
	 * 发送外部邮件
	 * @param email
	 * @return
	 */
	@PostMapping("/sendWithOutside")
	@ResponseBody
	public AjaxResult sendWithOutside(Email email) {
		return toAjax(emailService.sendWithOutside(email));
	}
	
	/**
	 * 移入回收站
	 * @param email
	 * @return
	 */
	@PostMapping("/moveToRecoveryBin")
	@ResponseBody
	public AjaxResult moveToRecoveryBin(Email email) {
		return toAjax(emailService.moveToRecoveryBin(email));
	}
	
	/**
	 * 恢复到收件箱
	 * @param email
	 * @return
	 */
	@PostMapping("/moveToInBox")
	@ResponseBody
	public AjaxResult moveToInBox(Email email) {
		return toAjax(emailService.moveToInBox(email));
	}
	
	/**
	 * 保存至草稿
	 * @param email
	 * @return
	 */
	@PostMapping("/saveToRough")
	@ResponseBody
	public AjaxResult saveToRough(Email email) {
		return toAjax(emailService.saveToRough(email));
	}
	
	/**
	 * 批量逻辑删除
	 * @param ids
	 * @return
	 */
	@PostMapping("/logicRemove")
	@ResponseBody
	public AjaxResult logicRemove(String ids) {
		try {
			return toAjax(emailService.deleteEmailByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
	
	/**
	 * 批量物理删除
	 * @param ids
	 * @return
	 */
	@PostMapping("/physicsRemove")
	@ResponseBody
	public AjaxResult physicsRemove(String ids) {
		try {
			return toAjax(emailService.truncateEmailByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
}
