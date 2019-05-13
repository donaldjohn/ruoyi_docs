package com.ruoyi.project.system.files.controller;

import java.io.File;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.project.system.files.domain.PlayOrder;
import com.ruoyi.project.system.files.service.IPlayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.service.IFilesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传 信息操作处理
 *
 * @author yc
 * @date 2018-12-20
 */
@Api("文件管理")
@Controller
@RequestMapping("/file")
public class FilesController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(FilesController.class);
    private static String Save_Url = FileUploadUtils.getDefaultBaseDir();
    private String prefix = "file";
    @Autowired
    private IFilesService filesService;

    @Autowired
    private IPlayOrderService playOrderService;

    @RequiresPermissions("file:view")
    @GetMapping()
    public String files() {
        return prefix + "/file";
    }

    /**
     * 查询文件上传列表
     */
    @RequiresPermissions("file:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Files files) {
        startPage();
        List<Files> list = filesService.selectFilesList(files);
        return getDataTable(list);
    }


    /**
     * 导出文件上传列表
     */
    @RequiresPermissions("file:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Files files) {
        List<Files> list = filesService.selectFilesList(files);
        ExcelUtil<Files> util = new ExcelUtil<Files>(Files.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 新增文件上传
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        Files files = new Files();
        mmap.put("files", files);
        return prefix + "/add";
    }

    /**
     * 新增保存文件上传
     */
    @RequiresPermissions("file:add")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult save(MultipartFile file, Files files) {
        int rtn = 0;
        Boolean isFile = false;
        try {
            files = dealFile(file, files);
            if (files.getUpdateFlag() == 1 && files.getId() > 0) {//修改
                if (file != null){
                    isFile = true ;
                }
                rtn = filesService.updateFiles(files,isFile);
            } else {//新增
                rtn = filesService.insertFiles(files);
            }
            if (file != null && !"2".equals(files.getType())) {
                File desc = FileUploadUtils.getAbsoluteFile(Save_Url, files.getUrl());
                file.transferTo(desc);
            }
        } catch (Exception e) {
            log.error("保存失败，请检查后重试！", e);
            return error(e.getMessage());
        }
        return toAjax(rtn);
    }

    /**
     * 修改文件View
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Files files = filesService.selectFilesById(id);
        mmap.put("files", files);
        String fileUrl = "/profile/" + files.getFileName() + "." + files.getSuffix();
        mmap.put("fileUrl", fileUrl);
        return prefix + "/add";
    }

    /**
     * 修改保存文件上传
     */
    @RequiresPermissions("file:edit")
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MultipartFile file, Files files) {
        return toAjax(filesService.updateFiles(files,false));
    }

    /**
     * 删除文件上传
     */
    @RequiresPermissions("file:remove")
    @Log(title = "文件上传", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(filesService.deleteFilesByIds(ids));
    }

    @ApiOperation("获取列表")
    @RequestMapping("/listOrder")
    @ResponseBody
    public TableDataInfo getShow(PlayOrder playOrder) {
        List<PlayOrder> playOrderList = playOrderService.selectOrderList(playOrder);
        return getDataTable(playOrderList);
    }

    @RequestMapping("/listOrderPage")
    public String listOrder() {
        return prefix + "/orderShow";
    }

    /**
     * 检验文件名是否唯一
     *
     * @param files
     * @return
     */
    @PostMapping("/checkFileNameUnqiue")
    @ResponseBody
    public String checkFileNameUnqiue(Files files) {
        return filesService.checkFileNameUnqiue(files);
    }

    private Files dealFile(MultipartFile file, Files files) throws Exception {
        if (file == null || "2".equals(files.getType())) return files;

        String suffix = FileUploadUtils.dealName(file.getOriginalFilename());
        if (StringUtils.isEmpty(suffix)) throw new Exception();

        String name = Save_Url + files.getFileName() + "." + suffix;
        files.setUrl(name);
        files.setSuffix(suffix);

        return files;
    }
    @RequestMapping("/show")
    public String show() {
        return  prefix + "/lunbo";
    }


}
