package mx.com.baseapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiPageResponse implements Serializable {

    private Info info;
    private List<Character> results;


    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
