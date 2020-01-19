package mx.com.baseapplication.model;

import java.io.Serializable;

import mx.com.baseapplication.utils.Constants;

public class Info implements Serializable {

    private Integer count;
    private Integer pages;
    private String next;
    private String prev;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public String getMethod(){
        if(next!=null){
            return next.replace(Constants.BASE_URL,"");
        }else{
            return  next;
        }
    }

    public String getBackMethod(){
        if(prev!=null){
            return prev.replace(Constants.BASE_URL,"");
        }else{
            return  prev;
        }
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}
