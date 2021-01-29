    package com.example.notes;

    public class DataContract {
        String name,email,password,username;
        int phone;

        public DataContract(String name, String username, int phone, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.username = username;
        }

        public DataContract() {

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }
    }
