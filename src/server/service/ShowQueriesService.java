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

    public Map<String, List<String[]>> getResultForQuery2() {
        Map<String, List<String[]>> resultForQuery2 = showQueriesDao.getResultForQuery2();
        return resultForQuery2;
    }

    public Map<String, List<String>> getResultForQuery3() {
        Map<String, List<String>> resultForQuery3 = showQueriesDao.getResultForQuery3();
        return resultForQuery3;
    }

    public Map<String, List<String>> getResultForQuery4() {
        Map<String, List<String>> resultForQuery4 = showQueriesDao.getResultForQuery4();
        return resultForQuery4;
    }

    public Map<String, List<String[]>> getResultForQuery5() {
        Map<String, List<String[]>> resultForQuery5 = showQueriesDao.getResultForQuery5();
        return resultForQuery5;
    }

    public Map<String, List<String[]>> getResultForQuery6() {
        Map<String, List<String[]>> resultForQuery6 = showQueriesDao.getResultForQuery6();
        return resultForQuery6;
    }

    public Map<String, List<String[]>> getResultForQuery7() {
        Map<String, List<String[]>> resultForQuery7 = showQueriesDao.getResultForQuery7();
        return resultForQuery7;
    }

    public Map<String, List<String[]>> getResultForQuery8() {
        Map<String, List<String[]>> resultForQuery8 = showQueriesDao.getResultForQuery8();
        return resultForQuery8;
    }
}
