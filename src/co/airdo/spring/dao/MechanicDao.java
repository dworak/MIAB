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

@Component("mechanicDao")
public class MechanicDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Mechanic> getMechanics() {
		return jdbc.query("select * from mechanics", new RowMapper<Mechanic>() {
			public Mechanic mapRow(ResultSet rs, int rowNum) throws SQLException {
				Mechanic mechanic = new Mechanic();

				mechanic.setId(rs.getInt("id"));
				mechanic.setName(rs.getString("name"));
				mechanic.setEmail(rs.getString("email"));
				return mechanic;
			}
		});
	}
	
	public Mechanic findByMechanicId(int mechanicId){
		MapSqlParameterSource params = new MapSqlParameterSource("id", mechanicId);
		return jdbc.queryForObject("select * from mechanics where id = :id", params, new BeanPropertyRowMapper<Mechanic>(Mechanic.class));
	}
	
	public boolean update(Mechanic mechanic) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(mechanic);
		return jdbc.update("update mechanics set name=:name, email=:email where id=:id", params) == 1;
	}
	
	public boolean create(Mechanic mechanic) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(mechanic);
		return jdbc.update("insert into mechanics (name, email) values (:name, :email)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<Mechanic> mechanic) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(mechanic.toArray());
		return jdbc.batchUpdate("insert into mechanics (id, name, email) values (:id, :name, :email)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from mechanics where id=:id", params) == 1;
	}

	public Mechanic getMechanic(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from mechanics where id=:id", params,
				new RowMapper<Mechanic>() {

					public Mechanic mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Mechanic mechanic = new Mechanic();
						mechanic.setId(rs.getInt("id"));
						mechanic.setName(rs.getString("name"));
						mechanic.setEmail(rs.getString("email"));

						return mechanic;
					}
				});
	}
}
