package main.storage.dal;

import main.exception.NotFoundException;
import main.model.Base64File;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class Base64FileRepository extends BaseRepository<Base64File> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM files";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM files WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO files (data, title, description, creation_date)" +
            " VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM files WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE files SET data = ?, title = ?, description = ? WHERE id = ?";

    public Base64FileRepository(JdbcTemplate jdbc, RowMapper<Base64File> mapper) {
        super(jdbc, mapper);
    }

    public Collection<Base64File> findAll() {
        return findAll(FIND_ALL_QUERY);
    }

    public Base64File findById(long id) {
        Base64File file =  findOne(FIND_BY_ID_QUERY, id).orElse(null);
        if (file == null) {
            throw new NotFoundException("File with id " + id + " not found");
        }
        return file;
    }

    public long insertFile(Base64File file) {
        return insert(INSERT_QUERY,
                file.getBase64Data(),
                file.getTitle(),
                file.getDescription(),
                file.getCreationDate());
    }

    public long updateFile(Base64File file) {
        return update(UPDATE_QUERY, file.getBase64Data(), file.getTitle(), file.getDescription(), file.getId());
    }

    public boolean deleteFile(long id) {
        return delete(DELETE_QUERY, id);
    }


}
