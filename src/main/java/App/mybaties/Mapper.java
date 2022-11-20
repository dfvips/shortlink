package App.mybaties;

import java.util.List;

import App.entity.db;

public interface Mapper {
    public List<db> search(String u_id);
    public Integer insert(String o_url,String url,String u_id);
}
