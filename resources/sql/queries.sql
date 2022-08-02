-- :name create-customer :! :n
-- :doc create a new customer using the username and password keys
insert into account_holder (username, password)
values(:username, :password)

-- :name create-profile :! :n
-- :doc create a customer profile using the first name, last name, email, phone number and home address keywords
update account_holder
set account_holder_id = :account_holder_id, first_name = :first_name,
       last_name = :last_name, email_address = :email_address,
       phone_number = :phone_number,
       residential_address = :residential_address
where username = :username

-- :name delete-customer :! :n
-- :doc delete a customers records
delete from account_holder
where account_holder_id = :account_holder_id

-- :name select-customers :* :?
-- :doc select all available customers
select * from account_holder

-- :name get-customer-for-auth! :? :1
-- :doc select a user for authentication
select * from account_holder
where username = :username
