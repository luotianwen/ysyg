package com.thinkgem.jeesite.modules.ysyg.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;

import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.ysyg.entity.YUserClothes;
import com.thinkgem.jeesite.modules.ysyg.entity.YUserLikeClothes;
import com.thinkgem.jeesite.modules.ysyg.entity.Yclothes;
import com.thinkgem.jeesite.modules.ysyg.entity.Yuser;
import com.thinkgem.jeesite.modules.ysyg.service.YUserClothesService;
import com.thinkgem.jeesite.modules.ysyg.service.YUserLikeClothesService;
import com.thinkgem.jeesite.modules.ysyg.service.YclothesService;
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
    @Autowired
    private YclothesService yclothesService;

    @Autowired
    private YUserClothesService yUserClothesService;
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
    @RequestMapping(value = "yf")
    @ResponseBody
    public ReqResponse<List<Yclothes>> yf() {
        ReqResponse< List<Yclothes>> r=new ReqResponse();
        List<Yclothes> s=yclothesService.findList(new Yclothes());
        r.setData(s);
        return r;

    }
    @RequestMapping(value = "add")
    @ResponseBody
    public ReqResponse<Yuser> add(YUserLikeClothes yUserLikeClothes) {
        ReqResponse<Yuser> r=new ReqResponse();

        yUserLikeClothesService.save(yUserLikeClothes);
      try {
          YUserClothes yUserClothes = new YUserClothes();
          yUserClothes.setC(yUserLikeClothes.getCid());
          yUserClothes.setUser(yUserLikeClothes.getUser());
          yUserClothesService.save(yUserClothes);
          }catch (Exception e){
              System.out.println("已经有了这个衣服");
          }
        return r;

    }

    @RequestMapping(value = "wdadd")
    @ResponseBody
    public ReqResponse<Yuser> wdadd(YUserLikeClothes yUserLikeClothes) {
        ReqResponse<Yuser> r=new ReqResponse();
        try {
            YUserClothes yUserClothes = new YUserClothes();
            yUserClothes.setC(yUserLikeClothes.getCid());
            yUserClothes.setUser(yUserLikeClothes.getUser());
            yUserClothesService.save(yUserClothes);
        }catch (Exception e){
            System.out.println("已经有了这个衣服");
        }
        return r;

    }
    @RequestMapping(value = "wddel")
    @ResponseBody
    public ReqResponse<Yuser> wddel(YUserClothes yUserClothes) {
        ReqResponse<Yuser> r=new ReqResponse();
        try {

            yUserClothesService.delete(yUserClothes);
        }catch (Exception e){
            System.out.println("已经有了这个衣服");
        }
        return r;

    }
    @RequestMapping(value = "wdlist")
    @ResponseBody
    public ReqResponse<List<YUserClothes>> wdlist(YUserClothes yUserClothes) {
        ReqResponse<List<YUserClothes>> r=new ReqResponse();


           r.setData(yUserClothesService.findList(yUserClothes));

        return r;

    }
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "area")
    @ResponseBody
    public ReqResponse<List<Area>> area() {
        ReqResponse<List<Area>> r=new ReqResponse();
        Area area=new Area();
        area.setType("2");
        r.setData(areaService.findList(area));
        return r;

    }
    @RequestMapping(value = "reg")
    @ResponseBody
    public ReqResponse<Yuser> reg(Yuser user) {
        ReqResponse<Yuser> r=new ReqResponse();
        if (null == user.getAccount()||user.getAccount().equals("")||null == user.getPass()||user.getPass().equals("")) {
            r.setCode(1);
            r.setMsg("用户名密码不能为空");
            return r;
        }
        else{
            studentService.save(user);
        }


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
    public ReqResponse<MainData> main(Yuser user) {
        ReqResponse<MainData> r=new ReqResponse();
        MainData m=new MainData();
        m.setGss(studentService.findList(new Yuser()));
          if(StringUtils.isEmpty(user.getId())) {
             m.setGqs(yclothesService.findList(new Yclothes()));
          }
          else {



            UserSet gedanSet = new UserSet();

            List<YUserLikeClothes> gds = yUserLikeClothesService.findNOList(new YUserLikeClothes());
            for (YUserLikeClothes u : gds
            ) {
                UserSet.User u1 = gedanSet.put(u.getUser().getId());


                List<YUserLikeClothes> gls = yUserLikeClothesService.findList(u);
                for (YUserLikeClothes g : gls
                ) {
                    YUserLikeClothes yUserLikeClothes=new YUserLikeClothes();
                    yUserLikeClothes.setCid(g.getCid());
                    yUserLikeClothes.setNote(user.getArea());
                    u1.set(g.getCid().getId(),yUserLikeClothesService.findList(yUserLikeClothes).size());
                }
                //if(null!=gls&&gls.size()>0){
                    u1.create();
               // }

            }
            Recommend gedanrecommend = new Recommend();
            List<UserSet.Set> gedanrecommendations2 = gedanrecommend.recommend(user.getId(), gedanSet);
            List<UserSet.Set> gedanrecommendations = gedanrecommendations2.subList(0, gedanrecommendations2.size() > 15 ? 15 : gedanrecommendations2.size());
            if (gedanrecommendations.size() == 0) {
                //m.setGqs(yclothesService.findList(new Yclothes()));
            }
            else{
                List<Yclothes> gqs= Lists.newArrayList();
                for (UserSet.Set set : gedanrecommendations) {
                    Yclothes yclothes=yclothesService.get(set.username);
                    yclothes.setBofangcount(set.score);
                    gqs.add(yclothes);
                }
                m.setGqs(gqs);
            }
        }
        r.setData(m);
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
