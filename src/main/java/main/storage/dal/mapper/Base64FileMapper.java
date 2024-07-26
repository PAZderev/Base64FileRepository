package main.storage.dal.mapper;

import main.model.Base64File;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class Base64FileMapper implements RowMapper<Base64File> {
    @Override
    public Base64File mapRow(ResultSet rs, int rowNum) throws SQLException {
        Base64File b64File = new Base64File();
        b64File.setId(rs.getLong("id"));
        b64File.setBase64Data(rs.getString("data"));
        b64File.setTitle(rs.getString("title"));
        b64File.setDescription(rs.getString("description"));
        b64File.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        return b64File;
    }
}
