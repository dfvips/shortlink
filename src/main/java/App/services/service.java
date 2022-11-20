package App.services;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import App.entity.db;
import App.mybaties.Mapper;
import App.mybaties.MybatisReader;

public class service {
	public static List<db> search(String u_id) {
		SqlSession sqlSession = MybatisReader.getSession();
		List<db> list = sqlSession.getMapper(Mapper.class).search(u_id);
		sqlSession.commit();
		sqlSession.close();
//		System.out.println(list);
		return list;
	}

	public static Integer insert(String u_id,String o_url,String url) {
		SqlSession sqlSession = MybatisReader.getSession();
		Integer num = sqlSession.getMapper(Mapper.class).insert(o_url, url, u_id);
		sqlSession.commit();
		sqlSession.close();
//		System.out.println(list);
		return num;
	}
}
