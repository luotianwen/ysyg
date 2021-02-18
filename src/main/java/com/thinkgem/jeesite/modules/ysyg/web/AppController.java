package com.thinkgem.jeesite.modules.ysyg.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.web.BaseController;

import com.thinkgem.jeesite.modules.ysyg.entity.YUserLikeClothes;
import com.thinkgem.jeesite.modules.ysyg.entity.Yclothes;
import com.thinkgem.jeesite.modules.ysyg.entity.Yuser;
import com.thinkgem.jeesite.modules.ysyg.service.YUserLikeClothesService;
import com.thinkgem.jeesite.modules.ysyg.service.YuserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/app")
public class AppController extends BaseController {
    Logger log = LogManager.getLogger(AppController.class);
    @Autowired
    private YuserService studentService;

    @Autowired
    private YUserLikeClothesService yUserLikeClothesService;

    @RequestMapping(value = "login")
    @ResponseBody
    public ReqResponse<Yuser> login(Yuser user) {
        ReqResponse<Yuser> r=new ReqResponse();
         List<Yuser>  musers=studentService.findList(user);

        if (null == musers||musers.size()==0) {
            r.setCode(1);
            r.setMsg("用户名密码错误");
            return r;
        }
        if (!musers.get(0).getPass().equals(user.getPass())) {
            r.setCode(1);
            r.setMsg("用户名密码错误");
            return r;
        }
         r.setData(musers.get(0));

        return r;

    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ReqResponse<String> update(Yuser user) {
        ReqResponse<String> r=new ReqResponse();
        Yuser m2=studentService.get(user.getId());
        if(!m2.getPass().equals(user.getPass())){
            r.setCode(1);
            r.setMsg("原密码不对");
            return r;
        }
        m2.setPass(user.getPass());
        studentService.save(m2);
        return r;

    }
    @RequestMapping(value = "main")
    @ResponseBody
    public ReqResponse<String> main(Yuser user) {
        ReqResponse<String> r=new ReqResponse();
        Yuser m2=studentService.get(user.getId());


        UserSet gedanSet = new UserSet();

        List<YUserLikeClothes> gds= yUserLikeClothesService.findList(new YUserLikeClothes());
        for (YUserLikeClothes u:gds
        ) {
            UserSet.User u1=gedanSet.put(u.getUser().getId());


            List<YUserLikeClothes> gls= yUserLikeClothesService.findList(u);
            for (YUserLikeClothes g:gls
            ) {
                u1.set(g.getCid().getId(), Integer.parseInt(g.getScore()));
            }
            u1.create();
        }
        Recommend gedanrecommend = new Recommend();
        List<UserSet.Set> gedanrecommendations2 = gedanrecommend.recommend(user.getId(), gedanSet);
        List<UserSet.Set> gedanrecommendations=gedanrecommendations2.subList(0, gedanrecommendations2.size()>15?15:gedanrecommendations2.size());
        if(gedanrecommendations.size()==0){

        }
        return r;

    }



    @RequestMapping("/upload")
    @ResponseBody
    public ReqResponse upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 获取文件存储路径（绝对路径）
        String path = Global.getUserfilesBaseDir();
        String realPath =  Global.USERFILES_BASE_URL + "/" + DateUtils.getYear() + "/" + DateUtils.getMonth() + "/"+ DateUtils.getDay();
        FileUtils.createDirectory(FileUtils.path(path+"/"+realPath));

        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path+ "/" +realPath, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        // 写入文件
        file.transferTo(filePath);
        ReqResponse r=new ReqResponse();
        r.setData(realPath+"/"+fileName);
        return r;
    }
}
