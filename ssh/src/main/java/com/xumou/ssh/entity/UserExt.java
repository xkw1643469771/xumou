package com.xumou.ssh.entity;

import java.util.List;

/**
 *
 */
public class UserExt extends User{


    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}