(ns bank-arabia.auth
  (:require [buddy.hashers :as hashers]
            [bank-arabia.db.core :as db]))

;; create the registration functionality
(defn create-user! [username password]
  (if-not (empty? (db/get-customer-for-auth! {:username username}))
    (throw (ex-info "User already exists!."
                    {:bank-arabia/username-error-id ::duplicate-user
                     :error "User already exists!."}))
    (db/create-customer {:username username
                         :password (hashers/derive password)})))

;; Create the login functionality.
;; Supply a username and password, check if password is same as
;; hashed values. if true return user details without password
(defn authenticate-user [username password]
  (let [{hashed-password :password :as user} (db/get-customer-for-auth! {:username username})]
    (when (hashers/check password hashed-password)
      (dissoc user :password))
    ))

