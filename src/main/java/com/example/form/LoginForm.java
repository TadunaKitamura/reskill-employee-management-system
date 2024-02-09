package com.example.form;

public class LoginForm {
    
    /** 登録された管理者のメールアドレス */
    private String mailAddress;

    /** 登録された管理者のパスワード */
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    

}
