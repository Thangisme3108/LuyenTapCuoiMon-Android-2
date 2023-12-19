package com.example.luyentapcuoimon.DTO;

public class DuLieuDTO {
    int id;
    String ngay_thi, ca, phong, ten_mon;

    public DuLieuDTO() {
    }

    public DuLieuDTO(int id, String ca, String ngay_thi, String phong, String ten_mon) {
        this.id = id;
        this.ca = ca;
        this.ngay_thi = ngay_thi;
        this.phong = phong;
        this.ten_mon = ten_mon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getNgay_thi() {
        return ngay_thi;
    }

    public void setNgay_thi(String ngay_thi) {
        this.ngay_thi = ngay_thi;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }
}
