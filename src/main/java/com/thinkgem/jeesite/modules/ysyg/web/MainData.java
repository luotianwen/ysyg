package com.thinkgem.jeesite.modules.ysyg.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.ysyg.entity.Yclothes;
import com.thinkgem.jeesite.modules.ysyg.entity.Yuser;

import java.util.List;

public class MainData {
    List<Yclothes>gqs= Lists.newArrayList();

    List<Yuser> gss= Lists.newArrayList();

    public List<Yclothes> getGqs() {
        return gqs;
    }

    public void setGqs(List<Yclothes> gqs) {
        this.gqs = gqs;
    }

    public List<Yuser> getGss() {
        return gss;
    }

    public void setGss(List<Yuser> gss) {
        this.gss = gss;
    }
}
