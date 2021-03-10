package com.example.myapplication;

public class model {
    String cname,ccode,ctutor;
    String filename,fileUrl;

    model(){

    }

    public model(String cname, String ccode, String ctutor) {
        this.cname = cname;
        this.ccode = ccode;
        this.ctutor = ctutor;
    }

    public model(String filename,String fileUrl){
        this.filename = filename;
        this.fileUrl = fileUrl;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCtutor() {
        return ctutor;
    }

    public void setCtutor(String ctutor) {
        this.ctutor = ctutor;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}

