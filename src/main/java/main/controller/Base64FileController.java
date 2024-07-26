package main.controller;

import main.model.Base64File;
import main.service.Base64FileService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/files")
public class Base64FileController {
    private final Base64FileService base64FileService;

    public Base64FileController(Base64FileService base64FileService) {
        this.base64FileService = base64FileService;
    }

    @PostMapping
    public long uploadFile(@RequestBody Base64File file) {
        return base64FileService.uploadBase64File(file);
    }

    @GetMapping("/{id}")
    public Base64File getFile(@PathVariable long id) {
        return base64FileService.getBase64FileById(id);
    }

    @GetMapping
    public Collection<Base64File> getAllFiles(
            @RequestParam(value = "page", defaultValue = "0", required = false) Long page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Long size,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort)
    {
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException("sort must be either asc or desc");
        }

        if (page < 0) {
            throw new IllegalArgumentException("page must be greater or equals 0");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("size must be greater than 0");
        }

        long from = page * size;
        return base64FileService.getAllBase64Files(size, from, sort);
    }

    @DeleteMapping
    public boolean deleteFile(long id) {
        return base64FileService.deleteBase64FileById(id);
    }

    @PutMapping
    public long updateFile(@RequestBody Base64File file) {
        return base64FileService.updateBase64File(file);
    }
}
