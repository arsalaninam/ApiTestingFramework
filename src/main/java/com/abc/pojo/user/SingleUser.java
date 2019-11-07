package com.abc.pojo.user;

import com.abc.pojo.address.Address;
import com.abc.pojo.company.Company;
import lombok.Getter;
import lombok.Setter;

/**
 * POJOs for Single User by id API Response
 * https://<domain>/users/<id>
 * e.g. https://jsonplaceholder.typicode.com/users/1
 *
 * @author Arsalan Inam
 */
@Getter
@Setter
public class SingleUser {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", name = " + name + ", username = " + username + ", email = " + email + ", address = " + address + ", phone = " + phone + ", website = " + website + ", company = " + company + "]";
    }
}
