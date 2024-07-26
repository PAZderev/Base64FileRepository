package main.service;

import lombok.extern.slf4j.Slf4j;
import main.model.Base64File;
import main.storage.dal.Base64FileRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Base64FileService {
    private final Base64FileRepository base64FileRepository;

    public Base64FileService(Base64FileRepository base64FileRepository) {
        this.base64FileRepository = base64FileRepository;
    }

    public long uploadBase64File(Base64File file) {
        log.info("Uploading Base64 File {}", file);
        return base64FileRepository.insertFile(file);
    }

    public Base64File getBase64FileById(long id) {
        log.info("Getting Base64 File by ID {}", id);
        return base64FileRepository.findById(id);
    }

    public Collection<Base64File> getAllBase64Files(long size, long from, String sort) {
        log.info("Getting All Base64 Files");
        Comparator<Base64File> comparator = sort.equals("asc")
                ? Comparator.comparing(Base64File::getCreationDate)
                : Comparator.comparing(Base64File::getCreationDate).reversed();
        return base64FileRepository.findAll()
                .stream()
                .sorted(comparator)
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public boolean deleteBase64FileById(long id) {
        log.info("Deleting Base64 File by ID {}", id);
        return base64FileRepository.deleteFile(id);
    }

    public long updateBase64File(Base64File file) {
        log.info("Updating Base64 File with ID {}", file.getId());
        return base64FileRepository.updateFile(file);
    }
}
