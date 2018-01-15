package org.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.CommentDao;
import org.dao.imp.CommentDaoImp;
import org.model.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VCommentId;

@RestController("/app/CommentController")
@RequestMapping("/app/comment")
public class CommentController {
	Map<String, Object> data;
	CommentDao cDao;

	@RequestMapping("addComment")
	public Object addComment(
			HttpServletRequest request,
			Comment comment,
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files) {
		Long time = System.currentTimeMillis() / 1000;
		String path = request.getSession().getServletContext()
				.getRealPath("/upload/comment/");
		List<String> urlList = new ArrayList<>();
		if (files != null && files.length > 0) {
			try {
				for (CommonsMultipartFile file : files) {
					String filename = time + "_" + file.getOriginalFilename();
					String filePath = path + filename;
					File newFile = new File(filePath);
					// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
					file.transferTo(newFile);
					urlList.add("upload/comment/" + filename);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return ResultUtils.toJson(101, "图片上传失败，请重试", "");
			}
		}
		cDao = new CommentDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		// 执行批量插入图片操作以及增加记录
		comment.setUserid(userid);
		if (cDao.addComment(comment, urlList)) {
			return ResultUtils.toJson(100, "评论成功", "");
		}
		return ResultUtils.toJson(101, "评论失败", "");
	}

	@RequestMapping("getCommentsByCatalogId")
	public Object getCommentsByCatalogId(HttpServletRequest request,
			Integer catalogId, Integer start, Integer limit, Integer type) {
		cDao = new CommentDaoImp();
		List<VCommentId> list = cDao.getList(catalogId, type, start, limit);
		Long total = cDao.getCount(catalogId, 0);
		Long total2 = cDao.getCount(catalogId, 1);
		Long total3 = cDao.getCount(catalogId, 2);
		Long total4 = total - total2 - total3;
		// System.out.println(Arrays.asList(list.get(0).getImgUrl().split(",")).toString());
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "暂无评论", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("all", total);
		data.put("good", total2);
		data.put("center", total3);
		data.put("bad", total4);
		return ResultUtils.toJson(100, "", data);
	}

}
