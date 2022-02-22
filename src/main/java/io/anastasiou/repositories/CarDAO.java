package io.anastasiou.repositories;

import io.anastasiou.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDAO implements VehicleDAO<Car> {
    @Autowired
    private DataSource mysqlDataSource;

    private static final String GET = "SELECT id, make, model, year FROM tblVehicles WHERE id=?";
    private static final String GET_ALL = "SELECT id, make, model, year FROM tblVehicles";
    private static final String INSERT = "INSERt INTO tblVehicles (make, model, year) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE tblVehicles SET make=?, model=?, year=? WHERE id=?";
    private static final String DELETE = "DELETE FROM tblVehicles WHERE id=?";

    @Override
    public Car getById(int id) {
        try(Connection conn = mysqlDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(GET);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Car result = new Car(rs.getInt("id"), rs.getString("make"), rs.getString("model"), rs.getInt("year"));
            return result;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> getAll() {
        try(Connection conn = mysqlDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(GET_ALL);) {
            ResultSet rs = stmt.executeQuery();
            List<Car> result = new ArrayList<>();

            while(rs.next()) {
                result.add(new Car(rs.getInt("id"), rs.getString("make"), rs.getString("model"), rs.getInt("year")));
            }

            return result;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Car entity) {
        try(Connection conn = mysqlDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, entity.getMake());
            stmt.setString(2, entity.getModel());
            stmt.setInt(3, entity.getYear());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int result = rs.getInt(1);
            return result;
        } catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(Car entity) {
        try(Connection conn = mysqlDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
            stmt.setString(1, entity.getMake());
            stmt.setString(2, entity.getModel());
            stmt.setInt(3, entity.getYear());
            stmt.setInt(4, entity.getId());
            int result = stmt.executeUpdate();

            if(result == 0) {
                throw new SQLException();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Car entity) {
        try(Connection conn = mysqlDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(DELETE);) {
            stmt.setInt(1, entity.getId());
            int result = stmt.executeUpdate();

            if(result == 0) {
                throw new SQLException();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
