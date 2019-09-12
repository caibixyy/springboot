package com.xyy.cache.bean.mysqljpa;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "phone", nullable = true, length = 17)
    private long phone;

    @Column(name = "zipcode", nullable = true, length = 12)
    private String zipcode;

    @Column(name = "address", nullable = true, length = 100)
    private String address;

    public Address() {
    }

    public Address(int phone, String zipcode, String address) {
        this.phone = phone;
        this.zipcode = zipcode;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
