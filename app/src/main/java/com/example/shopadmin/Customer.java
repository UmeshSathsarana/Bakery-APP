package com.example.shopadmin;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Customer {
    private  int Id;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;

    public void setId(int id) {Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return Id;
    }
    public void  Add(FirebaseDatabase fb)
    {
        try
        {
            DatabaseReference reference=
                    fb.getReference().child("customer");
            reference.push().setValue(this);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public List<Customer> GetCustomers(FirebaseDatabase fb)
    {
        try{
            List<Customer> customers=new ArrayList<>();
            DatabaseReference reference=fb.getReference().child("customer");
            DataSnapshot snapshot=reference.get().getResult();
            for (DataSnapshot child: snapshot.getChildren()){
                Customer customer= child.getValue(Customer.class);
                customers.add(customer);
            }

            return customers;
        }
        catch (Exception ex){
            throw ex;
        }
    }
    public void Delete(FirebaseDatabase fb) {
        try {
            DatabaseReference reference = fb.getReference().
                    child("customer").child(String.valueOf(this.Id));
            reference.removeValue();

        } catch (Exception ex) {
            throw ex;
        }
    }
        public void Update(FirebaseDatabase fb)
        {
            try{
                DatabaseReference reference=fb.getReference().
                        child("customer").child(String.valueOf(this.Id));
                HashMap<String, Object> result = new HashMap<>();
                result.put("Id",this.Id);
                result.put("Name",this.Name);
                result.put("Phone",this.Phone);
                result.put("Address",this.Address);
                result.put("Email",this.Email);
                reference.updateChildren(result);


            }
            catch (Exception ex)
            {
                throw ex;
            }

    }
}
