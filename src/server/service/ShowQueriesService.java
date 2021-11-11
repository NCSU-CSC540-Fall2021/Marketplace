package server.service;

import server.dao.ShowQueriesDao;

import java.util.List;
import java.util.Map;

public class ShowQueriesService {

    private ShowQueriesDao showQueriesDao;
    public ShowQueriesService () {
        showQueriesDao = new ShowQueriesDao();
    }


    public Map<String, List<String[]>> getResultForQuery1() {
        Map<String, List<String[]>> resultForQuery1 = showQueriesDao.getResultForQuery1();
        return resultForQuery1;
    }

    public Map<String, List<String>> getResultForQuery3() {
        Map<String, List<String>> resultForQuery3 = showQueriesDao.getResultForQuery3();
        return resultForQuery3;
    }

    public Map<String, List<String>> getResultForQuery4() {
        Map<String, List<String>> resultForQuery4 = showQueriesDao.getResultForQuery4();
        return resultForQuery4;
    }
}