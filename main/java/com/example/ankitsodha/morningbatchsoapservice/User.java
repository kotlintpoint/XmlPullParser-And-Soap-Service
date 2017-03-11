package com.example.ankitsodha.morningbatchsoapservice;

/**
 * Created by Ankit Sodha on 06-Nov-15.
 */
public class User {

    private String _fname,_lname,_address;

    public String get_fname() {
        return _fname;
    }

    public void set_fname(String _fname) {
        this._fname = _fname;
    }

    public String get_lname() {
        return _lname;
    }

    public void set_lname(String _lname) {
        this._lname = _lname;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    @Override
    public String toString() {
        return _fname+", "+_lname+", "+_address;
    }
}
