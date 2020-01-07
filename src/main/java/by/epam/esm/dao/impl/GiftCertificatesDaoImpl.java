package by.epam.esm.dao.impl;

import by.epam.esm.dao.connection.DBWorker;
import by.epam.esm.dao.mapper.GiftCertificatesMapper;
import by.epam.esm.entity.GiftCertificate;
import by.epam.esm.dao.GiftCertificatesDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GiftCertificatesDaoImpl implements GiftCertificatesDao<GiftCertificate> {

    JdbcTemplate jdbcTemplate = DBWorker.getJdbcTemplate();
    @Override
    public void save(GiftCertificate giftCertificate) {
        jdbcTemplate.update
                ("INSERT INTO gift_certificates (id, name, description, price, create_date, last_update_date, duration, tag_id) VALUES (?,?,?,?,?,?,?,?)",
                        giftCertificate.getId(), giftCertificate.getName(), giftCertificate.getDescription(), giftCertificate.getPrice(),
                        giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(), giftCertificate.getDuration(), 1);
    }

    @Override
    public void delete(GiftCertificate giftCertificate) {
        jdbcTemplate.update("DELETE FROM gift_certificates WHERE id = ?", giftCertificate.getId());
    }

    @Override
    public List<GiftCertificate> getAll() {
        return jdbcTemplate.query(
                "SELECT gift_certificates.id, gift_certificates.name, gift_certificates.description, gift_certificates.price, gift_certificates.create_date, gift_certificates.last_update_date, gift_certificates.duration, tag.id, tag.name FROM gift_certificates INNER JOIN tag ON gift_certificates.tag_id=tag.id", new GiftCertificatesMapper());
    }

    public GiftCertificate findById(int id){
        return jdbcTemplate.queryForObject("select * from gift_certificates where id = ?", new Object[]{id}, new GiftCertificatesMapper());
    }
    @Override
    public void update(GiftCertificate giftCertificate) {
        jdbcTemplate.update("UPDATE gift_certificates SET name = ?, description=?, price=?, create_date=?, last_update_date=?, duration=? WHERE id = ?",
                giftCertificate.getName(), giftCertificate.getDescription(), giftCertificate.getPrice(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(), giftCertificate.getDuration(), giftCertificate.getId());
    }

}
