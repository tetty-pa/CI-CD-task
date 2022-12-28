package com.epam.esm;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificatesService {
    private final GiftCertificateDAO giftCertificateDAO;

    @Autowired
    public GiftCertificatesService(GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    public List<GiftCertificate> getAll() {
        return giftCertificateDAO.getAll();
    }
}
