package paging.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import paging.dao.ItemsDao;
import paging.dao.impl.ItemsDaoImpl;
import paging.service.ItemService;
import paging.util.PagingBean;
import paging.util.SqlSessionFactoryManager;
import paging.vo.Item;

public class ItemServiceImpl implements ItemService {
	private SqlSessionFactory factory;
	private ItemsDao dao;
	private static ItemService instance;

	private ItemServiceImpl() throws IOException {
		factory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();
		dao = ItemsDaoImpl.getInstance();
	}
	public static ItemService getInstance() throws IOException {
		if (instance == null) {
			instance = new ItemServiceImpl();
		}
		return instance;
	}
	
	@Override
	public Map<String, Object> getItemList(int page){
		HashMap<String, Object> map = new HashMap<>(); //결과값을 담을 Map
		SqlSession session = factory.openSession();
		try {
			//1. PagingBean 객체 생성 → Paging 계산 처리하는 객체 → 보려는 페이지, 총 item 수
			int totalCount = dao.selectItemCount(session);
			PagingBean pageBean = new PagingBean(totalCount, page);
			map.put("pageBean", pageBean);
			//2. page에 보여줄 item 리스트
			List<Item> list = dao.selectItemList(session, pageBean.getBeginItemInPage(), pageBean.getEndItemInPage());
			map.put("list", list);
			return map;
		} finally {
			session.close();
		}
	}
	
	
}








