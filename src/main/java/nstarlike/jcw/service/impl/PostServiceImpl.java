package nstarlike.jcw.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;
import nstarlike.jcw.service.PostService;
import nstarlike.jcw.dao.PostDao;

@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	
	@Autowired
	private PostDao postDao;
	
	@Override
	public int write(Post post) {
		logger.debug("start PostServiceImpl.write");
		logger.debug("post=" + post);
		
		return postDao.create(post);
	}

	@Override
	public List<PostMap> listAll(Map<String, String> params) {
		logger.debug("start PostServiceImpl.listAll");
		logger.debug("params=" + params);
		
		return postDao.readAll(params);
	}

	@Override
	public Post getById(long id) {
		logger.debug("start PostServiceImpl.getById");
		logger.debug("id=" + id);
		
		return postDao.readById(id);
	}

	@Override
	public int update(Post post) {
		logger.debug("start PostServiceImpl.update");
		logger.debug("post=" + post);
		
		return postDao.update(post);
	}

	@Override
	public int hit(long id) {
		logger.debug("start PostServiceImpl.hit");
		logger.debug("id=" + id);
		
		return postDao.hit(id);
	}

	@Override
	public int delete(long id) {
		logger.debug("start PostServiceImpl.delete");
		logger.debug("id=" + id);
		
		return postDao.delete(id);
	}

}
