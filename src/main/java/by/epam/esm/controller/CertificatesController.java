package by.epam.esm.controller;


import by.epam.esm.entity.GiftCertificate;
import by.epam.esm.service.GiftCertificatesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificatesController {

    private final GiftCertificatesService giftCertificatesService;

    public CertificatesController(GiftCertificatesService giftCertificatesService) {
        this.giftCertificatesService = giftCertificatesService;
    }

    @PostMapping
    public List<GiftCertificate> save(@RequestBody GiftCertificate giftCertificate) {
        giftCertificatesService.save(giftCertificate);
        return giftCertificatesService.getAll();
    }

    @GetMapping
    public List<GiftCertificate> getAllCertificates() {
        return giftCertificatesService.getAll();
//        return new User(1, "asfsf");
    }

    @GetMapping(value = "/{id}")
    public GiftCertificate findById(@PathVariable int id) {
        return giftCertificatesService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public List<GiftCertificate> delete(@PathVariable int id) {
        GiftCertificate giftCertificate = giftCertificatesService.findById(id);
        giftCertificatesService.delete(giftCertificate);
        return giftCertificatesService.getAll();
    }

    private GiftCertificate check(GiftCertificate giftCertificate, GiftCertificate certificateBody) {
        if (!giftCertificate.getName().equals(certificateBody.getName())) {
            giftCertificate.setName(certificateBody.getName());
        } else if (!giftCertificate.getDescription().equals(certificateBody.getDescription())) {
            giftCertificate.setDescription(certificateBody.getDescription());
        } else if (giftCertificate.getPrice() != certificateBody.getPrice()) {
            giftCertificate.setPrice(certificateBody.getPrice());
        } else if (!giftCertificate.getCreateDate().equals(certificateBody.getCreateDate())) {
            giftCertificate.setCreateDate(certificateBody.getCreateDate());
        } else if (!giftCertificate.getLastUpdateDate().equals(certificateBody.getLastUpdateDate())) {
            giftCertificate.setLastUpdateDate(certificateBody.getLastUpdateDate());
        } else if (giftCertificate.getDuration() != certificateBody.getDuration()) {
            giftCertificate.setDuration(certificateBody.getDuration());
        }
        return giftCertificate;
    }

    //    @PutMapping(value = "/{id}/{name}")
    @PutMapping(value = "/{id}")
//    public List<GiftCertificate> update(@PathVariable int id, @PathVariable String name) {
    public List<GiftCertificate> update(@RequestBody GiftCertificate certificateBody, @PathVariable int id) {
        GiftCertificate giftCertificate = giftCertificatesService.findById(id);
        check(giftCertificate, certificateBody);
//        giftCertificate.setName(certificate.getName());
        giftCertificatesService.update(giftCertificate);
        return giftCertificatesService.getAll();
    }

    @GetMapping(value = "/sort/{type}")
    public List<GiftCertificate> sort(@PathVariable String type) {
        return giftCertificatesService.sortByName(type);
    }
}
