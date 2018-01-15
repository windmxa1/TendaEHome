package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.CommentDao;
import org.dao.imp.CommentDaoImp;
import org.model.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.ResultUtils;

@RestController("/back/CommentController")
@RequestMapping("/back/comment")
public class CommentController {
	Map<String, Object> data;
	CommentDao cDao;

	@RequestMapping("getCommentsByFranchisee")
	public Object getCommentsByFranchisee(HttpServletRequest request,
			Long franchiseeId, Integer start, Integer limit) {
		cDao = new CommentDaoImp();
		List<Comment> list = cDao.getList(franchiseeId, start, limit);
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "暂无评论", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}
	@RequestMapping("getComments")
	public Object getComments(HttpServletRequest request, Integer start,
			Integer limit) {
		cDao = new CommentDaoImp();
		List<Comment> list = cDao.getList(start, limit);
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "暂无评论", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}
}
