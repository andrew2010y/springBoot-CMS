package com.lzkj.lxzb.rest.modular.user.entity;

import java.math.BigDecimal;

public class UserPositionExt {

    private BigDecimal numuserpositionid;
//    private String vc2type;
//    private BigDecimal numrewardretio;

    private String content;

    public BigDecimal getNumuserpositionid() {
        return numuserpositionid;
    }

    public void setNumuserpositionid(BigDecimal numuserpositionid) {
        this.numuserpositionid = numuserpositionid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
