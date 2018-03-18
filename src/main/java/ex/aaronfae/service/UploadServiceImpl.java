package ex.aaronfae.service;

import ex.aaronfae.dao.HomeworkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    private final HomeworkDAO homeworkDAO;

    @Autowired
    public UploadServiceImpl(HomeworkDAO homeworkDAO) {
        this.homeworkDAO = homeworkDAO;
    }

    @Override
    public void upload(String uper, String filename) {
        homeworkDAO.upload(uper, filename);
    }
}
