package co.airdo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.airdo.spring.dao.Offer;
import co.airdo.spring.dao.OffersDAO;

@Service("offersService")
public class OffersService {
	
	private OffersDAO offersDao;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	public void setOffersDao(OffersDAO offersDao) {
		this.offersDao = offersDao;
	}

	public List<Offer> getCurrent() {
		return offersDao.getOffers();
	}

	public void create(Offer offer) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(offer.getEmail());
		email.setSubject("You repair request has been just created");
		email.setText("Your reapair request has been just created");
		sendMailInBackground(email);
		offersDao.create(offer);
	}
	
	public void update(Offer offer) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(offer.getEmail());
		email.setSubject("You repair request has been changed");
		email.setText("Administrator has changed your repair request");
		sendMailInBackground(email);
		offersDao.update(offer);
	}
	
	public Offer findOfferById(int id) {
		return offersDao.findByOfferId(id);
	}
	
	public boolean delete(int id) {
		return offersDao.delete(id);
	}

	public void throwTestException() {
		offersDao.getOffer(99999);
	}
	
	public void sendMailInBackground(final SimpleMailMessage email) {
		Runnable r = new Runnable() {
			public void run() {
				mailSender.send(email);
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
