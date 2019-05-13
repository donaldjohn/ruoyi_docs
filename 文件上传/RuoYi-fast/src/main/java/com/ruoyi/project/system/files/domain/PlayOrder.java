package com.ruoyi.project.system.files.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 播放顺序表 play_order
 * 
 * @author yc
 * @date 2018-12-25
 */
public class PlayOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/**  */
	private String fileName;
	/** 0 图片 1 视频 2 文字 */
	private Integer type;
	/**  */
	private String ur;
	/**  */
	private String content;
	/** 播放顺序 */
	private Integer order;
	/**  */
	private String remark;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setUr(String ur) 
	{
		this.ur = ur;
	}

	public String getUr() 
	{
		return ur;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setOrder(Integer order) 
	{
		this.order = order;
	}

	public Integer getOrder() 
	{
		return order;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("type", getType())
            .append("ur", getUr())
            .append("content", getContent())
            .append("order", getOrder())
            .append("remark", getRemark())
            .toString();
    }
}
