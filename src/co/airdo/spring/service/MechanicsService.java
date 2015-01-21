package co.airdo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.airdo.spring.dao.Mechanic;
import co.airdo.spring.dao.MechanicDao;

@Service("mechanicService")
public class MechanicsService {
	private MechanicDao mechanicsDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	public void setMechanicDao(MechanicDao mechanicsDao) {
		this.mechanicsDao = mechanicsDao;
	}

	public List<Mechanic> getCurrent() {
		return mechanicsDao.getMechanics();
	}

	public void create(Mechanic mechanic) {
		mechanicsDao.create(mechanic);
	}
	
	public void update(Mechanic mechanic) {
		mechanicsDao.update(mechanic);
	}
	
	public Mechanic findMechanicIdById(int id) {
		if (id == 0) {
			return null;
		}
		return mechanicsDao.findByMechanicId(id);
	}
	
	public boolean delete(int id) {
		return mechanicsDao.delete(id);
	}

	public void throwTestException() {
		mechanicsDao.getMechanic(99999);
	}
}
