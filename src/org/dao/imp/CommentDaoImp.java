package org.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dao.CommentDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.model.Comment;
import org.util.HibernateSessionFactory;
import org.view.VComment;
import org.view.VCommentId;

public class CommentDaoImp implements CommentDao {

	@Override
	public Long saveOrupdate(Comment comment) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Long id = 0L;
			if (comment.getId() == null) {
				id = (Long) session.save(comment);
			} else {
				session.update(comment);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean delete(Long commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("delete from Comment where id = ?");
			query.setParameter(0, commentId);
			query.executeUpdate();

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Comment");
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<Comment> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getList(Integer catalogId, Integer type, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 0:
				sql = "from VComment where id.catalogId=?";
				break;
			case 1:
				sql = "from VComment where id.catalogId=? and id.rating>3";
				break;
			case 2:
				sql = "from VComment where id.catalogId=? and (id.rating=3 or id.rating=2)";
				break;
			default:
				sql = "from VComment where id.catalogId=? and id.rating=1";
				break;
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VComment> list = query.list();
			List<VCommentId> list2 = new ArrayList<>();
			for (VComment v : list) {
				list2.add(v.getId());
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount(Integer catalogId, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(id.id) from VComment where id.catalogId=?";
				break;
			case 1:
				sql = "select count(id.id) from VComment where id.catalogId=? and id.rating>3";
				break;
			case 2:
				sql = "select count(id.id) from VComment where id.catalogId=? and (id.rating=3 or id.rating=2)";
				break;
			default:
				sql = "select count(id.id) from VComment where id.catalogId=? and id.rating=1";
				break;
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			query.setMaxResults(1);
			return (Long) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getList(Long franchiseeId, Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from Comment c,Orders o where o.franchisee_id=? and o.id = c.orderId");
			query.setParameter(0, franchiseeId);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<Comment> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean addComment(Comment comment, final List<String> urlList) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			final Long id = (Long) session.save(comment);
			if (urlList.size() > 0) {
				session.doWork(new Work() {
					public void execute(Connection connection)
							throws SQLException {
						PreparedStatement stmt = connection
								.prepareStatement("insert into comment_img(comment_id,url) values(?,?)");
						connection.setAutoCommit(false);
						for (String url : urlList) {
							stmt.setLong(1, id);
							stmt.setString(2, url);
							stmt.addBatch();
						}
						stmt.executeBatch();
					}
				});
			}
			Query query = session
					.createQuery("update orders set is_comment = 1 where id = ?");
			query.setParameter(0, comment.getOrderId());
			query.executeUpdate();
			ts.commit();
			session.flush();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
