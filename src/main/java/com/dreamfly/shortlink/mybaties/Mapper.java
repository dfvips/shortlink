package com.dreamfly.shortlink.mybaties;

import java.util.List;
import com.dreamfly.shortlink.entity.db;

public interface Mapper {
    public List<db> search(String u_id);
    public Integer insert(String o_url,String url,String u_id);
}
