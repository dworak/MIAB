package co.airdo.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers() {

		List<Offer> offers = jdbc.query("select * from offers", new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				offer.setMechanicId(rs.getInt("mechanicId"));
				offer.setDueDate(rs.getDate("dueDate"));
				offer.setCreateDate(rs.getDate("createDate"));
				
				return offer;
			}
		});
		
		return offers;
	}
	
	public Offer findByOfferId(int offerId){
		MapSqlParameterSource params = new MapSqlParameterSource("id", offerId);
		return jdbc.queryForObject("select * from offers where id = :id", params, new BeanPropertyRowMapper<Offer>(Offer.class));
	}
	
	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set name=:name, text=:text, email=:email, mechanicId=:mechanic.id, dueDate=:dueDate, createDate=:createDate where id=:id", params) == 1;
	}
	
	public boolean create(Offer offer) {
		System.out.println(offer);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers (name, text, email, mechanicId, dueDate, createDate) values (:name, :text, :email, :mechanic.id, :dueDate, :createDate)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<Offer> offers) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (id, name, text, email, mechanicId) values (:id, :name, :text, :email, mechanicId=:mechanic.id)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id=:id", params) == 1;
	}

	public Offer getOffer(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from offers where id=:id", params,
				new RowMapper<Offer>() {

					public Offer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Offer offer = new Offer();

						offer.setId(rs.getInt("id"));
						offer.setName(rs.getString("name"));
						offer.setText(rs.getString("text"));
						offer.setEmail(rs.getString("email"));

						return offer;
					}

				});
	}
}
