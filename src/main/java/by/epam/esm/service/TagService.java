package by.epam.esm.service;

import by.epam.esm.dao.impl.TagDaoImpl;
import by.epam.esm.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagDaoImpl tagDao;
    private final Tag tag;

    public TagService(TagDaoImpl tagDao, Tag tag) {
        this.tagDao = tagDao;
        this.tag = tag;
    }

    public void save(Tag tag){
        tagDao.save(tag);
    }

    public void delete(Tag tag) {
        tagDao.delete(tag);
    }

    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    public Tag findById(int id){
        return tagDao.findById(id);
    }

}
