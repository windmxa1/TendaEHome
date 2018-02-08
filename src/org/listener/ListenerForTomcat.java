package org.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dao.FranchiseeDao;
import org.dao.imp.FranchiseeDaoImp;
import org.util.Coordinate;
import org.util.RedisUtil;

public class ListenerForTomcat implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		FranchiseeDao fDao = new FranchiseeDaoImp();
		List<Object[]> list = fDao.getLatLonList();
		for (Object[] o : list) {
			Coordinate coordinate = new Coordinate((double) o[0],
					(double) o[1], "" + o[2]);
			RedisUtil.addReo(coordinate, "cooker");
		}
		
	}

}
