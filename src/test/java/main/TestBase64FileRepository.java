package main;

import lombok.RequiredArgsConstructor;
import main.exception.NotFoundException;
import main.model.Base64File;
import main.storage.dal.Base64FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ComponentScan("main")
public class TestBase64FileRepository {

    private final Base64FileRepository base64FileRepository;

    @Test
    public void testAddAndFindFileById() {
        Base64File testFile = new Base64File();
        testFile.setTitle("Test Add File");
        testFile.setDescription("Test Add File");
        testFile.setBase64Data("testdata");
        testFile.setCreationDate(LocalDateTime.now().withNano(0));
        long id = base64FileRepository.insertFile(testFile);
        Base64File findFIle = base64FileRepository.findById(id);
        assertThat(findFIle).isNotNull()
                .hasFieldOrPropertyWithValue("Title", testFile.getTitle())
                .hasFieldOrPropertyWithValue("Description", testFile.getDescription())
                .hasFieldOrPropertyWithValue("Base64Data", testFile.getBase64Data())
                .hasFieldOrPropertyWithValue("CreationDate", testFile.getCreationDate());
    }

    @Test
    public void testUpdateFile() {
        Base64File testFile = new Base64File();
        testFile.setTitle("Test Update File");
        testFile.setDescription("Test Update File");
        testFile.setBase64Data("testdata");
        testFile.setCreationDate(LocalDateTime.now().withNano(0));
        long id = base64FileRepository.insertFile(testFile);
        testFile.setId(id);
        testFile.setTitle("Updated Test File");
        base64FileRepository.updateFile(testFile);
        Base64File findFIle = base64FileRepository.findById(id);
        assertThat(findFIle).isNotNull().hasFieldOrPropertyWithValue("Title", testFile.getTitle());
    }

    @Test
    public void testDeleteFile() {
        Base64File testFile = new Base64File();
        testFile.setTitle("Test Delete File");
        testFile.setDescription("Test Delete File");
        testFile.setBase64Data("testdata");
        testFile.setCreationDate(LocalDateTime.now().withNano(0));
        long id = base64FileRepository.insertFile(testFile);
        base64FileRepository.deleteFile(id);
        assertThrows(NotFoundException.class, () -> base64FileRepository.findById(id));
    }

    @Test
    public void testFindAll() {
        Base64File testFile = new Base64File();
        testFile.setTitle("Test Find All File");
        testFile.setDescription("Test Find All File");
        testFile.setBase64Data("testdata");
        testFile.setCreationDate(LocalDateTime.now().withNano(0));
        base64FileRepository.insertFile(testFile);
        Collection<Base64File> testFiles = base64FileRepository.findAll();
        assertThat(testFiles).isNotNull().isNotEmpty();
    }


}
