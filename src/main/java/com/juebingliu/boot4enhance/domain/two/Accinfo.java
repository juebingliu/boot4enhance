package com.juebingliu.boot4enhance.domain.two;

public class Accinfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accinfo.account_id
     *
     * @mbggenerated Tue Dec 11 18:57:50 CST 2018
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accinfo.balance
     *
     * @mbggenerated Tue Dec 11 18:57:50 CST 2018
     */
    private Long balance;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accinfo.account_id
     *
     * @return the value of accinfo.account_id
     *
     * @mbggenerated Tue Dec 11 18:57:50 CST 2018
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accinfo.account_id
     *
     * @param accountId the value for accinfo.account_id
     *
     * @mbggenerated Tue Dec 11 18:57:50 CST 2018
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accinfo.balance
     *
     * @return the value of accinfo.balance
     *
     * @mbggenerated Tue Dec 11 18:57:50 CST 2018
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accinfo.balance
     *
     * @param balance the value for accinfo.balance
     *
     * @mbggenerated Tue Dec 11 18:57:50 CST 2018
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }
}