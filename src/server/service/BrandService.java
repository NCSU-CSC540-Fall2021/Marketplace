package server.service;

import server.dao.BrandDao;
import server.entity.Brand;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrandService {

    public BrandService() {
    }

    public String createBrand(String brandNameText, String brandAddressText,
                              String brandJoinDateText, String brandCreatorIdText) throws ParseException, SQLException {

        BrandDao brandDao = new BrandDao();
        Brand brand = new Brand();

        // todo: do all validations here
        brand.setName(brandNameText);
        brand.setAddress(brandAddressText);
        brand.setJoindate(convertStringToDate(brandJoinDateText));
        brand.setCreatedBy(Integer.valueOf(brandCreatorIdText));
        brand.setCreatedAt(new Date());
        brand.setUpdatedBy(Integer.valueOf(brandCreatorIdText));

        String response = brandDao.createBrand(brand);
        return response;
    }

    public Date convertStringToDate(String dateString) throws ParseException {
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
        return date;
    }

}
