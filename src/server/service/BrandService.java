package server.service;

import server.dao.BrandDao;
import server.entity.Brand;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BrandService {

    Scanner scanner = new Scanner(System.in);

    public BrandService () {

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


    private String getBrandName() {
        // todo: what to do if two brands with the same name is created
        System.out.println("Enter brand name");
        String brandName = scanner.nextLine();
        return brandName;
    }

    private String getBrandAddress() {
        System.out.println("Enter address for the brand");
        String brandAddress = scanner.nextLine();
        return brandAddress;
    }

    private Date getBrandJoinDate() {
        // todo : should we consider the default current date from java
        // todo : or should database have a default way of adding joining date as soon as entry is created like some trigger
        // todo : or should we get that as an input

        // todo : should date be in UTC or EST or any other timezone

        return new Date();

    }

    private int getBrandCreatorId() {
        // todo : there is no admin table in our ER diagram :P
        // todo : I guess every brand will be created by an admin. So we might need to store who created which one
        // todo : Also we might need to check if the admin id entered is valid

        System.out.println("Enter admin Id to create a brand");
        int adminId = scanner.nextInt();
        return adminId;
    }

    public Date convertStringToDate(String dateString) throws ParseException {
        Date date =new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
        return date;
    }

}
