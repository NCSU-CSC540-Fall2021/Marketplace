package server;

import server.dao.BrandDao;

public class Marketplace {
    BrandDao brandDao;


    public Marketplace() {
        brandDao = new BrandDao();
    }
}
