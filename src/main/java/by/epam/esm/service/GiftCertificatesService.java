package by.epam.esm.service;

import by.epam.esm.dao.impl.GiftCertificatesDaoImpl;
import by.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftCertificatesService {
    private final GiftCertificatesDaoImpl giftCertificatesDao;

    public GiftCertificatesService(GiftCertificatesDaoImpl giftCertificatesDao) {
        this.giftCertificatesDao = giftCertificatesDao;
    }


    public void save(GiftCertificate giftCertificate) {
        giftCertificatesDao.save(giftCertificate);
    }

    public void delete(GiftCertificate giftCertificate) {
        giftCertificatesDao.delete(giftCertificate);
    }

    public List<GiftCertificate> getAll() {
        return giftCertificatesDao.getAll();
    }

    public GiftCertificate findById(int id){
        return giftCertificatesDao.findById(id);
    }

    public void update(GiftCertificate giftCertificate) {
    giftCertificatesDao.update(giftCertificate);
    }

    public List<GiftCertificate> sortByName(String typeSort) {
        List<GiftCertificate> giftCertificates = giftCertificatesDao.getAll();
        List<GiftCertificate> collect = new ArrayList<>();
        if (typeSort.equals("desc")) {
            collect = giftCertificates.stream()
                    .sorted(Comparator.comparing(GiftCertificate::getName).reversed())
                    .collect(Collectors.toList());
        } else if (typeSort.equals("asc")) {
            collect = giftCertificates.stream()
                    .sorted(Comparator.comparing(GiftCertificate::getName))
                    .collect(Collectors.toList());
        }
        return collect;
    }
}
