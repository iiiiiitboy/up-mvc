package ex.aaronfae.service;

import ex.aaronfae.dao.HomeworkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ListServiceImpl implements ListService {

    private final HomeworkDAO homeworkDAO;

    @Autowired
    public ListServiceImpl(HomeworkDAO homeworkDAO) {
        this.homeworkDAO = homeworkDAO;
    }

    @Override
    public List<Map<String, Object>> list() {
        return homeworkDAO.list();
    }
}
